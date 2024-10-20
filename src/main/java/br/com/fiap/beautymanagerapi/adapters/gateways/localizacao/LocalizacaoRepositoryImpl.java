package br.com.fiap.beautymanagerapi.adapters.gateways.localizacao;

import br.com.fiap.beautymanagerapi.entities.LocalizacaoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalizacaoRepositoryImpl implements LocalizacaoRepository{

   private final JpaLocalizacaoRepository jpaLocalizacaoRepository;

    public LocalizacaoRepositoryImpl(JpaLocalizacaoRepository jpaLocalizacaoRepository) {
        this.jpaLocalizacaoRepository = jpaLocalizacaoRepository;
    }

    @Override
    public List<LocalizacaoEntity> buscarEstabelecimentosProximos(double latitude, double longitude, double radius) {
        return jpaLocalizacaoRepository.findLocationsWithinRadius(latitude, longitude, radius);
    }

    @Override
    public LocalizacaoEntity saveLocalizacao(LocalizacaoEntity localizacao) {
        return jpaLocalizacaoRepository.save(localizacao);
    }
}
