package br.com.insted.funcash.services;

import org.apache.commons.mail.EmailException;
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
