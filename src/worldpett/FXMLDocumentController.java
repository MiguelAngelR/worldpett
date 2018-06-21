/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldpett;

import base_datos.BaseDataAccessObject;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    BaseDataAccessObject con;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
      
    }
    
       
    @FXML
    private void entraHome(ActionEvent event) throws IOException, SQLException{
       
       //con = new BaseDataAccessObject();
       //Connection reg = con.getConnection();
        int idUsuario;
        String pass;
        String cargo;
        String codeUsuario = etUsuario.getText();
        pass = etPass.getText();
        
        BaseDataAccessObject con = new BaseDataAccessObject();
        Connection reg = con.getConnection();
        Statement stm =  reg.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM empleados WHERE codigo='"+codeUsuario+"' AND pass='"+pass+"'");
        
        if(rs.next()){
            cargo = rs.getString("cargo");
            
            switch(cargo){
                case "Administrador":
                
                    Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomeAdministrador.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                    
                    rs.close();
                    con.desconectar();
                    break;
                case "Capturista":
                    Parent home_capturista = FXMLLoader.load(getClass().getResource("FXMLHomeCapturista.fxml"));
                    Scene home_page_capturista = new Scene(home_capturista);
                    Stage app_capturista = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_capturista.setScene(home_page_capturista);
                    app_capturista.show();
                   
                    
                    rs.close();
                    con.desconectar();
                    break;
                    
                case "Vendedor":
                    Parent home_vendedor = FXMLLoader.load(getClass().getResource("FXMLHomeVendedor.fxml"));
                    Scene home_page_vendedor = new Scene(home_vendedor);
                    Stage app_vendedor = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_vendedor.setScene(home_page_vendedor);
                    app_vendedor.show();
                    
                    rs.close();
                    con.desconectar();
                    break;
                    
                default:
                    
                    rs.close();
                    con.desconectar();
                    break;
            
            }
           
        } else {
            tLogin.setVisible(true);
            rs.close();
            con.desconectar();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
