package net.froihofer.dsfinance.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomerDAO {
  @PersistenceContext private EntityManager entityManager;

  public List<Customer> findAllCustomers() {
    return entityManager.createNativeQuery("SELECT * from CUSTOMER", Customer.class)
            .getResultList();
  }

  //case sensitive. at least one char per surname and lastname
  public List<Customer> findCustomers(String lastName, String firstName){
    return entityManager.createNativeQuery("SELECT * FROM CUSTOMER WHERE LASTNAME LIKE :lastName AND FIRSTNAME LIKE :firstName", Customer.class)
            .setParameter("firstName", '%' + firstName + '%')
            .setParameter("lastName", '%' + lastName + '%')
            .getResultList();
  }

  public void persist(Customer customer) {
    entityManager.persist(customer);
  }
  
  public void delete(Customer customer) {
    entityManager.remove(customer);
  }
}
