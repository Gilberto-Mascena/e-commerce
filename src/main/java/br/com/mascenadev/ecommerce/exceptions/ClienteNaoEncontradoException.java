package br.com.mascenadev.ecommerce.exceptions;

/**
 * Exceção lançada quando um cliente não é encontrado no sistema.
 */
public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
