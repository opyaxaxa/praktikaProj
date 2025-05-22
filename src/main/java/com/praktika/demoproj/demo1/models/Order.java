package com.praktika.demoproj.demo1.models;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    public enum Status{
        WAITING,
        INCOMPLETE,
        DONE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start")
    private Point start_point;

    @Column(name = "finish")
    private Point finish_point;

    @Column(name = "time_start")
    private Time time_start;

    @Column(name = "time_finish")
    private Time time_finish;

    @Column(name = "weight")
    private double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "cost")
    private double cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "active")
    private Status active;

    public Order() {}

    public Order(Point start_point, Point finish_point, Time time_start, Time time_finish, double weight, Courier courier, Customer customer, List<String> products, double cost, Status active) {
        this.start_point = start_point;
        this.finish_point = finish_point;
        this.time_start = time_start;
        this.time_finish = time_finish;
        this.weight = weight;
        this.courier = courier;
        this.customer = customer;
        this.cost = cost;
        this.active = active;
    }

    public int getId() {
        return id;
    }
    public Time getTime_start() {
        return time_start;
    }
    public Time getTime_finish() {
        return time_finish;
    }
    public double getWeight() {
        return weight;
    }
    public Courier getCourier() {
        return courier;
    }
    public Customer getCustomer() {
        return customer;
    }
    public double getCost() {
        return cost;
    }
    public Status getActive() {
        return active;
    }

    public void setStart_point(Point start_point) {
        this.start_point = start_point;
    }
    public void setFinish_point(Point finish_point) {
        this.finish_point = finish_point;
    }
    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }
    public void setTime_finish(Time time_finish) {
        this.time_finish = time_finish;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setCourier(Courier courier) {
        this.courier = courier;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setActive(Status active) {
        this.active = active;
    }


}

