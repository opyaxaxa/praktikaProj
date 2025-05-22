package com.praktika.demoproj.demo1.service;

import com.praktika.demoproj.demo1.DAO.OrderDAO;
import com.praktika.demoproj.demo1.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO) { this.orderDAO = orderDAO; }

    @Transactional
    public void create(Order order) {
        order.setActive(Order.Status.WAITING);
        orderDAO.save(order);
    }

    @Transactional
    public void update(Order order) {
        orderDAO.update(order);
    }

    @Transactional
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Transactional
    public Order findById(int id) {
        return orderDAO.findById(id);
    }

    @Transactional
    public void delete(Order order) {
        orderDAO.delete(order);
    }
}
