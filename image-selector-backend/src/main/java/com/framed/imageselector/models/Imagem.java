package com.framed.imageselector.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String url;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "imagem_keyword", joinColumns = @JoinColumn(name = "imagem_id"), inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private List<Keyword> keywords = new ArrayList<>();


    //CONSTRUCTORS
    public Imagem(String url) {
        this.url = url;
    }

    public Imagem() {}


    //GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Keyword> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    //OUTROS MÉTODOS
    public void addKeyword(Keyword keyword) {
        if (keyword != null) {
            keywords.add(keyword);
            keyword.getImagens().add(this);
        }

    }

    public void removeKeyword(Keyword keyword) {
        keywords.remove(keyword);
        keyword.getImagens().remove(this);
    }
    
    
}
