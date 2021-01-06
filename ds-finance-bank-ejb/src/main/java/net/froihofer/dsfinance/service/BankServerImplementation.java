package net.froihofer.dsfinance.service;

import net.froihofer.dsfinance.dao.CustomerDAO;
import net.froihofer.dsfinance.dao.SecuritiesAccountDAO;
import net.froihofer.dsfinance.entity.Address;
import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.entity.SecuritiesAccount;
import net.froihofer.dsfinance.exceptions.UnauthorizedException;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import net.froihofer.dsfinance.ws.trading.TradingWSException_Exception;
import net.froihofer.dsfinance.ws.trading.TradingWebService;
import net.froihofer.dsfinance.ws.trading.TradingWebServiceService;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Stateless
@PermitAll
public class BankServerImplementation implements BankServerInterface {

    @Resource
    private SessionContext ctx;
    @Inject
    CustomerDAO customerDAO;
    @Inject
    SecuritiesAccountDAO securitiesAccountDAO;


    // gets login information so employees can be greeted
    public Map<String, String> getCallerInfo() {
        Map<String, String> temp = new HashMap<String, String>();
        temp.put("Name", ctx.getCallerPrincipal().getName());
        temp.put("Role", ctx.isCallerInRole("bank") ? "Employee" : "Customer");
        return temp;
    }

    public boolean isCallerEmployee() {
        return ctx.isCallerInRole("bank");
    }

    @Override
    public List<PublicStockQuote> getStocksByName(String companyName) {
        //TODO get from TradingService
        return null;
    }

    @Override
    public List<PublicStockQuote> getStocksBySymbol(String companySymbol) {
        //TODO get from TradingService
        return null;
    }

    @Override
    public BigDecimal buyStock(String stockSymbol, int noShares, int customerId) throws UnauthorizedException {
        //TODO buy from TradingService
        return null;
    }

    @Override
    public BigDecimal sellStock(String stockSymbol, int noSahres, int customerId) throws UnauthorizedException {
        //TODO sell on TradingService
        return null;
    }

    @Override
    public SecuritiesAccount getSecuritiesAccount(int id) throws UnauthorizedException {
        return securitiesAccountDAO.findById(id);
    }

    @RolesAllowed("bank")
    public void createCustomerAccount(String firstName, String lastName, String username, Address address, String password, SecuritiesAccount securitiesAccount) throws UnauthorizedException {
        customerDAO.persist(new Customer(firstName, lastName, username, address, password, securitiesAccount));
    }

    @RolesAllowed("bank")
    public List<Customer> findCustomers(String lastName, String firstName) {
        return customerDAO.findCustomers(lastName, firstName);
    }

    @Override
    public int getBankVolume() throws UnauthorizedException {
        //TODO Was genau soll hier passieren?
        //werden hier alle von unseren Kunden gehaltenen Aktienwerte summiert?
        return 0;
    }


    @RolesAllowed("bank")
    public List<Customer> findAllCustomers() {
        return customerDAO.findAllCustomers();
    }

    public List<PublicStockQuote> findCompaniesByName(String query) {
        try {
            TradingWebServiceService trdSvc = new TradingWebServiceService();
            TradingWebService proxy = trdSvc.getTradingWebServicePort();
            BindingProvider bindingProvider = (BindingProvider) proxy;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    "http://edu.dedisys.org/ds-finance/ws/TradingService?wsdl");
            bindingProvider.getRequestContext().put(
                    BindingProvider.USERNAME_PROPERTY, "csdc21bb_01");
            bindingProvider.getRequestContext().put(
                    BindingProvider.PASSWORD_PROPERTY, "aej4Ahzo");
            return proxy.findStockQuotesByCompanyName(query);

        } catch (TradingWSException_Exception e) {
            System.out.println(e.getMessage());
        }
       return null;
    }
}
