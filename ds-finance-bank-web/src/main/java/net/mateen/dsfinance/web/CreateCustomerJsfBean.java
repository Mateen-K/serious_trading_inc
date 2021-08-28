package net.mateen.dsfinance.web;

import net.mateen.dsfinance.entity.Address;
import net.mateen.dsfinance.entity.Customer;
import net.mateen.dsfinance.service.BankServerInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.math.BigDecimal;

@ManagedBean
@RequestScoped
public class CreateCustomerJsfBean {
    @EJB
    BankServerInterface bank;
    private Customer customer = new Customer();
    private Address address = new Address();
    private String password;
    private boolean uniqueUserName = false, uniqueId = false;
    @ManagedProperty(value="#{indexJsfBean}")
    private IndexJsfBean indexJsfBean;

    public void createCustomer() throws IOException {
        uniqueUserName = false;
        uniqueId = false;

        BigDecimal statusCode = bank.createCustomer(customer, address, password);

        switch (statusCode.intValue()) {
            case -1:
                uniqueUserName = true;
                break;
            case -2:
                uniqueId = true;
                break;
            case -3:
                uniqueUserName = true;
                uniqueId = true;
                break;
            default:
                indexJsfBean.setCurrentCustomer(customer.getUserName());
                FacesContext.getCurrentInstance().getExternalContext().redirect("portfolio.xhtml");
                break;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUniqueUserName() {
        return uniqueUserName;
    }

    public void setUniqueUserName(boolean uniqueUserName) {
        this.uniqueUserName = uniqueUserName;
    }

    public boolean isUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(boolean uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setIndexJsfBean(IndexJsfBean indexJsfBean) {
        this.indexJsfBean = indexJsfBean;
    }
}
