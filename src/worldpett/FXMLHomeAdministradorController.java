/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldpett;

import base_datos.AlertBox;
import base_datos.Conectar;
import base_datos.Empleado;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable; 
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    @FXML private TabPane tabPane;
    
    /**
     * Initializes the controller class.
     */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            cbCargo.setValue("Administrador");
            cbCargo.setItems(cargosList);
        }
        
        @FXML
        public void cambiarTab(ActionEvent event) {
            String origen = ((Button) event.getSource()).getText();
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

            int tabIndex = 0;
            switch (origen) {
            case "Productos":
                tabIndex = 1;
                break;
            case "Proveedores":
                tabIndex = 2;
                break;
            case "Ventas":
                tabIndex = 3;
                break;
            case "Empleados":
            default:
                tabIndex = 0;
            }
            selectionModel.select(tabIndex);
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
        
        @FXML
        public void salir(ActionEvent event) throws IOException {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
        
    }

