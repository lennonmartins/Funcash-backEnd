package br.com.insted.funcash.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.mappers.ResponsavelMapper;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.Usuario;
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

    private Responsavel buscarPeloEmailESenha(String email, String senha) {
        Usuario usuarioObtido =  usuarioRepository.obterPorEmailESenha(email, senha).get();
        Optional<Responsavel> responsavelRetornado = responsavelRepository.findById(usuarioObtido.getId());
        if (responsavelRetornado.isEmpty()) {
            throw new NoSuchElementException("Usuário Responsável não Encontrado");
        }
        return responsavelRetornado.get();
    }
}
