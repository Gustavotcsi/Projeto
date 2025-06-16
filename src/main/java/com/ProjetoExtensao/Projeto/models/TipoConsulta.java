package com.ProjetoExtensao.Projeto.models;

public enum TipoConsulta {
    CARDIOLOGIA("Cardiologia"),
    DERMATOLOGIA("Dermatologia"),
    PEDIATRIA("Pediatria"),
    CLINICA_GERAL("Clínica Geral"),
    ODONTOLOGIA("Odontologia"),
    OFTALMOLOGIA("Oftalmologia"),
    GINECOLOGIA("Ginecologia"),
    ORTOPEDIA("Ortopedia"),
    PSIQUIATRIA("Psiquiatria"),
    NEUROLOGIA("Neurologia"),
    GASTROENTEROLOGIA("Gastroenterologia"),
    ENDOCRINOLOGIA("Endocrinologia"),
    IMUNOLOGIA("Imunologia"),
    ONCOLOGIA("Oncologia"),
    UROLOGIA("Urologia"),
    FISIOTERAPIA("Fisioterapia"),
    PSICOLOGIA("Psicologia"),
    NUTRICIONISTA("Nutricionista"),
    FONOAUDIOLOGIA("Fonoaudiologia"),
    TERAPIA_OCUPACIONAL("Terapia Ocupacional"),
    OUTROS("Outros");


    private String descricao;

    TipoConsulta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


    public static TipoConsulta getType(String tipo) {
        for (TipoConsulta tc : TipoConsulta.values()) {
            if (tc.name().equalsIgnoreCase(tipo) || tc.getDescricao().equalsIgnoreCase(tipo)) {
                return tc;
            }
        }

        throw new IllegalArgumentException("Tipo de consulta inválido: " + tipo);
    }
}