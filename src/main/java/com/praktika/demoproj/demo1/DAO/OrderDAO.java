package com.praktika.demoproj.demo1.DAO;

import com.praktika.demoproj.demo1.models.Order;
import java.util.List;

public interface OrderDAO {
    void save(Order order);
    void update(Order order);
    void delete(Order order);
    Order findById(int id);
    List<Order> findAll();
}
