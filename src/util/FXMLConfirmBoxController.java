/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saul
 */
public class FXMLConfirmBoxController implements Initializable {
    private boolean confirmed = false;
    
    @FXML private Button btnCancelar;
    @FXML private Button btnOk;
    @FXML private Text txt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void onConfirm() {
        confirmed = true;
        onCancel();
    }
    
    @FXML
    public void onCancel() {
        Stage window = (Stage) btnCancelar.getScene().getWindow();
        window.close();
    }
    
    @FXML
    public boolean getConfirmed() {
        return confirmed;
    }
    
    public void setMessage(String message) {
        txt.setText(message);
    }
}
