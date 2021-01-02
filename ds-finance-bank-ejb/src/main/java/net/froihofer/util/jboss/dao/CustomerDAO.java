package net.froihofer.util.jboss.dao;

import net.froihofer.util.jboss.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomerDAO{
  @PersistenceContext
  private EntityManager entityManager;


  public Customer findById(int id) {
    return entityManager.find(Customer.class, id);
  }

  public void persist(Customer customer) {
    entityManager.persist(customer);
  }

  public void delete(Customer customer) {
    entityManager.remove(customer);
  }

  public List<Customer> findAll() {
    return entityManager.createQuery("SELECT x FROM Customer x", Customer.class).getResultList();
  }

  public List<Customer> findByFirstName(String firstName) {
    return entityManager.createQuery("SELECT x FROM Customer x " +
                    "WHERE x.firstName LIKE :firstName",
            Customer.class).setParameter("firstName", firstName).
            getResultList();
  }

  public List<Customer> findByLastName(String lastName) {
    return entityManager.createQuery("SELECT x FROM Customer x " +
                    "WHERE x.lastName LIKE :lastName",
            Customer.class).setParameter("lastName", lastName).
            getResultList();
  }
}








