package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    String url = "jdbc:mysql://localhost:3306/tiendacom";
    Connection con = null;

    public Connection iniciarConnexion() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(url, "root", "1234");
                System.out.println("Conexión Exitosa");

            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        return con;
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

  
