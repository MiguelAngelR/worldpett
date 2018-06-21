/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldpett;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable; 
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michael Liebheart
 */

public class FXMLHomeAdministradorController implements Initializable {
    @FXML private AnchorPane pnMenu;
    
    /**
     * Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setEmpleadosView();
    }
    
    @FXML
    public void setEmpleadosView() {
        try {
            AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource("/partials/FXMLEmpleados.fxml"));
            pnMenu.getChildren().setAll(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void salir(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.setTitle("Worldpet");
        app_stage.show();
    }
        
}

