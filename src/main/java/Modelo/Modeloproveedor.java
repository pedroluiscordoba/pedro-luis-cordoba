
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


public class Modeloproveedor {
     private int docume, sexo;
    private String nomb, tipo_docume, telefo, correo, direc, tipo_perso;
    private Date fecha;

    public int getDocume() {
        return docume;
    }

    public void setDocume(int docume) {
        this.docume = docume;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getTipo_docume() {
        return tipo_docume;
    }

    public void setTipo_docume(String tipo_docume) {
        this.tipo_docume = tipo_docume;
    }

    public String getTelefo() {
        return telefo;
    }

    public void setTelefo(String telefo) {
        this.telefo = telefo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getTipo_perso() {
        return tipo_perso;
    }

    public void setTipo_perso(String tipo_perso) {
        this.tipo_perso = tipo_perso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Map<String, Integer> llenarCombo(String valor) throws SQLException {
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
     public void insertarProveedor() throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();
        String sql = "Call inst_proveedor(?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, getDocume());
            ps.setString(2, getTipo_docume());
            ps.setString(3, getNomb());
            ps.setString(4, getTelefo());
            ps.setString(5, getCorreo());
            ps.setString(6, getDirec());
            ps.setString(7, getTipo_perso());
            ps.setInt(8, getSexo());
            ps.setDate(9, getFecha());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Información Guardada");

            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        conect.cerrarConexion();
    }
    public void limpiar(Component[] panelproveedor){
        for (Object limpiar : panelproveedor) {
            if (limpiar instanceof JTextField jTextField){
                jTextField.setText("");
            }
            if (limpiar instanceof JComboBox jComboBox){
                jComboBox.setSelectedItem("Seleccione...");
            }
            if (limpiar instanceof JDateChooser jDateChooser){
                jDateChooser.setDate(null);
            }
        }
        
    }
public void mostrarTablaProveedor(JTable tabla, String valor, String nomPesta) throws SQLException{
        Conexion conect = new Conexion();
        Connection conct = conect.iniciarConnexion();
         //Personalizar Encabezado
        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setDefaultRenderer(new GestionEncabezado());
        tabla.setTableHeader(encabezado);

        tabla.setDefaultRenderer(Object.class, new GestionCelda());

        JButton editar = new JButton();
        JButton eliminar = new JButton();
        JButton agregar = new JButton();

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png")));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/basura.png")));
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-archivo.png")));

        String[] titulo = {"Tipo de Documento", "Documento", "Nombre",  "Dirección", "Celular", "sexo", "Correo", "Fecha de Nacimiento"};
        int total = titulo.length;

        if (nomPesta.equals("proveedor")) {

            titulo = Arrays.copyOf(titulo, titulo.length + 2);
            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";

        } else {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
            titulo[titulo.length - 1] = "";
        }

        DefaultTableModel tablaProveedor = new DefaultTableModel(null, titulo) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String sqlCliente = valor.isEmpty() ? "SELECT * FROM mostrar_proveedor " : "call proveedor_cons('" + valor + "')";

        try {
            String[] dato = new String[titulo.length];
            Statement st = conct.createStatement();
            ResultSet rs = st.executeQuery(sqlCliente);
            while (rs.next()) {
                for (int i = 0; i < total; i++) {
                    dato[i] = rs.getString(i + 1);
                }
                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7]};
                if (nomPesta.equals("proveedor")) {
                    fila = Arrays.copyOf(fila, fila.length + 2);
                    fila[fila.length - 2] = editar;
                    fila[fila.length - 1] = eliminar;
                } else {
                    fila = Arrays.copyOf(fila, fila.length + 1);
                    fila[fila.length - 1] = agregar;
                }
                tablaProveedor.addRow(fila);
            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabla.setModel(tablaProveedor);
        //Darle tamaño a cada columna
        int numColumnas = tabla.getColumnCount();
        int[] tamanos = {150, 100, 150, 130, 150, 100, 100, 200, 150, 90, 90};

        if (nomPesta.equals("proveedor")) {
            tamanos = Arrays.copyOf(tamanos, tamanos.length + 2);
            tamanos[tamanos.length - 2] = 20;
            tamanos[tamanos.length - 1] = 20;
        } else {
            tamanos = Arrays.copyOf(tamanos, tamanos.length + 1);
            tamanos[tamanos.length - 1] = 20;
        }
        for (int i = 0; i < numColumnas; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(tamanos[i]);
        }
        conect.cerrarConexion();
    }

    public void buscarProveedor(int valor) throws SQLException {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConnexion();
        String sql = "call proveedor_bus(" + valor + ")";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setTipo_docume(rs.getString(1));
                setTipo_perso(rs.getString(2));
                setNomb(rs.getString(3));
                setFecha(rs.getDate(4));
                setSexo(rs.getInt(5));
                setTelefo(rs.getString(6));
                setCorreo(rs.getString(7));
                setDirec(rs.getString(8));
                cn.close();
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

    public void actualizarProveedor() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.iniciarConnexion();

        String actProveedor = "call proveedor_act(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(actProveedor);
            ps.setInt(1, getDocume());
            ps.setString(2, getNomb());
            ps.setString(3, getTipo_perso());
            ps.setDate(4, getFecha());
            ps.setInt(5, getSexo());
            ps.setString(6, getTelefo());
            ps.setString(7, getCorreo());
            ps.setString(8, getDirec());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Almacenado");
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProveedor() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.iniciarConnexion();

        String eliProveedor = "Call proveedor_elim(?)";
        try {
            PreparedStatement ps = cn.prepareStatement(eliProveedor);
            ps.setInt(1, getDocume());
            ps.executeUpdate();
            Icon eliminar = new ImageIcon(getClass().getResource("/img/eliminar(2).png"));
            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Proveedor", JOptionPane.PLAIN_MESSAGE, (Icon) eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


    

