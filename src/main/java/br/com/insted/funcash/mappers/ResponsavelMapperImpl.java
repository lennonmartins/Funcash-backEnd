package br.com.insted.funcash.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.insted.funcash.dtos.ResponsavelRequestDTO;
import br.com.insted.funcash.dtos.ResponsavelResponseDTO;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.user.Usuario;
import br.com.insted.funcash.utils.DataConvert;

@Component
public class ResponsavelMapperImpl implements ResponsavelMapper {

    @Override
    public ResponsavelResponseDTO responsavelParaResponsavelResponseDTO(Responsavel responsavel) {
        return new ResponsavelResponseDTO(responsavel.getId(),
                responsavel.getNome(),
                responsavel.getUsuario().getEmail(),
                responsavel.getCpf(),
                responsavel.getDataDeNascimento(),
                responsavel.getGenero(),
                responsavel.getUsuario().getSenha(),
                responsavel.getFoto());
    }

    @Override
    public Responsavel responsavelRequestparaResponsavel(ResponsavelRequestDTO responsavelRequestDTO) throws Exception {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(responsavelRequestDTO.getSenha());
        return new Responsavel(
                new Usuario(responsavelRequestDTO.getEmail(), senhaCriptografada, responsavelRequestDTO.getRole()),
                responsavelRequestDTO.getNome(),
                responsavelRequestDTO.getCpf(),
                DataConvert.obterData(responsavelRequestDTO.getDataDeNascimentoResponsavel()),
                responsavelRequestDTO.getGenero(),
                responsavelRequestDTO.getFoto());
    }
}
