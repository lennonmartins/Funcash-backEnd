package br.com.insted.funcash.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.ResponsavelRequestDTO;
import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.Usuario;
import br.com.insted.funcash.utils.DataConvert;


@Component
public class ResponsavelMapperImpl implements ResponsavelMapper {
    
    @Override
    public ResponsavelResponseDTO responsavelParaResponsavelResponseDTO(Responsavel responsavel){
        return new ResponsavelResponseDTO(responsavel.getId(),
        responsavel.getNome(),
        responsavel.getUsuario().getEmail(), 
        responsavel.getCpf(), 
        responsavel.getDataDeNascimentoResponsavel(), 
        responsavel.getGenero(), 
        responsavel.getUsuario().getSenha(),
        responsavel.getFoto()
        );
    }

    @Override
    public Responsavel responsavelRequestparaResponsavel(ResponsavelRequestDTO responsavelRequestDTO) {
        return Responsavel.builder()
                .nome(responsavelRequestDTO.getNome())
                .usuario(new Usuario(responsavelRequestDTO.getEmail(), responsavelRequestDTO.getSenha()))
                .cpf(responsavelRequestDTO.getCpf())
                .dataDeNascimentoResponsavel(DataConvert.obterData(responsavelRequestDTO.getDataDeNascimentoResponsavel()))
                .genero(responsavelRequestDTO.getGenero())
                .foto(responsavelRequestDTO.getFoto())
                .build();
    }

    @Override
    public Collection<ResponsavelResponseDTO> responsavelParaResponsavelResponsesDtos(Collection<Responsavel> responsavels) {
       Collection<ResponsavelResponseDTO> responsavelResponseDto = new ArrayList<>();

       for(Responsavel responsavel : responsavels){
        responsavelResponseDto.add(responsavelParaResponsavelResponseDTO(responsavel));
       }
       return responsavelResponseDto;
    }
    
}
