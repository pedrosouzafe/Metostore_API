package com.metostore.api_rest.services;

import com.metostore.api_rest.models.User.User;
import com.metostore.api_rest.models.User.dto.DadosCadastroUser;
import com.metostore.api_rest.models.User.dto.DadosLoginUser;
import com.metostore.api_rest.models.User.dto.DadosRetornoUser;
import com.metostore.api_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    // POST /auth/login
    public ResponseEntity<?> login(DadosLoginUser data) {
        String email = data.email();
        String password = data.password();

        User user = userRepository.findByEmail(email);

        if(user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidos");
        }

        return ResponseEntity.ok(new DadosRetornoUser(user));
    }

    // POST /auth/register
    public ResponseEntity<?> register(DadosCadastroUser data) {
        if(userRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado");
        }

        User user = userRepository.save(new User(data));

        return ResponseEntity.ok(new DadosRetornoUser(user));
    }
}
