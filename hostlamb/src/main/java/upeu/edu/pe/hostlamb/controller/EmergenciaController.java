package upeu.edu.pe.hostlamb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.hostlamb.model.Emergencia;
import upeu.edu.pe.hostlamb.service.EmergenciaService;

@Controller
@RequestMapping("/emergencias")
@RequiredArgsConstructor
public class EmergenciaController {

    private final EmergenciaService emergenciaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emergencias", emergenciaService.listarTodas());
        return "emergencias/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("emergencia", new Emergencia());
        return "emergencias/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        emergenciaService.buscarPorId(id).ifPresent(emergencia -> model.addAttribute("emergencia", emergencia));
        return "emergencias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Emergencia emergencia) {
        emergenciaService.guardar(emergencia);
        return "redirect:/emergencias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        emergenciaService.eliminar(id);
        return "redirect:/emergencias";
    }
}
