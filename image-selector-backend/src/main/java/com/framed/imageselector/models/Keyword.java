package com.framed.imageselector.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Keyword {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String categoria;

    @ManyToMany(mappedBy = "keywords")
    private List<Imagem> imagens = new ArrayList<>();

    public Keyword(String categoria) {
        this.categoria = categoria;
    }

    public Keyword() {}


    public String getCategoria() {
        return categoria;
    }

    public List<Imagem> getImagens() {
        if (this.imagens == null) {
            return Collections.emptyList();
        }
        return this.imagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public void addImagem(Imagem imagem) {
        if (imagens == null) {
            imagens = new ArrayList<>();
        }
        imagens.add(imagem);
        imagem.getKeywords().add(this);
    }

    public void removeImagem(Imagem imagem) {
        imagens.remove(imagem);
        imagem.getKeywords().remove(this);
    }

}
