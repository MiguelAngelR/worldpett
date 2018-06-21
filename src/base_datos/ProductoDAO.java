/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author Michael Liebheart
 */
public class Producto {
       private int id;
       private int compra;
       private int venta;
       private int cantidad;
       private String descripcion;
       private String proveedor;

    public Producto() {
    }

       
       
    public Producto(int id,int compra, int venta, int cantidad, String descripcion, String proveedor) {
        this.id = id;
        this.compra = compra;
        this.venta = venta;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    
    public void  agregarProducto(int compra, int venta, int cantidad, String descripcion, String proveedor) throws SQLException{
    
        Conectar con = new Conectar();
        Connection conn = con.getConnection();
        Statement stm = conn.createStatement();
        PreparedStatement preparaProducto = conn.prepareStatement("INSERT INTO productos(compra,venta,cantidad,descripcion,proveedor) VALUES (?,?,?,?,?)");
        preparaProducto.setInt(1,compra);
        preparaProducto.setInt(2,venta);
        preparaProducto.setInt(3,cantidad);
        preparaProducto.setString(4,descripcion);
        preparaProducto.setString(5,proveedor);
        preparaProducto.executeUpdate();
        
        AlertBox.pantalla("Agregar Producto", "Producto [ "+descripcion+" ] agregado satisfactoriamente");
        
        stm.close();
        con.desconectar();
       
    }
       
    public static void llenarTabla(Connection connection , ObservableList<Producto> lista) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM productos");
        
        while(result.next()){
            
            lista.add(
                    new Producto(result.getInt(1),
                                 result.getInt(2),
                                 result.getInt(3),
                                 result.getInt(4),
                                 result.getString(5),
                                 result.getString(6)
                                )
            
            );
        }  
        
    }

    
}
