package com.jis.frases.service;

import com.jis.frases.models.dao.FraseDAO;
import com.jis.frases.models.entities.Frase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FraseService implements IFrase {

    @Autowired
    private FraseDAO fraseDAO;

    @Override
    public List<Frase> listAll() {
        return fraseDAO.findAll();
    }

    @Override
    public Frase saveOrUpdate(Frase frase) {
        return fraseDAO.save(frase);
    }

    @Override
    public Optional<Frase> findById(Long id) {
        return fraseDAO.findById(id);
    }

    @Override
    public void delete(Frase frase) {
        fraseDAO.delete(frase);
    }
}
