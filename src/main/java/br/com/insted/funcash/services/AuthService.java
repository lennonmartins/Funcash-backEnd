package br.com.insted.funcash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dtos.UsuarioResponseDto;
import br.com.insted.funcash.models.user.Usuario;
import br.com.insted.funcash.repositories.UsuarioRepository;
import br.com.insted.funcash.seguranca.TokenService;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public UsuarioResponseDto login(String email, String senha) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(email, senha);
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        var userDetails = (Usuario) auth.getPrincipal();
        // Usuario usuarioObtido = usuarioRepository.obterPorEmail(email).get();

        var idDaPessoa = userDetails.getResponsavel().getId() != null
                ? userDetails.getResponsavel().getId()
                : userDetails.getCrianca().getId();

        var usuarioResponse = new UsuarioResponseDto(userDetails.getId(),
                idDaPessoa,
                userDetails.getRole(),
                token);

        return usuarioResponse;
    }
}
