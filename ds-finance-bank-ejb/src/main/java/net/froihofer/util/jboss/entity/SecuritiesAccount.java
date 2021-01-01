package net.froihofer.util.jboss.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="SecuritiesAccount")
public class SecuritiesAccount implements Serializable {
    @Id
    private int id;
    @NotEmpty
    @Column
    private BigDecimal currentValue;
    @NotEmpty
    @OneToOne
    @JoinColumn(name = "Customer_FK")
    private Customer customer;
    @NotEmpty
    @OneToMany(mappedBy = "securitiesAccount")
    @OrderBy("companyName")
    private List<StocksOwned> stocks;

    public SecuritiesAccount(){
    }

    public SecuritiesAccount(int id, BigDecimal currentValue, Customer customer, List<StocksOwned> stocks) {
        this.id = id;
        this.currentValue = currentValue;
        this.customer = customer;
        this.stocks = stocks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<StocksOwned> getStocks() {
        return stocks;
    }

    public void setStocks(List<StocksOwned> stocks) {
        this.stocks = stocks;
    }
}
