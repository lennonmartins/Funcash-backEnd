package br.com.insted.funcash.mappers;

import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.models.Tarefa;

public class TarefaMapper {
    
    private TarefaMapper(){}

    public static TarefaResponseDTO toDTO(Tarefa tarefa){
        return TarefaResponseDTO.builder()
        .hora_limite(tarefa.getHora_limite())
        .data_limite(tarefa.getData_limite())
        .moeda(tarefa.getMoeda())
        .nome(tarefa.getNome())
        .build();
    }

    public static Tarefa toTarefa(TarefaRequestDTO tarefaRequestDTO) {
        return Tarefa.builder()
        .hora_limite(tarefaRequestDTO.getHora_limite())
        .data_limite(tarefaRequestDTO.getData_limite())
        .moeda(tarefaRequestDTO.getMoeda())
        .nome(tarefaRequestDTO.getNome())
        .build();
    }
}
