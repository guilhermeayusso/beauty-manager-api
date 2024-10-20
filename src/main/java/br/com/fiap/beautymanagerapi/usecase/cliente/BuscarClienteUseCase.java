package br.com.fiap.beautymanagerapi.usecase.cliente;

import br.com.fiap.beautymanagerapi.adapters.gateways.cliente.ClienteRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import br.com.fiap.beautymanagerapi.exception.ClienteNotFoundException;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BuscarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public BuscarClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteOutputDTO buscarCliente(Long id) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.buscarClientePorId(id);

        ClienteEntity cliente= clienteOptional.orElseThrow(() -> {
                    log.error(MensagemConstantes.CLIENTE_NAO_ENCONTRADO, id);
                    return new ClienteNotFoundException(MensagemConstantes.EXCEPTION_CLIENTE_NAO_ENCONTRADO);
                }
        );

        return new ClienteOutputDTO(cliente.getId(),cliente.getNome(),cliente.getEmail(),cliente.getTelefone());

    }
}
