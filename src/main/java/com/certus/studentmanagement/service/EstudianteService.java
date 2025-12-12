package com.certus.studentmanagement.service;

import com.certus.studentmanagement.model.Estudiante;
import java.util.List;

public interface EstudianteService {
    List<Estudiante> getAllEstudiantes();
    Estudiante getEstudianteById(Long id);
    Estudiante saveEstudiante(Estudiante estudiante);
    void deleteEstudiante(Long id);
    void addCursoToEstudiante(Long estudianteId, Long cursoId);
    void removeCursoFromEstudiante(Long estudianteId, Long cursoId);
}