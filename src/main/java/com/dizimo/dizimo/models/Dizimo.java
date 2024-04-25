package com.dizimo.dizimo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Dizimo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, length = 100)
    private Float ammount;

    @Column(nullable = false, length = 10)
    private String data;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
    private User user;
    
    public Dizimo() {
    }

    public Dizimo(String nome, Float ammount, String data) {
        this.ammount = ammount;
        this.data = data;
    }

   
    public Float getAmmount() {
        return ammount;
    }

    public String getData() {
        return data;
    }

}
