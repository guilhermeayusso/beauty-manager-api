package br.com.fiap.beautymanagerapi.projection;

import br.com.fiap.beautymanagerapi.enums.TipoEstabelecimento;

import java.time.LocalTime;

public interface EstabelecimentoProjection {
    String getNome();
    LocalTime getHorarioDeAbertura();
    LocalTime getHorarioDeFechamento();
    TipoEstabelecimento getTipoEstabelecimento();
}
