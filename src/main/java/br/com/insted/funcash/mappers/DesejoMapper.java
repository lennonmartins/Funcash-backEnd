package br.com.insted.funcash.mappers;

import br.com.insted.funcash.dto.DesejoRequestDTO;
import br.com.insted.funcash.dto.DesejoResponseDTO;
import br.com.insted.funcash.models.Desejo;

public interface DesejoMapper {
    public DesejoResponseDTO desejoParaDesejoResponseDTO(Desejo desejo);
    public Desejo desejoRequestDTOParaDesejo(DesejoRequestDTO desejoRequestDTO);
}
