package com.framed.imageselector.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framed.imageselector.models.Keyword;
import com.framed.imageselector.repositories.KeywordRepository;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<Keyword> getAllKeywords() {
        return this.keywordRepository.findAll();
    }

    public Optional<Keyword> findByCategoria(String categoria) {
        return this.keywordRepository.findByCategoria(categoria);
    }

    public Keyword createKeyword(String categoria) {
        Keyword keywordToSave = null;
        Optional<Keyword> novaKeyword = keywordRepository.findByCategoria(categoria);

        if (novaKeyword.isEmpty()) {
            keywordToSave = keywordRepository.save(new Keyword(categoria.toLowerCase()));
        } else {
            keywordToSave = novaKeyword.get();
        }

        return keywordToSave;
    }

    public void saveAll(List<Keyword> keywords) {
        this.keywordRepository.saveAll(keywords);
    }

    public void delete(Keyword keyword) {
        this.keywordRepository.delete(keyword);
    }

}
