package br.com.insted.funcash.dtos;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TarefaResponsePaginadasDTO {
    private Collection<TarefaResponseDTO> tarefas;
    private int totalDePaginas;
}
