package br.com.insted.funcash.models;

public enum StatusDaTarefa {
    A_FAZER("A fazer"),
    REALIZADA("Realizada"),
    VENCIDA("Vencida"),
    CONCLUIDA("Concluída");

    private final String descricao;

    StatusDaTarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
