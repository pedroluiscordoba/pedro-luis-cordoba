package Modelo;

import controlador.Conexion;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Modeloproducto {

    private int idproducto;
    private String nom, des, ruta;
    private byte imagen[];

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public void buscarImagen() {
        JFileChooser archivos = new JFileChooser();
        String rutacarpeta = getClass().getClassLoader().getResource("producto").getPath();
        File carpeta = new File(rutacarpeta);
        archivos.setCurrentDirectory(carpeta);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "JPG,PNG,& GIF", "jpg", "png", "gif");
        archivos.setFileFilter(filtro);
        if (archivos.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            setRuta(archivos.getSelectedFile().getAbsolutePath());

        }

    }

    public byte[] convertirimagen(String andar) {
        try {
            File archivos = new File(andar);
            byte[] foto = new byte[(int) archivos.length()];

            InputStream img = new FileInputStream(archivos);

            img.read(foto);

            return foto;
        } catch (Exception e) {
            return null;

        }

    }

    public void insertarproducto() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.iniciarConnexion();
        String insProdocto = "call insersion_Producto(?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(insProdocto);
            ps.setString(1, getNom());
            ps.setString(2, getDes());
            ps.setBytes(3, getImagen());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "registro guardado");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//limpiar campos

    public void limpiarProducut(Component[] panel) {
        for (Object control : panel) {
            if (control instanceof JTextField) {
                ((JTextField) control).setText("");
            }
            if (control instanceof JScrollPane) {
                Component[] limpio = ((JScrollPane) control).getViewport().getComponents();
                for (Object controltext : limpio) {
                    if (controltext instanceof JTextArea) {
                        ((JTextArea) control).setText("");
                    }
                }
            }
        }
    }

//mostrar tabla producto
    public void mostrarTablaProducto(JTable tabla, String valor, String nompeste) throws SQLException {
        Conexion conect = new Conexion();
        Connection co = conect.iniciarConnexion();

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
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));

//        String[] titulo = {"Código", "Imagen", "Nombre del Producto", "Descripción", "Existencia", "Precio"};
        String[]titulo= nompeste.equals("Producto")? new String[]{"Codigo","Imagen","Nombre","Producto","descripcion","existencia","precio"}:
                new String[]{"codigo","imagen","producto","descripcion","cantidad","valor"};
            
        int opcion = titulo.length; //guarda el tamaño original del titulo

        if (nompeste.equals("Producto")) {
            titulo = Arrays.copyOf(titulo, titulo.length + 2);
            titulo[titulo.length - 2] = "editar";
            titulo[titulo.length - 1] = "elminar";
        } else {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
            titulo[titulo.length - 1] = "agregar";
        }
        DefaultTableModel tablaProducto = new DefaultTableModel(null, titulo) {
            public boolean isCellEditable(int row, int column) {
                if (!nompeste.equals("producto")){
                    if (column == 5|| column == 6){
                        return true;
                    }
                }
                return false;
            }
        };

        String sqlProducto = valor.isEmpty() ? "select * from mostrar_producto" : "call consultar_producto('" + valor + "')";

        try {
            Object dato[] = new Object[opcion];

            Statement st = co.createStatement(); //Crea una consulta
            ResultSet rs = st.executeQuery(sqlProducto);

            while (rs.next()) {
                try {//Al tener diferentes tipos de datos se puede hacer asi
                    byte[] imag = rs.getBytes(2);
                    BufferedImage bufIm = null;
                    InputStream inSt = new ByteArrayInputStream(imag);
                    bufIm = ImageIO.read(inSt);
                    ImageIcon icono = new ImageIcon(bufIm.getScaledInstance(64, 64, 0));
                    dato[1] = new JLabel(icono);

                } catch (Exception e) {
                    dato[1] = new JLabel("No imagenes");
                }
                dato[0] = rs.getString(1);
                dato[2] = rs.getString(2);
                dato[3] = rs.getString(3);
                dato[4] = rs.getInt(4);
                dato[3] = rs.getString(5);
                dato[5] = rs.getInt(6);
               
                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5]};
                if (nompeste.equals("Producto")) {
                    dato[4] =  rs.getString(5);
                    dato[5] = rs.getString(6);
                    fila = new Object[]{dato[0],dato[1],dato[2],dato[3],dato[4],dato[5]};
                    
                    fila = Arrays.copyOf(fila, fila.length + 2);
                    fila[fila.length - 2] = editar;
                    fila[fila.length - 1] = eliminar;
                } else {
                    fila = Arrays.copyOf(fila, fila.length + 1);
                    fila[fila.length - 1] = false;
                }
                tablaProducto.addRow(fila);
            }
            co.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabla.setModel(tablaProducto);
//        Renderizar la columna para que aparesca el chekbox
        int cantColum = tabla.getColumnCount();
        if (!nompeste.equals("producto")) {
            int col = cantColum - 1;
            int[] ancho = {100, 200, 100, 200, 100, 100, 30, 30};
            for (int i = 0; i < cantColum; i++) {
                TableColumn columna = tabla.getColumnModel().getColumn(i);
                columna.setPreferredWidth(ancho[i]);
            }
            conect.cerrarConexion();
        }
    }

    //buscar producto
    public void buscarProducto(int valor){
        Conexion conect = new Conexion();
        Connection co = conect.iniciarConnexion();
        String sql = "call buscar_producto(" + valor + ")";
        try {
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setIdproducto(rs.getInt(1));
                setNom(rs.getString(2));
                setDes(rs.getString(3));
                setImagen(rs.getBytes(4));
                setRuta(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ACTUALIZAR Producto
    public void actualizarProducto() throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();
        String sql = "call actualizar_producto(?,?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setString(2, getDes());
            ps.setBytes(3, getImagen());
            ps.setString(4, getRuta());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Información Actualizada");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }

    //ELIMINAR PRODUCTO
    public void eliminarProducto() throws SQLException {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConnexion();
        String sql = "call eliminar_producto(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            Icon elimina = new ImageIcon(getClass().getResource("/img/basura.png"));
            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Usuario", JOptionPane.PLAIN_MESSAGE, (Icon) elimina);
            JOptionPane.showMessageDialog(null, "¿Desea Eliminar el Registro?");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }
}
