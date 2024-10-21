package br.com.fiap.beautymanagerapi.records.avaliacao;

import java.io.Serializable;

public record AvaliacaoResponseModel(
        String autor,
        Double nota,
        String comentario
) implements Serializable {
}
