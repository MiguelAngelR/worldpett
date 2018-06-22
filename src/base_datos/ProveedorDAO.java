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
import valueObject.Proveedor;

/**
 *
 * @author Michael Liebheart
 */
public class ProveedorDAO extends BaseDataAccessObject {

    
    public ProveedorDAO() throws SQLException {
        super();
    }
    
    public void insertarProveedor(String nombre, String rfc) throws SQLException {
        PreparedStatement prepareProveedor = connection.prepareStatement("INSERT INTO proveedores(nombre, rfc) VALUES (?,?)");
        prepareProveedor.setString(1,nombre);
        prepareProveedor.setString(2,rfc);
        prepareProveedor.executeUpdate();
        
        prepareProveedor.close();
    }
    
    public void modificarProveedor(int id, String nombre, String rfc) throws SQLException {
        PreparedStatement prepareProveedor = connection.prepareStatement("UPDATE proveedores SET nombre = ?, rfc = ? WHERE id = ?");
        prepareProveedor.setString(1,nombre);
        prepareProveedor.setString(2,rfc);
        prepareProveedor.setInt(3,id);
        prepareProveedor.executeUpdate();
        
        prepareProveedor.close();
    }
    
    public ArrayList<Proveedor> getProveedores() throws SQLException {
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        ResultSet rs = null;
        
        PreparedStatement prepareProveedor = connection.prepareStatement("SELECT id, nombre, rfc from proveedores");
        rs = prepareProveedor.executeQuery();
        
        while(rs.next()) {
            Proveedor proveedor = new Proveedor();
            
            proveedor.setNombre(rs.getString("nombre"));
            proveedor.setRfc(rs.getString("rfc"));
            proveedor.setId(rs.getInt("id"));
            
            proveedores.add(proveedor);
        }
        
        prepareProveedor.close();
        
        return proveedores;
    }
    
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM proveedores where id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
    
}
