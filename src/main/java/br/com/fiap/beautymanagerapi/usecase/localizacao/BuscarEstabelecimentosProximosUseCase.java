package br.com.fiap.beautymanagerapi.usecase.localizacao;

import br.com.fiap.beautymanagerapi.adapters.gateways.localizacao.LocalizacaoRepository;
import br.com.fiap.beautymanagerapi.records.localizacao.LocalizacaoOutputDTO;
import br.com.fiap.beautymanagerapi.entities.LocalizacaoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarEstabelecimentosProximosUseCase {

    private final LocalizacaoRepository localizacaoRepository;

    public BuscarEstabelecimentosProximosUseCase(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    public List<LocalizacaoOutputDTO> buscarEstabelecimentosProximos(double latitude, double longitude, double radius) {

        List<LocalizacaoEntity> localizacoes = localizacaoRepository.buscarEstabelecimentosProximos(latitude, longitude, radius);

        return localizacoes.stream()
                .map(localizacao -> new LocalizacaoOutputDTO(
                        localizacao.getLatitude(),
                        localizacao.getLongitude(),
                        localizacao.getEstabelecimento().getNome()
                ))
                .collect(Collectors.toList());
    }
}

