package br.com.mascenadev.ecommerce.dtos;

import br.com.mascenadev.ecommerce.entities.Produto;
import jakarta.validation.constraints.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * DTO usado para criação ou atualização de produtos no sistema de e-commerce.
 * Contém validações para garantir integridade dos dados recebidos na requisição.
 *
 * @author Gilberto Dev
 */
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 255, message = "Descrição deve ter entre 3 e 255 caracteres")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    private Integer estoque;

    @NotBlank(message = "Categoria é obrigatória")
    @Size(min = 3, max = 30, message = "Categoria deve ter entre 3 e 30 caracteres")
    private String categoria;

    /**
     * Construtor padrão (necessário para frameworks como Spring).
     */
    public ProdutoRequestDTO() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param nome      nome do produto
     * @param descricao descrição do produto
     * @param preco     preço do produto
     * @param estoque   quantidade disponível em estoque
     * @param categoria categoria do produto
     */
    public ProdutoRequestDTO(String nome, String descricao, BigDecimal preco, Integer estoque, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome o novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return descrição do produto
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao a nova descrição do produto
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return o preço do produto
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco o novo preço do produto
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    /**
     * Obtém o estoque do produto.
     *
     * @return o estoque do produto
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Define o estoque do produto.
     *
     * @param estoque o novo estoque do produto
     */
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    /**
     * Obtém a categoria do produto.
     *
     * @return a categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria a nova categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Converte este DTO para uma entidade Produto.
     *
     * @return instância de Produto
     */
    public Produto toEntity() {
        Produto produto = new Produto();
        BeanUtils.copyProperties(this, produto);
        return produto;
    }

    /**
     * Cria um DTO de requisição a partir de uma entidade Produto.
     *
     * @param produto entidade Produto
     * @return instância preenchida de ProdutoRequestDTO
     */
    public static ProdutoRequestDTO fromEntity(Produto produto) {
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        BeanUtils.copyProperties(produto, dto);
        return dto;
    }

    /**
     * Retorna uma representação em string do objeto Produto.
     *
     * @return string contendo os dados do produto
     */
    @Override
    public String toString() {
        return "ProdutoRequestDTO{" +
               "nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", preco=" + preco +
               ", estoque=" + estoque +
               ", categoria='" + categoria + '\'' +
               '}';
    }
}
