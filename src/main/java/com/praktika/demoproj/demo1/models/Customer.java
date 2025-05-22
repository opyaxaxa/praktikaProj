package com.praktika.demoproj.demo1.models;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "customers")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(unique = true, name = "email")
    private String email;

    @Column(unique = true, name = "phone_number")
    private String phone_number;

    @Column(name = "password")
    private String password;

    @Column(name = "is_waiting")
    private boolean is_waiting;

    public Customer() {}

    public Customer(String name, String surname, String email, String phone_number, String password, boolean is_waiting) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.is_waiting = is_waiting;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsWaiting() {
        return is_waiting;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsWaiting(boolean is_waiting) {
        this.is_waiting = is_waiting;
    }

}
