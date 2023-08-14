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
import br.com.insted.funcash.models.Usuario;
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
                crianca.getUsuario().getEmail(),
                crianca.getUsuario().getSenha(),
                crianca.getSaldo(),
                crianca.getNome(),
                crianca.getApelido(),
                crianca.getGenero(),
                crianca.getFoto());
    }

    @Override
    public Crianca criancaRequestparaCrianca(CriancaRequestDTO criancaRequestDTO) throws Exception {
        Responsavel responsavel = verificaSeObjetoEhNulo(criancaRequestDTO);
        return new Crianca(
                (DataConvert.obterData(criancaRequestDTO.getDataDeNascimento())),
                new Usuario(criancaRequestDTO.getEmail(),criancaRequestDTO.getSenha()),
                criancaRequestDTO.getSaldo(),
                criancaRequestDTO.getNome(),
                criancaRequestDTO.getApelido(),
                criancaRequestDTO.getGenero(),
                criancaRequestDTO.getFoto(),
                responsavel);
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
