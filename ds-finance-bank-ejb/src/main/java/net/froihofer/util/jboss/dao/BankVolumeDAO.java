package net.froihofer.util.jboss.dao;

import net.froihofer.util.jboss.entity.BankVolume;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BankVolumeDAO{
    @PersistenceContext
    private EntityManager entityManager;

    //TODO nur 1 Volume. Implementierung ohne id
    public BankVolume findById(int id) {
        return entityManager.find(BankVolume.class, id);
    }

    public void persist(BankVolume bankVolume) {
        entityManager.persist(bankVolume);    }

    public void delete(BankVolume bankVolume) {
        entityManager.remove(bankVolume);    }
}
