package com.framed.imageselector.models;

import java.util.ArrayList;
import java.util.List;

public class CustomImagem {

    private Long id;

    private String url;

    private List<String> allKeywords;

    public CustomImagem(Long id, String url, List<Keyword> allKeywords) {
        this.id = id;
        this.url = url;
        this.allKeywords = keywordsToString(allKeywords);
    }

    private List<String> keywordsToString(List<Keyword> keywordsClass) {
        List<String> keywordsString = new ArrayList<>();
        keywordsClass.forEach(keyword -> keywordsString.add(keyword.getCategoria()));
        return keywordsString;
    }

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

    public List<String> getAllKeywords() {
        return allKeywords;
    }

    public void setAllKeywords(List<String> allKeywords) {
        this.allKeywords = allKeywords;
    }




}
