package net.froihofer.dsfinance.service;

import net.froihofer.dsfinance.entity.Address;
import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.entity.SecuritiesAccount;
import net.froihofer.dsfinance.exceptions.UnauthorizedException;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;

import javax.ejb.Remote;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Remote
public interface BankServerInterface {

    public Map<String,String> getCallerInfo();
    public boolean isCallerEmployee();

    public List<PublicStockQuote> getStocksByName(String companyName);
    public List<PublicStockQuote> getStocksBySymbol(String companySymbol);

    public BigDecimal buyStock(String stockSymbol, int noShares, int customerId) throws UnauthorizedException;
    public BigDecimal sellStock(String stockSymbol, int noSahres, int customerId) throws UnauthorizedException;

    public SecuritiesAccount getSecuritiesAccount(int customerId) throws UnauthorizedException;

    public void createCustomerAccount(String firstName, String lastName, String username, Address address, String password, SecuritiesAccount securitiesAccount) throws UnauthorizedException;
    public List<Customer> findAllCustomers();
    public List<Customer> findCustomers(String lastName, String firstName);

    public int getBankVolume() throws UnauthorizedException;

    public List<PublicStockQuote> findCompaniesByName(String query);
}

