package com.dizimo.dizimo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dizimo.dizimo.dizimoRepository.DizimoRepository;
import com.dizimo.dizimo.dizimoRepository.UserRepository;
import com.dizimo.dizimo.exceptions.DizimoNotFoundException;
import com.dizimo.dizimo.models.Dizimo;
import com.dizimo.dizimo.models.User;

@RequestMapping("/api")
@RestController
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @PostMapping("/register-new-user")
    public Object saveUser(@RequestBody User user) {
        try {

            BCryptPasswordEncoder pwd_encoder = new BCryptPasswordEncoder();
            Map<String, Object> message = new HashMap<>();
            Map<String, Object> userData = new HashMap<>();

            user.setPassword(pwd_encoder.encode(user.getPassword()));

            User u = repository.save(user);
            
            userData.put("nome", u.getName());
            userData.put("email", u.getEmail());

            message.put("message", "Usuario criado com suscesso");

            message.put("user_data", userData);

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("message", e.getMessage());

            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public Object deleteUser(@PathVariable Long id) {
        try {
            Map<String, Object> message = new HashMap<>();

            repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o id: " + id));

            repository.deleteById(id);

            message.put("message", "Usuário deletado com sucesso");

            return new ResponseEntity<>(message, HttpStatus.OK);
            // .put(null, message)
        } catch (Exception e) {
            Map<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("message", e.getMessage());

            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit-user/{id}")
    public Object editUser(@RequestBody User newUser, @PathVariable Long id) {
        try {

            Map<String, Object> message = new HashMap<>();

            User usr = repository.findById(id)
                    .map(u -> {
                        u.setEmail(newUser.getEmail());
                        u.setName(newUser.getName());
                        return repository.save(u);
                    })
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não econtrado com o id: " + id));

            message.put("message", "Usuário: " + usr.getName() + " editado com sucesso.\n");

            message.put("fresh_body", usr);

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("message", e.getMessage());

            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list-users")
    public Object listUsers() {
        Map<String, Object> userList = new HashMap<>();

        userList.put("name", "Lucas");

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
