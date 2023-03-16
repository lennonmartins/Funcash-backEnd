package br.com.insted.funcash.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.mappers.CriancaMapper;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repository.CriancaRepository;

@Service
public class CriancaService {
    @Autowired
    private CriancaRepository criancaRepository;

    @Autowired
    private CriancaMapper criancaMapper;

    public CriancaResponseDTO buscarPorId(Long id){
        return criancaMapper.criancaParaCriancaResponseDTO(buscarCricaPeloId(id));
    }

    private Crianca buscarCricaPeloId(Long id) {
        Optional<Crianca> criancaOptional = criancaRepository.findById(id);
        if(criancaOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return criancaOptional.get();
    }
}
