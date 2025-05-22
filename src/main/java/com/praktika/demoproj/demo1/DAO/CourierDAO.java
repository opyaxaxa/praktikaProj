package com.praktika.demoproj.demo1.DAO;

import com.praktika.demoproj.demo1.models.Courier;
import java.util.List;

public interface CourierDAO {
    void save(Courier courier);
    void delete(Courier courier);
    void update(Courier courier);
    Courier findById(int id);
    Courier findByPhoneNumber(String phone_number);
    List<Courier> findAll();
}
