package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    public List<Persona> listarPersonas(){
        return personaRepository.findAll();
    }

    public void guardarPersona(Persona persona){
        personaRepository.save(persona);
    }

    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    public void actualizarPersona (Long id, Persona personaActualizada){
        Persona persona = obtenerPorId(id);
        persona.setNombre(personaActualizada.getNombre());
        persona.setCorreo(personaActualizada.getCorreo());
        personaRepository.save(persona);
    }
    
    public void eliminarPersona(Long id){
        personaRepository.deleteById(id);
    }

}
