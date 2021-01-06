package net.froihofer.dsfinance.dao;

import net.froihofer.dsfinance.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomerDAO{
  @PersistenceContext
  private EntityManager entityManager;


  public Customer findById(int id) {
    return entityManager.find(Customer.class, id);
  }

  public List<Customer> findAllCustomers() {
    return entityManager.createQuery("SELECT x FROM Customer x", Customer.class).getResultList();
  }

  //case sensitive. at least one char per surname and lastname
  public List<Customer> findCustomers(String lastName, String firstName){
    return entityManager.createQuery("SELECT x FROM Customer x WHERE x.firstName LIKE :firstName AND x.lastName LIKE :lastName" , Customer.class)
            .setParameter("firstName", '%' + firstName + '%')
            .setParameter("lastName", '%' + lastName + '%')
            .getResultList();
  }

  public List<Customer> findByFirstName(String firstName) {
    return entityManager.createQuery("SELECT x FROM Customer x WHERE x.firstName LIKE :firstName",
            Customer.class).setParameter("firstName", firstName).
            getResultList();
  }

  public List<Customer> findByLastName(String lastName) {
    return entityManager.createQuery("SELECT x FROM Customer x WHERE x.lastName LIKE :lastName",
            Customer.class).setParameter("lastName", lastName).
            getResultList();
  }

  public void persist(Customer customer) {
    entityManager.persist(customer);
  }

  public void delete(Customer customer) {
    entityManager.remove(customer);
  }
}








