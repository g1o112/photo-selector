package com.framed.imageselector.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.framed.imageselector.models.CustomImagem;
import com.framed.imageselector.models.CustomKeyword;
import com.framed.imageselector.models.Imagem;
import com.framed.imageselector.models.Keyword;
import com.framed.imageselector.services.ImagemService;
import com.framed.imageselector.services.KeywordService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/keywords")
public class KeywordController {

    private final KeywordService keywordService;
    private final ImagemService imagemService;

    public KeywordController(KeywordService keywordService, ImagemService imagemService) {
        this.keywordService = keywordService;
        this.imagemService = imagemService;
    }

    @GetMapping("/exibir/{categoria}")
    public ResponseEntity<List<CustomImagem>> getAllImagesFromCategoria(@PathVariable String categoria) {
        Optional<Keyword> okw = this.keywordService.findByCategoria(categoria);
        if (okw.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        CustomKeyword ckw = new CustomKeyword(okw.get().getId(), okw.get().getCategoria(), okw.get().getImagens());
        return ResponseEntity.ok().body(ckw.getImagensString());
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Void> removeCategoriaFromImage(@RequestParam Long id, @RequestParam String categoria) {
        Optional<Imagem> oimg = this.imagemService.findById(id);
        Optional<Keyword> okw = keywordService.findByCategoria(categoria);
        if (oimg.isEmpty() || okw.isEmpty()) {
            return ResponseEntity.ok().build();
        }

        Imagem img = oimg.get();
        Keyword kw = okw.get();

        img.getKeywords().remove(kw);
        this.imagemService.save(img);

        if (kw.getImagens().isEmpty()) {
            this.keywordService.delete(kw);
        }
        
        return ResponseEntity.ok().body(null);
        
    }


    @GetMapping("/categorias")
    public ResponseEntity<String[]> getAllCategorias() {
        List<String> categoriaString = new ArrayList<>();
        this.keywordService.getAllKeywords().forEach(keyword -> categoriaString.add(keyword.getCategoria()));
        String[] categoriasArray = categoriaString.toArray(new String[0]);
        return ResponseEntity.ok().body(categoriasArray);
    }

    

    

    
    
}
