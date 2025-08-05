package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Usuario;
import com.example.demo.service.UserService;

@Controller
public class RegistroController {

    @Autowired
    private UserService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro"; 
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model){
        usuarioService.registrarUsuario(usuario);
        return "redirect:/login?registro_exitoso";
    }
    
}
