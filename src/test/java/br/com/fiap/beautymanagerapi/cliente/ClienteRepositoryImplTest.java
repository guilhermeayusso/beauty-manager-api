package br.com.fiap.beautymanagerapi.cliente;

import br.com.fiap.beautymanagerapi.adapters.gateways.cliente.ClienteRepositoryImpl;
import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import(ClienteRepositoryImpl.class)
public class ClienteRepositoryImplTest {

    @Autowired
    private ClienteRepositoryImpl clienteRepository;

    @Test
    public void testSaveCliente(){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome("Hildevan Costa");
        clienteEntity.setEmail("hildevan@gmail.com");
        clienteEntity.setTelefone("11988182345");

        ClienteEntity clienteEntitySaved = clienteRepository.save(clienteEntity);

        assertThat(clienteEntitySaved).isNotNull();
        assertThat(clienteEntitySaved.getNome()).isEqualTo("Hildevan Costa");
        assertThat(clienteEntitySaved.getEmail()).isEqualTo("hildevan@gmail.com");
        assertThat(clienteEntitySaved.getTelefone()).isEqualTo("11988182345");

    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testSaveCliente_DataIntegrityViolationException() {

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome("João Silva");
        clienteEntity.setEmail("joao.silva@example.com");
        clienteEntity.setTelefone("11988182345");

        assertThrows(DataIntegrityViolationException.class, () -> {
            clienteRepository.save(clienteEntity);
        });
    }
}
