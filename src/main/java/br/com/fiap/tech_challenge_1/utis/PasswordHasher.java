package br.com.fiap.tech_challenge_1.utis;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordHasher() {
        // O parâmetro strength define o fator de trabalho (work factor)
        // Valores entre 10-12 são recomendados para equilíbrio entre segurança e desempenho
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }

    /**
     * Gera um hash seguro da senha usando BCrypt
     */
    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verifica se a senha informada corresponde ao hash armazenado
     */
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

}
