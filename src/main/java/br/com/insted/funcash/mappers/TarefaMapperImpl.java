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
            tarefa.getHoraLimite(),
            tarefa.getDataLimite(),
            tarefa.getDataDeCriacao(),
            tarefa.getValor(),
            tarefa.getNome());
    }

    @Override
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO) {
        return Tarefa.builder()
        .horaLimite(tarefaRequestDTO.getHoraLimite())
        .dataLimite(tarefaRequestDTO.getDataLimite())
        .valor(tarefaRequestDTO.getValor())
        .nome(tarefaRequestDTO.getNome())
        .build();
    }
}
