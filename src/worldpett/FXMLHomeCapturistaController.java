/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldpett;

import util.AlertBox;
import base_datos.BaseDataAccessObject;
import base_datos.ProductoDAO;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Michael Liebheart
 */
public class FXMLHomeCapturistaController implements Initializable {

    
    @FXML Button btEliminar;
    @FXML Button btAgregar;
    
    @FXML TextField tfCompra;
    @FXML TextField tfVenta;
    @FXML TextField tfCantidad;
    @FXML TextField tfDescripcion;
    @FXML TextField tfProveedor;
    
    @FXML Pane   pnProductos;
    @FXML Pane   pnProveedores;
    
    String descripcion,proveedor;
    int compra,venta,cantidad;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void agregarProducto() throws SQLException{
        
        ProductoDAO producto = new ProductoDAO();
        compra = Integer.parseInt( tfCompra.getText());
        venta = Integer.parseInt(tfVenta.getText());
        cantidad = Integer.parseInt(tfCantidad.getText());
        proveedor = tfProveedor.getText();
        descripcion = tfDescripcion.getText();
        
        
        
        producto.agregarProducto(compra, venta, cantidad, descripcion, proveedor);
       
        
        tfCompra.setText("");
        tfVenta.setText("");
        tfDescripcion.setText("");
        tfCantidad.setText("");
        tfProveedor.setText("");
    }
    
    public void eliminarProducto(){
        
        
        
    }

 
}
