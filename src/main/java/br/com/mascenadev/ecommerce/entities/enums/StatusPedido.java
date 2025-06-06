package br.com.mascenadev.ecommerce.entities.enums;

/**
 * Enum que representa os possíveis status de um {@link br.com.mascenadev.ecommerce.entities.Pedido}.
 * <p>
 * Esses status indicam a situação atual do pedido no sistema de e-commerce.
 * A transição entre os status pode variar conforme as regras de negócio.
 * </p>
 *
 * <ul>
 *   <li>{@code PENDENTE} – Pedido criado, mas ainda não processado.</li>
 *   <li>{@code AGUARDANDO_PAGAMENTO} – Aguardando confirmação do pagamento.</li>
 *   <li>{@code APROVADO} – Pagamento aprovado e pedido pronto para seguir.</li>
 *   <li>{@code PAGO} – Pagamento foi efetuado com sucesso.</li>
 *   <li>{@code ENVIADO} – Pedido despachado para entrega.</li>
 *   <li>{@code ENTREGUE} – Pedido entregue ao cliente.</li>
 *   <li>{@code CANCELADO} – Pedido cancelado por alguma razão.</li>
 * </ul>
 *
 * @author Gilberto Dev
 */
public enum StatusPedido {
    PENDENTE,
    AGUARDANDO_PAGAMENTO,
    APROVADO,
    PAGO,
    ENVIADO,
    ENTREGUE,
    CANCELADO;

    /**
     * Verifica se a transição de status atual para o novo status é válida.
     *
     * @param novoStatus novo status desejado
     * @return true se a transição é permitida, false caso contrário
     */
    public boolean podeTransicionarPara(StatusPedido novoStatus) {
        return switch (this) {
            case PENDENTE -> novoStatus == AGUARDANDO_PAGAMENTO || novoStatus == CANCELADO;
            case AGUARDANDO_PAGAMENTO -> novoStatus == APROVADO || novoStatus == CANCELADO;
            case APROVADO -> novoStatus == PAGO || novoStatus == CANCELADO;
            case PAGO -> novoStatus == ENVIADO || novoStatus == CANCELADO;
            case ENVIADO -> novoStatus == ENTREGUE;
            default -> false; // ENTREGUE e CANCELADO não permitem transições
        };
    }
}

