package br.com.fiap.beautymanagerapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Table(name = "tb_cliente")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "telefone", nullable = false,unique = true, length = 17)
    private String telefone;

}
