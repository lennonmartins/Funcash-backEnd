package br.com.insted.funcash.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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

    @Column(nullable = false, length = 200)
    private String nome;
 
    @Column(nullable = false, length = 2500)
    private String descricao;

    @Column(nullable = false)
    private int valor;

    public Desejo(String nome, String descricao, int valor) throws Exception {
        validaNomeDescricao(descricao);
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    private void validaNomeDescricao(String  descricao) throws Exception {
        if (descricao == null) {
            throw new Exception("A data de nascimento não pode ser vazia");
        }
    }
}
