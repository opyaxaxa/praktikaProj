package com.praktika.demoproj.demo1.service;

import com.praktika.demoproj.demo1.DAO.CustomerDAO;
import com.praktika.demoproj.demo1.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) { this.customerDAO = customerDAO; }

    @Transactional
    public void create(Customer customer) {
        customerDAO.save(customer);
    }

    @Transactional
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Transactional
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Transactional
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Transactional
    public Customer findByPhoneNumber(String phone_num) {
        return customerDAO.findByPhoneNumber(phone_num);
    }

    @Transactional
    public void delete(Customer customer) {
        customerDAO.delete(customer);
    }
}
