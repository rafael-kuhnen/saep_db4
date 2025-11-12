package com.saep2025.saep_db1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // vai abrir o template login.html
    }

    @GetMapping("/principal")
    public String principal() {
        return "principal"; // página inicial após o login
    }

}