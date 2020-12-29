package net.froihofer.util.jboss.entity;


//import javax.mail.Address; //do not use library address class
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {
    @Id
    private int userId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String userName;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    public Customer(){
    }

    public Customer(int userId, String firstName, String lastName, String userName, List<Address> addresses) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.addresses = addresses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
