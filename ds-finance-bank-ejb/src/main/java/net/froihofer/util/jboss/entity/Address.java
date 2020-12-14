package net.froihofer.util.jboss.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Address")
public class Address implements Serializable {
    @Id
    private int id;
    @Column
    private String street;
    @Column
    private int houseNo;
    @Column
    private String appartmentNo;
    @Column
    private String zipCode;
    @Column
    private String city;
    @Column
    private String country;

    @ManyToOne
    @JoinColumn(name ="Customer_FK")
    private Customer customer;

    public Address(){
    }

    public Address(int id, String street, int houseNo, String appartmentNo, String zipCode, String city, String country, Customer customer) {
        this.id = id;
        this.street = street;
        this.houseNo = houseNo;
        this.appartmentNo = appartmentNo;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public String getAppartmentNo() {
        return appartmentNo;
    }

    public void setAppartmentNo(String appartmentNo) {
        this.appartmentNo = appartmentNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
