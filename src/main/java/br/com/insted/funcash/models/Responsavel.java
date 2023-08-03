package br.com.insted.funcash.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(mappedBy = "responsavel", cascade = CascadeType.PERSIST)
    private Usuario usuario;

    public Responsavel(String nome, String email, String cpf, LocalDate dataDeNascimentoResponsavel, Genero genero,
            String senha, String foto) throws Exception {
        this.usuario = new Usuario(email, senha);
        setUsuario(usuario);
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimentoResponsavel = dataDeNascimentoResponsavel;
        this.genero = genero;
        this.foto = foto;
    }

}
