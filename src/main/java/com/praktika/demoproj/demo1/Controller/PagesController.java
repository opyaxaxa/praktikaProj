package com.praktika.demoproj.demo1.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String goToLogin() {
        return "login";
    }

}
