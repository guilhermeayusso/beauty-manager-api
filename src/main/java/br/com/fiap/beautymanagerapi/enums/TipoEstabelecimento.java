package br.com.fiap.beautymanagerapi.enums;

public enum TipoEstabelecimento {
    BARBEARIA("Barbearia"),
    SALAO_DE_BELEZA("Salão de Beleza"),
    MANICURE("Manicure/Pedicure"),
    CLINICA_ESTETICA("Clínica de Estética"),
    SPA("Spa"),
    STUDIO_SOBRANCELHAS("Studio de Sobrancelhas"),
    CENTRO_DEPILACAO("Centro de Depilação"),
    CABELEIREIRO("Cabeleireiro"),
    ESTUDIO_MAQUIAGEM("Estúdio de Maquiagem"),
    BARBEARIA_GOURMET("Barbearia Gourmet");

    private final String descricao;

    TipoEstabelecimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

