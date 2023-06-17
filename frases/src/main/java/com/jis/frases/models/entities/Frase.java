package com.jis.frases.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "frases")
public class Frase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String frase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }


    public Frase(Long id, String frase) {
        this.id = id;
        this.frase = frase;
    }

    public Frase() {
    }

    @Override
    public String toString() {
        return  "id=" + id + ", frase='" + frase + '\'';
    }
}
