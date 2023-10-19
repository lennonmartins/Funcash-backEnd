package br.com.insted.funcash.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.insted.funcash.dtos.DesejoRequestDTO;
import br.com.insted.funcash.dtos.DesejoResponseDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Desejo;
import br.com.insted.funcash.repositories.CriancaRepository;

@Component
public class DesejoMapperImpl implements DesejoMapper {
    
    @Autowired
    private CriancaRepository criancaRepository;

    @Override
    public DesejoResponseDTO desejoParaDesejoResponseDTO(Desejo desejo){
        return new DesejoResponseDTO(
            desejo.getId(),
            desejo.getTitulo(),
            desejo.getDescricao(),
            desejo.getValor()
        );
    }

    @Override
    public Desejo desejoRequestDTOParaDesejo(DesejoRequestDTO desejoRequestDTO) throws Exception {
        Crianca crianca = verificaSeObjetoEhNulo(desejoRequestDTO);
        return new Desejo(desejoRequestDTO.getNome(), 
        desejoRequestDTO.getDescricao(), 
        desejoRequestDTO.getValor(), 
        crianca);
    }

    @Override
    public Collection<DesejoResponseDTO> desejoParaDesejosResponsesDtos(Collection<Desejo> desejos) {
            Collection<DesejoResponseDTO> desejoResponseDTOs = new ArrayList<>();

            for (Desejo desejo : desejos){
                desejoResponseDTOs.add(desejoParaDesejoResponseDTO(desejo));
            }
        return desejoResponseDTOs;
    }

    private Crianca verificaSeObjetoEhNulo(DesejoRequestDTO desejoRequestDTO) {
        Optional<Crianca> criancaOptional = criancaRepository.findById(desejoRequestDTO.getIdDaCrianca());
        if(criancaOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Crianca crianca = criancaOptional.get();
        return crianca;
    }
}
