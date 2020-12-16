package net.froihofer.dsfinance.ws.trading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

// TODO: inject values from ws-client.properties file
public class TradingWebServiceClient {
    // TODO: add values for username and password
    private final static String USERNAME = "csdc21bb_01";
    private final static String PASSWORD = "aej4Ahzo";
    private final static String WSDL_URL = "http://edu.dedisys.org/ds-finance/ws/TradingService?wsdl";
    private final static Logger logger = LoggerFactory.getLogger(TradingWebServiceClient.class);
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



    public static List<PublicStockQuote> getStocksDemo(String companyName) {
        List<PublicStockQuote> stocks = null;

        try {
            stocks = proxy.findStockQuotesByCompanyName(companyName);
            logger.info("First item on the list: " + stocks.get(0).companyName + " (last price: " + stocks.get(0).lastTradePrice + ")");
        } catch (TradingWSException_Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }

//    public BigDecimal buyStock (String symbol, int shares) throws TradingWSException_Exception {
//        return proxy.buy(symbol, shares);
//    }
//
//    public BigDecimal sellStock (String symbol, int shares) throws TradingWSException_Exception {
//        return proxy.sell(symbol, shares);
//    }
}
