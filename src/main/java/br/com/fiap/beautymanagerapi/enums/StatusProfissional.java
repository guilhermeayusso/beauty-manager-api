package br.com.fiap.beautymanagerapi.enums;

public enum StatusProfissional {
    DISPONIVEL("Disponível"),
    OCUPADO("Ocupado"),
    EM_ATENDIMENTO("Em Atendimento"),
    AUSENTE("Ausente"),
    FERIAS("Férias"),
    INATIVO("Inativo"),
    INDISPONIVEL("Indisponível");

    private final String descricao;

    StatusProfissional(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

