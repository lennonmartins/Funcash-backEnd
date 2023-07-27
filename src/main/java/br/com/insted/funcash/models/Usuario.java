package br.com.insted.funcash.models;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Usuario {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
}
