package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Direccion{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; 
    
    private String direccionpersona; 

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    //getters, setters

    public Long getId(){
        return id; 
    }

    public void setId(Long id){
        this.id = id; 
    }

    public String getDireccionpersona(){
        return direccionpersona; 
    }

    public void setDireccionpersona(String direccionpersona){
        this.direccionpersona = direccionpersona; 
    }

    public Persona getPersona() {
        return persona; 
    }

    public void setPersona(Persona persona){
        this.persona = persona;
    }
}