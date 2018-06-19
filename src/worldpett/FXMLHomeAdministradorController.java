/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldpett;

import base_datos.AlertBox;
import base_datos.Conectar;
import base_datos.Empleado;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Michael Liebheart
 */

public class FXMLHomeAdministradorController implements Initializable {
    
    ObservableList<String> cargosList  = FXCollections.observableArrayList("Administrador","Capturista","Vendedor"); 
    //Declaramos los botones
    @FXML private Button btEliminarEmpleado;
    @FXML private Button btAgregarEmpleado;
    
    //Declaramos los textfield
    
    @FXML private TextField tfCodigoUsuario;
    @FXML private TextField tfNombre;
    @FXML private PasswordField tfPass;
    @FXML private PasswordField tfConfirmPass;
    @FXML private ComboBox cbCargo;
    
    @FXML private TableView <Empleado> tbEmpleados;
    @FXML private TableColumn tbId;
    @FXML private TableColumn tbNombre;
    @FXML private TableColumn tbCargo;
    ObservableList<Empleado> empleados;
    
    @FXML private Pane pnMenu;
    
    /**
     * Initializes the controller class.
     */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            cbCargo.setValue("Administrador");
            cbCargo.setItems(cargosList);
        }    

            
        public void agregarEmpleado() throws SQLException{
          
            Empleado empleado = new Empleado(); 
            
            String codeuser ="";
            Date fecha = new Date(0);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("ddmmyyyy");
            String codigo = formatoFecha.format(fecha);
            
            String nombre      = tfNombre.getText();
            String pass        = tfPass.getText();
            String passConfirm = tfConfirmPass.getText();
            String cargo       = cbCargo.getValue().toString(); 
            
            if(cargo == "Administrador"){
               codeuser = "ca"+codigo+"";
            } else if(cargo == "Vendedor"){
               codeuser = "cv"+codigo+"";
            } else{
               codeuser = "cc"+codigo+"";
            }
            
            if(pass.equals(passConfirm)){   
                
               Conectar con = new Conectar();
               Connection conn = con.getConnection();
              
               empleado.insertarEmpleado(conn,codeuser,nombre,pass,cargo);
               tfCodigoUsuario.setText(codeuser);
               con.desconectar();
               
            }else{      
                AlertBox.pantalla("Error en formulario "," Las contraseñas no coinciden. \n por favor verifica que las contraseñas sean iguales"); 
            }
            
            
           
            
        }
        
        public void eliminarEmpleado() throws SQLException{
                      
            
        }
        
    }

