package net.froihofer.dsfinance.dao;
import net.froihofer.dsfinance.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class CustomerDAO {
  @PersistenceContext private EntityManager entityManager;

  public Customer findById(int id) {
    return entityManager.find(Customer.class, id);
  }

  public List<Customer> findAllCustomers() {
    return entityManager.createNativeQuery("SELECT * from CUSTOMER", Customer.class)
            .getResultList();
  }

  //case sensitive. at least one char per surname and lastname
  public List<Customer> findCustomers(String firstName, String lastName){
    return entityManager.createNativeQuery("SELECT * FROM CUSTOMER WHERE LASTNAME LIKE :lastName AND FIRSTNAME LIKE :firstName", Customer.class)
            .setParameter("firstName", '%' + firstName + '%')
            .setParameter("lastName", '%' + lastName + '%')
              .getResultList();
    }

  public Customer findCustomerByUserName (String userName){
    return (Customer) entityManager.createNativeQuery("SELECT * FROM CUSTOMER WHERE USERNAME IS :userName", Customer.class)
            .setParameter("userName", userName)
            .getResultList().get(0);
  }

  public void update(Customer customer) {
    Customer merge = entityManager.merge(customer);
    entityManager.persist(merge);
  }

  public void persist(Customer customer) {
    entityManager.persist(customer);
  }
  
  public void delete(Customer customer) {
    entityManager.remove(customer);
  }
}
