package br.com.insted.funcash.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.mappers.TarefaMapper;
import br.com.insted.funcash.models.Tarefa;
import br.com.insted.funcash.repository.TarefaRepository;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public TarefaResponseDTO buscarPorId(Long id){
        return tarefaMapper.tarefaParaTarefaResponseDTO(buscarTarefaPeloId(id));
    }

    private Tarefa buscarTarefaPeloId(Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if(tarefaOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return tarefaOptional.get();
    }

    public TarefaResponseDTO cadastrar(TarefaRequestDTO  tarefaRequestDTO) {
        Tarefa tarefa = tarefaMapper.tarefaRequestparaTarefa(tarefaRequestDTO);
        tarefaRepository.save(tarefa);
        return tarefaMapper.tarefaParaTarefaResponseDTO(tarefa);   
    }

    
}
