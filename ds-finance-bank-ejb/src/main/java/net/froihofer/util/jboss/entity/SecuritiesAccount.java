package net.froihofer.util.jboss.entity;

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
    @Column
    private BigDecimal currentValue;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "securitiesAccount")
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
