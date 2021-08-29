package net.mateen.dsfinance.web;

import net.mateen.dsfinance.entity.Stock;
import net.mateen.dsfinance.service.BankServerInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean
@SessionScoped
public class GetStockQuotesJsfBean {
    @EJB
    BankServerInterface bank;
    private String companyQuery;
    private BigDecimal buyPrice;
    private boolean resultsAvailable =false, tradingServiceError = false, buyComplete = false;
    private List<Stock> companyResults;
    private Stock selectedCompany;
    @ManagedProperty(value="#{indexJsfBean}")
    private IndexJsfBean indexJsfBean;

    public void findCompanyByName(){
        resultsAvailable = false;
        companyResults = bank.findCompaniesByName(companyQuery);
        resultsAvailable = true;
    }

    public void buy() throws IOException {
        BigDecimal buyPrice = bank.buyStock(selectedCompany.getSymbol(),
                selectedCompany.getCompanyName(), selectedCompany.getSharesOwnedNo(), indexJsfBean.getCurrentCustomer().getId());
        //Error with TradingService
        if (buyPrice.intValue() == 0){
            tradingServiceError = true;
        }
        else {
            this.buyPrice = buyPrice;
            this.buyComplete = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("portfolio.xhtml");
        }
    }

    public String getCompanyQuery() {
        return companyQuery;
    }

    public void setCompanyQuery(String companyQuery) {
        this.companyQuery = companyQuery;
    }

    public List<Stock> getCompanyResults() {
        return companyResults;
    }

    public boolean isResultsAvailable() {
        return resultsAvailable;
    }

    public void setResultsAvailable(boolean resultsAvailable) {
        this.resultsAvailable = resultsAvailable;
    }

    public Stock getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(Stock selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public void setIndexJsfBean(IndexJsfBean indexJsfBean) {
        this.indexJsfBean = indexJsfBean;
    }

    public boolean isTradingServiceError() {
        return tradingServiceError;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public boolean isBuyComplete() {
        return buyComplete;
    }

    public void setBuyComplete(boolean buyComplete) {
        this.buyComplete = buyComplete;
    }

}