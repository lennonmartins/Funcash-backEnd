package br.com.insted.funcash.mappers;

import br.com.insted.funcash.dtos.TarefaRequestDTO;
import br.com.insted.funcash.dtos.TarefaResponseDTO;
import br.com.insted.funcash.dtos.TarefaResponsePageDTO;
import br.com.insted.funcash.models.Tarefa;

import java.util.Collection;

public interface TarefaMapper {

    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa);
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO);
    public Collection<TarefaResponseDTO> tarefasParaTarefasResponsesDtos(Collection<Tarefa> tarefas);

    TarefaResponsePageDTO tarefasParaTarefasResponsesPaginadoEOrdenado(Collection<Tarefa> tarefas, int totalDePaginas);
}
