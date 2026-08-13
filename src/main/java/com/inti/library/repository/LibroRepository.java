package com.inti.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.library.models.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
}
