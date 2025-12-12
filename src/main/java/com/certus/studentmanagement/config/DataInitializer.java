package com.certus.studentmanagement.config;

import com.certus.studentmanagement.model.Curso;
import com.certus.studentmanagement.model.Estudiante;
import com.certus.studentmanagement.repository.CursoRepository;
import com.certus.studentmanagement.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (cursoRepository.count() == 0) {
            System.out.println("Creando cursos de ejemplo...");

            Curso curso1 = new Curso("Arquitectura de TI", "ATI001", 4);
            Curso curso2 = new Curso("Desarrollo Web", "DEW001", 3);
            Curso curso3 = new Curso("Base de Datos", "BDD001", 4);
            Curso curso4 = new Curso("Programacion Orientada a Objetos", "POO001", 5);

            cursoRepository.save(curso1);
            cursoRepository.save(curso2);
            cursoRepository.save(curso3);
            cursoRepository.save(curso4);

            System.out.println("Cursos de ejemplo creados");
        }

        if (estudianteRepository.count() == 0) {
            System.out.println("Creando estudiantes de ejemplo...");

            Estudiante est1 = new Estudiante("Juan", "Perez", "juan.perez@certus.edu.pe", "EST001");
            Estudiante est2 = new Estudiante("Maria", "Garcia", "maria.garcia@certus.edu.pe", "EST002");
            Estudiante est3 = new Estudiante("Carlos", "Lopez", "carlos.lopez@certus.edu.pe", "EST003");

            Curso curso1 = cursoRepository.findById(1L).orElse(null);
            Curso curso2 = cursoRepository.findById(2L).orElse(null);

            if (curso1 != null && curso2 != null) {
                est1.getCursos().add(curso1);
                est1.getCursos().add(curso2);
                est2.getCursos().add(curso1);
            }

            estudianteRepository.save(est1);
            estudianteRepository.save(est2);
            estudianteRepository.save(est3);

            System.out.println("Estudiantes de ejemplo creados");
        }

        System.out.println("========================================");
        System.out.println("Aplicacion lista!");
        System.out.println("Accede a: http://localhost:8080");
        System.out.println("========================================");
    }
}