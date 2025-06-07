package br.com.mascenadev.ecommerce.dtos;

import br.com.mascenadev.ecommerce.entities.ItemPedido;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * DTO utilizado para representar os dados recebidos na criação de um item de pedido.
 * Contém o ID do produto, a quantidade e o preço unitário.
 * <p>
 * Utilizado na requisição de criação de pedidos na API.
 *
 * @author Gilberto Dev
 */
public class ItemPedidoRequestDTO {

    @NotNull(message = "Produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser pelo menos 1")
    private Integer quantidade;

    @NotNull(message = "Preço unitário é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço unitário deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Preço unitário deve ter até 2 casas decimais")
    private BigDecimal precoUnitario;

    public ItemPedidoRequestDTO() {
    }

    public ItemPedidoRequestDTO(Long produtoId, Integer quantidade, BigDecimal precoUnitario) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    /**
     * Retorna o ID do produto associado ao item do pedido.
     *
     * @return ID do produto
     */
    public Long getProdutoId() {
        return produtoId;
    }

    /**
     * Define o ID do produto associado ao item do pedido.
     *
     * @param produtoId ID do produto
     */
    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    /**
     * Retorna a quantidade do produto no item do pedido.
     *
     * @return quantidade do produto
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade do produto no item do pedido.
     *
     * @param quantidade quantidade do produto
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o preço unitário do produto no item do pedido.
     *
     * @return preço unitário do produto
     */
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Define o preço unitário do produto no item do pedido.
     *
     * @param precoUnitario preço unitário do produto
     */
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public ItemPedido toItemPedido() {
        ItemPedido itemPedido = new ItemPedido();
        BeanUtils.copyProperties(this, itemPedido);
        return itemPedido;
    }

    @Override
    public String toString() {
        return "ItemPedidoRequestDTO{" +
               "produtoId=" + produtoId +
               ", quantidade=" + quantidade +
               ", precoUnitario=" + precoUnitario +
               '}';
    }
}
