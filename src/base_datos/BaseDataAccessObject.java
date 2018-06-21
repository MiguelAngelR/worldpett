/*
 *Clase para comenzar conexion connection la base de datos
*/
package base_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Liebheart
 */
public class BaseDataAccessObject {
    protected Connection connection = null;
    private static final String driver  = "com.mysql.jdbc.Driver";
    private static final String usuario  = "root";
    private static final String pass  = "root";
    private static final String url  = "jdbc:mysql://localhost/worldpet";
    
    public BaseDataAccessObject() throws SQLException {
        try{
            Class.forName(driver);
            connection =  DriverManager.getConnection(url,usuario,pass);
            if(connection != null){
                System.out.println("Conexion establecida");
            }
        } catch(ClassNotFoundException e){
            System.out.println("Error al conectar. Error " + e);
        }
    }
    
    
    public Connection getConnection(){
        return connection;
    }
    
    public void desconectar(){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexion terminada");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
  
}