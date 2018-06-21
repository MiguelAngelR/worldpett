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
public class Empleado {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty cargo;
    private SimpleStringProperty codigo;
    
    
    public Empleado() {
        this.cargo = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.codigo = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty();
    }
    
    public Empleado(String nombre, String cargo, String codigo, int id) {
        this.cargo = new SimpleStringProperty(cargo);
        this.nombre = new SimpleStringProperty(nombre);
        this.codigo = new SimpleStringProperty(codigo);
        this.id = new SimpleIntegerProperty(id);
    }
    
    public int getId() {
        return id.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }
    
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public String getCargo() {
        return cargo.get();
    }

    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }
}
