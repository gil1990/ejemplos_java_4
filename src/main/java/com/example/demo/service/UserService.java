package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.model.Rol;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository; 
    
    @Autowired
    private RolRepository rolRepository;

    @Autowired BCryptPasswordEncoder passwordEncoder;

    public void registrarUsuario (Usuario usuario){
        Rol rol = rolRepository.findByNombre("USER").get(); 
        usuario.setRol(rol);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setEnabled(true);

        usuarioRepository.save(usuario);
    }
}
