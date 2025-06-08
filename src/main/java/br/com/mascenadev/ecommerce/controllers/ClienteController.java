package br.com.mascenadev.ecommerce.controllers;

import br.com.mascenadev.ecommerce.dtos.ClienteRequestDTO;
import br.com.mascenadev.ecommerce.dtos.ClienteResponseDTO;
import br.com.mascenadev.ecommerce.entities.Cliente;
import br.com.mascenadev.ecommerce.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller responsável por gerenciar os clientes via API REST.
 * Disponibiliza operações de CRUD.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Cria um novo cliente.
     *
     * @param dto dados do cliente
     * @return cliente criado
     */
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) {
        Cliente cliente = clienteService.salvar(dto.toEntity());
        ClienteResponseDTO response = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
        URI location = URI.create("/api/clientes/" + cliente.getId());
        return ResponseEntity.created(location).body(response);
    }

    /**
     * Retorna um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return cliente encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        ClienteResponseDTO response = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
        return ResponseEntity.ok(response);
    }

    /**
     * Lista todos os clientes.
     *
     * @return lista de clientes
     */
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        List<ClienteResponseDTO> response = clientes.stream()
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNome(), c.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza um cliente existente.
     *
     * @param id  ID do cliente
     * @param dto dados atualizados
     * @return cliente atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody ClienteRequestDTO dto) {
        Cliente atualizado = clienteService.atualizar(id, dto.toEntity());
        ClienteResponseDTO response = new ClienteResponseDTO(atualizado.getId(), atualizado.getNome(), atualizado.getEmail());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return resposta sem conteúdo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
