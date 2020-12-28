package net.froihofer.util.jboss.dao;

import net.froihofer.util.jboss.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerDAO {
  @PersistenceContext private EntityManager entityManager;
  
  public Customer getById(int id) {
    return entityManager.find(Customer.class, id);
  }

  public Customer getByFirstName(String firstName) {
    return entityManager.find(Customer.class, firstName);
  }

  public Customer getByLastName(String lastName) {
    return entityManager.find(Customer.class, lastName);
  }
  
  public void persist(Customer customer) {
    entityManager.persist(customer);
  }
  
  public void delete(Customer Customer) {
    entityManager.remove(Customer);
  }
}
