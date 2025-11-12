package com.saep2025.saep_db1.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.saep2025.saep_db1.model.Tipo;
import com.saep2025.saep_db1.repository.TipoRepository;

@Controller
@RequestMapping("/tipos")
public class TipoController {

    private final TipoRepository tipoRepository;

    public TipoController(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    // ✅ Listar todos os tipos
    @GetMapping
    public String listar(Model model) {
        List<Tipo> tipos = tipoRepository.findAll();
        model.addAttribute("tipos", tipos);
        model.addAttribute("tituloPagina", "Lista de Tipos");
        model.addAttribute("ativo", "tipos");
        return "tipo/lista";
    }

    // ✅ Formulário para novo tipo
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tipo", new Tipo());
        model.addAttribute("tituloPagina", "Novo Tipo de Produto");
        return "tipo/form";
    }

    // ✅ Salvar (novo ou edição)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Tipo tipo) {
        tipoRepository.save(tipo);
        return "redirect:/tipos";
    }

    // ✅ Editar tipo existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Tipo tipo = tipoRepository.findById(id).orElse(new Tipo());
        model.addAttribute("tipo", tipo);
        model.addAttribute("tituloPagina", "Editar Tipo");
        return "tipo/form";
    }

    // ✅ Excluir tipo
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        tipoRepository.deleteById(id);
        return "redirect:/tipos";
    }
}
