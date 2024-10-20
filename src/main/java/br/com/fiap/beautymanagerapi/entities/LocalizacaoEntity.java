package br.com.fiap.beautymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "tb_localizacao")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double latitude;
    private double longitude;

    @OneToOne
    @JoinColumn(name = "estabelecimento_id")
    @ToString.Exclude
    private EstabelecimentoEntity estabelecimento;

}
