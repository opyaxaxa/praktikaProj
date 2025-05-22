package com.praktika.demoproj.demo1.Controller;

import com.praktika.demoproj.demo1.DTO.CourierPositionDTO;
import com.praktika.demoproj.demo1.models.Courier;
import com.praktika.demoproj.demo1.service.CourierService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {

    private final CourierService courierService;

    public MapController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping("/map")
    public String showMap(HttpSession session, Model model) {
        if (session.getAttribute("courier") == null) {
            return "redirect:/login";
        }
        Courier courier = (Courier) session.getAttribute("courier");
        model.addAttribute("courierId", courier.getId());
        return "map";
    }

    @GetMapping("/api/positions")
    @ResponseBody
    public ResponseEntity<?> getAllPositions() {
        try {
            List<Courier> couriers = courierService.findAll();
            System.out.println("Найдено курьеров: " + couriers.size());

            List<CourierPositionDTO> positions = new ArrayList<>();

            for (Courier courier : couriers) {
                if (courier.getPosition() != null) {
                    positions.add(new CourierPositionDTO(
                            courier.getPosition(),
                            courier.getId()
                    ));
                }
            }
            return ResponseEntity.ok(positions);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
