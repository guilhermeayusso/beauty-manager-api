package br.com.fiap.beautymanagerapi.usecase.servico;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepository;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.records.servico.ServicoInputDTO;
import br.com.fiap.beautymanagerapi.records.servico.ServicoOutputDTO;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CriarServicoUseCase {

    private final ServicoRepository servicoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProfissionalRepository profissionalRepository;

    public CriarServicoUseCase(ServicoRepository servicoRepository,
                               EstabelecimentoRepository estabelecimentoRepository,
                               ProfissionalRepository profissionalRepository) {
        this.servicoRepository = servicoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @Transactional
    public ServicoOutputDTO criarServico(ServicoInputDTO servicoInputDTO) {

        EstabelecimentoEntity estabelecimento = EntityValidatorUtil.validaEstabelecimento(servicoInputDTO.estabelecimentoId(), estabelecimentoRepository);
        List<ProfissionalEntity> profissionais = EntityValidatorUtil.validaListaDeProfissionais(servicoInputDTO.profissionaisIds(), profissionalRepository);

        // Construir a entidade ServicoEntity usando o Builder
        ServicoEntity servicoEntity = ServicoEntity.builder()
                .nome(servicoInputDTO.nome())
                .descricao(servicoInputDTO.descricao())
                .preco(servicoInputDTO.preco())
                .estabelecimento(estabelecimento)
                .profissionaisHabilitados(profissionais)
                .build();

        // Salvar o serviço no repositório
        ServicoEntity servicoSalvo = servicoRepository.saveServico(servicoEntity);

        // Retornar o output DTO
        return new ServicoOutputDTO(
                servicoSalvo.getId(),
                servicoSalvo.getNome(),
                servicoSalvo.getDescricao(),
                servicoSalvo.getPreco()
        );
    }
}
