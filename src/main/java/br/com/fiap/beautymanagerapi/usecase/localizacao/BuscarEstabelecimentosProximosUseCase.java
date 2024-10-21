package br.com.fiap.beautymanagerapi.usecase.localizacao;

import br.com.fiap.beautymanagerapi.adapters.gateways.localizacao.LocalizacaoRepository;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoOutputDTO;
import br.com.fiap.beautymanagerapi.records.localizacao.LocalizacaoOutputDTO;
import br.com.fiap.beautymanagerapi.entities.LocalizacaoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarEstabelecimentosProximosUseCase {

    private final LocalizacaoRepository localizacaoRepository;

    public BuscarEstabelecimentosProximosUseCase(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoOutputDTO> buscarEstabelecimentosProximos(double latitude, double longitude, double radius) {

        List<LocalizacaoEntity> localizacoes = localizacaoRepository.buscarEstabelecimentosProximos(latitude, longitude, radius);

        return localizacoes.stream()
                .map(localizacao -> new EstabelecimentoOutputDTO(
                        localizacao.getEstabelecimento().getId(),
                        localizacao.getEstabelecimento().getNome()
                ))
                .collect(Collectors.toList());
    }
}

