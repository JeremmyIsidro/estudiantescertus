package com.certus.studentmanagement.repository;

import com.certus.studentmanagement.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    boolean existsByEmail(String email);
    boolean existsByCodigo(String codigo);
}