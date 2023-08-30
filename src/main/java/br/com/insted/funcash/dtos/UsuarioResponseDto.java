package br.com.insted.funcash.dtos;

import br.com.insted.funcash.models.user.UserRole;

public record UsuarioResponseDto(Long idDoUsuario, Long idDaPessoa, UserRole role, String token) {

}
