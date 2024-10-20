package br.com.fiap.beautymanagerapi.records.avaliacao;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AvaliacaoInputDTO(
        String autor,
        Double nota,
        String comentario,
        LocalDateTime dataAvaliacao,
        Long estabelecimento_id
) implements Serializable {
}
