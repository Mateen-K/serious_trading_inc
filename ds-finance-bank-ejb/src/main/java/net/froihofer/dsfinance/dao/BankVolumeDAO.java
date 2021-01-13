package net.froihofer.dsfinance.dao;

import net.froihofer.dsfinance.entity.BankVolume;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

public class BankVolumeDAO{
    @PersistenceContext
    private EntityManager entityManager;


    public BankVolume get() {
        try {
            return entityManager.createQuery("SELECT x FROM BankVolume x", BankVolume.class).getSingleResult();
        }
        catch (NoResultException e){
            entityManager.persist(new BankVolume(1, BigDecimal.valueOf(1000000000)));
            return entityManager.createQuery("SELECT x FROM BankVolume x", BankVolume.class).getSingleResult();
        }
    }

    //https://www.objectdb.com/java/jpa/persistence/update
    public void update(BigDecimal newCurrent) {
        BankVolume bankVolume = get();
        entityManager.getTransaction().begin();
        bankVolume.setCurrent(newCurrent);
        entityManager.getTransaction().commit();
    }

    public void persist(BankVolume bankVolume) {
        entityManager.persist(bankVolume);    }

    public void delete(BankVolume bankVolume) {
        entityManager.remove(bankVolume);    }
}
