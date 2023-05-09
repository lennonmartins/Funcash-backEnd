package br.com.insted.funcash.mappers;

import java.io.IOException;

import br.com.insted.funcash.dto.ResponsavelRequestDTO;
import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.models.Responsavel;
import java.util.Collection;

public interface ResponsavelMapper {
    public ResponsavelResponseDTO responsavelParaResponsavelResponseDTO(Responsavel responsavel);
    public Responsavel responsavelRequestparaResponsavel(ResponsavelRequestDTO responsavelRequestDTO);
    public Collection<ResponsavelResponseDTO> responsavelParaResponsavelResponsesDtos(Collection<Responsavel> responsavel);

}
