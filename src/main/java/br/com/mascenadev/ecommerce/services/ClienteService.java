package br.com.mascenadev.ecommerce.services;

import br.com.mascenadev.ecommerce.entities.Cliente;
import br.com.mascenadev.ecommerce.exceptions.ClienteNaoEncontradoException;
import br.com.mascenadev.ecommerce.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável pela lógica de negócios relacionada à entidade Cliente.
 */
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Salva um novo cliente no banco de dados.
     *
     * @param cliente cliente a ser salvo
     * @return cliente salvo
     */
    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return cliente encontrado
     * @throws ClienteNaoEncontradoException se não encontrado
     */
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + id));
    }

    /**
     * Retorna todos os clientes cadastrados.
     *
     * @return lista de clientes
     */
    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Atualiza os dados de um cliente existente.
     *
     * @param id      ID do cliente a ser atualizado
     * @param cliente dados novos
     * @return cliente atualizado
     * @throws ClienteNaoEncontradoException se não encontrado
     */
    @Transactional
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente existente = buscarPorId(id);
        existente.setNome(cliente.getNome());
        existente.setEmail(cliente.getEmail());
        return clienteRepository.save(existente);
    }

    /**
     * Remove um cliente pelo ID.
     *
     * @param id ID do cliente
     * @throws ClienteNaoEncontradoException se não encontrado
     */
    @Transactional
    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}

