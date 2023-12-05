package br.com.insted.funcash.builders;

import br.com.insted.funcash.dtos.TarefaRequestDTO;
import br.com.insted.funcash.models.StatusDaTarefa;

public class TarefaRequestDTOBuilder {
    String horaEmString = "19:30:00";
    String dataLimiteEmString = "2023-03-23";
    double valor = 10.50;
    String titulo = "Tirar o lixo";
    private String descricao = "lorem impsun test etst";
    long idDaCrianca = 1L;
    StatusDaTarefa statusDaTarefa = StatusDaTarefa.A_FAZER;

    public TarefaRequestDTO construir() {
        return new TarefaRequestDTO(horaEmString, dataLimiteEmString, valor, titulo, descricao, idDaCrianca,
                statusDaTarefa);
    }

    public TarefaRequestDTOBuilder comDataLimite(String dataLimiteEmString) {
        this.dataLimiteEmString = dataLimiteEmString;
        return this;
    }

    public TarefaRequestDTOBuilder comHoraLimite(String horaLimiteEmString) {
        this.horaEmString = horaLimiteEmString;
        return this;
    }

    public TarefaRequestDTOBuilder comTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public TarefaRequestDTOBuilder comCrianca(Long id) {
        this.idDaCrianca = id;
        return this;
    }
}
