package com.metostore.api_rest.models.User;

import com.metostore.api_rest.models.User.dto.DadosCadastroUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders =  new ArrayList<>();

    public User(DadosCadastroUser data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
    }
}
