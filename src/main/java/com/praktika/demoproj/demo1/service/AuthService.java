package com.praktika.demoproj.demo1.service;

import com.praktika.demoproj.demo1.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CustomerService cs;

    public boolean registerUser(
            String name,
            String surname,
            String email,
            String phone_number,
            String password,
            Boolean isWaiting) {
        if (cs.findByPhoneNumber(phone_number) != null) {
            return false;
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setEmail(email);
        customer.setPhoneNumber(phone_number);
        customer.setPassword(password);
        customer.setIsWaiting(isWaiting != null ? isWaiting : false);
        cs.create(customer);
        return true;
    }

    public boolean loginUser(String phone_number, String password) {
        Customer customer = cs.findByPhoneNumber(phone_number);
        return customer != null && password.equals(customer.getPassword());
    }
}
