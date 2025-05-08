package br.com.fiap.tech_challenge_1.exception;

public class AutenticacaoException extends RuntimeException {
    private final String mensagemUsuario;

    public AutenticacaoException(String mensagemUsuario) {
        this.mensagemUsuario = mensagemUsuario;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }
}
