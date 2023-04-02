package br.com.insted.funcash.mappers;

import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.utils.DataConvert;

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
                crianca.getApelido(),
                crianca.getGenero());
    }

    @Override
    public Crianca criancaRequestparaCrianca(CriancaRequestDTO criancaRequestDTO) {
        return Crianca.builder()
                .dataDeNascimento(DataConvert.obterData(criancaRequestDTO.getDataDeNascimento()))
                .email(criancaRequestDTO.getEmail())
                .senha(criancaRequestDTO.getSenha())
                .saldo(criancaRequestDTO.getSaldo())
                .nome(criancaRequestDTO.getNome())
                .apelido(criancaRequestDTO.getApelido())
                .genero(criancaRequestDTO.getGenero())
                .build();
    }
}
