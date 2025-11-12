package com.saep2025.saep_db1.controller;


import com.saep2025.saep_db1.model.Movimento;
import com.saep2025.saep_db1.model.Produto;
import com.saep2025.saep_db1.model.Usuario;
import com.saep2025.saep_db1.repository.MovimentoRepository;
import com.saep2025.saep_db1.repository.ProdutoRepository;
import com.saep2025.saep_db1.repository.UsuarioRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movimentos")
public class MovimentoController {


    private MovimentoRepository movimentoRepository;   
    private ProdutoRepository produtoRepository; 
    public MovimentoController(MovimentoRepository movimentoRepository, ProdutoRepository produtoRepository,
            UsuarioRepository usuarioRepository) {
        this.movimentoRepository = movimentoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(Model model) {
        List<Movimento> movimentos = movimentoRepository.findAll();
        model.addAttribute("movimentos", movimentos);
        return "movimento/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("movimento", new Movimento());
        model.addAttribute("produtos", produtoRepository.findAll());
        return "movimento/form";
    }

   @PostMapping("/salvar")
public String salvar(@ModelAttribute Movimento movimento,
                     @AuthenticationPrincipal UserDetails userDetails,
                     Model model) {

    Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername()).orElse(null);
    if (usuario != null) {
        movimento.setUsuario(usuario);
    }
    
    Produto produto = produtoRepository.findById(movimento.getProduto().getId()).orElse(null);

    if (produto != null) {
        // Verificação de saída maior que o estoque
        if (movimento.getTipomovto() == 'S' && 
            movimento.getQtdmovto().compareTo(produto.getEstoqueatual()) > 0) {

            model.addAttribute("erro", 
                "❌ Quantidade solicitada (" + movimento.getQtdmovto() + 
                ") é maior que o estoque atual (" + produto.getEstoqueatual() + ").");
            model.addAttribute("movimento", movimento);
            model.addAttribute("produtos", produtoRepository.findAll());
            return "movimento/form";
        }

        // Atualização do estoque
        if (movimento.getTipomovto() == 'E') {
            produto.setEstoqueatual(produto.getEstoqueatual().add(movimento.getQtdmovto()));
        } else if (movimento.getTipomovto() == 'S') {
            produto.setEstoqueatual(produto.getEstoqueatual().subtract(movimento.getQtdmovto()));
        }
        produtoRepository.save(produto);
    }

    movimentoRepository.save(movimento);
    return "redirect:/movimentos";
}

}

