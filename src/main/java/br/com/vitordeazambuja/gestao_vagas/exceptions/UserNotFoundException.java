package br.com.vitordeazambuja.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuario nao encontrado");
    }
}
