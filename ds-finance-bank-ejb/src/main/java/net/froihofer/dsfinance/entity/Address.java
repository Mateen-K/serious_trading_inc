package net.froihofer.dsfinance.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="Address")
public class Address implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Column
    private String street;
    @NotNull
    @Column
    private int houseNo;
    @NotNull
    @Column
    private String apartmentNo;
    @NotNull
    @Column
    private String zipCode;
    @NotNull
    @Column
    private String city;
    @NotNull
    @Column
    private String country;
    @NotNull
    @ManyToOne
    @JoinColumn(name ="Customer_FK")
    private Customer customer;

    public Address(){
    }

    public Address(String street, int houseNo, String apartmentNo, String zipCode, String city, String country, Customer customer) {
        this.street = street;
        this.houseNo = houseNo;
        this.apartmentNo = apartmentNo;
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

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
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



