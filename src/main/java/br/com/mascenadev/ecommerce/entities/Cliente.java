package br.com.mascenadev.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade que representa um cliente do sistema de e-commerce.
 * Contém informações de identificação, contato e endereço.
 * <p>
 * Esta classe é mapeada para a tabela "clientes" no banco de dados.
 * Validações são aplicadas para garantir a integridade dos dados inseridos ou atualizados.
 *
 * @author Gilberto Dev
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    /**
     * Identificador único do cliente (chave primária).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do cliente.
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    /**
     * Endereço de e-mail do cliente (único).
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;

    /**
     * CPF do cliente no formato xxx.xxx.xxx-xx (único).
     */
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 14, max = 14, message = "CPF deve ter 14 caracteres no formato xxx.xxx.xxx-xx")
    @Column(unique = true)
    @Pattern(
            regexp = "\\d{3}.\\d{3}.\\d{3}-\\d{2}",
            message = "CPF deve estar no formato xxx.xxx.xxx-xx"
    )
    private String cpf;

    /**
     * Número de telefone do cliente no formato brasileiro.
     */
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "Telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
    private String telefone;

    /**
     * Data de nascimento do cliente.
     */
    @NotNull(message = "Data de nascimento é obrigatório")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    /**
     * Endereço residencial do cliente.
     */
    @NotBlank(message = "Endereço é obrigatório")
    @Size(min = 3, max = 255, message = "Endereço deve ter entre 3 e 255 caracteres")
    private String endereco;

    /**
     * Construtor padrão (necessário para o JPA).
     */
    public Cliente() {
    }

    /**
     * Construtor completo para instanciar um cliente com todos os campos.
     *
     * @param nome           nome completo do cliente
     * @param email          email do cliente
     * @param cpf            CPF do cliente (com máscara)
     * @param telefone       número de telefone
     * @param dataNascimento data de nascimento (no passado)
     * @param endereco       endereço residencial
     */
    public Cliente(String nome, String email, String cpf, String telefone, LocalDate dataNascimento, String endereco) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    /**
     * Obtém o ID do Cliente.
     *
     * @return o ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do cliente.
     *
     * @param id o novo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome o novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email do cliente.
     *
     * @return o email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do cliente.
     *
     * @param email o novo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o CPF do cliente.
     *
     * @return o CPF
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf o novo CPF
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o telefone do cliente.
     *
     * @return o telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente.
     *
     * @param telefone o novo telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém a data de nascimento do cliente.
     *
     * @return a data de nascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define a data de nascimento do cliente.
     *
     * @param dataNascimento a nova data de nascimento
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Obtém o endereço do cliente.
     *
     * @return o endereço
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do cliente.
     *
     * @param endereco o novo endereço
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Compara clientes com base no ID.
     *
     * @param o objeto a ser comparado
     * @return true se os IDs forem iguais
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    /**
     * Gera o hash com base no ID do cliente.
     *
     * @return código hash do cliente
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Representação textual do cliente.
     *
     * @return string com os dados do cliente
     */
    @Override
    public String toString() {
        return "Cliente{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               ", cpf='" + cpf + '\'' +
               ", telefone='" + telefone + '\'' +
               ", dataNascimento=" + dataNascimento +
               ", endereco='" + endereco + '\'' +
               '}';
    }
}
