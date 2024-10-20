package br.com.fiap.beautymanagerapi.enums;

public enum StatusAgendamento {
    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private final String descricao;

    StatusAgendamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
