package com.praktika.demoproj.demo1.Controller;

import com.praktika.demoproj.demo1.models.Customer;
import com.praktika.demoproj.demo1.service.AuthService;
import com.praktika.demoproj.demo1.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AuthService as;
    
    @Autowired
    private CustomerService cs;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam("phone_number") String phone_number,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        if (as.loginUser(phone_number, password)) {
            Customer customer = cs.findByPhoneNumber(phone_number);
            session.setAttribute("user", customer);
            return "map";

        }
        model.addAttribute("error", "Неверные данные");
        return "login";
    }

    @PostMapping("/register")
    public String processRegistration(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String phone_number,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(required = false) Boolean is_waiting,
            Model model
    ) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совпадают");
            return "register";
        }

        if (as.registerUser(name, surname, email, phone_number, password, is_waiting)) {
            return "redirect:/login?registered=true";
        }
        model.addAttribute("error", "Пользователь уже существует");
        return "register";
    }
}
