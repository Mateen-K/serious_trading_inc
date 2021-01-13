package net.froihofer.dsfinance.entity;

import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="STOCK")
public class Stock implements Serializable {
    @Id @GeneratedValue
    private int id;
    @NotNull
    @Column
    private String symbol;
    @NotNull
    @Column
    private String companyName;
    @NotNull
    @Column
    private int sharesOwnedNo;
    @NotNull
    @Column
    private BigDecimal shareValue;
    @NotNull
    @Column
    private LocalDate updatedOn;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "Customer_FK")
    private Customer customer;

    public Stock(){
    }

    public Stock(String symbol, String companyName, int sharesOwnedNo, BigDecimal shareValue, LocalDate updatedOn, Customer customer) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sharesOwnedNo = sharesOwnedNo;
        this.shareValue = shareValue;
        this.updatedOn = updatedOn;
        this.customer = customer;
    }

    public Stock (PublicStockQuote publicStockQuote){
        this.symbol = publicStockQuote.getSymbol();
        this.companyName = publicStockQuote.getCompanyName();
        this.sharesOwnedNo = 0;
        this.shareValue = publicStockQuote.getLastTradePrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSharesOwnedNo() {
        return sharesOwnedNo;
    }

    public void setSharesOwnedNo(int sharesOwnedNo) {
        this.sharesOwnedNo = sharesOwnedNo;
    }

    public BigDecimal getShareValue() {
        return shareValue;
    }

    public void setShareValue(BigDecimal shareValue) {
        this.shareValue = shareValue;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}


