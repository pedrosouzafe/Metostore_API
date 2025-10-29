package com.metostore.api_rest.controllers;

import com.metostore.api_rest.models.User.dto.DadosCadastroUser;
import com.metostore.api_rest.models.User.dto.DadosLoginUser;
import com.metostore.api_rest.repositories.UserRepository;
import com.metostore.api_rest.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DadosLoginUser data) {
        return authService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DadosCadastroUser data) {
        return authService.register(data);
    }
}
