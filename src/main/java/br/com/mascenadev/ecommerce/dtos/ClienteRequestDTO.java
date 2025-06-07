package br.com.mascenadev.ecommerce.dtos;

import br.com.mascenadev.ecommerce.entities.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

/**
 * DTO utilizado para criar ou atualizar um cliente.
 * Este DTO é utilizado para enviar as informações necessárias para a criação ou atualização de um cliente no sistema.
 * Ele inclui validações para garantir que o nome e o email sejam informados corretamente.
 * <p>
 * O DTO é usado em operações de criação e atualização de clientes via API.
 *
 * @author Gilberto Dev
 */
public class ClienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    /**
     * Construtor padrão.
     */
    public ClienteRequestDTO() {
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome o novo nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o e-mail do cliente.
     *
     * @return e-mail do cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do cliente.
     *
     * @param email o novo e-mail do cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Converte o DTO para uma entidade Cliente.
     *
     * @return a entidade Cliente
     */
    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(this, cliente);
        return cliente;
    }

    @Override
    public String toString() {
        return String.format("ClienteRequestDTO{nome='%s', email='%s'}",
                nome,
                email);
    }
}
