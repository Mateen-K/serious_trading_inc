package net.froihofer.dsfinance.service;

import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.entity.CustomerDAO;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
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


}
