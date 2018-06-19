/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

   

/**
 *
 * @author Michael Liebheart
 */
public class Empleado {
    public SimpleIntegerProperty id        = new SimpleIntegerProperty();
    public SimpleStringProperty nombre     = new SimpleStringProperty();
    public SimpleStringProperty contrasena = new SimpleStringProperty();
    public SimpleStringProperty cargo      = new SimpleStringProperty();

    
    public Empleado(){
        
    }
    
    public Empleado(SimpleStringProperty nombre,SimpleStringProperty contrasena,SimpleStringProperty cargo){
        this.cargo = cargo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        
    }
    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public void setNombre(SimpleStringProperty nombre) {
        this.nombre = nombre;
    }

    public SimpleStringProperty getContrasena() {
        return contrasena;
    }

    public void setContrasena(SimpleStringProperty contrasena) {
        this.contrasena = contrasena;
    }

    public SimpleStringProperty getCargo() {
        return cargo;
    }

    public void setCargo(SimpleStringProperty cargo) {
        this.cargo = cargo;
    }
    
    public void loginEmpelado(int id){

    }
    
    public void insertarEmpleado(Connection conn ,String codigo, String nombre, String pass, String cargo) throws SQLException{
        
                Statement stm = conn.createStatement();
                PreparedStatement prepareEmpleado = conn.prepareStatement("INSERT INTO empleados(codigo,nombre,pass,cargo) VALUES (?,?,?,?)");
                prepareEmpleado.setString(1,codigo);
                prepareEmpleado.setString(2,nombre);
                prepareEmpleado.setString(3,pass);
                prepareEmpleado.setString(4,cargo);
                prepareEmpleado.executeUpdate();
                
                AlertBox.pantalla("Usuario agregado", "Usuario "+nombre+" con cargo "+cargo+" \n y codigo de usuario ["+codigo+"] agregado con exito" );
                
                prepareEmpleado.close();
    }
    
}
