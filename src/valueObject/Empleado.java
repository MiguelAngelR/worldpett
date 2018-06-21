/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Saul
 */
public class Empleado {
    public SimpleStringProperty nombre;
    public SimpleStringProperty cargo;
    public SimpleStringProperty codigo;
    
    
    public Empleado() {
        this.cargo = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.codigo = new SimpleStringProperty();
    }
    
    public Empleado(String nombre, String cargo, String codigo) {
        this.cargo = new SimpleStringProperty(cargo);
        this.nombre = new SimpleStringProperty(nombre);
        this.codigo = new SimpleStringProperty(codigo);
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
