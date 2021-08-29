package net.mateen.dsfinance.web;

import net.mateen.dsfinance.entity.Stock;
import net.mateen.dsfinance.service.BankServerInterface;

import javax.ejb.EJB;
import javax.faces.bean.*;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean
@SessionScoped
public class PortfolioJsfBean {
    @EJB
    BankServerInterface bank;
    private boolean resultsAvailable = false, notEnoughStocks = false, sellComplete = false, tradingServiceError = false;
    private List<Stock> stocks;
    private Stock selectedStock;
    private BigDecimal sellPrice;
    @ManagedProperty(value = "#{indexJsfBean}")
    private IndexJsfBean indexJsfBean;
    @ManagedProperty(value="#{getStockQuotesJsfBean}")
    private GetStockQuotesJsfBean getStockQuotesJsfBean;


    public void loadCustomerStocks() {
        resultsAvailable = false;
        stocks = bank.getCustomerStocks(indexJsfBean.getCurrentCustomer().getId());
        resultsAvailable = stocks != null;
    }

    public void sellStock() {
        notEnoughStocks = false;
        sellComplete = false;
        tradingServiceError = false;
        // disables notification from previous buy operations
        getStockQuotesJsfBean.setBuyComplete(false);
        sellPrice = bank.sellStock(selectedStock.getSymbol(),
                selectedStock.getCompanyName(), selectedStock.getSharesOwnedNo(), indexJsfBean.getCurrentCustomer().getId());
        switch (sellPrice.intValue()) {
            case -1:
                notEnoughStocks = true;
                break;
            case 0:
                tradingServiceError = true;
                break;
            default:
                sellComplete = true;
        }
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setIndexJsfBean(IndexJsfBean indexJsfBean) {
        this.indexJsfBean = indexJsfBean;
    }

    public boolean isResultsAvailable() {
        return resultsAvailable;
    }

    public Stock getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(Stock selectedStock) {
        this.selectedStock = selectedStock;
    }

    public boolean isNotEnoughStocks() {
        return notEnoughStocks;
    }

    public void setNotEnoughStocks(boolean notEnoughStocks) {
        this.notEnoughStocks = notEnoughStocks;
    }


    public boolean isSellComplete() {
        return sellComplete;
    }

    public void setSellComplete(boolean sellComplete) {
        this.sellComplete = sellComplete;
    }

    public boolean isTradingServiceError() {
        return tradingServiceError;
    }

    public void setTradingServiceError(boolean tradingServiceError) {
        this.tradingServiceError = tradingServiceError;
    }

    public void setGetStockQuotesJsfBean(GetStockQuotesJsfBean getStockQuotesJsfBean) {
        this.getStockQuotesJsfBean = getStockQuotesJsfBean;
    }
}
