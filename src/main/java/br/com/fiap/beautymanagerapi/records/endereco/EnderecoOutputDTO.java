package br.com.fiap.beautymanagerapi.records.endereco;

import java.io.Serializable;

public record EnderecoOutputDTO(
        Long id,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf
) implements Serializable {}
