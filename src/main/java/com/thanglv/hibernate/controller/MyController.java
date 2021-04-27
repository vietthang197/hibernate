package com.thanglv.hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thanglv on 4/27/2021
 * @project hibernate
 */
@Controller
@RequestMapping("/")
public class MyController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/403")
    public String unauth() {
        return "403";
    }
}
