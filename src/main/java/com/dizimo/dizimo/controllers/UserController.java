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
            Map<String, Object> message = new HashMap<>();

            User u = repository.save(user);// User fresh if needed

            message.put("message", "Usuario criado com suscesso");

            message.put("fresh", u);

            return new ResponseEntity<>(message, HttpStatus.OK);

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

            repository.findById(id)
                    .map(u -> {
                        u.setEmail(newUser.getEmail());
                        u.setName(newUser.getName());
                        return repository.save(u); // Terminal operation: save the updated user
                    })
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
            message.put("message", newUser.getEmail() + newUser.getPassword());

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("message", e.getMessage());

            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/list-users")
    public Object listUsers() {
        Map<String, Object> userList = new HashMap<>();

        userList.put("name", "Lucas");

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
