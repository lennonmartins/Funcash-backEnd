package br.com.insted.funcash.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.DesejoRequestDTO;
import br.com.insted.funcash.dto.DesejoResponseDTO;
import br.com.insted.funcash.models.Desejo;

@Component
public class DesejoMapperImpl implements DesejoMapper {
    
    @Override
    public DesejoResponseDTO desejoParaDesejoResponseDTO(Desejo desejo){
        return new DesejoResponseDTO(
            desejo.getId(),
            desejo.getNome(),
            desejo.getDescricao(),
            desejo.getValor()
        );
    }

    @Override
    public Desejo desejoRequestDTOParaDesejo(DesejoRequestDTO desejoRequestDTO){
        return Desejo.builder()
        .nome(desejoRequestDTO.getNome())
        .descricao(desejoRequestDTO.getDescricao())
        .valor(desejoRequestDTO.getValor())
        .build();
    }

    @Override
    public Collection<DesejoResponseDTO> desejoParaDesejosResponsesDtos(Collection<Desejo> desejos) {
            Collection<DesejoResponseDTO> desejoResponseDTOs = new ArrayList<>();

            for (Desejo desejo : desejos){
                desejoResponseDTOs.add(desejoParaDesejoResponseDTO(desejo));
            }
        return desejoResponseDTOs;
    }
}
