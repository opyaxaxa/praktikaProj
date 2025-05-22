package com.praktika.demoproj.demo1.DAO;

import com.praktika.demoproj.demo1.models.Customer;
import java.util.List;

public interface CustomerDAO {
    void save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    Customer findById(int id);
    Customer findByPhoneNumber(String phoneNumber);
    List<Customer> findAll();
}
