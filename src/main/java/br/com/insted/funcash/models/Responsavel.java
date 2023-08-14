package br.com.insted.funcash.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.insted.funcash.utils.EntidadeBase;
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
public class Responsavel extends EntidadeBase {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataDeNascimentoResponsavel;

    @Column(nullable = false)
    private Genero genero;

    @Lob
    @Column(nullable = true, length = 16777215)
    private String foto;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.REMOVE)
    private List<Crianca> criancas;

    @OneToOne(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private Usuario usuario;

    public Responsavel(Usuario usuario, String nome, String cpf, LocalDate dataDeNascimentoResponsavel, Genero genero, String foto) throws Exception {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimentoResponsavel = dataDeNascimentoResponsavel;
        this.genero = genero;
        this.foto = foto;
        setUsuario(usuario);
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        usuario.vincularResponsavel(this);
    }
}
