package com.jis.frases.service;

import com.jis.frases.models.entities.Frase;

import java.util.List;
import java.util.Optional;

public interface IFrase {

    List<Frase> listAll();
    Frase saveOrUpdate(Frase frase);

    Optional<Frase> findById(Long id);

    void delete(Frase frase);


}
