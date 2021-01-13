package net.froihofer.dsfinance.dao;


import net.froihofer.dsfinance.entity.Stock;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StockDAO {
    @PersistenceContext
    private EntityManager entityManager;


    public Stock find(int id) {
        return entityManager.find(Stock.class, id);
    }

    public Stock findCompanyStockByCustomer(int customerId, String symbol) {
        return entityManager.createQuery("SELECT x FROM Stock x " + "WHERE str(x.customer.id) LIKE :customerId AND x.symbol LIKE :symbol", Stock.class)
                .setParameter("customerId", String.valueOf(customerId))
                .setParameter("symbol", symbol)
                .getSingleResult();
    }

    public List<Stock> findStockByCustomer(int customerId) {
        return entityManager.createQuery("SELECT x FROM Stock x " +
                        "WHERE str(x.customer.id) LIKE :customerId",
                Stock.class).setParameter("customerId", String.valueOf(customerId)).
                getResultList();
    }

    public Stock findCompanyStockOfCustomer(int customerId, String symbol){
        return entityManager.createQuery("SELECT x FROM Stock x " + "WHERE str(x.customer.id) LIKE :customerId AND x.companyName LIKE :symbol", Stock.class)
                .setParameter(customerId, String.valueOf(customerId))
                .setParameter("symbol", symbol)
                .getSingleResult();
    }

    public void persist(Stock stock) {
        entityManager.persist(stock);
    }

    public void delete(Stock stock) {
        entityManager.remove(stock);
    }
}
