package com.example.demo.config;

import org.springframework.boot.CommandLineRunner; 
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Rol; 
import com.example.demo.repository.RolRepository;

@Component
public class DataSeeder implements CommandLineRunner{

    @Autowired
    private RolRepository rolRepository; 

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.findByNombre("USER").isEmpty()){
            rolRepository.save(new Rol("USER"));
        }

        if (rolRepository.findByNombre("ADMIN").isEmpty()){
            rolRepository.save(new Rol("ADMIN"));
        }
    }
    
}
