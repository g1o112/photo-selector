package com.framed.imageselector.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.framed.imageselector.models.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    
    Optional<Keyword> findByCategoria(String categoria);

    List<Keyword> findAll();

}
