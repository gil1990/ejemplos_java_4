package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
     @GetMapping("/login")
     public String mostrarLogin() {
         return "login";
     }

     @GetMapping("/default")
     public String redireccionPorRol(org.springframework.security.core.Authentication auth){
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/home";
        }
     }
}
