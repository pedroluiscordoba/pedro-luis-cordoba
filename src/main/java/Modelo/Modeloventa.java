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
import java.util.Map;
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
public class Modeloventa {

    private int idfactu, docuclient, docusu;
    private float total_factu, impuest;
    private String tipo_pag;
    private Date fecha;

    public int getIdfactu() {
        return idfactu;
    }

    public void setIdfactu(int idfactu) {
        this.idfactu = idfactu;
    }

    public int getDocuclient() {
        return docuclient;
    }

    public void setDocuclient(int docuclient) {
        this.docuclient = docuclient;
    }

    public int getDocusu() {
        return docusu;
    }

    public void setDocusu(int docusu) {
        this.docusu = docusu;
    }

    public float getTotal_factu() {
        return total_factu;
    }

    public void setTotal_factu(float total_factu) {
        this.total_factu = total_factu;
    }

    public float getImpuest() {
        return impuest;
    }

    public void setImpuest(float impuest) {
        this.impuest = impuest;
    }

    public String getTipo_pag() {
        return tipo_pag;
    }

    public void setTipo_pag(String tipo_pag) {
        this.tipo_pag = tipo_pag;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    //insertar venta
    public void insertarVenta() throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();
        String sql = "call inst_factura(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, getDocuclient());
            ps.setInt(2, getDocusu());
            ps.setString(3, getTipo_pag());
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
    public void limpiar(Component[] panelventa) {
        for (Object limpiar : panelventa) {
            if (limpiar instanceof JTextField) {
                ((JTextField) limpiar).setText("");
            }
            if (limpiar instanceof JComboBox) {
                ((JComboBox) limpiar).setSelectedItem("Seleccione...");
            }
        }
    }

    //Creación de la tabla factura asociada a la venta en la ventana principal 
    public void mostrarTablaVenta(JTable tabla, String valor, String nompeste) throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();

        //Personalizar Encabezado
        JTableHeader encabeza = tabla.getTableHeader();
        encabeza.setDefaultRenderer(new GestionEncabezado());
        tabla.setTableHeader(encabeza);

        //Personalizar Celdas
        tabla.setDefaultRenderer(Object.class, new GestionCelda());

        JButton editar = new JButton();
//        JButton agregar_producto = new JButton();
//        JButton mostrar_detalle = new JButton();
//        JButton imprimir_factuta = new JButton();

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png")));
//        agregar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-producto.png")));
//        mostrar_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/factura.png")));
//        imprimir_factuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imprimir.png")));

        String[] titulo = {"N°Factura", "Empleado", "Cliente", "Fecha de Compra", "Tipo de Pago",
            "Impuesto", "Total"};
        int opcion = titulo.length;//tiene el tamaño original del titulo

        if (nompeste.equals("Factura")) {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
//            titulo[titulo.length - 4] = "";
//            titulo[titulo.length - 3] = "";
//            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";
        }
        DefaultTableModel tablaFact = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String sqlFactura;
        if (valor.equals("")) {
            sqlFactura = "SELECT * FROM mostrar_factura";
        } else {
            sqlFactura = "call consultar_factura('" + valor + "')";
        }
        try {
            String[] dato = new String[titulo.length];
            Statement st = con.createStatement(); //Crea una consulta
            ResultSet rs = st.executeQuery(sqlFactura);
            while (rs.next()) {//Como todos los datos son del mismo tipo se puede hacer el ciclo
                for (int i = 0; i < opcion; i++) {
                    dato[i] = rs.getString(i + 1);
                }
                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[6]};
                if (nompeste.equals("Factura")) {
                    fila = Arrays.copyOf(fila, fila.length + 1);
                    fila[fila.length - 1] = editar;
//                    fila[fila.length - 3] = agregar_producto;
//                    fila[fila.length - 2] = mostrar_detalle;
//                    fila[fila.length - 1] = imprimir_factuta;
                }
                tablaFact.addRow(fila);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabla.setModel(tablaFact);
        //Darle Tamaño a cada Columna
        int cantColum = tabla.getColumnCount();
        int[] ancho = {100, 180, 100, 150, 100, 160, 100, 30};
        for (int i = 0; i < cantColum; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(ancho[i]);
        }
        conect.cerrarConexion();
    }

    //buscar factura
    public void buscarVenta(int valor) throws SQLException {
        Conexion conect = new Conexion();
        Connection co = conect.iniciarConnexion();
        String sql = "call buscar_factura(" + valor + ")";
        try {
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setIdfactu(rs.getInt(1));
                setDocuclient(rs.getInt(2));
                setDocusu(rs.getInt(3));
                setTipo_pag(rs.getString(4));
                setFecha(rs.getDate(5));
                setImpuest(rs.getInt(6));
                setTotal_factu(rs.getFloat(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Para que al actualizar me muestre el dato que selecciono el usuario
    public String obtenerSeleccion(Map<String, Integer> info, int valor) {
        for (Map.Entry<String, Integer> seleccion : info.entrySet()) {
            if (seleccion.getValue() == valor) {
                return seleccion.getKey();
            }
        }
        return null;
    }

    //Actualizar factura 
    public void actualizarVenta() throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();
        String sql = "call actualizar_factura(?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, getIdfactu());
            ps.setInt(2, getDocuclient());
            ps.setInt(3, getDocusu());
            ps.setString(4, getTipo_pag());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Información Actualizada");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();

    }
}
