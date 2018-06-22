/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partials;

import base_datos.ProveedorDAO;
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
import valueObject.Proveedor;
import worldpett.FXMLHomeAdministradorController;

/**
 * FXML Controller class
 *
 * @author Saul
 */
public class FXMLProveedoresController implements Initializable {
    @FXML private Button btEliminarProveedor;
    
    @FXML private TableView <Proveedor> tbProveedores;
    @FXML private TableColumn <Proveedor, String> tbRFC;
    @FXML private TableColumn <Proveedor, String> tbNombre;
    ObservableList<Proveedor> proveedores;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbNombre.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("nombre"));
        tbRFC.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("rfc"));
        buscarProveedores();
    }
    
    @FXML
    public void buscarProveedores() {
        try {
            ProveedorDAO dao = new ProveedorDAO();
            proveedores = FXCollections.observableArrayList(dao.getProveedores());
            tbProveedores.setItems(proveedores);
            dao.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHomeAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void agregarProveedor(ActionEvent event) throws SQLException{
        NuevoProveedorFormController window = new NuevoProveedorFormController("Crear proveedor");
        Proveedor proveedor = window.getProveedor();
        
        if (proveedor.getNombre() != null) {
            tbProveedores.getItems().add(proveedor);
        }
    }
    
    @FXML
    public void modificarProveedor(ActionEvent event) throws SQLException{
        Proveedor selected = tbProveedores.getSelectionModel().getSelectedItem();
        
        ModificarProveedorFormController window = new ModificarProveedorFormController("Modificar empleado", selected);
        
        tbProveedores.refresh();
    }

    @FXML
    public void eliminarProveedor(ActionEvent event) throws SQLException{
        ProveedorDAO dao = new ProveedorDAO();
        Proveedor selected = tbProveedores.getSelectionModel().getSelectedItem();
        int id = selected.getId();
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/FXMLConfirmBox.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            FXMLConfirmBoxController controller = (FXMLConfirmBoxController) fxmlLoader.getController();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Eliminar proveedor");
            stage.setScene(new Scene(root1));  
            
            controller.setMessage("¿Seguro de que desea eliminar al proveedor " + selected.getNombre() + "? \nEsta acción es permanente y no se puede deshacer.");
            stage.showAndWait();
            
            if (controller.getConfirmed()) {
                dao.deleteById(id);

                tbProveedores.getItems().remove(selected);
                AlertBox.pantalla("Proveedor eliminado", "El proveedor " + selected.getNombre() + " ha \nsido eliminado con éxito."); 
                dao.desconectar();
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    class ProveedorFormController extends VBox {
        @FXML protected TextField tfNombre;
        @FXML protected TextField tfRFC;
        @FXML protected Button btAgregarProveedor;
        
        protected Stage stage;
        protected Proveedor proveedor;
        protected String txtBoton, titulo, accion;
        
        public ProveedorFormController(String titulo, String txtBoton, String accion) {
            proveedor = new Proveedor();
            
            this.titulo = titulo;
            this.txtBoton = txtBoton;
            this.accion = accion;
            
            try {
                loadWindow();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        
        public ProveedorFormController(String titulo, String txtBoton, String accion, Proveedor proveedor) {
            this.proveedor = proveedor;
            
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/partials/FXMLProveedorForm.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            Parent root = (Parent) loader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(this.titulo);
            stage.setScene(new Scene(root));  

            btAgregarProveedor.setText(this.txtBoton);
            
            doCustom();
            addBtnAction();
                
            stage.showAndWait();
        }
        
        void doCustom() {}
        
        void addBtnAction() {
        }
    
        Proveedor getProveedor() {
            return proveedor;
        }
    }
    
    class NuevoProveedorFormController extends ProveedorFormController {
        public NuevoProveedorFormController(String titulo) {
            super(titulo, "Crear proveedor", "agregado");
        }
        
        void addBtnAction() {
            btAgregarProveedor.setOnAction(e -> {
                ProveedorDAO dao;

                String rfc = tfRFC.getText();
                String nombre = tfNombre.getText();

                try {
                    dao = new ProveedorDAO();

                    dao.insertarProveedor(nombre,rfc);

                    AlertBox.pantalla("Proveedor " + this.accion, "Proveedor "+nombre+" con RFC "+rfc+" \n " + this.accion + " con exito" );
                    proveedor.setRfc(rfc);
                    proveedor.setNombre(nombre);

                    dao.desconectar();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            });
        }
    }
    
    class ModificarProveedorFormController extends ProveedorFormController {
        public ModificarProveedorFormController(String titulo, Proveedor proveedor) {
            super(titulo, "Modificar proveedor", "modificado", proveedor);
        }
        
        void doCustom() {
            tfRFC.setText(this.proveedor.getRfc());
            tfNombre.setText(this.proveedor.getNombre());
        }
        
        void addBtnAction() {
            btAgregarProveedor.setOnAction(e -> {
                ProveedorDAO dao;
                
                int id = proveedor.getId();
                String rfc = tfRFC.getText();
                String nombre = tfNombre.getText();

                try {
                    dao = new ProveedorDAO();

                    dao.modificarProveedor(id,nombre,rfc);

                    AlertBox.pantalla("Proveedor " + this.accion, "Proveedor "+nombre+" con RFC "+rfc+" \n " + this.accion + " con exito" );
                    proveedor.setRfc(rfc);
                    proveedor.setNombre(nombre);

                    dao.desconectar();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            });
        }
    }
}
