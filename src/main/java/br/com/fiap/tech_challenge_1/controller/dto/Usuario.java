package br.com.fiap.tech_challenge_1.controller.dto;

public record Usuario(
       Long id, String nome, String email, String login, String senha, String endereco) {}
