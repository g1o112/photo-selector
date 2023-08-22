package com.framed.imageselector.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.framed.imageselector.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
    
}
