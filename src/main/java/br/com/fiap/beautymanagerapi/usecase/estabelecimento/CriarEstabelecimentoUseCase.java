package br.com.fiap.beautymanagerapi.usecase.estabelecimento;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.entities.EnderecoEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoInputDTO;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoOutputDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarEstabelecimentoUseCase {

    private final EstabelecimentoRepository estabelecimentoRepository;

    public CriarEstabelecimentoUseCase(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Transactional
    public EstabelecimentoOutputDTO criarEstabelecimento(EstabelecimentoInputDTO estabelecimentoInputDTO){


        EstabelecimentoEntity estabelecimento = EstabelecimentoEntity.builder()
                .nome(estabelecimentoInputDTO.nome())
                .tipoEstabelecimento(estabelecimentoInputDTO.tipoEstabelecimento())
                .fotos(estabelecimentoInputDTO.fotos())
                .horarioDeFechamento(estabelecimentoInputDTO.horarioDeFechamento())
                .horarioDeAbertura(estabelecimentoInputDTO.horarioDeAbertura())
                .build();

        EstabelecimentoEntity estabelecimentoSaved = estabelecimentoRepository.criarEstabelecimento(estabelecimento);



        return new EstabelecimentoOutputDTO(estabelecimentoSaved.getId(),estabelecimentoSaved.getNome());
    }
}
