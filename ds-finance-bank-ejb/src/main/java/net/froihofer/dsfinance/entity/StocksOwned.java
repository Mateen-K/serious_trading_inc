package net.froihofer.dsfinance.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.Past;

@Entity
@Table(name="StockOwned")
public class StocksOwned implements Serializable {
    @Id
    private String symbol;
    @NotEmpty
    @Column
    private String companyName;
    @NotEmpty
    @Column
    private int sharesOwnedNo;
    @NotEmpty
    @Column
    private BigDecimal shareValue;
    @Past
    @NotEmpty
    @Column
    private LocalDate updatedOn;
    @NotEmpty
    @ManyToOne
    @JoinColumn(name ="SecuritiesAccount_FK")
    private SecuritiesAccount securitiesAccount;

    public StocksOwned(){
    }

    public StocksOwned(String symbol, String companyName, int sharesOwnedNo, BigDecimal shareValue, LocalDate updatedOn, SecuritiesAccount securitiesAccount) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sharesOwnedNo = sharesOwnedNo;
        this.shareValue = shareValue;
        this.updatedOn = updatedOn;
        this.securitiesAccount = securitiesAccount;
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

    public SecuritiesAccount getSecuritiesAccount() {
        return securitiesAccount;
    }

    public void setSecuritiesAccount(SecuritiesAccount securitiesAccount) {
        this.securitiesAccount = securitiesAccount;
    }
}
