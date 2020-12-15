package net.froihofer.dsfinance.web;

import net.froihofer.dsfinance.service.BankServerInterface;
import net.froihofer.dsfinance.entity.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class BankServiceJsfBean {
    @EJB
    BankServerInterface bank;
    private String userName, userRole, customerFirstName, customerLastName, searchCustomerFirstName, searchCustomerLastName;
    private List<Customer> customerResults;
    private boolean resultsAvailable;

    //checks Role of User and redirects to the appropriate landing page
    public void redirect() throws IOException {
        String targetPage = bank.isCallerEmployee() ? "employeeLandingPage.xhtml" : "depot.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(targetPage);
    }

    //used for welcome messages atm
    public void loadUserData() {
        Map<String, String> userInfo = bank.getCallerInfo();
        userName = userInfo.get("Name");
        userRole = userInfo.get("Role");
    }

    public boolean isCallerEmployee() {
        return bank.isCallerEmployee();
    }

    public void createCustomer() {
        bank.createCustomer(customerFirstName, customerLastName);
    }

    public void lookupCustomer() {
        resultsAvailable = false;
        customerResults = bank.findCustomers(searchCustomerLastName, searchCustomerFirstName);
        resultsAvailable = true;
    }

    public void showAllCustomers() {
        resultsAvailable = false;
        customerResults = bank.findAllCustomers();
        resultsAvailable = true;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public boolean isResultsAvailable() {
        return resultsAvailable;
    }

    public List<Customer> getCustomerResults() {
        return customerResults;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getSearchCustomerFirstName() {
        return searchCustomerFirstName;
    }

    public void setSearchCustomerFirstName(String searchCustomerFirstName) {
        this.searchCustomerFirstName = searchCustomerFirstName;
    }

    public String getSearchCustomerLastName() {
        return searchCustomerLastName;
    }

    public void setSearchCustomerLastName(String searchCustomerLastName) {
        this.searchCustomerLastName = searchCustomerLastName;
    }
}
