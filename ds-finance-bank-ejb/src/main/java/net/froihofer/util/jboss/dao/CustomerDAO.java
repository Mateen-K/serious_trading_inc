package net.froihofer.util.jboss.dao;

import net.froihofer.util.jboss.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerDAO {
  @PersistenceContext private EntityManager entityManager;
  
  public Customer findById(String name) {
    return entityManager.find(Customer.class, name);
  }
  
  public void persist(Customer customer) {
    entityManager.persist(customer);
  }
  
  public void delete(Customer Customer) {
    entityManager.remove(Customer);
  }
}
