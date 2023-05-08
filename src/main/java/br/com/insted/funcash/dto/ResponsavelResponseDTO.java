package br.com.insted.funcash.dto;

import java.time.LocalDate;

import br.com.insted.funcash.models.Genero;
import br.com.insted.funcash.models.Responsavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataDeNascimentoResponsavel;
    private Genero genero;
    private String senha;
    private byte[] foto;

    public ResponsavelResponseDTO(Responsavel responsavel){
        this.id = responsavel.getId();
        this.nome = responsavel.getNome();
        this.email = responsavel.getEmail();
        this.cpf = responsavel.getCpf();
        this.dataDeNascimentoResponsavel = responsavel.getDataDeNascimentoResponsavel();
        this.genero = responsavel.getGenero();
        this.senha = responsavel.getSenha();
        this.foto = responsavel.getFoto();
    }
}
