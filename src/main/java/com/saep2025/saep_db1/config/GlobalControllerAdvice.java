package com.saep2025.saep_db1.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.saep2025.saep_db1.model.Usuario;
import com.saep2025.saep_db1.repository.UsuarioRepository;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final UsuarioRepository usuarioRepository;

    public GlobalControllerAdvice(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @ModelAttribute("usuarioNome")
    public String usuarioNome(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return "";

        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername()).orElse(null);

        return (usuario != null) ? usuario.getNome() : "";
    }
}
