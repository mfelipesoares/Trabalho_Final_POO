package com.marcosfelipe.estudantes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="quadrinhos")
public class Quadrinho {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    @Column(name = "titulo")
    private String titulo;
 
    @Column(name = "descricao")
    private String descricao;
 
    @Column(name = "editora")
    private String editora;
 
    public Quadrinho() {
 
    }
 
    public Quadrinho(String titulo, String descricao, String editora) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.editora = editora;
    }
 
    public long getId() {
        return id;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public String getDescricao() {
        return descricao;
    }
 
    public String getEditora() {
        return editora;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
 
    public void setEditora(String editora) {
        this.editora = editora;
    }
 
    @Override
    public String toString() {
        return "Quadrinho [id=" + id + ", titulo=" + titulo + ", desc=" + descricao + ", editora=" + editora + "]";
 
    }
}