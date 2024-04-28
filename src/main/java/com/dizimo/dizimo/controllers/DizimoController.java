package com.dizimo.dizimo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dizimo.dizimo.dizimoRepository.DizimoRepository;
import com.dizimo.dizimo.exceptions.DizimoNotFoundException;
import com.dizimo.dizimo.models.Dizimo;

@RequestMapping("/api")
@RestController
public class DizimoController {
    private final DizimoRepository repository;

    DizimoController(DizimoRepository dizimoRepository) {
        this.repository = dizimoRepository;
    }

    @GetMapping(value = "/find-by-id", produces = "application/json")
    public Dizimo findById(@RequestParam Long id) {
        return repository.findById(id).orElseThrow(() -> new DizimoNotFoundException(id));

    }

    @GetMapping("/handle")
    public List<Dizimo> handle() {
        return repository.findAll();
    }

    @PostMapping("/make-dizimo")
    public Dizimo postDizimo(@RequestBody Dizimo dizimo) {

        return repository.save(dizimo);
    }

    /* @PostMapping("/register-user")
    public Object registerUser(@RequestBody Dizimo dizimo) {
        try {

            Map<String, Object> list = new HashMap<>();

            list.put("message", "teste");

            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();

            error.put("message", e.getMessage());

            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    } */
}
