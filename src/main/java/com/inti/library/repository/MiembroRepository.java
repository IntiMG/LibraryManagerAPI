package com.inti.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.library.models.Miembro;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Long> {
    
}
