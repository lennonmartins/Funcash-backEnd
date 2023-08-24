package br.com.insted.funcash.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

   @NotNull
   @OneToOne
   @JoinColumn(name = "responsavel_id")
   private Responsavel responsavel;

   @NotNull
   @OneToOne
   @JoinColumn(name = "crianca_id")
   private Crianca crianca;

    
    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

   public void vincularResponsavel(Responsavel responsavel){
       this.responsavel = responsavel;
   }

   public void vincularCrianca(Crianca crianca){
       this.crianca = crianca;
   }
}