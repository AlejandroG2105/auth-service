package com.auth.auth_service.controller;

import com.auth.auth_service.dto.UsuarioDto;
import com.auth.auth_service.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

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
}
