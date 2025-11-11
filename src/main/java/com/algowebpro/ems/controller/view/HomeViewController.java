package com.algowebpro.ems.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeViewController {

    @RequestMapping("/home")
    public String getHome(){
        return "home";
    }
    
    @GetMapping("/")
    public String home() {
        return "index"; // loads templates/index.html
    }
}
