package com.auth.auth_service.controller;

import com.auth.auth_service.dto.UsuarioDto;
import com.auth.auth_service.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

// Se obtiene datos del usuario autenticado (Perfil).

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UsuarioDto> getProfile(Authentication authentication){
        String username = authentication.getName();
        UsuarioDto usuarioDto = usuarioService.obtenerUsuarioPorUsername(username);
        return ResponseEntity.ok(usuarioDto);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
            Authentication authentication,
            @RequestBody UsuarioDto usuarioDto
    ){
        String username = authentication.getName();
        usuarioService.updateUser(username, usuarioDto);
        return ResponseEntity.ok("Usuario actualizado");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Authentication authentication){
        String username = authentication.getName();
        usuarioService.deleteUser(username);
        return ResponseEntity.ok("Usuario eliminado");
    }
}
