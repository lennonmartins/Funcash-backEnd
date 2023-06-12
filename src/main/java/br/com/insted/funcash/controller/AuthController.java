package br.com.insted.funcash.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = {"/api/v1/autenticacao"}, produces = {"application/json"})
public class AuthController {

    @Autowired
    AuthService authService;
    
    @PostMapping(path = {"/entrar"},consumes = { "application/json" })
    public ResponseEntity<ResponsavelResponseDTO> autentitcar(@Valid @RequestBody LoginDTO loginRequest ){
        System.out.println("------------------------------------");
        System.out.println("emial: " + loginRequest.getEmail());
        System.out.println("senha: " + loginRequest.getSenha());
        System.out.println("------------------------------------");
       return ResponseEntity.ok(authService.loginResponsavel(loginRequest.getEmail(), loginRequest.getSenha()));
    }
}
