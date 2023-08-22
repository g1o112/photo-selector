package com.framed.imageselector.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.framed.imageselector.models.Imagem;
import com.framed.imageselector.models.Keyword;
import com.framed.imageselector.repositories.ImagemRepository;

import java.net.URL;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;
    private final KeywordService keywordService;

    @Autowired
    public ImagemService(ImagemRepository imagemRepository, KeywordService keywordService) {
        this.imagemRepository = imagemRepository;
        this.keywordService = keywordService;
    }

    public Map<String, String> extractAllMetadataFromImage(String imageUrl) {
        Map<String, String> metadataKeywords = new HashMap<>();

        try {
            URL url = new URL(imageUrl);
            Metadata metadata = ImageMetadataReader.readMetadata(url.openStream());

            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    String tagName = tag.getTagName();
                    String tagDescription = tag.getDescription();
                    metadataKeywords.put(tagName, tagDescription);
                }
            }
        } catch (IOException | ImageProcessingException erro) {
            System.out.println("Erro ao ler a imagem: " + erro.getMessage());
        }

        return metadataKeywords;
    }

    public List<String> getKeywordsFromUrl(String imagemUrl) {
        try {
            URL url = new URL(imagemUrl);
            Metadata metadata = ImageMetadataReader.readMetadata(url.openStream());
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if (tag.getTagName().equals("Keywords")) {
                        return Arrays.asList(tag.getDescription().split(";"));
                    }
                }
            }
        } catch (IOException | ImageProcessingException erro) {
            System.out.println("Erro ao ler os metadados da imagem: " + erro.getMessage());
        }
        return Collections.emptyList();

    }
    
    public void delete(Imagem imagem) {
        this.imagemRepository.delete(imagem);
    }

    public Long addImagem(String imageUrl) {

        Imagem imagem = createImagem(imageUrl);

        if (imagem == null) {
            return null;
        }

        List<String> palavrasChave = getKeywordsFromUrl(imagem.getUrl());
        List<Keyword> keywords = new ArrayList<>();

        palavrasChave.forEach( palavra -> {
            Keyword keywordToAdd = keywordService.createKeyword(palavra);
            keywords.add(keywordToAdd);
        });

        keywords.forEach( keyword -> {
            if (!imagem.getKeywords().contains(keyword)) {
                imagem.addKeyword(keyword);
            }
        });

        imagemRepository.save(imagem);
        return imagem.getId();
    }

    private Imagem createImagem(String url) {

        if (urlIsNotValid(url) || imagemRepository.findByUrl(url) != null ) {
            return null;
        }
        
        return imagemRepository.save(new Imagem(url));
    }

    public List<Imagem> getAllImages() {
        return imagemRepository.findAll();
    }

    private boolean urlIsNotValid(String url) {
        try {
            new java.net.URL(url);
            return false;
        } catch (java.net.MalformedURLException e) {
            return true;
        }
    }

    public Optional<Imagem> findById(Long id) {
        return imagemRepository.findById(id);
    }

    public boolean addCategoriaToImagem(Long idImagem, String categoria) {
        Optional<Imagem> img = findById(idImagem);
        
        if (img.isEmpty()) {
            return false;
        }
        
        Optional<Keyword> optionalKw = keywordService.findByCategoria(categoria);

        Keyword kw = null;
        if (optionalKw.isEmpty()) {
            kw = keywordService.createKeyword(categoria);
        } else {
            kw = optionalKw.get();
        }
        
        if (img.get().getKeywords().contains(kw)) {
            return false;
        }

        img.get().addKeyword(kw);
        imagemRepository.save(img.get());
        return true;
    }

    public void save(Imagem imagem) {
        this.imagemRepository.save(imagem);
    }


}
