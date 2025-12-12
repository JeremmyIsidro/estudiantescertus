package com.certus.studentmanagement.controller;

import com.certus.studentmanagement.model.Estudiante;
import com.certus.studentmanagement.service.CursoService;
import com.certus.studentmanagement.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
        return "estudiantes/list";
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("cursos", cursoService.getAllCursos());
        return "estudiantes/form";
    }

    @PostMapping
    public String saveEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.saveEstudiante(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", estudianteService.getEstudianteById(id));
        model.addAttribute("cursos", cursoService.getAllCursos());
        return "estudiantes/form";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return "redirect:/estudiantes";
    }

    @PostMapping("/{estudianteId}/cursos/{cursoId}")
    public String addCurso(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        estudianteService.addCursoToEstudiante(estudianteId, cursoId);
        return "redirect:/estudiantes/editar/" + estudianteId;
    }

    @DeleteMapping("/{estudianteId}/cursos/{cursoId}")
    public String removeCurso(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        estudianteService.removeCursoFromEstudiante(estudianteId, cursoId);
        return "redirect:/estudiantes/editar/" + estudianteId;
    }
}