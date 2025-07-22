package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Direccion;
import com.example.demo.service.DireccionService;



@Controller
@RequestMapping("/personas/{personaId}/direcciones")
public class DireccionController {
    
    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService){
        this.direccionService = direccionService;
    }

    @GetMapping
    public String listar(@PathVariable Long personaId, Model model) {
        model.addAttribute("direcciones", direccionService.listarPorPersona(personaId));
        model.addAttribute("personaId", personaId); 
        return "direcciones"; 
    }
    
    @GetMapping("/nueva")
    public String mostrarFormulario(@PathVariable Long personaId, Model model){
        model.addAttribute("direccion", new Direccion());
        model.addAttribute("personaId", personaId);
        return "crear_direccion";
    }

    @PostMapping("/guardar")
    public String guardar(@PathVariable Long personaId, @ModelAttribute Direccion direccion){
        direccionService.guardar(personaId, direccion);
        return "redirect:/personas/"+ personaId + "/direcciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long personaId, @PathVariable Long id, Model model) {
        model.addAttribute("direccion", direccionService.obtenerPorId(id));
        model.addAttribute("personaId", personaId);
        return "editar_direccion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPersona(@PathVariable Long personaId, @PathVariable Long id, @ModelAttribute Direccion direccion){
        direccionService.actualizar(id, direccion);
        return "redirect:/personas/"+ personaId + "/direcciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long personaId, @PathVariable Long id) {
        direccionService.eliminar(id);
        return "redirect:/personas/"+ personaId + "/direcciones";
    }
}
