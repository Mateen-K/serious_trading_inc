package net.froihofer.dsfinance.service;

import net.froihofer.dsfinance.entity.Address;
import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.entity.Stock;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import net.froihofer.dsfinance.ws.trading.TradingWSException_Exception;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BankServerInterface {

    boolean isCallerEmployee();

    String  getUserName();

    BigDecimal createCustomer(Customer customer, Address address, String password);

    List<Customer> findCustomers(String lastName, String firstName, Integer customerId);

    List<Customer> findAllCustomers();

    Customer findCustomerByUserName(String userName);

    List<Stock> findCompaniesByName(String query);

    List<Stock> getCustomerStocks(int customerId);

    BigDecimal buyStock(String symbol, String companyName, int amount, int costumerId);

    BigDecimal sellStock(String symbol, String companyName, int amount, int customerId);

    BigDecimal getBankVolume();

}
