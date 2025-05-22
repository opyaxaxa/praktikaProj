package com.praktika.demoproj.demo1.Controller;

import com.praktika.demoproj.demo1.models.Courier;
import com.praktika.demoproj.demo1.models.Customer;
import com.praktika.demoproj.demo1.models.Order;
import com.praktika.demoproj.demo1.service.CourierService;
import com.praktika.demoproj.demo1.service.OrderService;
import com.praktika.demoproj.demo1.service.geocoding.GeocodingService;
import com.praktika.demoproj.demo1.service.geocoding.exception.GeocodingException;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final GeocodingService geocodingService;
    private final CourierService courierService;

    @Autowired
    public OrderController(OrderService orderService, GeocodingService geocodingService, CourierService courierService) {
        this.orderService = orderService;
        this.geocodingService = geocodingService;
        this.courierService = courierService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequest request,
            @SessionAttribute Customer customer
    ) {
        try {

            Point start = geocodingService.geocode(request.getStart());
            Point end = geocodingService.geocode(request.getEnd());

            Optional<Courier> courier = courierService.findAvailableCourier(start, request.getWeight());

            if (courier.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Нет доступных курьеров"));
            }

            Order order = new Order();
            order.setStart_point(start);
            order.setFinish_point(end);
            order.setWeight(request.getWeight());
            order.setCustomer(customer);
            order.setCourier(courier.get());
            order.setActive(Order.Status.INCOMPLETE);

            courier.get().setStatus(Courier.CourierStatus.BUSY);
            courierService.update(courier.get());

            orderService.create(order);

            return ResponseEntity.ok(order);

        } catch (GeocodingException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    public static class OrderRequest {
        private String start;
        private String end;
        private double weight;

        public String getStart() { return start; }
        public String getEnd() { return end; }
        public double getWeight() { return weight; }

        public void setStart(String start) { this.start = start; }
        public void setEnd(String end) { this.end = end; }
        public void setWeight(double weight) { this.weight = weight; }
    }
}