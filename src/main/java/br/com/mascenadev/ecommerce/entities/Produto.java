package br.com.mascenadev.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Representa um produto disponível no sistema de e-commerce.
 * Esta entidade contém informações básicas como nome, descrição, preço, estoque e categoria.
 */
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do produto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do produto. Deve ter entre 3 e 100 caracteres.
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    /**
     * Descrição do produto. Deve ter entre 3 e 100 caracteres.
     */
    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descricao;

    /**
     * Preço do produto. Deve ser maior que zero.
     */
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    @Column(nullable = false)
    private BigDecimal preco;

    /**
     * Quantidade de unidades disponíveis em estoque. Não pode ser negativa.
     */
    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    @Column(nullable = false)
    private Integer estoque;

    /**
     * Categoria do produto. Deve ter entre 3 e 30 caracteres.
     */
    @NotBlank(message = "Categoria é obrigatória")
    @Size(min = 3, max = 30, message = "Categoria deve ter entre 3 e 30 caracteres")
    @Column(nullable = false, length = 30)
    private String categoria;

    /**
     * Construtor padrão.
     */
    public Produto() {
    }

    /**
     * Construtor com todos os campos (exceto o ID).
     *
     * @param nome      nome do produto
     * @param descricao descrição do produto
     * @param preco     preço do produto
     * @param estoque   quantidade em estoque
     * @param categoria categoria do produto
     */
    public Produto(String nome, String descricao, BigDecimal preco, Integer estoque, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    /**
     * Obtém o ID do produto.
     *
     * @return ID do produto
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id novo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome Novo Nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return Descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao Nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return Preço
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco Novo preço
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    /**
     * Obtém a quantidade do estoque.
     *
     * @return Estoque
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Define a quantidade do estoque.
     *
     * @param estoque Novo estoque
     */
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    /**
     * Obtém a categoria.
     *
     * @return Categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria.
     *
     * @param categoria Nova categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Compara dois objetos Produto com base no ID.
     *
     * @param o objeto a ser comparado
     * @return true se os objetos tiverem o mesmo ID
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    /**
     * Retorna uma representação em string do objeto Produto.
     *
     * @return string contendo os dados do produto
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", preco=" + preco +
               ", estoque=" + estoque +
               ", categoria='" + categoria + '\'' +
               '}';
    }
}
