package br.com.fiap.tech_challenge_1.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;


    private String email;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    private LocalDate dataUpdate;

    private String endereco;


    public UsuarioEntity(String nome, String email, String login, String senha, String endereco) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.dataUpdate = LocalDate.now();
    }
}
