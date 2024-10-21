package br.com.fiap.beautymanagerapi.usecase.estabelecimento;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.exception.EstabelecimentoNotFoundException;
import br.com.fiap.beautymanagerapi.projection.EstabelecimentoProjection;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BuscarEstabelecimentoUseCase {

    private final EstabelecimentoRepository estabelecimentoRepository;

    public BuscarEstabelecimentoUseCase(EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Transactional(readOnly = true)
    public EstabelecimentoOutputDTO buscarEstabelecimentoPorId(Long idEstabelecimento) {

        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.buscarPorId(idEstabelecimento);
        EstabelecimentoEntity estabelecimento =  estabelecimentoOptional.orElseThrow(() -> {
            log.error(MensagemConstantes.ESTABELECIMENTO_NAO_ENCONTRADO, idEstabelecimento);
            return new EstabelecimentoNotFoundException(MensagemConstantes.EXCEPTION_ESTABELECIMENTO_NAO_ENCONTRADO);
        });

        return new EstabelecimentoOutputDTO(
                estabelecimento.getId(),
                estabelecimento.getNome());
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoOutputDTO> buscarEstabelecimentosPorNome(String nome) {
        List<EstabelecimentoEntity> estabelecimentos = estabelecimentoRepository.buscarPorNome(nome);

        return estabelecimentos.stream()
                .map(estabelecimento -> new EstabelecimentoOutputDTO(
                        estabelecimento.getId(),
                        estabelecimento.getNome()))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<EstabelecimentoOutputDTO> buscarEstabelecimentosPorTipo(String tipoEstabelecimento) {
        List<EstabelecimentoEntity> estabelecimentos = estabelecimentoRepository.buscarPorTipoDoEstabelecimento(tipoEstabelecimento);

        return estabelecimentos.stream()
                .map(estabelecimento -> new EstabelecimentoOutputDTO(
                        estabelecimento.getId(),
                        estabelecimento.getNome()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoOutputDTO> buscarEstabelecimentosPorCidade(String cidade) {
        List<EstabelecimentoEntity> estabelecimentos = estabelecimentoRepository.buscarPorCidade(cidade);

        return estabelecimentos.stream()
                .map(estabelecimento -> new EstabelecimentoOutputDTO(
                        estabelecimento.getId(),
                        estabelecimento.getNome()))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<EstabelecimentoOutputDTO> buscarPorStatusDoProfissional(StatusProfissional status) {
        List<EstabelecimentoEntity> estabelecimentos = estabelecimentoRepository.buscarPorStatusDoProfissional(status);

        return estabelecimentos.stream()
                .map(estabelecimento -> new EstabelecimentoOutputDTO(
                        estabelecimento.getId(),
                        estabelecimento.getNome()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoOutputDTO> buscarPorServicosOferecidos(String servico) {
        List<EstabelecimentoEntity> estabelecimentos = estabelecimentoRepository.buscarPorServicoOferecido(servico);

        return estabelecimentos.stream()
                .map(estabelecimento -> new EstabelecimentoOutputDTO(
                        estabelecimento.getId(),
                        estabelecimento.getNome()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<EstabelecimentoProjection> buscarTodosEstabelecimentos(Pageable pageable) {
        return estabelecimentoRepository.findAllPageable(pageable);
    }

}
