package com.saep2025.saep_db1.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.saep2025.saep_db1.model.Tipo;
import com.saep2025.saep_db1.model.Usuario;
import com.saep2025.saep_db1.repository.TipoRepository;
import com.saep2025.saep_db1.repository.UsuarioRepository;

@Component
public class DataVerifier implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final TipoRepository tipoRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DataVerifier(UsuarioRepository usuarioRepository, TipoRepository tipoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoRepository = tipoRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("==============================================");
        System.out.println("🔍 Verificando dados iniciais do SAEP 2025...");

        // 1️⃣ Verificar se existe usuário administrador
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@saep.com");
            admin.setSenha(passwordEncoder.encode("admin"));
            usuarioRepository.save(admin);
            System.out.println("✅ Usuário padrão criado: admin@saep.com (senha: admin)");
        } else {
            System.out.println("ℹ️ Usuário(s) já cadastrado(s). Nenhuma ação necessária.");
        }

        // 2️⃣ Verificar se existem tipos
        if (tipoRepository.count() == 0) {
            List<Tipo> tipos = new ArrayList<>();

            Tipo t1 = new Tipo();
            t1.setDescricao("Corante");
            Tipo t2 = new Tipo();
            t2.setDescricao("Alvejante");
            Tipo t3 = new Tipo();
            t3.setDescricao("Auxiliar");
            Tipo t4 = new Tipo();
            t4.setDescricao("Não definido");

            tipos.addAll(Arrays.asList(t1, t2, t3, t4));

            System.out.println("✅ Tipos padrões criados: Corante, Alvejante, Auxiliar, Não definido");
        } else {
            System.out.println("ℹ️ Tipos já existentes. Nenhuma ação necessária.");
        }

        System.out.println("==============================================");
        System.out.println("🚀 Banco de dados SAEP 2025 pronto para uso!");
        System.out.println("==============================================");
    }
}
