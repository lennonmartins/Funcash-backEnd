package br.com.insted.funcash.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import br.com.insted.funcash.utils.EntidadeBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Pessoa extends EntidadeBase {
    
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;
    
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    // @Column(nullable = false)
    private Usuario usuario; 
    
    @Lob
    @Column(nullable = true, length = 16777215)
    private String foto;


    public Pessoa(String nome, LocalDate dataDeNascimento, Genero genero, Usuario usuario, String foto) throws Exception {
        validaDataDeNAscimento(dataDeNascimento);
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
        this.usuario = usuario;
        this.foto = foto;
        setUsuario(usuario);
    }

    private void validaDataDeNAscimento(LocalDate dataDeNascimetno) throws Exception {
        if (dataDeNascimetno == null) {
            throw new Exception("A data de nascimento não pode ser vazia");
        }
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        usuario.vincularPessoa(this);
    }
}
