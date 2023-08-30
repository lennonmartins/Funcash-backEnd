package br.com.insted.funcash.services;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dtos.ResponsavelRequestDTO;
import br.com.insted.funcash.dtos.ResponsavelResponseDTO;
import br.com.insted.funcash.mappers.ResponsavelMapper;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.user.Usuario;
import br.com.insted.funcash.repositories.ResponsavelRepository;
import br.com.insted.funcash.repositories.UsuarioRepository;
import br.com.insted.funcash.utils.DataConvert;

@Service
public class ResponsavelService {
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponsavelResponseDTO buscarPorId(Long id){
        return responsavelMapper.responsavelParaResponsavelResponseDTO(buscarResponsavelPeloId(id));
    }

    private Responsavel buscarResponsavelPeloId(Long id) {
        Optional<Responsavel> responsavelOptional = responsavelRepository.findById(id);
        if(responsavelOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return responsavelOptional.get();
    }

    public ResponsavelResponseDTO alterarReponsavel(ResponsavelRequestDTO responsavelRequestDTO, Long id){
        Usuario usuarioParaAlterar = usuarioRepository.findByIdResponsavel(id);
        usuarioParaAlterar.setEmail(responsavelRequestDTO.getEmail());
        usuarioParaAlterar.setSenha(responsavelRequestDTO.getSenha());
        Responsavel responsavelParaAlterar = buscarResponsavelPeloId(id);
        responsavelParaAlterar.setNome(responsavelRequestDTO.getNome());
        responsavelParaAlterar.setGenero(responsavelRequestDTO.getGenero());
        responsavelParaAlterar.setFoto(responsavelRequestDTO.getFoto());
        responsavelParaAlterar.setDataDeNascimento(DataConvert.obterData(responsavelRequestDTO.getDataDeNascimentoResponsavel()));
        responsavelParaAlterar.setUsuario(usuarioParaAlterar);

        responsavelRepository.save(responsavelParaAlterar);

        return responsavelMapper.responsavelParaResponsavelResponseDTO(responsavelParaAlterar);
    }

    public ResponsavelResponseDTO cadastrar(ResponsavelRequestDTO responsavelRequestDTO) throws Exception{
        Responsavel responsavel = responsavelMapper.responsavelRequestparaResponsavel(responsavelRequestDTO);
        responsavelRepository.save(responsavel);
        responsavel.setUsuario(responsavel.getUsuario());
        usuarioRepository.save(responsavel.getUsuario());
        return responsavelMapper.responsavelParaResponsavelResponseDTO(responsavel);
    }
}
