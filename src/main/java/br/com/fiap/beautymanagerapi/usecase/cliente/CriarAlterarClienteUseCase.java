package br.com.fiap.beautymanagerapi.usecase.cliente;

import br.com.fiap.beautymanagerapi.adapters.gateways.cliente.ClienteRepository;
import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import br.com.fiap.beautymanagerapi.exception.UniqueViolationException;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteInputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarAlterarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public CriarAlterarClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteOutputDTO criarCliente(ClienteInputDTO clienteDTO){
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .nome(clienteDTO.nome())
                .email(clienteDTO.email())
                .telefone(clienteDTO.telefone())
                .build();

        try {
            ClienteEntity clienteSaved = clienteRepository.save(clienteEntity);
            return new ClienteOutputDTO(clienteSaved.getId(),clienteSaved.getNome(),clienteSaved.getEmail(), clienteSaved.getTelefone());
        }catch (DataIntegrityViolationException e){
            throw new UniqueViolationException("O e-mail ou telefone deste cliente já possue cadastro. ");
        }

    }

    public ClienteOutputDTO atualizarCliente(ClienteInputDTO clienteDTO, Long id){
       ClienteEntity cliente = EntityValidatorUtil.validaCliente(id,clienteRepository);

        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setEmail(clienteDTO.email());

        try {
            ClienteEntity clienteSaved = clienteRepository.save(cliente);
            return new ClienteOutputDTO(clienteSaved.getId(),clienteSaved.getNome(),clienteSaved.getEmail(), clienteSaved.getTelefone());
        }catch (DataIntegrityViolationException e){
            throw new UniqueViolationException("O e-mail ou telefone deste cliente já possue cadastro. ");
        }

    }
}
