package br.com.mascenadev.ecommerce.dtos;

import br.com.mascenadev.ecommerce.entities.Cliente;
import br.com.mascenadev.ecommerce.entities.ItemPedido;
import br.com.mascenadev.ecommerce.entities.Pedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;

/**
 * DTO utilizado para representar os dados recebidos na criação de um pedido.
 * Contém o ID do cliente e a lista de itens do pedido.
 * <p>
 * Validações são aplicadas para garantir integridade dos dados.
 *
 * @author Gilberto Dev
 */
public class PedidoRequestDTO {

    /**
     * ID do cliente que realizou o pedido.
     */
    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    /**
     * Lista de itens que compõem o pedido.
     */
    @NotNull(message = "Itens do pedido são obrigatórios")
    @Size(min = 1, message = "O pedido deve conter pelo menos um item")
    private List<ItemPedidoRequestDTO> itens;

    /**
     * Construtor padão
     */
    public PedidoRequestDTO() {
    }

    /**
     * Obtém o ID do cliente.
     *
     * @return ID do cliente
     */
    public Long getClienteId() {
        return clienteId;
    }

    /**
     * Obtém a lista de itens do pedido.
     *
     * @return lista de itens do pedido
     */
    public List<ItemPedidoRequestDTO> getItens() {
        return itens;
    }

    public Pedido toEntity(Cliente cliente, List<ItemPedido> itensConvertidos) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(new HashSet<>(itensConvertidos));
        return pedido;
    }

    @Override
    public String toString() {
        return "PedidoRequestDTO{" +
               "clienteId=" + clienteId +
               ", itens=" + itens +
               '}';
    }
}
