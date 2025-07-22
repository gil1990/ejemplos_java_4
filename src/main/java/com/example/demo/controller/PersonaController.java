package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
 

@Controller
@RequestMapping("/personas")
public class PersonaController {
    
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService){
        this.personaService = personaService;
    }
    
    @GetMapping
    public String listar(Model model){
        model.addAttribute("listaPersonas", personaService.listarPersonas());
        return "personas";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model){
        model.addAttribute("persona", new Persona());
        return "crear_persona";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Persona persona){
        personaService.guardarPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPorId(id);
        model.addAttribute("persona", persona);
        return "editar_persona";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute Persona persona){
        personaService.actualizarPersona(id, persona);
        return "redirect:/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);

        return "redirect:/personas";
    }
    
    

}
