package br.com.insted.funcash.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.ResponsavelRequestDTO;
import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.utils.ArquivoUtils;
import br.com.insted.funcash.utils.DataConvert;


@Component
public class ResponsavelMapperImpl implements ResponsavelMapper {
    
    @Override
    public ResponsavelResponseDTO responsavelParaResponsavelResponseDTO(Responsavel responsavel){
        return new ResponsavelResponseDTO(responsavel.getId(),
        responsavel.getNome(),
        responsavel.getEmail(), 
        responsavel.getCpf(), 
        responsavel.getDataDeNascimentoResponsavel(), 
        responsavel.getGenero(), 
        responsavel.getSenha(),
        responsavel.getFoto()
        );
    }

    @Override
    public Responsavel responsavelRequestparaResponsavel(ResponsavelRequestDTO responsavelRequestDTO) throws IOException {
        return Responsavel.builder()
                .nome(responsavelRequestDTO.getNome())
                .email(responsavelRequestDTO.getEmail())
                .cpf(responsavelRequestDTO.getCpf())
                .dataDeNascimentoResponsavel(DataConvert.obterData(responsavelRequestDTO.getDataDeNascimentoResponsavel()))
                .genero(responsavelRequestDTO.getGenero())
                .senha(responsavelRequestDTO.getSenha())
                .foto(responsavelRequestDTO.getFoto())
                .build();
    }

   
    
}
