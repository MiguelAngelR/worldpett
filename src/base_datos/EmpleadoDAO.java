/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base_datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import valueObject.Empleado;

/**
 *
 * @author Michael Liebheart
 */
public class EmpleadoDAO extends BaseDataAccessObject {

    
    public EmpleadoDAO() throws SQLException {
        super();
    }

    public void loginEmpelado(int id){

    }
    
    public void insertarEmpleado(String codigo, String nombre, String pass, String cargo) throws SQLException {
        PreparedStatement prepareEmpleado = connection.prepareStatement("INSERT INTO empleados(codigo,nombre,pass,cargo) VALUES (?,?,?,?)");
        prepareEmpleado.setString(1,codigo);
        prepareEmpleado.setString(2,nombre);
        prepareEmpleado.setString(3,pass);
        prepareEmpleado.setString(4,cargo);
        prepareEmpleado.executeUpdate();
        
        prepareEmpleado.close();
    }
    
    public void modificarEmpleado(String codigo, String nombre, String pass, String cargo) throws SQLException {
        PreparedStatement prepareEmpleado = connection.prepareStatement("UPDATE empleados SET nombre = ?, pass = ?, cargo = ? WHERE codigo = ?");
        prepareEmpleado.setString(1,nombre);
        prepareEmpleado.setString(2,pass);
        prepareEmpleado.setString(3,cargo);
        prepareEmpleado.setString(4,codigo);
        prepareEmpleado.executeUpdate();
        
        prepareEmpleado.close();
    }
    
    public ArrayList<Empleado> getEmpleados() throws SQLException {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        ResultSet rs = null;
        
        PreparedStatement prepareEmpleado = connection.prepareStatement("SELECT id, nombre, cargo, codigo from empleados");
        rs = prepareEmpleado.executeQuery();
        
        while(rs.next()) {
            Empleado empleado = new Empleado();
            
            empleado.setNombre(rs.getString("nombre"));
            empleado.setCargo(rs.getString("cargo"));
            empleado.setCodigo(rs.getString("codigo"));
            empleado.setId(rs.getInt("id"));
            
            empleados.add(empleado);
        }
        
        prepareEmpleado.close();
        
        return empleados;
    }
    
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM empleados where id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
    
}
