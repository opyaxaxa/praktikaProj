package com.praktika.demoproj.demo1.DAO;

import com.praktika.demoproj.demo1.models.Courier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourierDAOIml implements CourierDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Courier courier) {this.em.persist(courier);}

    @Transactional
    public void update(Courier courier) {this.em.merge(courier);}

    @Transactional
    public void delete(Courier courier) {this.em.remove(courier);}

    @Transactional
    public Courier findById(int id) {return this.em.find(Courier.class, id);}

    @Transactional
    public Courier findByPhoneNumber(String phone_number) {
        return em.createQuery("SELECT u FROM Courier u where u.phone_number = :phone", Courier.class)
                .setParameter("phone", phone_number)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public List<Courier> findAll() {return this.em.createQuery("from Courier").getResultList();}
}
