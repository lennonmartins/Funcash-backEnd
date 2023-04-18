package br.com.insted.funcash.mappers;

import java.util.Collection;

import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.models.Tarefa;

public interface TarefaMapper {

    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa);
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO);
    public Collection<TarefaResponseDTO> tarefasParaTarefasResponsesDtos(Collection<Tarefa> tarefa);
}
