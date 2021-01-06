package net.froihofer.dsfinance.dao;


import net.froihofer.dsfinance.entity.Customer;
import net.froihofer.dsfinance.entity.SecuritiesAccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SecuritiesAccountDAO{
    @PersistenceContext
    private EntityManager entityManager;


    public SecuritiesAccount findById(int id) {
        return entityManager.find(SecuritiesAccount.class, id);
    }

    public SecuritiesAccount findByCustomer(Customer customer) {
        return entityManager.createQuery("FROM SecuritiesAccount x "+
                        "WHERE x.customer = :customer ",
                SecuritiesAccount.class).setParameter("customer", customer).getSingleResult();
    }

    public void persist(SecuritiesAccount account) {
        entityManager.persist(account);
    }

    public void delete(SecuritiesAccount account) {
        entityManager.remove(account);
    }
}
