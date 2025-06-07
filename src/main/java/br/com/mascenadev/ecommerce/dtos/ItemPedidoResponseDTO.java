package br.com.mascenadev.ecommerce.dtos;

import br.com.mascenadev.ecommerce.entities.ItemPedido;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO utilizado para exibir os dados de um item de pedido.
 *
 * @author Gilberto Dev
 */
public class ItemPedidoResponseDTO {

    private final Long id;
    private final ProdutoResponseDTO produto;
    private final Integer quantidade;
    private final BigDecimal precoUnitario;
    private final BigDecimal subTotal;

    public ItemPedidoResponseDTO(ItemPedido entity) {
        this.id = entity.getId();
        this.produto = new ProdutoResponseDTO(entity.getProduto());
        this.quantidade = entity.getQuantidade();
        this.precoUnitario = entity.getPrecoUnitario();
        this.subTotal = entity.getSubTotal();
    }

    /**
     * @return ID do item do pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return dados do produto associado.
     */
    public ProdutoResponseDTO getProduto() {
        return produto;
    }

    /**
     * @return quantidade de produtos associados.
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @return preço unitário do produto associado.
     */
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * @return subtotal dos produtos associados.
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoResponseDTO that = (ItemPedidoResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemPedidoResponseDTO{" +
               "id=" + id +
               ", produtoId=" + produto.getId() +
               ", quantidade=" + quantidade +
               ", precoUnitario=" + precoUnitario +
               ", subTotal=" + subTotal +
               '}';
    }
}
