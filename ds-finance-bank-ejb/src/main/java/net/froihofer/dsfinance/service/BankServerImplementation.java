package net.froihofer.dsfinance.service;


import net.froihofer.dsfinance.dao.AddressDAO;
import net.froihofer.dsfinance.dao.BankVolumeDAO;
import net.froihofer.dsfinance.dao.StockDAO;
import net.froihofer.dsfinance.entity.Address;
import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.dao.CustomerDAO;
import net.froihofer.dsfinance.entity.Stock;
import net.froihofer.dsfinance.trading.TradingWebServiceClient;
import net.froihofer.dsfinance.web.util.jboss.WildflyAuthDBHelper;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.TransactionRolledbackException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
@PermitAll
public class BankServerImplementation implements BankServerInterface {

    @Resource
    private SessionContext ctx;
    @Inject
    CustomerDAO customerDAO;
    @Inject
    StockDAO stocksDAO;
    @Inject
    BankVolumeDAO bankVolumeDAO;
    @Inject
    AddressDAO addressDAO;
    TradingWebServiceClient tradingClient;
    WildflyAuthDBHelper dbHelper;


    public BankServerImplementation() {
        tradingClient = new TradingWebServiceClient();
        //hardcoded
        //TODO ADD YOUR PATH!
        dbHelper = new WildflyAuthDBHelper(new File("C:\\Program Files\\Wildfly\\wildfly-18.0.1.Final-lf1"));
    }

    //checks wildfly user role
    public boolean isCallerEmployee() {
        return ctx.isCallerInRole("bank");
    }

    //loads wildfly username (for employee version)
    public String getUserName() {
        return ctx.getCallerPrincipal().getName();
    }

    /*
        status codes:
         0 = customer created
        -1 = userName not unique
        -2 = customerId not unique
        -3 = userName & customerId not unique
         */
    //adds a new customer to wildfly + the database (incl. an entry for the address)
    @RolesAllowed("bank")
    public BigDecimal createCustomer(Customer customer, Address address, String password) {
        boolean isUniqueUserName = checkUnique(customer.getUserName());
        boolean isUniqueId = checkUnique(customer.getId());

        if (!isUniqueUserName && !isUniqueId) {
            return BigDecimal.valueOf(-3);
        }else if (!isUniqueId) {
            return BigDecimal.valueOf(-2);
        }else if (!isUniqueUserName) {
            return BigDecimal.valueOf(-1);
        }else {
            String[] roles = {"customer"};
            try {
                dbHelper.addUser(customer.getUserName(), password, roles);
                customerDAO.persist(customer);
                address.setCustomer(customer);
                addressDAO.persist(address);
            } catch (IOException e) {
                System.out.println("Couldn´t add user to Wildfly.\n" + e.getMessage());
            }
            return BigDecimal.valueOf(0);
        }
    }

    //looks up customer by name or id, depending on filled in filled in values
    @RolesAllowed("bank")
    public List<Customer> findCustomers(String lastName, String firstName, Integer customerId) {
        List<Customer> customers = new ArrayList<Customer>();
        if (lastName != null && firstName != null) {
            customers.addAll(customerDAO.findCustomers(lastName, firstName));
        }
        if (customerId != null) {
            customers.add(customerDAO.findById(customerId));
        }
        return customers;
    }

    //lists all customers
    @RolesAllowed("bank")
    public List<Customer> findAllCustomers() {
        return customerDAO.findAllCustomers();
    }

    //finds customer by means of username
    //used by IndexJsfBean - can be replaced by new Method "findCustomer"
    public Customer findCustomerByUserName(String userName) {
        return customerDAO.findCustomerByUserName(userName);
    }

    public List<Stock> findCompaniesByName(String query) {
        return TradingWebServiceClient.getStocksByName(query);
    }

    //finds all stocks owned by a specified customer
    public List<Stock> getCustomerStocks(int customerId) {
        return stocksDAO.findStockByCustomer(customerId);
    }


