package br.com.fiap.beautymanagerapi.adapters.gateways.cliente;

import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private static final Logger logger = LoggerFactory.getLogger(ClienteRepositoryImpl.class);

    private final JpaClienteRepository jpaClienteRepository;

    public ClienteRepositoryImpl(JpaClienteRepository jpaClienteRepository) {
        this.jpaClienteRepository = jpaClienteRepository;
    }

    @Override
    public ClienteEntity save(ClienteEntity cliente) {
        try {
            var clienteSaved = jpaClienteRepository.save(cliente);
            logger.info("Cliente salvo com sucesso: {}", clienteSaved);
            return clienteSaved;

        } catch (DataIntegrityViolationException e) {
            logger.error("Erro de integridade ao salvar o cliente: {}, motivo: {}", cliente, e.getMessage());
            throw e;

        } catch (EntityExistsException e) {
            logger.error("Entidade já existe no banco de dados: {}, motivo: {}", cliente, e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar o cliente: {}, motivo: {}", cliente, e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<ClienteEntity> buscarClientePorId(Long id) {
        return jpaClienteRepository.findById(id);
    }
}
