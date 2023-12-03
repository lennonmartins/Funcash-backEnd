package br.com.insted.funcash.dtos;

import br.com.insted.funcash.models.user.UserRole;

public class UsuarioRequestDTO {
    UserRole role;

    public UserRole getRole() {
        return role;
    }
}
