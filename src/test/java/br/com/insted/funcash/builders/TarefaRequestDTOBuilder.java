package br.com.insted.funcash.builders;

import br.com.insted.funcash.dto.TarefaRequestDTO;

public class TarefaRequestDTOBuilder {
    String horaEmString = "19:30:00";
    String dataLimiteEmString = "2023-03-23";
    double valor = 10.50;
    String nome = "Tirar o lixo";
    Long idDaCrianca = 1L;

    public TarefaRequestDTO construir() {
        return new TarefaRequestDTO(horaEmString, dataLimiteEmString, valor, nome, idDaCrianca);
    }

    public TarefaRequestDTOBuilder comDataLimite(String dataLimiteEmString) {
        this.dataLimiteEmString = dataLimiteEmString;
        return this;
    }

    public TarefaRequestDTOBuilder comHoraLimite(String horaLimiteEmString) {
        this.horaEmString = horaLimiteEmString;
        return this;
    }

    public TarefaRequestDTOBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

}
