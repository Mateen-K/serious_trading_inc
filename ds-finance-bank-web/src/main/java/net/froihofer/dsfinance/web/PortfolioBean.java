package net.froihofer.dsfinance.web;

import net.froihofer.dsfinance.service.BankServerInterface;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class PortfolioBean {
    @EJB
    BankServerInterface bank;
    private List<PublicStockQuote> SecuritiesAccountResults;
    private String companyQuery, userName, userRole, symbol, companyName;
    boolean securitiesResultsAvailable;
    int noSharesOwned;
    Date updatedOn;
    BigDecimal sharesValue;

    public void loadUserData() {
        Map<String, String> userInfo = bank.getCallerInfo();
        userName = userInfo.get("Name");
        userRole = userInfo.get("Role");
    }

    public boolean isCallerEmployee() {
        return bank.isCallerEmployee();
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

    public String getSecuritiesAccount(int customerId) {
        return companyQuery;
    }

    public void setCompanyQuery(String companyQuery) {
        this.companyQuery = companyQuery;
    }

    public List<PublicStockQuote> SecuritiesAccountResults() {
        return SecuritiesAccountResults;
    }
    public boolean securitiesAccountResultsAvailable() {
        return securitiesResultsAvailable;
    }

    public int getNoSharesOwned() {
        return noSharesOwned;
    }

    public void setNoSharesOwned(int noSharesOwned) {
        this.noSharesOwned = noSharesOwned;
    }


}
