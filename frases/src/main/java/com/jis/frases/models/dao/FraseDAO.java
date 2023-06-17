package com.jis.frases.models.dao;

import com.jis.frases.models.entities.Frase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraseDAO extends JpaRepository<Frase, Long> {
}
