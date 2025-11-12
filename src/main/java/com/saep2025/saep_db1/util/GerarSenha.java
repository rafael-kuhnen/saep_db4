package com.saep2025.saep_db1.util;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe utilitária para gerar senhas criptografadas em BCrypt.
 * 
 * Uso:
 * 1️⃣ Execute essa classe como aplicação Java.
 * 2️⃣ Digite a senha desejada quando solicitado.
 * 3️⃣ Copie o hash gerado e use no banco de dados.
 * 
 * Exemplo:
 *  - Senha digitada: admin
 *  - Hash gerado: $2a$10$zvCz1Yo6/7... (copie tudo)
 * 
 *  Depois, no MySQL:
 *    UPDATE usuario SET senha = '<hash_gerado>' WHERE email = 'admin@saep.com';
 */
public class GerarSenha {

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Digite a senha a ser criptografada: ");
        String senha = scanner.nextLine();
        scanner.close();

        String hash = new BCryptPasswordEncoder().encode(senha);
        System.out.println("\nSenha criptografada:");
        System.out.println(hash);
    }
}

