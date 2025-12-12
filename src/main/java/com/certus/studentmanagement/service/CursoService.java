package com.certus.studentmanagement.service;

import com.certus.studentmanagement.model.Curso;
import java.util.List;

public interface CursoService {
    List<Curso> getAllCursos();
    Curso getCursoById(Long id);
    Curso saveCurso(Curso curso);
    void deleteCurso(Long id);
}