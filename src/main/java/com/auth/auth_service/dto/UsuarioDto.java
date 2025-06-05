package com.auth.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//Informacion para exponer del user.

public class UsuarioDto {

    private String nombreUsuario;
    private String email;
}
