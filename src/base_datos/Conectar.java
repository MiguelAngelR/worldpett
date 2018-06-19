/*
 *Clase para comenzar conexion con la base de datos
*/
package base_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Michael Liebheart
 */
public class Conectar {
    private static Connection con;
    private static Statement stm;
    private static final String driver  = "com.mysql.cj.jdbc.Driver";
    private static final String usuario  = "root";
    private static final String pass  = "";
    private static final String url  = "jdbc:mysql://localhost/worldpet";
    
    public Conectar() throws SQLException{
        con = null;
        try{
            Class.forName(driver);
            con =  DriverManager.getConnection(url,usuario,pass);
            if(con != null){
                
                System.out.println("Conexion establecida");
            }
            
            
        }catch(ClassNotFoundException e){
            System.out.println("Eror al conectar erro " + e);
        }
        
    }
    
    
    public Connection getConnection(){
        return con;
    }
    
    public void desconectar(){
        con = null;
        if(con == null){
            System.out.println("Conexion terminada");
        }else{
            System.out.println("fallo en la desconexion");
        }
     }
   
  
}