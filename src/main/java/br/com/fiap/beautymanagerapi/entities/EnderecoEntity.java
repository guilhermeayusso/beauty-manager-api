package br.com.fiap.beautymanagerapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

/**
 * Classe para representar a Entidade Endereco
 */
@Entity
@Data
@Table(name = "tb_endereco")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    @OneToOne
    @JoinColumn(name = "estabelecimento_id", unique = true)
    @ToString.Exclude
    private EstabelecimentoEntity estabelecimento;

}

