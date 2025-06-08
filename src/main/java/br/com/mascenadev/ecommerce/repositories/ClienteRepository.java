package br.com.mascenadev.ecommerce.repositories;

import br.com.mascenadev.ecommerce.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
