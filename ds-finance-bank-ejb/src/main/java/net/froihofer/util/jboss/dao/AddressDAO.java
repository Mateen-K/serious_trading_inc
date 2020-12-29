package net.froihofer.util.jboss.dao;

import net.froihofer.util.jboss.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AddressDAO{
    @PersistenceContext
    private EntityManager entityManager;


    public Address findById(int id) {
        return entityManager.find(Address.class, id);
    }

    public void persist(Address address) {
        entityManager.persist(address);
    }

    public void delete(Address address) {
        entityManager.remove(address);
    }
}
