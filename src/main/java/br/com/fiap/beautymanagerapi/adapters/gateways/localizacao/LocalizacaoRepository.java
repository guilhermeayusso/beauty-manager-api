package br.com.fiap.beautymanagerapi.adapters.gateways.localizacao;

import br.com.fiap.beautymanagerapi.entities.LocalizacaoEntity;

import java.util.List;

public interface LocalizacaoRepository {

    List<LocalizacaoEntity> buscarEstabelecimentosProximos(double latitude, double longitude, double radius);
    LocalizacaoEntity saveLocalizacao(LocalizacaoEntity localizacao);
}
