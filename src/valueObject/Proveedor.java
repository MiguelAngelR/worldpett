/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Saul
 */
public class Proveedor {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty rfc;
    
    
    public Proveedor() {
        this.rfc = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty();
    }
    
    public Proveedor(String nombre, String rfc, int id) {
        this.rfc = new SimpleStringProperty(rfc);
        this.nombre = new SimpleStringProperty(nombre);
        this.id = new SimpleIntegerProperty(id);
    }
    
    public int getId() {
        return id.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public String getRfc() {
        return rfc.get();
    }

    public void setRfc(String rfc) {
        this.rfc.set(rfc);
    }
}
