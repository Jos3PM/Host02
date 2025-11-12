package upeu.edu.pe.hostlamb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.hostlamb.model.Doctor;
import upeu.edu.pe.hostlamb.service.DoctorService;

@Controller
@RequestMapping("/doctores")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("doctores", doctorService.listarTodos());
        return "doctores/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctores/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        doctorService.buscarPorId(id).ifPresent(doctor -> model.addAttribute("doctor", doctor));
        return "doctores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Doctor doctor) {
        doctorService.guardar(doctor);
        return "redirect:/doctores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        doctorService.eliminar(id);
        return "redirect:/doctores";
    }
}
