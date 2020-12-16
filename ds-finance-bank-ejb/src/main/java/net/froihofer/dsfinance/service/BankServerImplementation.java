package net.froihofer.dsfinance.service;

import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.entity.CustomerDAO;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import net.froihofer.dsfinance.ws.trading.TradingWSException_Exception;
import net.froihofer.dsfinance.ws.trading.TradingWebService;
import net.froihofer.dsfinance.ws.trading.TradingWebServiceService;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import java.net.URL;
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

    public void createCustomer(String firstName, String lastName) {
        customerDAO.persist(new Customer(firstName, lastName));
    }

    public List<Customer> findCustomers(String lastName, String firstName) {
        return customerDAO.findCustomers(lastName, firstName);
    }

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
        //TODO: find better solution
       return null;
    }
}
