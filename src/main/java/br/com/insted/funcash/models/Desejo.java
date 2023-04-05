package br.com.insted.funcash.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Desejo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private double valor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "crianca_id")
    private Crianca crianca;

    public Desejo(String _nome, String _descricao, double _valor) throws Exception {
        validaNome(_nome);
        this.nome = _nome;
        this.descricao = _descricao;
        this.valor = _valor;
    }

    private void validaNome(String nome) throws Exception {
        if (nome == null) {
            throw new Exception("O nome não pode ser vazio");
        }
    }
}
