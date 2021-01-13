package net.froihofer.dsfinance.trading;

import net.froihofer.dsfinance.entity.Stock;
import net.froihofer.dsfinance.ws.trading.*;


import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TradingWebServiceClient {
    //TODO: ADD YOUR CREDENTIALS
    private final static String USERNAME = "XXXXXX";
    private final static String PASSWORD = "XXXXXX";
    private final static String WSDL_URL = "http://edu.dedisys.org/ds-finance/ws/TradingService?wsdl";
    private static TradingWebServiceService tradingService = null;

    static {
        try {
            tradingService = new TradingWebServiceService(new URL(WSDL_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private final static TradingWebService proxy = tradingService.getPort(TradingWebService.class);

    static {
        BindingProvider bindingProvider = (BindingProvider) proxy;
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, USERNAME);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);
    }


    public static List<Stock> getStocksByName(String companyName) {
        List<PublicStockQuote> publicStockQuotes = null;
        ArrayList<Stock> privateStockQuotes = new ArrayList<Stock>();
        try {
            publicStockQuotes = proxy.findStockQuotesByCompanyName(companyName);
            publicStockQuotes.forEach(s -> privateStockQuotes.add(new Stock(s)));
        } catch (TradingWSException_Exception e) {
            e.printStackTrace();
        }
        return privateStockQuotes;
    }


    public BigDecimal buyStock(String symbol, int shares) {
        try{
            return proxy.buy(symbol, shares);
        }
        catch (TradingWSException_Exception e){
            return BigDecimal.valueOf(0);
        }

    }

    public BigDecimal sellStock(String symbol, int shares) {
        try{
            return proxy.sell(symbol, shares);
        }
        catch (TradingWSException_Exception e){
            return BigDecimal.valueOf(0);
        }
    }
}
