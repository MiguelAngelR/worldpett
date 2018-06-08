/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldpett;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Michael Liebheart
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField etUsuario;
    @FXML
    private PasswordField etPass;
    @FXML
    private Text tLogin;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
      
    }
    
    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
    }
    
    
    @FXML
    private void entraHome(ActionEvent event) throws IOException{
      
        String usuario, pass;
        
        usuario = etUsuario.getText();
        pass = etPass.getText();
        
        if(usuario.equals("Miguel") && pass.equals("123456")){
        
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomeVendedor.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } else {
            tLogin.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
