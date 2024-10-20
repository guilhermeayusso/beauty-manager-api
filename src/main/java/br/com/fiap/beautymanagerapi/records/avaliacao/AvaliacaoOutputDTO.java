package br.com.fiap.beautymanagerapi.records.avaliacao;

import java.io.Serializable;

public record AvaliacaoOutputDTO(
        String autor,
        Double nota,
        String comentario
) implements Serializable {
}
