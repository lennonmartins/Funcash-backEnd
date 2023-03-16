package br.com.insted.funcash.mappers;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;

public class CriancaMapper {

    private CriancaMapper(){}

    public static CriancaResponseDTO toDTO(Crianca crianca) {
        return CriancaResponseDTO.builder()
            .email(crianca.getEmail())
            .idade(crianca.getIdade())
            .nome(crianca.getNome())
            .build();
    }

    public static Crianca toCrianca(CriancaRequestDTO criancaRequestDTO) {
        return Crianca.builder()
        .email(criancaRequestDTO.getEmail())
        .nome(criancaRequestDTO.getNome())
        .senha(criancaRequestDTO.getSenha())
        .idade(criancaRequestDTO.getIdade())
            .build();
    }

}
