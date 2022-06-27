/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.dao;

import com.mycompany.mavenproject3.interfaces.PersonaInterface;
import com.mycompany.mavenproject3.models.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desarrollo
 */
public class PersonaDao implements PersonaInterface {

    Connection conexion = null;

    public void conectar() {
        String baseDeDatos = "java";
        String usuario = "root";
        String password = "";
        String hosting = "localhost";
        String puerto = "3306";
        String driver = "com.mysql.jdbc.Driver";
        String conexionUrl = "jdbc:mysql://" + hosting + ":" + puerto + "/" + baseDeDatos + "?useSSL=false";
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(conexionUrl, usuario, password);
        } catch (Exception ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Persona registrar(Persona persona) {
        String sql = "INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `email`, `telefono`) VALUES (NULL, '" + persona.getNombre() + "', '" + persona.getApellido() + "', '" + persona.getEmal() + "', '" + persona.getTelefono() + "')";
        Statement statement = null;
        try {
            statement = conexion.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }

    @Override
    public Boolean actualizar(String nombre, String apellido, String email, String telefono,int id) {
        Boolean resultado = false;
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id = ?";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = conexion.prepareStatement(sql);
            preparedStmt.setString(1, nombre);
            preparedStmt.setString(2, apellido);
            preparedStmt.setString(3, email);
            preparedStmt.setString(4, telefono);
            preparedStmt.setInt(5, id);
            preparedStmt.executeUpdate();
            resultado = true;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public Boolean delete(int id) {
        Boolean resultado = false;
        String sql = "DELETE FROM clientes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            conexion.close();
            resultado = true;
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return resultado;
    }

    @Override
    public List<Persona> personas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY id DESC";
        Statement statement = null;
        try {
            statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmal(rs.getString("email"));
                persona.setTelefono(rs.getString("telefono"));
                lista.add(persona);
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
