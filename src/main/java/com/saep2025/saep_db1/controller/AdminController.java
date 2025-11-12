package com.saep2025.saep_db1.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    /**
     * Gera uma senha criptografada em BCrypt diretamente pelo navegador.
     * Exemplo:
     * http://localhost:8080/admin/gerarsenha?senha=admin
     */
    @GetMapping("/admin/gerarsenha")
    public String gerarSenha(@RequestParam String senha) {
        String hash = new BCryptPasswordEncoder().encode(senha);
        return """
                🧩 <b>Senha digitada:</b> %s<br>
                🔒 <b>Hash gerado (BCrypt):</b><br>
                <code>%s</code>
                """.formatted(senha, hash);
    }
}
