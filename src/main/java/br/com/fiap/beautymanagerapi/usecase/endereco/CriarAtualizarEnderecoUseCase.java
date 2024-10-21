package br.com.fiap.beautymanagerapi.usecase.endereco;

import br.com.fiap.beautymanagerapi.adapters.gateways.endereco.EnderecoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.EnderecoEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.exception.ConflictException;
import br.com.fiap.beautymanagerapi.exception.EnderecoNotFoundException;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoInputDTO;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoOutputDTO;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CriarAtualizarEnderecoUseCase {

    private final EnderecoRepository enderecoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public CriarAtualizarEnderecoUseCase(EnderecoRepository enderecoRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Transactional
    public EnderecoOutputDTO criarEndereco(EnderecoInputDTO enderecoInputDTO) {

        EstabelecimentoEntity estabelecimento = EntityValidatorUtil.validaEstabelecimento(enderecoInputDTO.estabelecimentoId(), estabelecimentoRepository);

        // Verifica se o estabelecimento já tem um endereço cadastrado
        Optional<EnderecoEntity> enderecoExistente = enderecoRepository.findByEstabelecimento_Id(estabelecimento.getId());
        if (enderecoExistente.isPresent()) {
            throw new ConflictException(MensagemConstantes.ENDERECO_JA_CADASTRADO);
        }

        // Cria um novo endereço
        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                .cep(enderecoInputDTO.cep())
                .logradouro(enderecoInputDTO.logradouro())
                .numero(enderecoInputDTO.numero())
                .complemento(enderecoInputDTO.complemento())
                .bairro(enderecoInputDTO.bairro())
                .cidade(enderecoInputDTO.cidade())
                .uf(enderecoInputDTO.uf())
                .estabelecimento(estabelecimento)
                .build();

        EnderecoEntity enderecoSalvo = enderecoRepository.salvarEndereco(enderecoEntity);

        return new EnderecoOutputDTO(
                enderecoSalvo.getId(),
                enderecoSalvo.getCep(),
                enderecoSalvo.getLogradouro(),
                enderecoSalvo.getNumero(),
                enderecoSalvo.getComplemento(),
                enderecoSalvo.getBairro(),
                enderecoSalvo.getCidade(),
                enderecoSalvo.getUf()
        );
    }

    @Transactional
    public EnderecoOutputDTO atualizarEndereco(Long enderecoId, EnderecoInputDTO enderecoInputDTO) {

        EnderecoEntity endereco = enderecoRepository.buscarEnderecoPorId(enderecoId)
                .orElseThrow(() -> new EnderecoNotFoundException(MensagemConstantes.ENDERECO_NAO_ENCONTRADO));

        // Atualiza os dados do endereço
        endereco.setCep(enderecoInputDTO.cep());
        endereco.setLogradouro(enderecoInputDTO.logradouro());
        endereco.setNumero(enderecoInputDTO.numero());
        endereco.setComplemento(enderecoInputDTO.complemento());
        endereco.setBairro(enderecoInputDTO.bairro());
        endereco.setCidade(enderecoInputDTO.cidade());
        endereco.setUf(enderecoInputDTO.uf());

        EnderecoEntity enderecoAtualizado = enderecoRepository.salvarEndereco(endereco);

        return new EnderecoOutputDTO(
                enderecoAtualizado.getId(),
                enderecoAtualizado.getCep(),
                enderecoAtualizado.getLogradouro(),
                enderecoAtualizado.getNumero(),
                enderecoAtualizado.getComplemento(),
                enderecoAtualizado.getBairro(),
                enderecoAtualizado.getCidade(),
                enderecoAtualizado.getUf()
        );
    }
}
