package net.froihofer.util.jboss.entity;


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
    private int customerId;
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
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;
    @NotEmpty
    @OneToOne(mappedBy = "customer")
    private SecuritiesAccount securitiesAccount;

    public Customer(){
    }

    public Customer(int userId, String firstName, String lastName, String userName, List<Address> addresses, SecuritiesAccount securitiesAccount) {
        this.customerId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.addresses = addresses;
        this.securitiesAccount = securitiesAccount;
    }

    public int getUserId() {
        return customerId;
    }

    public void setUserId(int userId) {
        this.customerId = userId;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public SecuritiesAccount getSecuritiesAccount() {
        return securitiesAccount;
    }

    public void setSecuritiesAccount(SecuritiesAccount securitiesAccount) {
        this.securitiesAccount = securitiesAccount;
    }
}
