package com.framed.imageselector.models;

import java.util.ArrayList;
import java.util.List;

public class CustomKeyword {

    private Long id;

    private String categoria;

    private List<CustomImagem> imagensString;

    public CustomKeyword(Long id, String categoria, List<Imagem> imagens) {
        this.id = id;
        this.categoria = categoria;
        this.imagensString = imagensToCustomImages(imagens);
    }

    private List<CustomImagem> imagensToCustomImages(List<Imagem> imagens) {
        List<CustomImagem> results = new ArrayList<>();
        imagens.forEach(img -> {
            CustomImagem imagem = new CustomImagem(img.getId(), img.getUrl(), img.getKeywords());
            results.add(imagem);
        });
        return results;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CustomImagem> getImagensString() {
        return imagensString;
    }

    public void setImagensString(List<CustomImagem> imagensString) {
        this.imagensString = imagensString;
    }
    
}
