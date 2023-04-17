package br.com.insted.funcash.mappers;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.models.Tarefa;
import br.com.insted.funcash.utils.DataConvert;

@Component
public class TarefaMapperImpl implements TarefaMapper {

    @Override
    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa) {
        return new TarefaResponseDTO(tarefa.getId(),
                tarefa.getHoraLimite(),
                tarefa.getDataLimite(),
                tarefa.getDataDeCriacao(),
                tarefa.getValor(),
                tarefa.getNome());
    }

    @Override
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO) {
        return new Tarefa(DataConvert.obterHoraLimiteCompleta(tarefaRequestDTO.getDataLimite(), tarefaRequestDTO.getHoraLimite()),
        DataConvert.obterData(tarefaRequestDTO.getDataLimite()),tarefaRequestDTO.getValor(),
        tarefaRequestDTO.getNome()
        );
    }
}
