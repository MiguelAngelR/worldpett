/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partials;

import base_datos.EmpleadoDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.AlertBox;
import util.FXMLConfirmBoxController;
import valueObject.Empleado;
import worldpett.FXMLHomeAdministradorController;

/**
 * FXML Controller class
 *
 * @author Saul
 */
public class FXMLEmpleadosController implements Initializable {
    //Declaramos los botones
    @FXML private Button btEliminarEmpleado;
    
    @FXML private TableView <Empleado> tbEmpleados;
    @FXML private TableColumn <Empleado, String> tbId;
    @FXML private TableColumn <Empleado, String> tbNombre;
    @FXML private TableColumn <Empleado, String> tbCargo;
    ObservableList<Empleado> empleados;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tbId.setCellValueFactory(new PropertyValueFactory<Empleado, String>("codigo"));
        tbNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        tbCargo.setCellValueFactory(new PropertyValueFactory<Empleado, String>("cargo"));
        buscarEmpleados();
    }
    
    @FXML
    public void buscarEmpleados() {
        try {
            EmpleadoDAO dao = new EmpleadoDAO();
            empleados = FXCollections.observableArrayList(dao.getEmpleados());
            tbEmpleados.setItems(empleados);
            dao.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHomeAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void modificarEmpleado(ActionEvent event) throws SQLException{
        Empleado selected = tbEmpleados.getSelectionModel().getSelectedItem();
        
        ModificarEmpleadoFormController window = new ModificarEmpleadoFormController("Modificar empleado", selected);
        
        tbEmpleados.refresh();
    }

    @FXML
    public void eliminarEmpleado(ActionEvent event) throws SQLException{
        EmpleadoDAO dao = new EmpleadoDAO();
        Empleado selected = tbEmpleados.getSelectionModel().getSelectedItem();
        int id = selected.getId();
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/FXMLConfirmBox.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            FXMLConfirmBoxController controller = (FXMLConfirmBoxController) fxmlLoader.getController();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Eliminar empleado");
            stage.setScene(new Scene(root1));  
            
            controller.setMessage("¿Seguro de que desea eliminar al empleado " + selected.getNombre() + "? \nEsta acción es permanente y no se puede deshacer.");
            stage.showAndWait();
            
            if (controller.getConfirmed()) {
                dao.deleteById(id);

                tbEmpleados.getItems().remove(selected);
                AlertBox.pantalla("Empleado eliminado", "El empleado " + selected.getNombre() + " ha \nsido eliminado con exito."); 
                dao.desconectar();
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void agregarEmpleado(ActionEvent event) {
        NuevoEmpleadoFormController window = new NuevoEmpleadoFormController("Crear empleado");
        Empleado empleado = window.getEmpleado();
        
        if (empleado.getNombre() != null) {
            tbEmpleados.getItems().add(empleado);
        }
    }
    
    class EmpleadoFormController extends VBox {
        protected ObservableList<String> cargosList  = FXCollections.observableArrayList("Administrador","Capturista","Vendedor"); 
    
        @FXML protected TextField tfNombre;
        @FXML protected PasswordField tfPass;
        @FXML protected PasswordField tfConfirmPass;
        @FXML protected ComboBox cbCargo;
        @FXML protected Button btAgregarEmpleado;
        
        protected Stage stage;
        protected Empleado empleado;
        protected String txtBoton, titulo, accion;
        
        public EmpleadoFormController(String titulo, String txtBoton, String accion) {
            empleado = new Empleado();
            
            this.titulo = titulo;
            this.txtBoton = txtBoton;
            this.accion = accion;
            
            try {
                loadWindow();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        
        public EmpleadoFormController(String titulo, String txtBoton, String accion, Empleado empleado) {
            this.empleado = empleado;
            
            this.titulo = titulo;
            this.txtBoton = txtBoton;
            this.accion = accion;
            
            try {
                loadWindow();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        
        void loadWindow() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/partials/FXMLEmpleadoForm.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            Parent root = (Parent) loader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(this.titulo);
            stage.setScene(new Scene(root));  

            cbCargo.setValue("Administrador");
            cbCargo.setItems(cargosList);
            btAgregarEmpleado.setText(this.txtBoton);
            
            doCustom();
            addBtnAction();
                
            stage.showAndWait();
        }
        
        void doCustom() {}
        
        void addBtnAction() {
        }
    
        Empleado getEmpleado() {
            return empleado;
        }
    }
    
    class NuevoEmpleadoFormController extends EmpleadoFormController {
        public NuevoEmpleadoFormController(String titulo) {
            super(titulo, "Crear empleado", "agregado");
        }
        
        void addBtnAction() {
            btAgregarEmpleado.setOnAction(e -> {
                EmpleadoDAO dao;

                Date fecha = new Date(0);
                SimpleDateFormat formatoFecha = new SimpleDateFormat("ddmmyyyy");
                String codigo = formatoFecha.format(fecha);
                String codeuser = "";
                String pass = tfPass.getText();
                String passConfirm = tfConfirmPass.getText();
                String cargo = cbCargo.getValue().toString();
                String nombre = tfNombre.getText();

                try {
                    dao = new EmpleadoDAO();

                    if(cargo == "Administrador"){
                       codeuser = "ca"+codigo+"";
                    } else if(cargo == "Vendedor"){
                       codeuser = "cv"+codigo+"";
                    } else{
                       codeuser = "cc"+codigo+"";
                    }

                    if(pass.equals(passConfirm)){
                       dao.insertarEmpleado(codeuser,nombre,pass,cargo);

                       AlertBox.pantalla("Usuario " + this.accion, "Usuario "+nombre+" con cargo "+cargo+", \n codigo de usuario ["+codigo+"] \n y password  " + pass + " " + this.accion + " con exito" );
                       empleado.setCargo(cargo);
                       empleado.setNombre(nombre);
                       empleado.setCodigo(codigo);
                       
                       dao.desconectar();
                    } else{      
                        AlertBox.pantalla("Error en formulario "," Las contraseñas no coinciden. \n por favor verifica que las contraseñas sean iguales"); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            });
        }
    }
    
    class ModificarEmpleadoFormController extends EmpleadoFormController {
        public ModificarEmpleadoFormController(String titulo, Empleado empleado) {
            super(titulo, "Modificar empleado", "modificado", empleado);
        }
        
        void doCustom() {
            cbCargo.setValue(this.empleado.getCargo());
            tfNombre.setText(this.empleado.getNombre());
        }
        
        void addBtnAction() {
            btAgregarEmpleado.setOnAction(e -> {
                EmpleadoDAO dao;
                
                String codeuser = empleado.getCodigo();
                System.out.println(codeuser);
                String pass = tfPass.getText();
                String passConfirm = tfConfirmPass.getText();
                String cargo = cbCargo.getValue().toString();
                String nombre = tfNombre.getText();

                try {
                    dao = new EmpleadoDAO();

                    if(pass.equals(passConfirm)){
                       dao.modificarEmpleado(codeuser,nombre,pass,cargo);

                       AlertBox.pantalla("Usuario " + this.accion, "Usuario "+nombre+" con cargo "+cargo+", \n codigo de usuario ["+codeuser+"] \n y password  " + pass + " " + this.accion + " con exito" );
                       empleado.setCargo(cargo);
                       empleado.setNombre(nombre);
                       empleado.setCodigo(codeuser);
                       
                       dao.desconectar();
                    } else{      
                        AlertBox.pantalla("Error en formulario "," Las contraseñas no coinciden. \n por favor verifica que las contraseñas sean iguales"); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            });
        }
    }
}
