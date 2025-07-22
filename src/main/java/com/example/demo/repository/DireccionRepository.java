package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.example.demo.model.Direccion; 

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    List<Direccion> findByPersonaId(Long personaId);
}
