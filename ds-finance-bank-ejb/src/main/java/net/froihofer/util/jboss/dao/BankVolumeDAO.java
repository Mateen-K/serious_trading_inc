package net.froihofer.util.jboss.dao;

import net.froihofer.util.jboss.entity.BankVolume;
import net.froihofer.util.jboss.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BankVolumeDAO{
    @PersistenceContext
    private EntityManager entityManager;


    public BankVolume findById(int id) {
        return entityManager.find(BankVolume.class, id);
    }

    public void persist(BankVolume bankVolume) {
        entityManager.persist(bankVolume);    }

    public void delete(BankVolume bankVolume) {
        entityManager.remove(bankVolume);    }
}
