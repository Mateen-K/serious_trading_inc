package net.froihofer.dsfinance.entity;


//import javax.mail.Address; //do not use library address class
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty
    @Column
    private String firstName;
    @NotEmpty
    @Column
    private String lastName;
    @NotEmpty
    @Column(unique=true)
    private String userName;
    @NotEmpty
    @OneToOne(mappedBy = "customer")
    private Address address;
    @NotEmpty
    @Column
    private String password;
    @NotEmpty
    @OneToOne(mappedBy = "customer")
    private SecuritiesAccount securitiesAccount;

    public Customer(){
    }

    public Customer(String firstName, String lastName, String userName, Address address, String password, SecuritiesAccount securitiesAccount) {
//        this.id = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.address = address;
        this.securitiesAccount = securitiesAccount;
    }

    public int getCustomerId() {
        return id;
    }

    public void setCustomerId(int customerId) {
        this.id = customerId;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public SecuritiesAccount getSecuritiesAccount() {
        return securitiesAccount;
    }

    public void setSecuritiesAccount(SecuritiesAccount securitiesAccount) {
        this.securitiesAccount = securitiesAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
