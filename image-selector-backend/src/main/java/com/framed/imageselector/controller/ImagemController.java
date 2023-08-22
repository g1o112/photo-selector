package com.framed.imageselector.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.framed.imageselector.models.CustomImagem;
import com.framed.imageselector.models.IdxCategoria;
import com.framed.imageselector.models.Imagem;
import com.framed.imageselector.models.ImagemDto;
import com.framed.imageselector.models.Keyword;
import com.framed.imageselector.services.ImagemService;
import com.framed.imageselector.services.KeywordService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/images")
public class ImagemController {

    private final ImagemService imagemService;
    private final KeywordService keywordService;
    private final KeywordController keywordController;

    @Autowired
    public ImagemController(ImagemService imagemService, KeywordService keywordService, KeywordController keywordController) {
        this.imagemService = imagemService;
        this.keywordService = keywordService;
        this.keywordController = keywordController;
    }

    @GetMapping("/raw-metadata")
    public ResponseEntity<Map<String, String>> getImageMetadata2(@RequestParam String url) {
        Map<String, String> result = imagemService.extractAllMetadataFromImage(url);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-keywords")
    public ResponseEntity<List<String>> getImageMetadata(@RequestParam String url) {
        List<String> result = imagemService.getKeywordsFromUrl(url);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Long> cadastrarImagem(@RequestParam String url, UriComponentsBuilder uriBuilder) {
        
        Long imagemId = imagemService.addImagem(url);
        
        if (imagemId == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        java.net.URI uri = uriBuilder.path("/cadastrar/{id}").buildAndExpand(imagemId).toUri();
        
        return ResponseEntity.created(uri).body(imagemId);
    }
    
    @PostMapping("/imagem-dto")
    public ResponseEntity<Boolean> receiveImagemDto(@RequestBody ImagemDto imagemDto) {
        Long imagemId = this.cadastrarImagem(imagemDto.getImagemUrl(), UriComponentsBuilder.newInstance()).getBody();
        if (imagemId == null) {
            return ResponseEntity.ok().body(false);
        }
        Arrays.asList(imagemDto.getTagsToRemove()).forEach(tag -> this.keywordController.removeCategoriaFromImage(imagemId, tag));
        Arrays.asList(imagemDto.getTagsToAdd()).forEach(tag -> this.imagemService.addCategoriaToImagem(imagemId, tag));
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/all-images")
    public ResponseEntity<List<CustomImagem>> getAllImages() {
        List<Imagem> allImages = imagemService.getAllImages();
        List<CustomImagem> results = new ArrayList<>();
        allImages.forEach(imagem -> {
            CustomImagem img = new CustomImagem(imagem.getId(), imagem.getUrl(), imagem.getKeywords());
            results.add(img);
        });

        return ResponseEntity.ok(results);
    }

    @PostMapping("/add-categoria/")
    public ResponseEntity<Void> addCategoriaToImagem(@RequestParam Long id, @RequestParam String categoria) {
        boolean result = imagemService.addCategoriaToImagem(id, categoria);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/add-categoria/body")
    public ResponseEntity<Void> receiveNewTagsToImagem(@RequestBody List<IdxCategoria> results) {
        results.forEach(result -> imagemService.addCategoriaToImagem(result.getId(), result.getCategoria()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-categoria/body")
    public ResponseEntity<Void> deleteTagsFromImagem(@RequestBody List<IdxCategoria> results) {
        results.forEach(result -> keywordController.removeCategoriaFromImage(result.getId(), result.getCategoria()));
        return ResponseEntity.ok().body(null);
    }


    @DeleteMapping("/delete-image/{id}")
    public ResponseEntity<Void> deleteImagem(@PathVariable Long id) {

    Optional<Imagem> imgToDelete = imagemService.findById(id);
    if (imgToDelete.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }

    List<Keyword> keywordList = imgToDelete.get().getKeywords();

    imgToDelete.get().setKeywords(Collections.emptyList());
    imagemService.save(imgToDelete.get());

    keywordList.forEach(keyword -> {
        if (keyword.getImagens().isEmpty()) {
            keywordService.delete(keyword);
        }
    });

    imagemService.delete(imgToDelete.get());

    return ResponseEntity.ok().build();
}

@GetMapping("/imagem/{id}")
public ResponseEntity<CustomImagem> findById(@PathVariable Long id) {
    Optional<Imagem> img = this.imagemService.findById(id);
    if (img.isPresent()) {
        CustomImagem result = new CustomImagem(img.get().getId(), img.get().getUrl(), img.get().getKeywords());
        return ResponseEntity.ok().body(result);
    } else {
        return ResponseEntity.badRequest().body(null);
    }
}

    // METODO DE TESTE, REMOVER QUANDO NECESSARIO
    @GetMapping("/start-bd")
    public ResponseEntity<List<Long>> adicionarNoBd() {
        List<String> urls = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        urls.add("https://cdn.46graus.com/files/portfolio/2609/1ce67f7c-87be-497c-b7d6-f44999871ce7/original_f0992e7c-1d25-407f-b68c-e3919fd941c3.jpg");
        urls.add("https://cdn.46graus.com/files/portfolio/2609/1ce67f7c-87be-497c-b7d6-f44999871ce7/original_94793a46-e4d8-4e67-b705-8d917410dbb3.jpg");
        urls.add("https://cdn.46graus.com/files/portfolio/2609/1ce67f7c-87be-497c-b7d6-f44999871ce7/original_1b12434e-9abd-48d0-bdb7-2eed607f3e7c.jpg");
        urls.add("https://cdn.46graus.com/files/portfolio/2609/1ce67f7c-87be-497c-b7d6-f44999871ce7/original_b9db5164-a4f2-4501-9c91-37f3888b5ccf.jpg");
        urls.add("https://cdn-sites-images.46graus.com/files/portfolio/2609/5345eabe-7de3-4773-bb5c-59789c85e7c2/300_fd2a847c-ed82-4289-afc3-1a66ecd028e1.jpg");
        urls.add("https://cdn-sites-images.46graus.com/files/portfolio/2609/5345eabe-7de3-4773-bb5c-59789c85e7c2/300_42487b93-694b-4d0b-a63f-f206aa7a8590.jpg");
        urls.add("https://cdn-sites-images.46graus.com/files/portfolio/2609/5345eabe-7de3-4773-bb5c-59789c85e7c2/300_850014d9-e700-422b-9c76-0e92c185c06e.jpg");
        urls.add("https://cdn-sites-images.46graus.com/files/portfolio/2609/5345eabe-7de3-4773-bb5c-59789c85e7c2/300_c8ad99ca-3f3d-4db0-8ce2-f7912da18e2c.jpg");
        urls.add("https://cdn-sites-images.46graus.com/files/portfolio/2609/5951e885-77b9-4975-a8f4-15531b09422f/300_f7365a5a-e7f9-4c4a-b1f0-8c1b37c104be.jpg");
        return ResponseEntity.ok().body(ids);
    }

}
