package br.com.fiap.beautymanagerapi.entities;

import br.com.fiap.beautymanagerapi.enums.TipoEstabelecimento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Classe para representar os estabelecimentos
 */
@Entity
@Data
@Table(name = "tb_estabelecimento")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class EstabelecimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    private LocalTime horarioDeAbertura;
    private LocalTime horarioDeFechamento;
    @Enumerated(EnumType.STRING)
    private TipoEstabelecimento tipoEstabelecimento;
    @OneToOne(mappedBy = "estabelecimento", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private EnderecoEntity endereco;

    @ElementCollection
    private List<String> fotos;

    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ProfissionalEntity> profissionaisDisponiveis;

    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ServicoEntity> servicosOferecidos;

    @OneToOne(mappedBy = "estabelecimento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private LocalizacaoEntity localizacao;

}
