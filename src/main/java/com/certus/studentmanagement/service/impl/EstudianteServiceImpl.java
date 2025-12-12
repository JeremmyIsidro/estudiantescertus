package com.certus.studentmanagement.service.impl;

import com.certus.studentmanagement.model.Curso;
import com.certus.studentmanagement.model.Estudiante;
import com.certus.studentmanagement.repository.CursoRepository;
import com.certus.studentmanagement.repository.EstudianteRepository;
import com.certus.studentmanagement.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante getEstudianteById(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    @Override
    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public void addCursoToEstudiante(Long estudianteId, Long cursoId) {
        Estudiante estudiante = getEstudianteById(estudianteId);
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        estudiante.addCurso(curso);
        estudianteRepository.save(estudiante);
    }

    @Override
    public void removeCursoFromEstudiante(Long estudianteId, Long cursoId) {
        Estudiante estudiante = getEstudianteById(estudianteId);
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        estudiante.removeCurso(curso);
        estudianteRepository.save(estudiante);
    }
}