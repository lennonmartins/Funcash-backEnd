package br.com.insted.funcash.services;

import javax.mail.event.MailEvent;
import javax.validation.constraints.Email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dtos.UsuarioResponseDto;
import br.com.insted.funcash.models.user.Usuario;
import br.com.insted.funcash.seguranca.TokenService;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public UsuarioResponseDto login(String email, String senha) throws EmailException {

        var usernamePassword = new UsernamePasswordAuthenticationToken(email, senha);
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        var userDetails = (Usuario) auth.getPrincipal();

        var idDaPessoa = obterIdDaPessoaValida(userDetails);

        var usuarioResponse = new UsuarioResponseDto(userDetails.getId(),
                idDaPessoa,
                userDetails.getRole(),
                token);

        if (usuarioResponse != null) {
            org.apache.commons.mail.Email objetoEmail = new SimpleEmail();
            objetoEmail.setHostName("smtp.gmail.com");
            objetoEmail.getSmtpPort();
            objetoEmail.setAuthenticator(new DefaultAuthenticator( "funcash.edu@gmail.com", "funcash123"));
            objetoEmail.setSSLOnConnect(true);
            //objetoEmail.setStartTLSRequired(true);
            objetoEmail.setFrom("no-reply@funcash.com");
            objetoEmail.addTo("lennonmcarlos@gmail.com");
            objetoEmail.setSubject("Teste");
            objetoEmail.setMsg("Entrei?");
            objetoEmail.send();
        }
        return usuarioResponse;
    }

    private Long obterIdDaPessoaValida(Usuario userDetails) {
        if (userDetails.getCrianca() == null)
            return userDetails.getResponsavel().getId();
        else {
            return userDetails.getCrianca().getId();
        }
    }

}
