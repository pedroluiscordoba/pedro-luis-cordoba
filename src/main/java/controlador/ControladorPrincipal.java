package controlador;

import Modelo.ModeloCliente;
import Modelo.ModeloUsuario;
import Vista.Nueva_venta;
import Vista.Nuevo_Cliente;
import Vista.Nuevo_Producto;
import Vista.Nuevo_proveedor;
import Vista.Nuevos_Usuarios;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ControladorPrincipal implements ActionListener, ChangeListener {

  Principal prin = new Principal();
    Nuevos_Usuarios usua = new Nuevos_Usuarios();
    Nuevo_Cliente clien = new Nuevo_Cliente();
    Nuevo_proveedor provee = new Nuevo_proveedor();
    Nuevo_Producto produc = new Nuevo_Producto();
//    Nueva_Factura_Compra factcompr = new Nueva_Factura_Compra();
    Nueva_venta nuevaven = new Nueva_venta();
    Controladorusuario controusu = new  Controladorusuario();
    ControladorCliente controcli = new ControladorCliente();
//    Controladorproveedor contropro = new Controladorproveedor();
    Cntrolador_producto controproduc = new Cntrolador_producto();
    ModeloUsuario modusu = new ModeloUsuario();
    ModeloCliente modcli = new ModeloCliente();

    public ControladorPrincipal() {
//        prin.getBtnNuevo().addActionListener(this);
//        prin.getBtnnuevocliente().addActionListener(this);
//        prin.getBuscarprovedor().addActionListener(this);
//        prin.getBtnNuevoproducto().addActionListener(this);
//        prin.getBtnNuevarfactura().addActionListener(this);
//        prin.getBtnnuevaventa().addActionListener(this);
//        prin.getBtnNuevousuario().addActionListener(this);
        usua.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clien.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        provee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        produc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        .setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuevaven.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void iniciarPrincipal() {
        prin.setLocationRelativeTo(null);//Centra la ventana
        prin.setTitle("Principal");//Le da titulo a la ventana
        prin.getjTable_pricipal();
        prin.setExtendedState(JFrame.MAXIMIZED_BOTH);//Maximiza la ventana
        prin.setVisible(true);//Se visualiza la ventana
        gestionUsuario();//llamo al metodo de Gestion usuario
        gestionCliente();
    }

    public void gestionarPestanas() {

    }

    public void gestionUsuario() {
//        modusu.mostrarTablaUsuario(prin.getjTableUsuario(), "", "Usuario");

        prin.getjTable_pricipal().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
        prin.getBtnNuevousuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                prin.getjTable_pricipal().set("");
                prin.getjTableUsuario().setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        //Para darle clic al boton de editar
        prin.getjTableUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getjTableUsuario().rowAtPoint(e.getPoint());
                int colum = prin.getjTableUsuario().columnAtPoint(e.getPoint());
                modusu.setDocum(Integer.parseInt(prin.getjTableUsuario().getValueAt(fila, 0).toString()));

                if (colum == 9) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    controusu.actualizarUsuario(modusu.getDocum());
                }
                if (colum == 10) {
                    controusu.eliminarUsuario(modusu.getDocum());
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
//                    modusu.mostrarTablaUsuario(prin.getjTableUsuario(), "", "Usuario");
                }
            }
        });
    }

    public void gestionCliente() {
        modcli.mostrarTablaCliente(prin.getjTableCliente(), "", "Cliente");
    }

    public void gestionProveedor() {

    }

    public void gestionProducto() {

    }

    public void gestionFacturacompra() {

    }

    public void gestionVenta() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(prin.getBtnNuevousuario())) {
            prin.setVisible(false);
            controusu.controladorusuario();
        }
        if (e.getSource().equals(prin.getBtnnuevocliente())) {
            prin.setVisible(false);
            controcli.controlCliente();
        }
        if (e.getSource().equals(prin.getBtnproveedor())) {
            prin.setVisible(false);
//            contropro.controlProveedor();
        }
        if (e.getSource().equals(prin.getBtnNuevoproducto())) {
            prin.setVisible(false);
//            controproduc.Cntrolador_product();

        }
        if (e.getSource().equals(prin.getBtnNuevarfactura())) {
            prin.setVisible(false);
//            factcompr.setLocationRelativeTo(null);
//            factcompr.setTitle("Nueva Factura Compra");
//            factcompr.setVisible(true);
        }
        if (e.getSource().equals(prin.getBtnnuevaventa())) {
            prin.setVisible(false);
            nuevaven.setLocationRelativeTo(null);
            nuevaven.setTitle("Nueva Venta");
            nuevaven.setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int seleccionar = prin.getjTable_pricipal().getSelectedIndex();
        if (seleccionar == 0) {
            gestionUsuario();
        }
        if (seleccionar == 1) {
            gestionCliente();
        }
        if (seleccionar == 2) {
            gestionProveedor();
        }
        if (seleccionar == 3) {
            gestionProducto();
        }
        if (seleccionar == 4) {
            gestionFacturacompra();
        }
        if (seleccionar == 5) {
            gestionVenta();
        }
    }

//    public void insertUpdate(DocumentEvent e) {
//        modusu.mostrarTablaUsuario(prin.getjTableUsuario(), prin.getTxtbuscarUsuario().getText(), "Usuario");
//    }
//
//    public void removeUpdate(DocumentEvent e) {
//        modusu.mostrarTablaUsuario(prin.getjTableUsuario(), prin.getTxtbuscarUsuario().getText(), "Usuario");
//    }
//
//    public void changedUpdate(DocumentEvent e) {
//        modusu.mostrarTablaUsuario(prin.getjTableUsuario(), prin.getTxtbuscarUsuario().getText(), "Usuario");
//    }
}