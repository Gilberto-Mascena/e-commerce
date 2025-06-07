package br.com.mascenadev.ecommerce.dtos;

import jakarta.validation.constraints.Email;

import java.util.Objects;

/**
 * DTO utilizado para retornar os dados de um cliente na API.
 * <p>
 * Este DTO é utilizado para exibir informações básicas de um cliente, como ID, nome e email,
 * geralmente nas respostas de consulta de clientes.
 * </p>
 *
 * @author Gilberto Dev
 */
public class ClienteResponseDTO {

    private final Long id;
    private final String nome;

    @Email(message = "Email deve ser válido")
    private final String email;

    /**
     * Construtor privado para inicializar o DTO com os dados de um cliente.
     *
     * @param id    ID do cliente
     * @param nome  Nome do cliente
     * @param email Email do cliente
     */
    public ClienteResponseDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    /**
     * Retorna o ID do cliente.
     *
     * @return ID do cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o email do cliente.
     *
     * @return email do cliente
     */
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClienteResponseDTO that = (ClienteResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("ClienteResponseDTO{id=%d, nome='%s', email='%s'}",
                id,
                nome != null ? nome : "N/A",
                email != null ? email : "N/A");
    }

}
