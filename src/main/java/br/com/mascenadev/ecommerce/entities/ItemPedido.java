package br.com.mascenadev.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidade que representa um item pertencente a um {@link Pedido}.
 * <p>
 * Cada item está associado a um {@link Produto}, possui uma quantidade
 * e o preço unitário registrado no momento da compra.
 * A entidade é mapeada para a tabela "itens_pedido" no banco de dados.
 * </p>
 *
 * @author Gilberto Dev
 */
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    /**
     * Identificador único do item (chave primária).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Produto relacionado ao item do pedido.
     * Este campo é obrigatório.
     */
    @NotNull(message = "Produto é obrigatório")
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    /**
     * Pedido ao qual o item pertence.
     * Este campo é obrigatório.
     */
    @NotNull(message = "Pedido é obrigatório")
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    /**
     * Quantidade do produto incluída no pedido.
     * Deve ser pelo menos 1.
     */
    @NotNull(message = "Quantidade é obrigatório")
    @Min(value = 1, message = "Quantidade deve ser pelo menos 1")
    private Integer quantidade;

    /**
     * Preço unitário do produto no momento do pedido.
     * Deve ser maior que zero e com até duas casas decimais.
     */
    @NotNull(message = "Preço unitário é o obrigatório")
    @DecimalMin(value = "0.01", message = "Preço unitário deve ser maior que zero")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    /**
     * Construtor padrão.
     */
    public ItemPedido() {
    }

    /**
     * Construtor completo.
     *
     * @param produto       produto relacionado ao item
     * @param pedido        pedido ao qual o item pertence
     * @param quantidade    quantidade do produto
     * @param precoUnitario preço unitário do produto
     */
    public ItemPedido(Produto produto, Pedido pedido, Integer quantidade, BigDecimal precoUnitario) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    /**
     * Define o produto associado ao item do pedido.
     *
     * @param produto produto a ser associado
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Define o pedido ao qual este item pertence.
     *
     * @param pedido pedido a ser associado
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade de produtos neste item.
     *
     * @param quantidade valor inteiro maior que zero
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Define o preço unitário do produto no momento da compra.
     *
     * @param precoUnitario valor monetário maior que zero
     */
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * Calcula o valor subtotal deste item, multiplicando
     * a quantidade pelo preço unitário.
     *
     * @return valor total do item
     */
    public BigDecimal getSubTotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    /**
     * Compara dois itens de pedido com base no ID.
     *
     * @param o objeto a ser comparado
     * @return {@code true} se os IDs forem iguais
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Retorna o hash code baseado no ID do item.
     *
     * @return valor de hash code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Retorna uma representação textual do item do pedido.
     *
     * @return string com os dados do item
     */
    @Override
    public String toString() {
        return "ItemPedido{" +
               "id=" + id +
               ", produto=" + produto +
               ", pedido=" + pedido +
               ", quantidade=" + quantidade +
               ", precoUnitario=" + precoUnitario +
               '}';
    }
}
