package com.praktika.demoproj.demo1.models;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "couriers")
public class Courier{
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

    @Column(name = "speed")
    private int speed;

    @Column(name = "carrying")
    private int carrying;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "position", columnDefinition = "geometry(Point, 4326)")
    private Point position;

    @Column(name = "cost_per_hour")
    private int cost_per_hour;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CourierStatus status;

    public enum CourierStatus {
        AVAILABLE,
        BUSY
    }

    public Courier() {}

    public Courier(String name, String surname, String email, String phone_number, int speed, int carrying, String vehicle, Point position, int cost_per_hour) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.speed = speed;
        this.carrying = carrying;
        this.vehicle = vehicle;
        this.position = position;
        this.cost_per_hour = cost_per_hour;
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

    public int getSpeed() {
        return speed;
    }

    public int getCarrying() {
        return carrying;
    }

    public String getVehicle() {
        return vehicle;
    }

    public Point getPosition() {
        return position;
    }

    public int getCostPerHour() {
        return cost_per_hour;
    }

    public String getStatus() {
        return status.toString();
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setCostPerHour(int cost_per_hour) {
        this.cost_per_hour = cost_per_hour;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }
}
