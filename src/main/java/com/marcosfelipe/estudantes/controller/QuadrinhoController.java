package com.marcosfelipe.estudantes.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcosfelipe.estudantes.model.Quadrinho;
import com.marcosfelipe.estudantes.repository.QuadrinhoRepository;
 
@RestController
@RequestMapping("/api")
public class QuadrinhoController {
 
    @Autowired
    QuadrinhoRepository quadrinhoRepository;
 
    @GetMapping("/quadrinhos")
    public ResponseEntity<List<Quadrinho>> getAllQuadrinhos(@RequestParam(required = false) String titulo) {
        try {
            @SuppressWarnings("Convert2Diamond")
            List<Quadrinho> quadrinhos = new ArrayList<Quadrinho>();
 
            if (titulo == null) {
                quadrinhoRepository.findAll().forEach(quadrinhos::add);
            } else {
                quadrinhoRepository.findByTitulo(titulo).forEach(quadrinhos::add);
            }
 
            if (quadrinhos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(quadrinhos, HttpStatus.OK);
 
 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/quadrinhos/{id}")
    public ResponseEntity<Quadrinho> getQuadrinhobyId(@PathVariable("id") long id) {
        Optional<Quadrinho> quadrinhoData = quadrinhoRepository.findById(id);
 
        if (quadrinhoData.isPresent()) {
            return new ResponseEntity<>(quadrinhoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @PostMapping("/quadrinhos")
    public ResponseEntity<Quadrinho> createQuadrinho(@RequestBody Quadrinho quadrinho) {
        try {
            Quadrinho _quadrinho  = quadrinhoRepository
                    .save(new Quadrinho(quadrinho.getTitulo(), quadrinho.getDescricao(), quadrinho.getEditora()));
            return new ResponseEntity<>(_quadrinho, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @PutMapping("/quadrinhos/{id}")
    public ResponseEntity<Quadrinho> updateQuadrinho(@PathVariable("id") long id, @RequestBody Quadrinho quadrinho) {
        Optional<Quadrinho> quadrinhoData = quadrinhoRepository.findById(id);
 
        if (quadrinhoData.isPresent()) {
            Quadrinho _quadrinho = quadrinhoData.get();
            _quadrinho.setTitulo(quadrinho.getTitulo());
            _quadrinho.setDescricao(quadrinho.getDescricao());
            _quadrinho.setEditora(quadrinho.getEditora());
            return new ResponseEntity<>(quadrinhoRepository.save(_quadrinho), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/quadrinhos/{id}")
    public ResponseEntity<HttpStatus> deleteQuadrinho(@PathVariable("id") long id) {
        try {
            quadrinhoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @DeleteMapping("/quadrinhos")
    public ResponseEntity<HttpStatus> deleteAllQuadrinhos() {
        try {
            quadrinhoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/editoras/{editora}")
    public ResponseEntity<List<Quadrinho>> findByEditora(@PathVariable("editora") String editora) {
        try {
            List<Quadrinho> quadrinhos = quadrinhoRepository.findByEditora(editora);
 
            if (quadrinhos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
 
            return new ResponseEntity<>(quadrinhos, HttpStatus.OK);
 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
}