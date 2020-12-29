package net.froihofer.util.jboss.dao;


import net.froihofer.util.jboss.entity.SecuritiesAccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SecuritiesAccountDAO{
    @PersistenceContext
    private EntityManager entityManager;


    public SecuritiesAccount findById(int id) {
        return entityManager.find(SecuritiesAccount.class, id);
    }

    public SecuritiesAccount findByCustomer(Customer customer) {
        return entityManager.createQuery("FROM StocksOwned x "+
                        "WHERE x.customer = :customer ",
                StocksOwned.class).setParameter("customer", customer)
    }

    public void persist(SecuritiesAccount account) {
        entityManager.persist(account);
    }

    public void delete(SecuritiesAccount account) {
        entityManager.remove(account);
    }
}
