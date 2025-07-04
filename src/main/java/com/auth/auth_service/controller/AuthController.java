package com.auth.auth_service.controller;

import com.auth.auth_service.dto.AuthRequest;
import com.auth.auth_service.dto.AuthResponse;
import com.auth.auth_service.dto.RegistroRequest;
import com.auth.auth_service.service.AuthService;
import com.auth.auth_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;
// Se manejan las rutas relacionadas con autenticacion.

    //Se definen las rutas relacionadas con autenticacion bajo el path base /api/auth

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse authResponse = authService.login(request);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/registry")
    public ResponseEntity<AuthResponse> register (@Valid @RequestBody RegistroRequest request) {
        try {
            usuarioService.registrarUsuario(request);

            AuthResponse authResponse = authService.login(new AuthRequest(request.getUsername(), request.getPassword()));
            return ResponseEntity.ok(authResponse);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage()));
        }
    }
}
