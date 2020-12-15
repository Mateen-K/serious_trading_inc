/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.froihofer.dsfinance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lorenz Froihofer
 * @version $Id: Variable.java 2:c6f7db7b0e8d 2013/01/21 23:37:39 Lorenz Froihofer $
 */
@Entity
@Table(name="Customer")
public class Customer implements Serializable {

  @Id @GeneratedValue
  long id;
  private String firstName;
  private String lastName;

  public Customer() {
  }

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}

