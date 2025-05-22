package com.praktika.demoproj.demo1.DAO;

import com.praktika.demoproj.demo1.models.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDAOIml implements OrderDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Order order) {this.em.persist(order);}

    @Transactional
    public void update(Order order) {this.em.merge(order);}

    @Transactional
    public void delete(Order order) {this.em.remove(order);}

    @Transactional
    public Order findById(int id) {return this.em.find(Order.class, id);}

    @Transactional
    public List<Order> findAll() {return this.em.createQuery("from Order").getResultList();}
}
