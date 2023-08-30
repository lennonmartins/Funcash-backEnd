package br.com.insted.funcash.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.insted.funcash.dtos.LoginDTO;
import br.com.insted.funcash.dtos.UsuarioResponseDto;
import br.com.insted.funcash.services.AuthService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = { "/api/v1/autenticacao" }, produces = { "application/json" })
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(path = { "/entrar" })
    public ResponseEntity<UsuarioResponseDto> autenticar(@Valid @RequestBody LoginDTO loginRequest) {
        UsuarioResponseDto responseDTO = authService.login(loginRequest.getEmail(), loginRequest.getSenha());
        return ResponseEntity.ok(responseDTO);
    }
}
