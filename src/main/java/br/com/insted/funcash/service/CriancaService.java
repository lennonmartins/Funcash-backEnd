package br.com.insted.funcash.service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.mappers.CriancaMapper;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repository.CriancaRepository;
import br.com.insted.funcash.utils.DataConvert;

@Service
public class CriancaService {
    @Autowired
    private CriancaRepository criancaRepository;

    @Autowired
    private CriancaMapper criancaMapper;

    public CriancaResponseDTO buscarPorId(Long id){
        return criancaMapper.criancaParaCriancaResponseDTO(buscarCriancaPeloId(id));
    }

    private Crianca buscarCriancaPeloId(Long id) {
        Optional<Crianca> criancaOptional = criancaRepository.findById(id);
        if(criancaOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return criancaOptional.get();
    }

    public CriancaResponseDTO cadastrar(CriancaRequestDTO criancaRequestDTO){
        Crianca crianca = criancaMapper.criancaRequestparaCrianca(criancaRequestDTO);
        criancaRepository.save(crianca);
        return criancaMapper.criancaParaCriancaResponseDTO(crianca);
    }

    public Collection<CriancaResponseDTO> buscarTodas(){
        return criancaMapper.criancasParaCriancasResponsesDtos((Collection<Crianca>) criancaRepository.findAll());
    }

    public Collection<CriancaResponseDTO> buscarCriancasPeloResponsavel(Long id){
        return criancaMapper.criancasParaCriancasResponsesDtos((Collection<Crianca>) criancaRepository.findAllByResponsavel(id));
    }

    public CriancaResponseDTO alterar(CriancaRequestDTO criancaRequestDTO, Long id){
        Crianca criancaParaAlterar = buscarCriancaPeloId(id);
        criancaParaAlterar.setNome(criancaRequestDTO.getNome());
        criancaParaAlterar.setGenero(criancaRequestDTO.getGenero());
        criancaParaAlterar.setDataDeNascimento(DataConvert.obterData(criancaRequestDTO.getDataDeNascimento()));
        criancaParaAlterar.setEmail(criancaRequestDTO.getEmail());
        criancaParaAlterar.setApelido(criancaRequestDTO.getApelido());
        criancaParaAlterar.setSenha(criancaRequestDTO.getSenha());
        criancaParaAlterar.setFoto(criancaRequestDTO.getFoto());


        criancaRepository.save(criancaParaAlterar);

        return criancaMapper.criancaParaCriancaResponseDTO(criancaParaAlterar);  
    }
}
