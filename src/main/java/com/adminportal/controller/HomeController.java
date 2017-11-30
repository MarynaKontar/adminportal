package com.adminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by User on 30.11.2017.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){return "redirect:/home";}

    @GetMapping("/home")
    public String home(){return "home";}

    @GetMapping("/login")
    public String login(){return "login";}

}
