package br.com.insted.funcash.mappers;

import java.util.Collection;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;

public interface CriancaMapper {
    public CriancaResponseDTO criancaParaCriancaResponseDTO(Crianca crianca);  
    public Crianca criancaRequestparaCrianca(CriancaRequestDTO criancaRequestDTO);
    public Collection<CriancaResponseDTO> criancasParaCriancasResponsesDtos(Collection<Crianca> criancas);
}
