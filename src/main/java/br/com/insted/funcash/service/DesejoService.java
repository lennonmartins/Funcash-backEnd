package br.com.insted.funcash.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dto.DesejoResponseDTO;
import br.com.insted.funcash.mappers.DesejoMapper;
import br.com.insted.funcash.models.Desejo;
import br.com.insted.funcash.repository.DesejoRepository;

@Service
public class DesejoService {
  @Autowired
  private DesejoRepository desejoRepository;
  
  @Autowired
  private DesejoMapper desejoMapper;

  public DesejoResponseDTO buscarPorId(Long id){
    return desejoMapper.desejoParaDesejoResponseDTO(buscarDesejoPeloId(id));
  }

  private Desejo buscarDesejoPeloId(Long id){
    Optional<Desejo> desejoOptional = desejoRepository.findById(id);
    if (desejoOptional.isEmpty()) {
        throw new NoSuchElementException();
    }
    return desejoOptional.get();
  }
}
