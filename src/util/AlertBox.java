/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Michael Liebheart
 */
public class AlertBox {
 
    public static void pantalla(String tittle, String mensaje){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(tittle);
        window.setMinWidth(200);
        
        Label label = new Label();
        label.setText(mensaje);
        Button closeButton = new Button("Cerrar alerta");
        closeButton.setOnAction(e -> window.close());
         
        VBox layout  = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout,350,200);
        window.setScene(scene);
        window.showAndWait();
        
    }   
    
}
