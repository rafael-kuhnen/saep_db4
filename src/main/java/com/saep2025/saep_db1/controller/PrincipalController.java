package com.saep2025.saep_db1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {


    @GetMapping("/")
public String redirecionarParaPrincipal() {
    return "redirect:/inicio";
}

    // ✅ Página principal do sistema
    @GetMapping("/inicio")
    public String principal(Model model) {
        model.addAttribute("tituloPagina", "Página Inicial - SAEP 2025");
        return "principal"; // carrega o template templates/principal.html
    }

    @GetMapping("/teste")
public String testeLayout() {
    return "teste";
}
}
