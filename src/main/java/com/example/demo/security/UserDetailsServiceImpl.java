package com.example.demo.security;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository; 

    // @Override
    // public UserDetailsServiceImpl(UsuarioRepository usuarioRepository){
    //     this.usuarioRepository = usuarioRepository;
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.isEnabled(),
            true, true, true, 
            Collections.singleton(new SimpleGrantedAuthority("ROLE_"+ usuario.getRol().getNombre()))
        );
    }

}
