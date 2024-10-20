package br.com.fiap.beautymanagerapi.entities;

import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_agendamento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @ToString.Exclude
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    @ToString.Exclude
    private ProfissionalEntity profissional;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    @ToString.Exclude
    private ServicoEntity servico;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    @ToString.Exclude
    private EstabelecimentoEntity estabelecimento;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

}
