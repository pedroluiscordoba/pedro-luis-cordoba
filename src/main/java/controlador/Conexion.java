package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;


public class Conexion {

    String url = "jdbc:mysql://localhost:3306/tiendacom";
    BasicDataSource con;
//    Connection con = null;
    
//    public Connection Connexion() {
//        if (con == null) {
//            try {
//                con = DriverManager.getConnection(url, "root", "1234");
//                System.out.println("Conexión Exitosa");
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//
//            }
//
//        }
//        return con;
//    }
    public Connection iniciarConnexion() throws SQLException{
     
            con= new BasicDataSource();
            con.setDriverClassName("com.mysql.cj.jdbc.Driver");
            con.setUrl(url);
            con.setUsername("root");
            con.setPassword("1234");
            
            //Configurar la conexion por pool
            con.setInitialSize(5);
            con.setMaxActive(50);
            
       
        return con.getConnection();
    }

    public void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
}

  
