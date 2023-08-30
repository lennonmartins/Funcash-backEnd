package br.com.insted.funcash.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Responsavel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

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

   @NotNull
   private UserRole role;
    
    public Usuario(String email, String senha, UserRole role){
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

   public void vincularResponsavel(Responsavel responsavel){
       this.responsavel = responsavel;
   }

   public void vincularCrianca(Crianca crianca){
       this.crianca = crianca;
   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.RESPONSAVEL)
            return List.of(new SimpleGrantedAuthority("ROLE_RESPONSAVEL"), new SimpleGrantedAuthority("ROLE_CRIANCA"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_CRIANCA"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}