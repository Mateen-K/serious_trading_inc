package net.froihofer.dsfinance.service;

import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import net.froihofer.dsfinance.ws.trading.TradingWSException_Exception;

import java.util.List;
import java.util.Map;

public interface BankServerInterface {

    public Map<String,String> getCallerInfo();

    public boolean isCallerEmployee();

    public void createCustomer(String firstName, String lastName);

    public List<Customer> findCustomers(String lastName, String firstName);

    public List<Customer> findAllCustomers();

    public List<PublicStockQuote> findCompaniesByName(String query);
}