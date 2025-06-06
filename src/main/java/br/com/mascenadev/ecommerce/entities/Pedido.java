package br.com.mascenadev.ecommerce.entities;

import br.com.mascenadev.ecommerce.entities.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade que representa um pedido realizado por um cliente no sistema de e-commerce.
 * <p>
 * Cada pedido contém informações como data, status, cliente associado e os itens comprados.
 * A entidade é mapeada para a tabela "pedidos" no banco de dados.
 * Um pedido pode conter múltiplos {@link ItemPedido}.
 *
 * @author Gilberto Dev
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    /**
     * Identificador único do pedido (chave primária).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cliente que realizou o pedido.
     * Esta relação é obrigatória.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /**
     * Data em que o pedido foi realizado.
     */
    @NotNull(message = "Data do pedido é obrigatória")
    private LocalDate dataPedido;

    /**
     * Status atual do pedido (ex: AGUARDANDO_PAGAMENTO, ENVIADO, ENTREGUE).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    /**
     * Conjunto de itens que compõem o pedido.
     * Cada item está associado a um produto e a uma quantidade.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "O pedido deve conter pelo menos 1 item")
    private Set<ItemPedido> itens = new HashSet<>();

    /**
     * Construtor padrão que inicializa a data do pedido com a data atual
     * e o status como {@code StatusPedido.AGUARDADO_PAGAMENTO}.
     */
    public Pedido() {
        this.dataPedido = LocalDate.now();
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    /**
     * Construtor completo para criar um pedido com todos os dados necessários.
     *
     * @param cliente    cliente que fez o pedido
     * @param dataPedido data em que o pedido foi realizado
     * @param status     status atual do pedido
     * @param itens      conjunto de itens incluídos no pedido
     */
    public Pedido(Cliente cliente, LocalDate dataPedido, StatusPedido status, Set<ItemPedido> itens) {
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.status = status;
        this.itens = itens;
    }


    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente associado ao pedido.
     *
     * @param cliente o cliente a ser associado
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    /**
     * Define a data em que o pedido foi realizado.
     *
     * @param dataPedido a nova data do pedido
     */
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    /**
     * Define o status atual do pedido.
     *
     * @param status novo status do pedido
     */
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    /**
     * Define o conjunto de itens do pedido.
     *
     * @param itens itens a serem atribuídos ao pedido
     */
    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    /**
     * Calcula o valor total do pedido somando os subtotais de cada item.
     *
     * @return valor total do pedido
     */
    @Transient
    public BigDecimal getTotal() {
        return itens.stream()
                .map(ItemPedido::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Adiciona um item ao pedido e define a referência inversa do pedido no item.
     *
     * @param item o item a ser adicionado
     */
    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
    }

    /**
     * Remove um item do pedido e desvincula sua referência ao pedido.
     *
     * @param item o item a ser removido
     */
    public void removerItem(ItemPedido item) {
        this.itens.remove(item);
        item.setPedido(null);
    }

    /**
     * Compara dois pedidos com base no ID.
     *
     * @param o objeto a ser comparado
     * @return {@code true} se os IDs forem iguais
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    /**
     * Retorna o hash code baseado no ID do pedido.
     *
     * @return valor de hash code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Retorna uma representação textual do pedido.
     *
     * @return string com os dados do pedido
     */
    @Override
    public String toString() {
        return "Pedido{" +
               "id=" + id +
               ", cliente=" + cliente +
               ", dataPedido=" + dataPedido +
               ", status=" + status +
               ", itens=" + itens +
               '}';
    }
}
