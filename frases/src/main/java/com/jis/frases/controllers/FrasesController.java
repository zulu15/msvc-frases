package com.jis.frases.controllers;

import com.jis.frases.models.entities.Frase;
import com.jis.frases.service.IFrase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/frases")
public class FrasesController {

    @Autowired
    private IFrase fraseService;

    private Logger log = LoggerFactory.getLogger(FrasesController.class);

    @GetMapping("/")
    public Map<String,List<Frase>> index(){
        return Collections.singletonMap("frases",fraseService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Frase> frase = fraseService.findById(id);
        if(!frase.isPresent())
            return buildMessage("error","La frase con id: "+id + " no existe!", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(frase.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Frase frase){
        if(frase != null && frase.getFrase() != null && !frase.getFrase().trim().isEmpty()){
            Frase fraseCreated = fraseService.saveOrUpdate(frase);
            return ResponseEntity.status(HttpStatus.CREATED).body(fraseCreated);
        }

        return buildMessage("error","La frase tiene datos inválidos", HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Frase newFrase){
        Optional<Frase> fraseOptional = fraseService.findById(id);
        if(!fraseOptional.isPresent())
            return buildMessage("error","No se encontró la frase a actualizar", HttpStatus.NOT_FOUND);

        if(newFrase == null || newFrase.getFrase().trim().isEmpty())
            return buildMessage("error","La frase tiene datos inválidos", HttpStatus.BAD_REQUEST);

        Frase fraseInDatabase= fraseOptional.get();
        fraseInDatabase.setFrase(newFrase.getFrase());
        fraseService.saveOrUpdate(fraseInDatabase);
        return ResponseEntity.ok(fraseInDatabase);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable  Long id){
        Optional<Frase> fraseOptional = fraseService.findById(id);
        if(!fraseOptional.isPresent())
            return buildMessage("error","No se encontró la frase a eliminar", HttpStatus.NOT_FOUND);

        fraseService.delete(fraseOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<?> buildMessage(String key, String value, HttpStatus
                                           code){
        return new ResponseEntity<>(Map.of(key,value),code);
    }

}
