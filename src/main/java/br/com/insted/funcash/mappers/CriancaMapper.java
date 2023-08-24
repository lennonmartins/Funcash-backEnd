package br.com.insted.funcash.mappers;

import java.util.Collection;

import br.com.insted.funcash.dtos.CriancaRequestDTO;
import br.com.insted.funcash.dtos.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;

public interface CriancaMapper {
    public CriancaResponseDTO criancaParaCriancaResponseDTO(Crianca crianca);  
    public Crianca criancaRequestparaCrianca(CriancaRequestDTO criancaRequestDTO) throws Exception;
    public Collection<CriancaResponseDTO> criancasParaCriancasResponsesDtos(Collection<Crianca> criancas);
}
