package br.com.insted.funcash.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Responsavel extends Pessoa {

    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.REMOVE)
    private List<Crianca> criancas;

    @OneToOne(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private Usuario usuario;

    public Responsavel(Usuario usuario, String nome, String cpf, LocalDate dataDeNascimentoResponsavel, Genero genero, String foto) throws Exception {
       super(nome, dataDeNascimentoResponsavel, genero, foto);
        this.cpf = cpf;
        setUsuario(usuario);
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        usuario.vincularEntidade(this);
    }
}
