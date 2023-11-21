/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Peter Louis
 */
public class ModeloFacturaCompra {

    private int docprovee, docusu, idfact, comprovan;
    private float total_compr;
    private String tipo_pag;
    private Date fec;

    public int getIdfact() {
        return idfact;
    }

    public int getComprovan() {
        return comprovan;
    }

    public void setComprovan(int comprovan) {
        this.comprovan = comprovan;
    }

    public void setIdfact(int idfact) {
        this.idfact = idfact;
    }

    public int getDocprovee() {
        return docprovee;
    }

    public void setDocprovee(int docprovee) {
        this.docprovee = docprovee;
    }

    public int getDocusu() {
        return docusu;
    }

    public void setDocusu(int docusu) {
        this.docusu = docusu;
    }

    public String getTipo_pag() {
        return tipo_pag;
    }

    public void setTipo_pag(String tipo_pag) {
        this.tipo_pag = tipo_pag;
    }

    public float getTotal_compr() {
        return total_compr;
    }

    public void setTotal_compr(float total_compr) {
        this.total_compr = total_compr;
    }

    public Date getFec() {
        return fec;
    }

    public void setFec(Date fec) {
        this.fec = fec;
    }

    //insertar usuario
    public void insertarFactcompra() throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();
        String sql = "call inst_factura_compra(?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2, getDocprovee());
            ps.setInt(3, getDocusu());
            ps.setString(4, getTipo_pag());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Información Guardada");

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        conect.cerrarConexion();
    }

    public void actualizarProducto() throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();
        String sql = "call actualizarProducto(?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2, getDocprovee());
            ps.setInt(3, getDocusu());
            ps.setString(4, getTipo_pag());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Información Guardada");

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        conect.cerrarConexion();
    }

    //limpiar campos
    public void limpiar(Component[] panelfactcompra) {
        for (Object limpiar : panelfactcompra) {
            if (limpiar instanceof JTextField) {
                ((JTextField) limpiar).setText("");
            }
            if (limpiar instanceof JComboBox) {
                ((JComboBox) limpiar).setSelectedItem("Seleccione...");
            }
        }
    }
//Creación de la tabla Usuario en la ventana principal 

    public void mostrarTablaFactCompra(JTable tabla, String valor, String nompeste) throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();

        //Personalizar Encabezado
        JTableHeader encabeza = tabla.getTableHeader();
        encabeza.setDefaultRenderer(new GestionEncabezado());
        tabla.setTableHeader(encabeza);

        //Personalizar Celdas
        tabla.setDefaultRenderer(Object.class, new GestionCelda());
        JButton editar = new JButton();
       JButton eliminar = new JButton();  
       JButton agregar = new JButton();

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png")));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png")));

        String[] titulo = {"N°Factura Compra", "Identificación Proveedor", "Identificación Usuario", "Tipo de Pago",
            "Descuento", "Total Compra", "Fecha de Compra"};
        int opcion = titulo.length;//tiene el tamaño original del titulo

        if (nompeste.equals("Factura")) {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
            titulo[titulo.length - 4] = "";
            titulo[titulo.length - 3] = "";
            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";


        }
//        else {
//            titulo = Arrays.copyOf(titulo, titulo.length + 1);
//            titulo[titulo.length - 1] = "";
//        }


        DefaultTableModel tablaFactcompr = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String sqlFactura;
        if (valor.equals("")) {
            sqlFactura = "SELECT * FROM mostrar_factura_compra";
        } else {
            sqlFactura = "call consultar_facturacompra('" + valor + "')";
        }
        try {
            String[] dato = new String[titulo.length];
            Statement st = con.createStatement(); //Crea una consulta
            ResultSet rs = st.executeQuery(sqlFactura);
            while (rs.next()) {//Como todos los datos son del mismo tipo se puede hacer el ciclo
                for (int i = 0; i < opcion; i++) {
                    dato[i] = rs.getString(i + 1);
                }
                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[1]};
                if (nompeste.equals("Factura")) {
                    fila = Arrays.copyOf(fila, fila.length + 1);
                    fila[fila.length - 1] = editar;
//                    fila[fila.length - 1] = eliminar;
                }
//                else {
//                    fila = Arrays.copyOf(fila, fila.length + 1);
//                    fila[fila.length - 1] = agregar;
//                }
                tablaFactcompr.addRow(fila);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabla.setModel(tablaFactcompr);
        //Darle Tamaño a cada Columna
        int cantColum = tabla.getColumnCount();
        int[] ancho = {100, 180, 100, 150, 100, 160, 100, 30};
        for (int i = 0; i < cantColum; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(ancho[i]);
        }
        conect.cerrarConexion();
    }

    public boolean selecione(JTable tabla) {
        int c = tabla.getColumnCount() - 1;
        for (int i = 0; i< tabla.getColumnCount(); i++) {
            Boolean selec = (Boolean) tabla.getValueAt(i, c);
            if (selec != null && selec) {
                return true;

            }
        }
        return false;
    }

    public void buscarFactcompra(int valor) throws SQLException {
        Conexion conect = new Conexion();
        Connection co = conect.iniciarConnexion();
        String sql = "call buscar_facturacompra(" + valor + ")";
        try {
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setIdfact(rs.getInt(1));
                setDocprovee(rs.getInt(2));
                setDocusu(rs.getInt(3));
                setTipo_pag(rs.getString(4));
                setDocusu(rs.getInt(5));
                setTotal_compr(rs.getFloat(6));
                setIdfact(rs.getInt(7));
                setFec(rs.getDate(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean seleciono(JTable tabla) {
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Boolean selec = (Boolean) tabla.getValueAt(i, 6);
            
if (selec != null && selec) {
                return true;
            }

        }
        return false;
    }

 public void agregarDatos(JTable tablaprod, JTable tablaDeta) {
        JTableHeader encabezado = tablaDeta.getTableHeader();
        encabezado.setDefaultRenderer(new GestionEncabezado());
        tablaDeta.setTableHeader(encabezado);
        tablaDeta.setDefaultRenderer(Object.class, new GestionCelda());

        Object[] titulo = {"factura", "fecha", "Cliente", "Usuario", "Tipopago", "Impuestos", "Total_Factura"};
        DefaultTableModel tabla_addproducto = new DefaultTableModel(null, titulo) {
            public boolean isCellEditable(int row, int column) {

                return column == 3 || column == 4;

            }
        };
        if (seleciono(tablaprod)) {
            for (int i = 0; i < tablaprod.getRowCount(); i++) {
                Boolean selec = (Boolean) tablaDeta.getValueAt(i, 6);
                if (selec != null && selec) {
                    Object dato[] = new Object[titulo.length];
                    dato[0] = tablaprod.getValueAt(i, 0);
                    dato[1] = tablaprod.getValueAt(i, 2);
                    dato[2] = tablaprod.getValueAt(i, 3);
                    Object fila []={dato[0],dato[1],dato[2]};
                    tabla_addproducto.addRow(fila);
                }
            }
        } else {
            JOptionPane.showConfirmDialog(null, "Debe seleccionar al menos un producto");
        }
        tablaDeta.setModel(tabla_addproducto);
    }
 
}








