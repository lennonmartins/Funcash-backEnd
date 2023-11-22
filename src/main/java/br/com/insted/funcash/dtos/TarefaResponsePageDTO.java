package br.com.insted.funcash.dtos;

import java.util.Collection;

public record TarefaResponsePageDTO(Collection<TarefaResponseDTO> tarefas, int totalPaginas){
}
