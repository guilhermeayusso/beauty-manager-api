package br.com.fiap.beautymanagerapi.entities;

import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@Table(name = "tb_profissional")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProfissionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    private String especialidades;
    @Enumerated(EnumType.STRING)
    private StatusProfissional statusProfissional;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    @ToString.Exclude
    private EstabelecimentoEntity estabelecimento;

}

