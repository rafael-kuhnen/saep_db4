package com.saep2025.saep_db1.controller;


import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saep2025.saep_db1.model.Produto;
import com.saep2025.saep_db1.model.Tipo;
import com.saep2025.saep_db1.repository.ProdutoRepository;
import com.saep2025.saep_db1.repository.TipoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {


    private final  ProdutoRepository produtoRepository;
    
    private final TipoRepository tipoRepository;

     // ✅ Injeção via construtor (recomendada pelo Sonar)
    public ProdutoController(ProdutoRepository produtoRepository, TipoRepository tipoRepository) {
        this.produtoRepository = produtoRepository;
        this.tipoRepository = tipoRepository;
    }
    
@GetMapping
public String listar(Model model) {
    List<Produto> produtos = produtoRepository.findAll();

    long produtosCriticos = produtos.stream()
            .filter(p -> p.getEstoqueatual() != null && p.getEstoqueminimo() != null)
            .filter(p -> p.getEstoqueatual().compareTo(p.getEstoqueminimo()) <= 0)
            .count();

    model.addAttribute("produtos", produtos);
    model.addAttribute("produtosCriticos", produtosCriticos);
    model.addAttribute("ativo", "produtos");
    return "produto/lista";
}


    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("tipos", tipoRepository.findAll());
        return "produto/form";
    }

@GetMapping("/editar/{id}")
public String editar(@PathVariable Long id, Model model) {
    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));

    // Evita NullPointerException no select
    if (produto.getTipo() == null) {
        produto.setTipo(new Tipo());
    }

    model.addAttribute("produto", produto);
    model.addAttribute("tipos", tipoRepository.findAll());
    return "produto/form";
}



@GetMapping("/excluir/{id}")
public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
    try {
        produtoRepository.deleteById(id);
        redirect.addFlashAttribute("mensagemSucesso", "Produto excluído com sucesso!");
    } catch (Exception e) {
        redirect.addFlashAttribute("mensagemErro", "Não foi possível excluir: o produto está vinculado a outros registros.");
    }
    return "redirect:/produtos";
}

@GetMapping("/buscar")
public String buscar(@RequestParam(required = false) String termo, Model model) {
    List<Produto> produtos;
    if (termo != null && !termo.isEmpty()) {
        produtos = produtoRepository.findByDescprodutoContainingIgnoreCaseOrTipoDescricaoContainingIgnoreCase(termo, termo);
    } else {
        produtos = produtoRepository.findAll();
    }

    long produtosCriticos = produtos.stream()
            .filter(p -> p.getEstoqueatual().compareTo(p.getEstoqueminimo()) <= 0)
            .count();

    model.addAttribute("produtos", produtos);
    model.addAttribute("produtosCriticos", produtosCriticos);
    model.addAttribute("ativo", "produtos");
    model.addAttribute("termo", termo);
    return "produto/lista";
}



}
