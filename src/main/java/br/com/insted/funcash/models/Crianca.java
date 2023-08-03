package br.com.insted.funcash.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.springframework.lang.Nullable;

import br.com.insted.funcash.utils.EntidadeBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Crianca extends EntidadeBase {

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 16)
    private String senha;

    @Column(nullable = false, length = 15)
    private double saldo;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = true, length = 50)
    private String apelido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Genero genero;

    @OneToMany(mappedBy = "crianca", cascade = CascadeType.REMOVE)
    private List<Tarefa> tarefas;

    @OneToMany(mappedBy = "crianca", cascade = CascadeType.REMOVE)
    private List<Desejo> desejos;

    @Lob
    @Column(nullable = true, length=16777215)
    private String foto;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    public Crianca(LocalDate _dataDeNascimento, @Email String _email, String _senha, double _saldo, String _nome,
            String _apelido, Genero _genero, String _foto, Responsavel _responsavel) throws Exception {
        validaDataDeNAscimento(_dataDeNascimento);
        this.dataDeNascimento = _dataDeNascimento;
        this.email = _email;
        this.senha = _senha;
        this.saldo = _saldo;
        this.nome = _nome;
        this.apelido = _apelido;
        this.genero = _genero;
        this.foto = _foto;
        this.responsavel = _responsavel;
    }

    private void validaDataDeNAscimento(LocalDate dataDeNascimetno) throws Exception {
        if (dataDeNascimetno == null) {
            throw new Exception("A data de nascimento não pode ser vazia");
        }
    }

}
