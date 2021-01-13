package net.froihofer.dsfinance.web;

import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.service.BankServerInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.math.BigDecimal;


@ManagedBean
@SessionScoped
public class IndexJsfBean {
    @EJB
    BankServerInterface bank;
    private Customer currentCustomer;
    private boolean customerSet = false;
    private BigDecimal currentBankVolume;
    private String employee;

    //called after login to redirect ot the correct landing page
    public void redirect() throws IOException {
        String targetPage;
        if (bank.isCallerEmployee()){
            targetPage = "customerSearch.xhtml";
            employee = bank.getUserName();

        }
        else{
            targetPage = "portfolio.xhtml";
            setCurrentCustomer(bank.getUserName());
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect(targetPage);
    }

    //Updates the bankVolume and the data of the selected Customer before rendering each page
    public void updateData(){
        try{
            currentBankVolume = bank.getBankVolume();
            currentCustomer = bank.findCustomerByUserName(currentCustomer.getUserName());
        }
        catch (NullPointerException e){
        }
    }

    //Sets the Frontend-Customer-Object
    public void setCurrentCustomer(String userName){
        try{
            customerSet = false;
            this.currentCustomer = bank.findCustomerByUserName(userName);
            customerSet = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("portfolio.xhtml");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isCallerEmployee() {
        return bank.isCallerEmployee();
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public boolean isCustomerSet() {
        return customerSet;
    }

    public BigDecimal getCurrentBankVolume() {
        return currentBankVolume;
    }

    public String getEmployee() {
        return employee;
    }
}
