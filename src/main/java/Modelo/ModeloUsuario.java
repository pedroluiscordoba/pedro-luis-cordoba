package Modelo;

import com.toedter.calendar.JDateChooser;
import controlador.Conexion;
import java.awt.Component;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ModeloUsuario {

    private int docum, sex, cargo;
    private String nomb,Tip_docum, direcc, telef, corre, logi, clave;
    private Date fech;

    public int getDocum() {
        return docum;
    }

    public void setDocum(int docum) {
        this.docum = docum;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getTip_docum() {
        return Tip_docum;
    }

    public void setTip_docum(String Tip_docum) {
        this.Tip_docum = Tip_docum;
    }

    public String getDirecc() {
        return direcc;
    }

    public void setDirecc(String direcc) {
        this.direcc = direcc;
    }

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public String getCorre() {
        return corre;
    }

    public void setCorre(String corre) {
        this.corre = corre;
    }

    public String getLogi() {
        return logi;
    }

    public void setLogi(String logi) {
        this.logi = logi;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFech() {
        return fech;
    }

    public void setFech(Date fech) {
        this.fech = fech;
    }

    
    //llenar los combos
    public Map<String, Integer> llenarCombo(String valor) {
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConnexion();

        String sql = "select * from mostrar_" + valor;

        Map<String, Integer> llenar_combo = new HashMap<>();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                llenar_combo.put(rs.getString(2), rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return llenar_combo;
    }

    public void llenarnuevousuario() {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConnexion();//instanciamos la conexion
        String sql = "call ins_usuario (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, getDocum());
            ps.setString(2, getNomb());
            ps.setString(3, getTelef());
            ps.setString(4, getCorre());
            ps.setString(5, getDirecc());
            ps.setDate(6, getFech());
            ps.setInt(7, getSex());
            ps.setInt(8, getCargo());
            ps.setString(9, getLogi());
            ps.setString(10, getTip_docum());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro almacenado");
            cn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);

        }
        cone.cerrarConexion();
    }

    public void Limpiar(Component[] panel) {
        for (Object control : panel) {
            if (control instanceof JTextField jTextField) {
                jTextField.setText("");
            
                if (control instanceof JComboBox jComboBox) {
                    jComboBox.setSelectedItem("seleccione...");
                }
                if (control instanceof JDateChooser jDateChooser) {
                    jDateChooser.setDate(null);

                }
            }
        }
    }
//creacion de la tabla usuario en la ventana principal

   public void mostrarTablaUsuario(JTable tabla, String valor, String Nompesta) {

        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConnexion();

        //Personalizar Emcabezado
        JTableHeader encabeza = tabla.getTableHeader();
        // encabeza.setDefaultRenderer(new Gestion_Encabezado());
      tabla.setTableHeader(encabeza);

        //Personalizar Celdas
        // tabla.setDefaultRenderer(Object.class, new GestionCeldas());
        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");
        JButton agregar = new JButton("Agregar");

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png")));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
        String[] titulo = {"Documento", "Tipo de Documento", "Nombre", "Cargo", "Telefono", "Correo", "Género", "Dirección", "Fecha de Nacimiento", "", ""};
        int total = titulo.length;
        if (nomb.equals("usuario")) {
            titulo = Arrays.copyOf(titulo, titulo.length + 2);
            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";
        } else {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
           titulo[titulo.length - 1] = "";

            DefaultTableModel tablaUsuario = new DefaultTableModel(null, titulo) {
                public boolean isCellEditable(int row, int column) {

                    return false;

                }
            };

            String sqlusuario;
            if (valor.equals("")) {
                sqlusuario = "SELECT * FROM mostrarusuario";
            } else {
                sqlusuario = "call consultar_usuario('" + valor + "')";
            }
           try {
                String[] dato = new String[titulo.length];
                Statement st = cn.createStatement(); //Crea una consulta
                ResultSet rs = st.executeQuery(sqlusuario);
               while (rs.next()) {
                    for (int i = 0; i < titulo.length - 2; i++) {
                       dato[i] = rs.getString(i + 1);
                   }
                   Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7], dato[8]};

                   tablaUsuario.addRow(new Object[]{dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7], dato[8], editar, eliminar});
               }
               cn.close();

           } catch (SQLException e) {
               e.printStackTrace();
            }
           tabla.setModel(tablaUsuario);
            //Darle Tamaño a cada Columna
           int cantColum = tabla.getColumnCount();
            int[] ancho = {100, 180, 100, 150, 100, 160, 100, 180, 150, 30};
            for (int i = 0; i < cantColum; i++) {
               TableColumn columna = tabla.getColumnModel().getColumn(i);
                columna.setPreferredWidth(ancho[i]);
            }
            conect.cerrarConexion();
        }
   }

    public void buscarUsuario(int valor) {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConnexion();
        String sql = "call buscar_usuario(" + valor + ")";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setDocum(rs.getInt(1));
                setTip_docum(rs.getString(2));
                setNomb(rs.getString(3));
                setTelef(rs.getString(4));
                setCorre(rs.getString(5));
                setDirecc(rs.getString(6));
                setFech(rs.getDate(7));
                setSex(rs.getInt(8));
                setCargo(rs.getInt(9));
                setLogi(rs.getString(10));
                setClave(rs.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String obtenerSeleccion(Map<String, Integer> dato, int valor) {
        for (Map.Entry<String, Integer> seleccion : dato.entrySet()) {
            if (seleccion.getValue() == valor) {
                return seleccion.getKey();
            }
        }
        return null;
    }

    public void actualizarUsuario() {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConnexion();

        String actUsuario = "call usuario_act(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(actUsuario);
            ps.setInt(1, getDocum());
            ps.setString(2, getNomb());
            ps.setString(3, getTelef());
            ps.setString(4, getCorre());
            ps.setString(5, getDirecc());
            ps.setDate(6, getFech());
            ps.setInt(7, getSex());
            ps.setInt(8, getCargo());
            ps.setString(9, getClave());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Almacenado");
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario() {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConnexion();

        String eliUsuario = "Call usuario_elim(?)";
        try {
            PreparedStatement ps = cn.prepareStatement(eliUsuario);
            ps.setInt(1, getDocum());
            ps.executeUpdate();
            Icon eliminar = new ImageIcon(getClass().getResource("/img/eliminar(2).png"));
            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Usuario", JOptionPane.PLAIN_MESSAGE, (Icon) eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
