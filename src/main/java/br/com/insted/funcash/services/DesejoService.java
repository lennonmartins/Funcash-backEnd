package br.com.insted.funcash.services;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dtos.DesejoRequestDTO;
import br.com.insted.funcash.dtos.DesejoResponseDTO;
import br.com.insted.funcash.mappers.DesejoMapper;
import br.com.insted.funcash.models.Desejo;
import br.com.insted.funcash.repositories.DesejoRepository;

@Service
public class DesejoService {

  @Autowired
  private DesejoRepository desejoRepository;

  @Autowired
  private DesejoMapper desejoMapper;

  public DesejoResponseDTO buscarPorId(Long id) {
    return desejoMapper.desejoParaDesejoResponseDTO(buscarDesejoPeloId(id));
  }

  private Desejo buscarDesejoPeloId(Long id) {
    Optional<Desejo> desejoOptional = desejoRepository.findById(id);
    if (desejoOptional.isEmpty()) {
      throw new NoSuchElementException();
    }
    return desejoOptional.get();
  }

  public DesejoResponseDTO cadastrar(DesejoRequestDTO desejoRequestDTO) throws Exception  {
    Desejo desejo = desejoMapper.desejoRequestDTOParaDesejo(desejoRequestDTO);
    desejoRepository.save(desejo);
    return desejoMapper.desejoParaDesejoResponseDTO(desejo);
  }

  public Collection<DesejoResponseDTO> buscarTodos() {
    return desejoMapper.desejoParaDesejosResponsesDtos((Collection<Desejo>) desejoRepository.findAll());
  }

  public DesejoResponseDTO alterar(DesejoRequestDTO desejoRequestDTO, long id) {
    Desejo desejoParaAlterar = buscarDesejoPeloId(id);
    desejoParaAlterar.setTitulo(desejoRequestDTO.getNome());
    desejoParaAlterar.setDescricao(desejoRequestDTO.getDescricao());
    desejoParaAlterar.setValor(desejoRequestDTO.getValor());

    desejoRepository.save(desejoParaAlterar);

    return desejoMapper.desejoParaDesejoResponseDTO(desejoParaAlterar);
  }
}
