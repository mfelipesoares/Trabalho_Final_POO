package com.marcosfelipe.estudantes.repository;

 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosfelipe.estudantes.model.Quadrinho;
 
public interface QuadrinhoRepository extends JpaRepository<Quadrinho, Long> {
 
    List<Quadrinho> findByEditora(String editora);
 
    List<Quadrinho> findByTitulo(String titulo);
 
}