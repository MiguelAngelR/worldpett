/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldpett;

import base_datos.AlertBox;
import base_datos.Conectar;
import base_datos.Producto;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Michael Liebheart
 */
public class FXMLHomeVendedorController implements Initializable {

    private ObservableList <Producto> listaProductos;
    
    
    @FXML TextField tfTotal;
    @FXML TextField tfPago;
    @FXML TextField tfCambio;
    
    int total,pago,cambio;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conectar con;
        
        try {
            con = new Conectar();
            con.getConnection(); 
            Producto.llenarTabla(con.getConnection(), listaProductos);
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHomeVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tfTotal.setText("100");
        listaProductos = FXCollections.observableArrayList();
    }    
    
    public void cobrar(){
    
       total = Integer.parseInt(tfTotal.getText());
       pago = Integer.parseInt(tfPago.getText());
       
       if(pago < total){
           
           AlertBox.pantalla("Error al cobrar", "La cantidad de efectivo ingresada es menor \n por favor ingrese una cantidad superior al total");
           
       }else{
   
           cambio = pago - total;
           tfCambio.setText(Integer.toString(cambio));
       }
       
    }
    
}
