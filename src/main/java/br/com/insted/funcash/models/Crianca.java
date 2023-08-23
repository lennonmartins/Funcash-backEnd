package br.com.insted.funcash.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crianca extends Pessoa {

    @Column(nullable = false, length = 15)
    private double saldo;

    @Column(nullable = true, length = 50)
    private String apelido;

    @OneToOne(mappedBy = "crianca", cascade = CascadeType.ALL)
    private Usuario usuario; 

    @OneToMany(mappedBy = "crianca", cascade = CascadeType.REMOVE)
    private List<Tarefa> tarefas;

    @OneToMany(mappedBy = "crianca", cascade = CascadeType.REMOVE)
    private List<Desejo> desejos;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    public Crianca(LocalDate dataDeNascimento, Usuario usuario , double saldo, String nome,
            String apelido, Genero genero, String foto, Responsavel responsavel) throws Exception {
            super(nome, dataDeNascimento, genero, foto);
        this.saldo = saldo;
        this.apelido = apelido;
        this.responsavel = responsavel;
        setUsuario(usuario);
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        usuario.vincularEntidade(this);
    }
}
