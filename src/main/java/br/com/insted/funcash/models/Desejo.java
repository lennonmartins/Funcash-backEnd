package br.com.insted.funcash.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.insted.funcash.utils.EntidadeBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Desejo extends EntidadeBase {
    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 500, nullable = true)
    private String descricao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private LocalDateTime dataDeCriacao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_crianca")
    private Crianca crianca;

    public Desejo(String titulo, String descricao, double valor, Crianca crianca) throws Exception {
        validaNome(titulo);
        this.dataDeCriacao = LocalDateTime.now();
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.crianca = crianca;
    }

    private void validaNome(String nome) throws Exception {
        if (nome == null) {
            throw new Exception("O nome não pode ser vazio");
        }
    }
}
