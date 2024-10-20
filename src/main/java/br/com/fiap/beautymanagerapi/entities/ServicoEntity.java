package br.com.fiap.beautymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "tb_servico")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;

    @ManyToMany
    @JoinTable(
            name = "servico_profissionais",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "profissional_id"))
    private List<ProfissionalEntity> profissionaisHabilitados;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    @ToString.Exclude
    private EstabelecimentoEntity estabelecimento;

    // Adiciona a relação com agendamentos
    @OneToMany(mappedBy = "servico", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AgendamentoEntity> agendamentos;

}



