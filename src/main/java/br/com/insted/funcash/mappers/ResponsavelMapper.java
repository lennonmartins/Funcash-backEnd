package br.com.insted.funcash.mappers;

import java.util.Collection;

import br.com.insted.funcash.dtos.ResponsavelRequestDTO;
import br.com.insted.funcash.dtos.ResponsavelResponseDTO;
import br.com.insted.funcash.models.Responsavel;

public interface ResponsavelMapper {
    public ResponsavelResponseDTO responsavelParaResponsavelResponseDTO(Responsavel responsavel);
    public Responsavel responsavelRequestparaResponsavel(ResponsavelRequestDTO responsavelRequestDTO) throws Exception;
}
