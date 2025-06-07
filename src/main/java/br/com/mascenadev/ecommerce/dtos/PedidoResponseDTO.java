package br.com.mascenadev.ecommerce.dtos;

import br.com.mascenadev.ecommerce.entities.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO utilizado para representar os dados de um pedido em respostas da API.
 * <p>
 * Contém informações do cliente, data de realização, status, itens do pedido e o valor total.
 * </p>
 *
 * <p>Ideal para utilização em respostas de endpoints RESTful.</p>
 *
 * @author Gilberto Dev
 */
public class PedidoResponseDTO {

    private final Long id;
    private final ClienteResponseDTO cliente;
    private final LocalDate dataPedido;
    private final StatusPedido status;
    private final List<ItemPedidoResponseDTO> itens;
    private final BigDecimal total;

    /**
     * Construtor que inicializa todos os campos do DTO.
     *
     * @param id         ID do pedido
     * @param cliente    dados do cliente
     * @param dataPedido data da realização do pedido
     * @param status     status atual do pedido
     * @param itens      lista de itens do pedido
     * @param total      valor total do pedido
     */
    public PedidoResponseDTO(Long id, ClienteResponseDTO cliente, LocalDate dataPedido, StatusPedido status,
                             List<ItemPedidoResponseDTO> itens, BigDecimal total) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.status = status;
        this.itens = itens != null ? itens : new ArrayList<>();
        this.total = total != null ? total : BigDecimal.ZERO;
    }

    /**
     * @return o ID do pedido
     */
    public Long getId() {
        return id;
    }

    /**
     * @return cliente associado ao pedido
     */

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    /**
     * @return data em que o pedido foi realizado
     */
    public LocalDate getDataPedido() {
        return dataPedido;
    }

    /**
     * @return status atual do pedido
     */
    public StatusPedido getStatus() {
        return status;
    }

    /**
     * @return lista de itens do pedido
     */
    public List<ItemPedidoResponseDTO> getItens() {
        return itens != null ? itens : new ArrayList<>();
    }

    /**
     * @return valor total do pedido
     */
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoResponseDTO that = (PedidoResponseDTO) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        String itensStr = itens.isEmpty() ? "[vazio]" :
                itens.stream()
                        .limit(5)  // Limita a exibição a 5 itens
                        .map(ItemPedidoResponseDTO::toString)  // Chama o toString de cada item
                        .reduce((a, b) -> a + ", " + b)  // Junta os itens em uma string
                        .orElse("[vazio]");
        return "PedidoResponseDTO{" +
               "id=" + id +
               ", cliente=" + cliente +
               ", dataPedido=" + dataPedido +
               ", status=" + status +
               ", itens=" + itensStr +
               ", total=" + total +
               '}';
    }

}
