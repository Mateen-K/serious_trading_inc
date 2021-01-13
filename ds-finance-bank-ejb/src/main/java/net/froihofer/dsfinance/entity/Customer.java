package net.froihofer.dsfinance.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {

  @Id
  int id;
  @NotNull
  @Column
  private String firstName;
  @NotNull
  @Column
  private String lastName;
  @NotNull
  @Column(unique=true)
  private String userName;
  @NotNull
  @Column
  private BigDecimal currentValue;

  public Customer(){
    this.currentValue = BigDecimal.valueOf(0);
  }

  public Customer(int customerId, String firstName, String lastName, String userName) {
    this.id = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.currentValue = BigDecimal.valueOf(0);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public BigDecimal getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(BigDecimal currentValue) {
    this.currentValue = currentValue;
  }
}

