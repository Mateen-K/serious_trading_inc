package net.froihofer.util.jboss.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="StockOwned")
public class StocksOwned implements Serializable {
    @Id
    private String symbol;
    @Column
    private String companyName;
    @Column
    private int sharesOwnedNo;
    @Column
    private BigDecimal shareValue;
    @Column
    private Date updatedOn;
    @ManyToOne
    @JoinColumn(name ="SecuritiesAccount_FK")
    private SecuritiesAccount securitiesAccount;

    public StocksOwned(){
    }

    public StocksOwned(String symbol, String companyName, int sharesOwnedNo, BigDecimal shareValue, Date updatedOn) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sharesOwnedNo = sharesOwnedNo;
        this.shareValue = shareValue;
        this.updatedOn = updatedOn;
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
