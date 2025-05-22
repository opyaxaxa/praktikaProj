package com.praktika.demoproj.demo1.DAO;

import com.praktika.demoproj.demo1.models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOIml implements CustomerDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Customer customer) {this.em.persist(customer);}

    @Transactional
    public void update(Customer customer) {this.em.merge(customer);}

    @Transactional
    public void delete(Customer customer) {this.em.remove(customer);}

    @Transactional
    public Customer findById(int id) {return this.em.find(Customer.class, id);}

    @Transactional
    public Customer findByPhoneNumber(String phone_number) {
        return em.createQuery("SELECT u FROM Customer u where u.phone_number = :phone", Customer.class)
                .setParameter("phone", phone_number)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public List<Customer> findAll() {return this.em.createQuery("from Customer").getResultList();}
}