    //return 0 for problems with TradingService
    public BigDecimal buyStock(String symbol, String companyName, int amount, int customerId) {
        //load Customer (should be set in db by now)
        Customer customer = customerDAO.findById(customerId);
        // buy Stocks from TradingService
        BigDecimal buyPrice = tradingClient.buyStock(symbol, amount);
        if (buyPrice.equals(BigDecimal.valueOf(0))){
            return buyPrice;
        }
        BigDecimal change = buyPrice.multiply(BigDecimal.valueOf(amount));
        //if customer already has a stock of the specified company
        try {
            Stock stock = stocksDAO.findCompanyStockByCustomer(customerId, symbol);
            stock.setSharesOwnedNo(stock.getSharesOwnedNo() + amount);
            stock.setShareValue(buyPrice);
            stock.setUpdatedOn(LocalDate.now());
            customer.setCurrentValue(customer.getCurrentValue().add(change));
            bankVolumeDAO.get().setCurrent(bankVolumeDAO.get().getCurrent().subtract(change));
            return buyPrice;
        //if customer has no stock of the company yet
        } catch (NoResultException e) {
            Stock stock = new Stock(symbol, companyName, amount, buyPrice, LocalDate.now(), customer);
            stocksDAO.persist(stock);
            customer.setCurrentValue((customer.getCurrentValue().add(change)));
            bankVolumeDAO.get().setCurrent(bankVolumeDAO.get().getCurrent().subtract(change));
            return buyPrice;
        }
    }

    /*
    error codes:
     0 = problem with TradingService
    -1 = not enough stocks available
    -2 = customer doesnt own specified stocks
     */
    public BigDecimal sellStock(String symbol, String companyName, int amount, int customerId) {
        Customer customer = customerDAO.findById(customerId);
        BigDecimal sellPrice = tradingClient.sellStock(symbol, amount);
        if (sellPrice.intValue() == 0) {
            return sellPrice;
        }
        BigDecimal change = sellPrice.multiply(BigDecimal.valueOf(amount)).multiply(BigDecimal.valueOf(-1));
        try {
            Stock stock = stocksDAO.findCompanyStockByCustomer(customerId, symbol);
            int noOfNewStocks = stock.getSharesOwnedNo() - amount;
            int caseInt = checkSellOperation(noOfNewStocks);
            switch (caseInt) {
                //all stocks sold
                case 0:
                    stocksDAO.delete(stock);
                    customer.setCurrentValue(customer.getCurrentValue().add(change));
                    bankVolumeDAO.get().setCurrent((bankVolumeDAO.get().getCurrent()).subtract(change));
                    return sellPrice;
                //customer still has stocks after operation
                case 1:
                    stock.setSharesOwnedNo(noOfNewStocks);
                    stock.setShareValue(sellPrice);
                    stock.setUpdatedOn(LocalDate.now());
                    customer.setCurrentValue(customer.getCurrentValue().add(change));
                    bankVolumeDAO.get().setCurrent(bankVolumeDAO.get().getCurrent().subtract(change));
                    return sellPrice;
                //not enough stocks to sell
                default:
                    return BigDecimal.valueOf(-1);
            }
        } catch (NoResultException e) {
            return BigDecimal.valueOf(-2);
        }
    }

    //helper function for switch case in sell Method
    private int checkSellOperation(double number) {
        //enough stocks left
        if (number > 0) {
            return 1;
        }
        //all stocks sold
        if (number == 0) {
            return 0;
        }
        // not enough stocks to sell
        return -1;
    }

    public BigDecimal getBankVolume() {
        return bankVolumeDAO.get().getCurrent();
    }

    private boolean checkUnique(int customerId){
        List<Customer> customers = customerDAO.findAllCustomers();
        List<Integer> idList = customers.stream().map(Customer::getId).collect(Collectors.toList());

        boolean isUniqueId = !idList.contains(customerId);

        return isUniqueId;
    }

    private boolean checkUnique(String userName){
        List<Customer> customers = customerDAO.findAllCustomers();
        List<String> userNameList = customers.stream().map(Customer::getUserName).collect(Collectors.toList());

        boolean isUniqueUserName = !userNameList.contains(userName);

        return isUniqueUserName;
    }
}
