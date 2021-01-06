package net.froihofer.dsfinance.dao;

import net.froihofer.dsfinance.entity.StocksOwned;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class StocksOwnedDAO {
    @PersistenceContext
    private EntityManager entityManager;


    public StocksOwned findById(int id) {
        return entityManager.find(StocksOwned.class, id);
    }

    public void persist(StocksOwned stocks) {
        entityManager.persist(stocks);
    }

    public void delete(StocksOwned stocks) {
        entityManager.remove(stocks);
    }
}
