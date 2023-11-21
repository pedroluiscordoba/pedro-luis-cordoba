package Modelo;

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

public class ModeloCliente {

    private int docum, cedu, sexo;
    private String nomb, dirr, tele,corr, tipodoc;
    private Date fech;

    public int getCedu() {
        return cedu;
    }

    public void setCedu(int cedu) {
        this.cedu = cedu;
    }
    
    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getDirr() {
        return dirr;
    }

    public void setDirr(String dirr) {
        this.dirr = dirr;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getCorr() {
        return corr;
    }

    public void setCorr(String corr) {
        this.corr = corr;
    }

    public Date getFech() {
        return fech;
    }

    public void setFech(Date fech) {
        this.fech = fech;
    }

    public int getDocum() {
        return docum;
    }

    public void setDocum(int docum) {
        this.docum = docum;
    }

   

    
    

    public Map<String, Integer> llenarCombo(String valor) throws SQLException{
        //Llamamos a la clase conexión
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConnexion();//Instanciamos la conexion
        String sql = "Select * from mostrar_" + valor;

        Map<String, Integer> llenar_combo = new HashMap<>();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                llenar_combo.put(rs.getString(2), rs.getInt(1));
            }
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return llenar_combo;
    }

    public void insertarCliente() throws SQLException {
        //Llamamos a la clase conexión
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConnexion();//Instanciamos la conexion

        String sql = "Call cliente_ins(?,?,?,?,?,?,?,?)";//Consulta a realizar a la base de datos
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, getTipodoc());
            ps.setInt(2, getCedu());
            ps.setString(3, getNomb());
            ps.setString(4, getTele());
            ps.setDate(5, getFech());
            ps.setInt(6, getSexo());
            ps.setString(9, getCorr());
            ps.setString(10, getDirr());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Almacenado");
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conect.cerrarConexion();
        }
    }

    public void limpiar(Component[] panel) {
        for (Object control : panel) {
            if (control instanceof JTextField jTextField) {
                jTextField.setText("");
            }
            if (control instanceof JComboBox jComboBox) {
                jComboBox.setSelectedItem("Seleccione...");
            }
        }

    }

    public void mostrarTablaCliente(JTable tabla, String valor, String nomPesta) throws SQLException{
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConnexion();

        //Personalizar Encabezado
        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setDefaultRenderer(new GestionEncabezado());
        tabla.setTableHeader(encabezado);

        tabla.setDefaultRenderer(Object.class, new GestionCelda ());

        JButton editar = new JButton();
        JButton eliminar = new JButton();
        JButton agregar = new JButton();

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png")));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
//        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar_archivo.png")));

        String[] titulo = {"Tipo de Documento", "Documento", "Nombre", "Dirección", "Celular", "Género", "Correo", "Fecha de Nacimiento"};
        int total = titulo.length;

        if (nomPesta.equals("cliente")) {

            titulo = Arrays.copyOf(titulo, titulo.length + 2);
            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";

        } else {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
            titulo[titulo.length - 1] = "";
        }

        DefaultTableModel tablaCliente = new DefaultTableModel(null, titulo) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String sqlCliente = valor.isEmpty() ? "SELECT * FROM mostrar_cliente " : "call cliente_cons('" + valor + "')";

        try {
            String[] dato = new String[titulo.length];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlCliente);
            while (rs.next()) {
                for (int i = 0; i < total; i++) {
                    dato[i] = rs.getString(i + 1);
                }
                Object[] fila = {dato[7], dato[0], dato[2], dato[5], dato[3], dato[1], dato[4], dato[6]};
                if (nomPesta.equals("cliente")) {
                    fila = Arrays.copyOf(fila, fila.length + 2);
                    fila[fila.length - 2] = editar;
                    fila[fila.length - 1] = eliminar;
                } else {
                    fila = Arrays.copyOf(fila, fila.length + 1);
                    fila[fila.length - 1] = agregar;
                }
                tablaCliente.addRow(fila);
            }
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabla.setModel(tablaCliente);
        //Darle tamaño a cada columna
        int numColumnas = tabla.getColumnCount();
        int[] tamanos = {150, 150, 150, 150, 100, 100, 200, 130};

        if (nomPesta.equals("cliente")) {
            tamanos = Arrays.copyOf(tamanos, tamanos.length + 2);
            tamanos[tamanos.length - 2] = 30;
            tamanos[tamanos.length - 1] = 30;
        } else {
            tamanos = Arrays.copyOf(tamanos, tamanos.length + 1);
            tamanos[tamanos.length - 1] = 30;
        }
        for (int i = 0; i < numColumnas; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(tamanos[i]);

        }
        conect.cerrarConexion();
    }

    public void buscarCliente(int valor) throws SQLException {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConnexion();
        String sql = "call cliente_bus(" + valor + ")";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setTipodoc(rs.getString(1));
                setCedu(rs.getInt(2));
                setNomb(rs.getString(3));
                setTele(rs.getString(4));
                setFech(rs.getDate(5));
                setSexo(rs.getInt(6));
                setCorr(rs.getString(7));
                setDirr(rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        
            }
        }
    public void actualizarCliente() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.iniciarConnexion();

        String actCliente = "call cliente_act(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(actCliente);
            ps.setString(1,  getTipodoc());
            ps.setInt(2, getCedu());
            ps.setString(3, getNomb());
            ps.setString(4, getTele());
            ps.setDate(5, getFech());
            ps.setInt(6, getSexo());
            ps.setString(7, getCorr());
            ps.setString(8, getDirr());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Almacenado");
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarCliente() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.iniciarConnexion();

        String eliCliente = "Call cliente_elim(?)";
        try {
            PreparedStatement ps = cn.prepareStatement(eliCliente);
            ps.setInt(1, getCedu());
            ps.executeUpdate();
            Icon eliminar = new ImageIcon(getClass().getResource("/img/eliminar(2).png"));
            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Cliente", JOptionPane.PLAIN_MESSAGE, (Icon) eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.cerrarConexion();
    }

}
