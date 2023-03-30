package br.com.insted.funcash.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Desejo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 200 )
    private String descricao;

    @Column(nullable = false, length = 20)
    private int valor;

    public Desejo(String nome, String descricao, int valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }
}
