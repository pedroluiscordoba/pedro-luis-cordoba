
package Modelo;


import Vista.Iniciar_sesion;
import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo_login {
    Conexion conect = new Conexion();
    Connection cn = conect.iniciarConnexion();
    Iniciar_sesion lg = new Iniciar_sesion();
    
    
    String usu,contra;
  
    public Modelo_login (){    
   }
    
    public String getUsu(){
        return usu;
        
    }
    public void setUsu(String usu){
        this.usu = usu;
        
    }
    public String getContra(){
        return contra;
        
    }
    public void setContra(String contra){
        this.contra = contra;
        
    }
    public boolean validar(String usuario, String pass) {
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConnexion();

        boolean result = false;
        String sql = "Call login(?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = true;
            }
            if (result == true){
                cn.close();
                conect.cerrarConexion();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
  
    
    

