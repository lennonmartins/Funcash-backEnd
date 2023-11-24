package br.com.insted.funcash.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.insted.funcash.dtos.TarefaRequestDTO;
import br.com.insted.funcash.dtos.TarefaResponseDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Tarefa;
import br.com.insted.funcash.repositories.CriancaRepository;
import br.com.insted.funcash.utils.DataConvert;

@Component
public class TarefaMapperImpl implements TarefaMapper {

    @Autowired
    private CriancaRepository criancaRepository;

    @Override
    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa) {
        
        return new TarefaResponseDTO(tarefa.getId(),
                tarefa.getHoraLimite(),
                tarefa.getDataLimite(),
                tarefa.getDataDeCriacao(),
                tarefa.getValor(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getCrianca().getId()
                );
    }

    @Override
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO) {
        Crianca crianca = verificaSeObjetoEhNulo(tarefaRequestDTO);
        return new Tarefa(
        DataConvert.obterHoraLimiteCompleta(tarefaRequestDTO.getDataLimite(), 
        tarefaRequestDTO.getHoraLimite()),
        DataConvert.obterData(tarefaRequestDTO.getDataLimite()),
        tarefaRequestDTO.getValor(),
        tarefaRequestDTO.getNome(),   
        crianca,
        tarefaRequestDTO.getDescricao()
        );
    }

    private Crianca verificaSeObjetoEhNulo(TarefaRequestDTO tarefaRequestDTO) {
        Optional<Crianca> criancaOptional = criancaRepository.findById(tarefaRequestDTO.getIdDaCrianca());
        if(criancaOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Crianca crianca = criancaOptional.get();
        return crianca;
    }

    @Override
    public Collection<TarefaResponseDTO> tarefasParaTarefasResponsesDtos(Collection<Tarefa> tarefas) {
       Collection<TarefaResponseDTO> tarefasResponsesDtos = new ArrayList<>();

       for(Tarefa tarefa : tarefas){
        tarefasResponsesDtos.add(tarefaParaTarefaResponseDTO(tarefa));
       }
       return tarefasResponsesDtos;
    }
}
