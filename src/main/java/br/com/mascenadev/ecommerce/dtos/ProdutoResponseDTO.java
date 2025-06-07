package br.com.mascenadev.ecommerce.dtos;

import br.com.mascenadev.ecommerce.entities.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * DTO de resposta contendo dados do produto.
 * Usado para retornar informações ao cliente em chamadas de API.
 * É construído a partir da entidade {@link Produto}.
 * <p>
 * Os dados são imutáveis após a criação do objeto.
 *
 * @author Gilberto Dev
 */
public class ProdutoResponseDTO {

    private final Long id;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final Integer estoque;
    private final String categoria;

    /**
     * Constrói um DTO com base em um {@link Produto} não nulo.
     *
     * @param entity entidade Produto com os dados
     * @throws NullPointerException se a entidade for nula
     */
    public ProdutoResponseDTO(Produto entity) {
        Objects.requireNonNull(entity, "Produto não pode ser nulo");
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.preco = entity.getPreco();
        this.estoque = entity.getEstoque();
        this.categoria = entity.getCategoria();
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
     * Obtém o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
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
     * Obtém o preço do produto.
     *
     * @return preço do produto
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Obtém a quantidade em estoque do produto.
     *
     * @return estoque do produto
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Obtém a categoria do produto.
     *
     * @return categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Converte uma lista de entidades {@link Produto} em uma lista de {@link ProdutoResponseDTO}.
     *
     * @param produtos lista de entidades Produto (pode ser nula)
     * @return lista de DTOs ProdutoResponseDTO (nunca nula)
     */
    public static List<ProdutoResponseDTO> fromEntityList(List<Produto> produtos) {
        return produtos == null ? List.of() :
                produtos.stream()
                        .map(ProdutoResponseDTO::new)
                        .toList();
    }

    /**
     * Compara dois objetos ProdutoResponseDTO com base no ID.
     *
     * @param o objeto a comparar
     * @return true se os IDs forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoResponseDTO that = (ProdutoResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Retorna o hash code com base no ID.
     *
     * @return valor de hash
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Retorna a representação em string do objeto ProdutoResponseDTO.
     *
     * @return string com os dados do produto
     */
    @Override
    public String toString() {
        return "ProdutoResponseDTO{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", preco=" + preco +
               ", estoque=" + estoque +
               ", categoria='" + categoria + '\'' +
               '}';
    }
}
