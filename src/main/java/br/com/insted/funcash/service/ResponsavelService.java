package br.com.insted.funcash.service;


import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dto.ResponsavelRequestDTO;
import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.mappers.ResponsavelMapper;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.repository.UsuarioRepository;

@Service
public class ResponsavelService {
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponsavelResponseDTO buscarPorId(Long id){
        return responsavelMapper.responsavelParaResponsavelResponseDTO(buscarResponsavelPeloId(id));
    }

    private Responsavel buscarResponsavelPeloId(Long id) {
        Optional<Responsavel> responsavelOptional = responsavelRepository.findById(id);
        if(responsavelOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return responsavelOptional.get();
    }

    public ResponsavelResponseDTO cadastrar(ResponsavelRequestDTO responsavelRequestDTO) throws IOException{
        Responsavel responsavel = responsavelMapper.responsavelRequestparaResponsavel(responsavelRequestDTO);
        responsavelRepository.save(responsavel);
        responsavel.setUsuario(responsavel.getUsuario());
        usuarioRepository.save(responsavel.getUsuario());
        return responsavelMapper.responsavelParaResponsavelResponseDTO(responsavel);
    }
}
