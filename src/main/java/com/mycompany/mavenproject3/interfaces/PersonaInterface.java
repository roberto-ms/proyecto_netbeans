/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject3.interfaces;

import com.mycompany.mavenproject3.models.Persona;
import java.util.List;

/**
 *
 * @author Desarrollo
 */
public interface PersonaInterface {
    public Persona registrar(Persona persona);
    public Boolean actualizar(String nombre,String apellido,String email, String telefono,int id);
    public Boolean delete(int id);
    public List<Persona> personas();
}
