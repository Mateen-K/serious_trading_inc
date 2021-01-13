package net.froihofer.dsfinance.web;

import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.service.BankServerInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class CustomerSearchJsfBean {
    @EJB
    BankServerInterface bank;
    private String firstName, lastName, selectedCustomer;
    private Integer customerId;
    private List<Customer> customerResults;
    private boolean resultsAvailable;
    @ManagedProperty(value="#{indexJsfBean}")
    private IndexJsfBean indexJsfBean;


    public void lookupCustomer() {
        resultsAvailable = false;
        customerResults = bank.findCustomers(firstName, lastName, customerId);
        resultsAvailable = true;
    }

    public void showAllCustomers() {
        resultsAvailable = false;
        customerResults = bank.findAllCustomers();
        resultsAvailable = true;
    }

    public void setCustomer(){
        indexJsfBean.setCurrentCustomer(selectedCustomer);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Customer> getCustomerResults() {
        return customerResults;
    }

    public boolean isResultsAvailable() {
        return resultsAvailable;
    }

    public void setResultsAvailable(boolean resultsAvailable) {
        this.resultsAvailable = resultsAvailable;
    }

    public String getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(String selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public void setIndexJsfBean(IndexJsfBean indexJsfBean) {
        this.indexJsfBean = indexJsfBean;
    }
}
