package com.saep2025.saep_db1.config;

/*💻 Arquivo: DatabaseInitializer.java

Função: executar automaticamente um código assim que a aplicação Spring Boot é iniciada.*/



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    /*➡️ A classe DatabaseInitializer implementa CommandLineRunner, logo o método run() será chamado automaticamente na inicialização da aplicação. */


    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============================================");
        System.out.println(" 🚀 SAEP 2025 - Banco de Dados Inicializado com sucesso!");
        System.out.println(" ✅ Tabelas criadas e dados carregados via schema.sql e data.sql");
        System.out.println(" 💾 Pronto para uso no ambiente MySQL local (saep_db1)");
        System.out.println("==============================================");
    }
}

/*➡️ O método run() é sobrescrito da interface CommandLineRunner.
O parâmetro String... args representa os argumentos passados na linha de comando, caso a aplicação fosse executada com parâmetros (por exemplo, java -jar app.jar arg1 arg2). */

/*➡️ 
Essas mensagens são apenas impressões no console, mas cumprem uma função prática importante: confirmar visualmente que o banco foi inicializado e os scripts schema.sql e data.sql foram executados com sucesso.
Elas aparecem logo após a inicialização do contexto Spring — útil em testes locais. */