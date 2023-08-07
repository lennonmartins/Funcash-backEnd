package br.com.insted.funcash.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.mappers.ResponsavelMapper;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    ResponsavelRepository responsavelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ResponsavelMapper responsavelMapper;

    public ResponsavelResponseDTO loginResponsavel(String email, String senha) {
        return responsavelMapper.responsavelParaResponsavelResponseDTO(buscarPeloEmailESenha(email, senha));
    }

    //Mudar para Usuario
    private Responsavel buscarPeloEmailESenha(String email, String senha) {
        Optional<Responsavel> responsavelOptional = (usuarioRepository.obterPorEmailESenha(email, senha)) ;
        if (responsavelOptional.isEmpty()) {
            throw new NoSuchElementException("Usuário Responsável não Encontrado");
        }
        return responsavelOptional.get();
    }

}
