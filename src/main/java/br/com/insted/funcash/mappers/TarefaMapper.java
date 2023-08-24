package br.com.insted.funcash.mappers;

import java.util.Collection;

import br.com.insted.funcash.dtos.TarefaRequestDTO;
import br.com.insted.funcash.dtos.TarefaResponseDTO;
import br.com.insted.funcash.models.Tarefa;

public interface TarefaMapper {

    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa);
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO);
    public Collection<TarefaResponseDTO> tarefasParaTarefasResponsesDtos(Collection<Tarefa> tarefas);
}
