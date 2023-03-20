package br.com.insted.funcash.mappers;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;

@Component
public class CriancaMapperImpl implements CriancaMapper {

    @Override
    public CriancaResponseDTO criancaParaCriancaResponseDTO(Crianca crianca) {
        return new CriancaResponseDTO(
                crianca.getId(),
                crianca.getDataDeNascimento(),
                crianca.getEmail(),
                crianca.getSenha(),
                crianca.getSaldo(),
                crianca.getNome(),
                crianca.getApelido());
    }

    @Override
    public Crianca criancaRequestparaCrianca(CriancaRequestDTO criancaRequestDTO) {
        return Crianca.builder()
                .dataDeNascimento(criancaRequestDTO.getDataDeNascimento())
                .email(criancaRequestDTO.getEmail())
                .senha(criancaRequestDTO.getSenha())
                .saldo(criancaRequestDTO.getSaldo())
                .nome(criancaRequestDTO.getNome())
                .apelido(criancaRequestDTO.getApelido())
                .build();
    }
}
