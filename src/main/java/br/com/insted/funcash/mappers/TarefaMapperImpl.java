package br.com.insted.funcash.mappers;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.models.Tarefa;

@Component
public class TarefaMapperImpl implements TarefaMapper {
    
@Override
    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa){
        return new TarefaResponseDTO(
            tarefa.getHora_limite(),
            tarefa.getData_limite(),
            tarefa.getData_de_criacao(),
            tarefa.getValor(),
            tarefa.getNome());
    }

    @Override
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO) {
        return Tarefa.builder()
        .hora_limite(tarefaRequestDTO.getHora_limite())
        .data_limite(tarefaRequestDTO.getData_limite())
        .data_de_criacao(tarefaRequestDTO.getData_de_criacao())
        .valor(tarefaRequestDTO.getValor())
        .nome(tarefaRequestDTO.getNome())
        .build();
    }
}
