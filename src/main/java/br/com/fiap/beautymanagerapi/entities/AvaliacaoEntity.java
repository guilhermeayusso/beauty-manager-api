package br.com.fiap.beautymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe para representar as avaliações de cliente
 * para os estabelecimentos.
 */
@Entity
@Data
@Table(name = "tb_avaliacao")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String autor;
    private Double nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estabelecimento_id")
    @ToString.Exclude
    private EstabelecimentoEntity estabelecimentoEntity;

}
