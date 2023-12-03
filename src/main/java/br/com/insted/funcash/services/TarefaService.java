package br.com.insted.funcash.services;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.insted.funcash.dtos.AlterarStatusTarefaDTO;
import br.com.insted.funcash.dtos.TarefaRequestDTO;
import br.com.insted.funcash.dtos.TarefaResponseDTO;
import br.com.insted.funcash.dtos.UsuarioRequestDTO;
import br.com.insted.funcash.mappers.TarefaMapper;
import br.com.insted.funcash.models.Tarefa;
import br.com.insted.funcash.models.user.UserRole;
import br.com.insted.funcash.repositories.TarefaRepository;
import br.com.insted.funcash.utils.DataConvert;

@Service
public class TarefaService {
    
    private TarefaRepository tarefaRepository;
    private TarefaMapper tarefaMapper;

    public TarefaService (TarefaRepository tarefaRepository, TarefaMapper tarefaMapper){
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
    }

    public TarefaResponseDTO buscarPorId(Long id){
        return tarefaMapper.tarefaParaTarefaResponseDTO(buscarTarefaPeloId(id));
    }

    private Tarefa buscarTarefaPeloId(Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if(tarefaOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return tarefaOptional.get();
    }

    @Transactional
    public TarefaResponseDTO cadastrar(TarefaRequestDTO  tarefaRequestDTO) {
        Tarefa tarefa = tarefaMapper.tarefaRequestparaTarefa(tarefaRequestDTO);
        tarefaRepository.save(tarefa);
        return tarefaMapper.tarefaParaTarefaResponseDTO(tarefa);   
    }

    public Collection<TarefaResponseDTO> buscarTodas() {        
        return tarefaMapper.tarefasParaTarefasResponsesDtos((Collection<Tarefa>) tarefaRepository.findAll());
    }

    public TarefaResponseDTO alterar(TarefaRequestDTO tarefaRequestDto, long id) {
        Tarefa tarefaParaAlterar = buscarTarefaPeloId(id);
        tarefaParaAlterar.setDataLimite(DataConvert.obterData(tarefaRequestDto.getDataLimite()));
        tarefaParaAlterar.setHoraLimite(DataConvert.obterHoraLimiteCompleta(tarefaRequestDto.getDataLimite(), tarefaRequestDto.getHoraLimite()));
        tarefaParaAlterar.setTitulo(tarefaRequestDto.getNome());
        tarefaParaAlterar.setValor(tarefaRequestDto.getValor());

        tarefaRepository.save(tarefaParaAlterar);

        return tarefaMapper.tarefaParaTarefaResponseDTO(tarefaParaAlterar); 
    }    

    public Collection<TarefaResponseDTO> buscarTarefasPelaCrianca(Long id){
        return tarefaMapper.tarefasParaTarefasResponsesDtos((Collection<Tarefa>) tarefaRepository.findAllByCrianca(id));
    }

    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }

    public void alterarStatusTarefa(@Valid AlterarStatusTarefaDTO alteraStatusTarefaRequestDTO, Long idTarefa) {
        var tarefaParaAlterar = buscarTarefaPeloId(idTarefa);
        var tarefaRequest = alteraStatusTarefaRequestDTO.getTarefaRequestDTO();
        var usuarioDarequisicao = alteraStatusTarefaRequestDTO.getUsuarioRequestDTO().getRole();

        if(usuarioDarequisicao.equals(UserRole.CRIANCA)){
            var criancaDaTarefa = tarefaParaAlterar.getCrianca();
            var tarefaRealizada = criancaDaTarefa.realizarTarefa(tarefaParaAlterar, tarefaRequest.getStatus());
            tarefaRepository.save(tarefaRealizada);
        } else{
            var responsavelDaTarefa = tarefaParaAlterar.getCrianca().getResponsavel();
            var tarefaConcluida = responsavelDaTarefa.concluirTarefa(tarefaParaAlterar, tarefaRequest.getStatus());
            tarefaRepository.save(tarefaConcluida);
        }
    }
}
