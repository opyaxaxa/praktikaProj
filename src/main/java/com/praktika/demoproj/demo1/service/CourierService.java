package com.praktika.demoproj.demo1.service;

import com.praktika.demoproj.demo1.DAO.CourierDAO;
import com.praktika.demoproj.demo1.models.Courier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourierService {
    private final CourierDAO courierDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CourierService(CourierDAO courierDAO) {
        this.courierDAO = courierDAO;
    }

    @Transactional
    public void create(Courier courier) {
        courierDAO.save(courier);
    }

    @Transactional
    public void update(Courier courier) {
        courierDAO.update(courier);
    }

    @Transactional
    public List<Courier> findAll() {
        return courierDAO.findAll();
    }

    @Transactional
    public Courier findById(int id) {
        return courierDAO.findById(id);
    }

    @Transactional
    public Courier findByPhoneNumber(String phone_num) {
        return courierDAO.findByPhoneNumber(phone_num);
    }

    @Transactional
    public void delete(Courier courier) {
        courierDAO.delete(courier);
    }

    @Transactional
    public Optional<Courier> findAvailableCourier(Point pickupPoint, double orderWeight) {
        String sql = """
            SELECT c.* 
            FROM couriers c
            WHERE c.status = 'AVAILABLE'
            AND c.max_weight >= :weight
            ORDER BY ST_Distance(c.position, :point)
            LIMIT 1
        """;

        Query query = entityManager.createNativeQuery(sql, Courier.class)
                .setParameter("point", pickupPoint)
                .setParameter("weight", orderWeight);

        try {
            return Optional.of((Courier) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
