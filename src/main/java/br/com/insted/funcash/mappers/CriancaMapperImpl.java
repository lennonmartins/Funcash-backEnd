package br.com.insted.funcash.mappers;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;

@Component
public class CriancaMapperImpl implements CriancaMapper{

    @Override
    public  CriancaResponseDTO criancaParaCriancaResponseDTO(Crianca crianca) {
        return new CriancaResponseDTO(
                crianca.getId(),
                crianca.getEmail(),
                crianca.getNome(),
                crianca.getSenha(),
                crianca.getIdade());
    }

    @Override
    public  Crianca criancaRequestparaCrianca(CriancaRequestDTO criancaRequestDTO) {
        return Crianca.builder()
                .email(criancaRequestDTO.getEmail())
                .nome(criancaRequestDTO.getNome())
                .senha(criancaRequestDTO.getSenha())
                .idade(criancaRequestDTO.getIdade())
                .build();
    }
}
