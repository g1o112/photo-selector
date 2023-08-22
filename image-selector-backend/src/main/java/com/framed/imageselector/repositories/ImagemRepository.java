package com.framed.imageselector.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.framed.imageselector.models.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
    Imagem findByUrl(String url);

}
