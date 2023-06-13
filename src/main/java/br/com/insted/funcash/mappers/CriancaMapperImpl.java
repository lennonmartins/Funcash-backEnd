package br.com.insted.funcash.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.utils.DataConvert;

@Component
public class CriancaMapperImpl implements CriancaMapper {

    @Autowired
    private ResponsavelRepository responsavelRepository;

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
        Responsavel responsavel = verificaSeObjetoEhNulo(criancaRequestDTO);
        return Crianca.builder()
                .dataDeNascimento(DataConvert.obterData(criancaRequestDTO.getDataDeNascimento()))
                .email(criancaRequestDTO.getEmail())
                .senha(criancaRequestDTO.getSenha())
                .saldo(criancaRequestDTO.getSaldo())
                .nome(criancaRequestDTO.getNome())
                .apelido(criancaRequestDTO.getApelido())
                .genero(criancaRequestDTO.getGenero())
                .responsavel(responsavel)
                .build();
    }

    private Responsavel verificaSeObjetoEhNulo(CriancaRequestDTO criancaRequestDTO) {
        Responsavel responsavel;
        Optional<Responsavel> responsavelOptional = responsavelRepository
                .findById(criancaRequestDTO.getIdDoResponsavel());
        if (responsavelOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        responsavel = responsavelOptional.get();
        return responsavel;
    }

    @Override
    public Collection<CriancaResponseDTO> criancasParaCriancasResponsesDtos(Collection<Crianca> criancas) {
        Collection<CriancaResponseDTO> criancasRetornadas = new ArrayList<>();

        for (Crianca crianca : criancas) {
            criancasRetornadas.add(criancaParaCriancaResponseDTO(crianca));
        }
        return criancasRetornadas;
    }
}
