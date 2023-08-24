package br.com.insted.funcash.mappers;

import java.util.Collection;

import br.com.insted.funcash.dtos.DesejoRequestDTO;
import br.com.insted.funcash.dtos.DesejoResponseDTO;
import br.com.insted.funcash.models.Desejo;

public interface DesejoMapper {
    public DesejoResponseDTO desejoParaDesejoResponseDTO(Desejo desejo);
    public Desejo desejoRequestDTOParaDesejo(DesejoRequestDTO desejoRequestDTO);
    public Collection<DesejoResponseDTO> desejoParaDesejosResponsesDtos(Collection<Desejo> desejo);
}
