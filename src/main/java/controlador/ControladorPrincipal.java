package controlador;

import Modelo.ModeloCliente;
import Modelo.ModeloFacturaCompra;
import Modelo.ModeloUsuario;
import Modelo.Modeloproducto;
import Modelo.Modeloproveedor;
import Modelo.Modeloventa;
import Vista.Nueva_venta;
import Vista.Nuevafactura;
import Vista.Nuevo_Cliente;
import Vista.Nuevo_Producto;
import Vista.Nuevo_proveedor;
import Vista.Nuevos_Usuarios;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;

public class ControladorPrincipal implements ActionListener, ChangeListener {

    Principal prin = new Principal();
    Nuevafactura nueavafa = new Nuevafactura();
    Nuevos_Usuarios usua = new Nuevos_Usuarios();
    Nuevo_Cliente clien = new Nuevo_Cliente();
    Nuevo_proveedor provee = new Nuevo_proveedor();
    Nuevo_Producto produc = new Nuevo_Producto();
    Nueva_venta nuevaven = new Nueva_venta();
    CotroladorFacturaCompra controfatucom = new CotroladorFacturaCompra();
    Controladorventa controven = new Controladorventa();
    Controladorusuario controusu = new Controladorusuario();
    ModeloFacturaCompra modefactucom = new ModeloFacturaCompra();
    Modeloproducto modeprocdu = new Modeloproducto();

    public ControladorPrincipal() {
        prin.getjButnuevousuario().addActionListener(this);
        prin.getjButbuscarcliente().addActionListener(this);
        prin.getJbutBuscarproveedor().addActionListener(this);
        prin.getjButbuscarproducto().addActionListener(this);
        prin.getjButbuscarfactura().addActionListener(this);
        prin.getjButbuscarventa().addActionListener(this);
        prin.getjButnuevousuario().addActionListener(this);
        produc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuevaven.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prin.getjButnuevousuario().addActionListener(this);
        usua.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prin.getJbutBuscarproveedor().addActionListener(this);
        provee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prin.getjButbuscarcliente().addActionListener(this);
        clien.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prin.getJbutBuscarproveedor().addActionListener(this);
        usua.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prin.getJtableprincipal().addChangeListener(this);

    }

    public void iniciarPrincipal(int valor) {
        prin.setLocationRelativeTo(null);//Centra la ventana
        prin.setTitle("Principal");//Le da titulo a la ventana
        prin.setExtendedState(JFrame.MAXIMIZED_BOTH);//Maximiza la ventana
        prin.setVisible(true);//Se visualiza la ventana
        gestionUsuario();//llamo al metodo de Gestion usuario
    }

    public void Gestionpestaña() {

    }

    public void gestionUsuario() {
        ModeloUsuario modusu = new ModeloUsuario();

        modusu.mostrarTablaUsuario(prin.getJttablanuevousuario(), "", "Usuario");
        prin.getjTexbuscarusuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               prin.getjTexbuscarusuario().setText("");
               prin.getjTexbuscarusuario().setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        //Para darle clic al boton de editar
        prin.getJttablanuevousuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ModeloUsuario modusu = new ModeloUsuario();

                int fila = prin.getJttablanuevousuario().rowAtPoint(e.getPoint());
                int colum = prin.getJttablanuevousuario().columnAtPoint(e.getPoint());
                modusu.setDocum(Integer.parseInt(prin.getJttablanuevousuario().getValueAt(fila, 0).toString()));

                if (colum == 9) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    try {
                        controusu.actualizarUsuario(modusu.getDocum());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (colum == 10) {
                    try {
                        controusu.eliminarUsuario(modusu.getDocum());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    modusu.mostrarTablaUsuario(prin.getJttablanuevousuario(), "", "Usuario");
                }
            }
        });
    }
    public boolean validarcorreo(String correo){
        String validacion = "^[+@{A-Za-z0-9.-]+@[A-Za-z0-9.-]+.[A-Z|a-z]{2,}$";
        Pattern validar = Pattern.compile(validacion); 
        Matcher cor = validar.matcher(correo);
        return cor.matches();
    }

    public void gestionCliente() throws SQLException {
        ModeloCliente modcli = new ModeloCliente();
        modcli.mostrarTablaCliente(prin.getTablecliente(), "", "Cliente");
        prin.getTxtBuscarCliente().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTxtBuscarCliente().setText("");
                prin.getTxtBuscarCliente().setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        //Para darle clic al boton de editar
        prin.getJtablecliente().addMouseListener(new MouseAdapter() {
            ControladorCliente controcli = new ControladorCliente();

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtablecliente().rowAtPoint(e.getPoint());
                int colum = prin.getJtablecliente().columnAtPoint(e.getPoint());
                modcli.setDocum(Integer.parseInt(prin.getJtablecliente().getValueAt(fila, 0).toString()));

                if (colum == 8) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    try {
                        controcli.actualizarCliente(modcli.getDocum());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (colum == 9) {
//                    controcli.eliminarCliente(modcli.getDocum());
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    try {
                        modcli.mostrarTablaCliente(prin.getJtablecliente(), "", "Cliente");
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void gestionProveedor() throws SQLException {
        Modeloproveedor modeprovee = new Modeloproveedor();
        modeprovee.mostrarTablaProveedor(prin.getjTableproveedor(), "", "Proveedor");
        prin.getjTableproveedor().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getjTextbuscarproveedor().setText("");
                prin.getjTableproveedor().setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        //Para darle clic al boton de editar
        prin.getjTableproveedor().addMouseListener(new MouseAdapter() {
            Modeloproveedor modeprovee = new Modeloproveedor();
            Controladorproveedor contropro = new Controladorproveedor();

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getjTableproveedor().rowAtPoint(e.getPoint());
                int colum = prin.getjTableproveedor().columnAtPoint(e.getPoint());
                modeprovee.setDocume(Integer.parseInt(prin.getjTableproveedor().getValueAt(fila, 0).toString()));

                if (colum == 9) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    try {
                        contropro.actualizarProveedor(modeprovee.getDocume());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (colum == 10) {
                    try {
                        contropro.eliminarproveedor(modeprovee.getDocume());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    try {
                        modeprovee.mostrarTablaProveedor(prin.getjTableproveedor(), "", "Proveedor");
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void gestionProducto() throws SQLException {
        Modeloproducto modeprodu = new Modeloproducto();
        modeprocdu.mostrarTablaProducto(prin.getjTableproducto(), "", "producto");
        prin.getjTexbuscarproducto().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getjTexbuscarproducto().setText("");
                prin.getjTexbuscarproducto().setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        //Para darle clic al boton de editar
        prin.getjTableproducto().addMouseListener(new MouseAdapter() {
            Cntrolador_producto controproduc = new Cntrolador_producto();

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getjTableproducto().rowAtPoint(e.getPoint());
                int colum = prin.getjTableproducto().columnAtPoint(e.getPoint());
//                modeprocdu.setDes(Integer.parseInt(prin.getjTableproducto().getValueAt(fila, 0).toString()));

                if (colum == 6) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    controproduc.actualizarProducto(modeprodu.getIdproducto());
                }
                if (colum == 7) {
                    controproduc.eliminarProducto(modeprocdu.getIdproducto());
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    try {
                        modeprocdu.mostrarTablaProducto(prin.getjTableproducto(), "", "Producto");
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void gestionFacturacompra() throws SQLException {
        ModeloFacturaCompra modefatucom = new ModeloFacturaCompra();
        modefatucom.mostrarTablaFactCompra(prin.getjTablefactura(), "", "Factura");
        prin.getjTextbuscarfactura().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getjTextbuscarfactura().setText("");
                prin.getjTextbuscarfactura().setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        //Para darle clic al boton de editar y eliminar
        prin.getjTablefactura().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getjTablefactura().rowAtPoint(e.getPoint());
                int colum = prin.getjTablefactura().columnAtPoint(e.getPoint());
                modefatucom.setIdfact(Integer.parseInt(prin.getjTablefactura().getValueAt(fila, 0).toString()));

                if (colum == 7) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    try {
                        controfatucom.actualizarFactcompra( modefactucom.getIdfact());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    controfatucom.buscar();
                }
                if (colum == 8) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    produc.setVisible(true);
                    produc.setLocationRelativeTo(null);
                    produc.setTitle("Agregar Producto");
                    produc.getTxtbuscarimagenproducto().setText(String.valueOf(modefatucom.getIdfact()));
//                    controproduc.md.mostrarTablaProducto(produc.getJbtnGuardarproducto(), "", "Agregarproducto");

                }
                if (colum == 9) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    try {
                        controfatucom.actualizarFactcompra(modefatucom.getIdfact());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    controfatucom.buscar();
                }
                if (colum == 10) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    try {
                        controfatucom.actualizarFactcompra(modefatucom.getIdfact());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    controfatucom.buscar();
                }
            }
        });

    }

    public void gestionVenta() throws SQLException {
            Modeloventa modeven = new Modeloventa();

        modeven.mostrarTablaVenta(prin.getjTablenuevaventa(), "", "Venta");
        prin.getjTextbuscarventa().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getjTextbuscarventa().setText("");
                prin.getjTextbuscarventa().setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        //Para darle clic al boton de editar
        prin.getjTablenuevaventa().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getjTablenuevaventa().rowAtPoint(e.getPoint());
                int colum = prin.getjTablenuevaventa().columnAtPoint(e.getPoint());
                modeven.setIdfactu(Integer.parseInt(prin.getjTablenuevaventa().getValueAt(fila, 0).toString()));

                if (colum == 7) {
                    prin.setVisible(false);
                    prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
//                    controven.actualizarVenta(modeprocdu.getIdproducto());
                }
            }
        });

    }
    

    

    public boolean modificadorAccesoCorreo(String correo) {
        String corr = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\\.[A-Z|a-z]{2,}$";
        Pattern validar = Pattern.compile(corr);//compila y valida lo anterior
        Matcher cor = validar.matcher(correo);//convierte el correo a caracter
        return cor.matches();//si esta de acuerdo con la estructura el retorna verdadero o falso
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(prin.getjButbuscarcliente())) {
            prin.setVisible(false);
            ControladorCliente controlcli = new ControladorCliente();
            try {
                controlcli.controlCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(prin.getjButnuevousuario())) {
            prin.setVisible(false);
            controusu.controladorusuario();
        }
        if (e.getSource().equals(prin.getjButnuevousuario())) {
            prin.setVisible(false);
            controfatucom.ControladorFacturaCompra();
        }
         if (e.getSource().equals(prin.getjButbuscarfactura())) {
            prin.setVisible(false);
            controfatucom.ControladorFacturaCompra();
        }
        if (e.getSource().equals(prin.getJbutBuscarproveedor())) {
            prin.setVisible(false);
            Controladorproveedor contropro = new Controladorproveedor();
            try {
                contropro.controlProveedor();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource().equals(prin.getjButbuscarproducto())) {
            prin.setVisible(false);
            Cntrolador_producto controproduc = new Cntrolador_producto();
            controproduc.controlProducto();

        }
        if (e.getSource().equals(prin.getjButbuscarventa())) {
            prin.setVisible(false);
            Controladorventa controven = new Controladorventa();
            controven.controlVenta();
        }
        if (e.getSource().equals(prin.getjButbuscarfactura())) {
            prin.setVisible(false);
            CotroladorFacturaCompra controfatucom = new CotroladorFacturaCompra();
            controfatucom.controlFacturaCompra();
        }

        if (e.getSource().equals(prin.getjButbuscarfactura())) {
            prin.setVisible(false);
//            factcompr.setLocationRelativeTo(null);
//            factcompr.setTitle("Nueva Factura Compra");
//            factcompr.setVisible(true);
        }
        if (e.getSource().equals(prin.getjButbuscarventa())) {
            prin.setVisible(false);
            nuevaven.setLocationRelativeTo(null);
            nuevaven.setTitle("Nueva Venta");
            nuevaven.setVisible(true);
        }
    }
        
    
    

    @Override
    public void stateChanged(ChangeEvent e) {
        int seleccionar = prin.getJtableprincipal().getSelectedIndex();
        if (seleccionar == 0) {
            gestionUsuario();
        }
        if (seleccionar == 5) {
            try {
                gestionCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (seleccionar == 1) {
            try {
                gestionProveedor();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (seleccionar == 2) {
            try {
                gestionProducto();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (seleccionar == 4) {
            try {
                gestionFacturacompra();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (seleccionar == 3) {
            try {
                gestionVenta();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean ingresecorreo(String correo) {
        String valor = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Z|a-z]{2,}$";
        Pattern validar = Pattern.compile(valor);
        Matcher cor = validar.matcher(correo);
        return cor.matches();

    }

    public void insertUpdate(DocumentEvent e) throws SQLException {
        ModeloFacturaCompra modefatucom = new ModeloFacturaCompra();
        Modeloproducto modeprocdu = new Modeloproducto();
        Modeloproveedor modeprovee = new Modeloproveedor();
        ModeloCliente modcli = new ModeloCliente();
        ModeloUsuario modusu = new ModeloUsuario();
        modusu.mostrarTablaUsuario(prin.getJttablanuevousuario(), prin.getjTexbuscarusuario().getText(), "Usuario");
        modcli.mostrarTablaCliente(prin.getJtablecliente(), prin.getjButbuscarcliente().getText(), "Cliente");
        modeprovee.mostrarTablaProveedor(prin.getjTableproveedor(), prin.getJbutBuscarproveedor().getText(), "Proveedor");
        modeprocdu.mostrarTablaProducto(prin.getjTableproducto(), prin.getjTexbuscarproducto().getText(), "Producto");
        modefatucom.mostrarTablaFactCompra(prin.getjTablefactura(), prin.getjTextbuscarfactura().getText(), "Factura");
    }

    public void removeUpdate(DocumentEvent e) throws SQLException {
        ModeloFacturaCompra modefatucom = new ModeloFacturaCompra();
        modeprocdu = new Modeloproducto();
        Modeloproveedor modeprovee = new Modeloproveedor();
        ModeloCliente modcli = new ModeloCliente();
        ModeloUsuario modusu = new ModeloUsuario();
        modusu.mostrarTablaUsuario(prin.getJttablanuevousuario(), prin.getjTexbuscarusuario().getText(), "Usuario");
        modcli.mostrarTablaCliente(prin.getJtablecliente(), prin.getjTexbuscacliente().getText(), "Cliente");
        modeprovee.mostrarTablaProveedor(prin.getjTableproveedor(), prin.getJbutBuscarproveedor().getText(), "Proveedor");
        modeprocdu.mostrarTablaProducto(prin.getjTableproducto(), prin.getjTexbuscarproducto().getText(), "Producto");
        modefatucom.mostrarTablaFactCompra(prin.getjTablefactura(), prin.getjTextbuscarfactura().getText(), "Factura");
    }

    public void changedUpdate(DocumentEvent e) throws SQLException {
        ModeloFacturaCompra modefatucom = new ModeloFacturaCompra();
        Modeloproveedor modeprovee = new Modeloproveedor();
        ModeloCliente modcli = new ModeloCliente();
        ModeloUsuario modusu = new ModeloUsuario();
        modusu.mostrarTablaUsuario(prin.getJttablanuevousuario(), prin.getjTexbuscarusuario().getText(), "Usuario");
        modcli.mostrarTablaCliente(prin.getJtablecliente(), prin.getjButbuscarcliente().getText(), "Cliente");
        modeprovee.mostrarTablaProveedor(prin.getjTableproveedor(), prin.getjTextbuscarproveedor().getText(), "Proveedor");
        modeprocdu.mostrarTablaProducto(prin.getjTableproducto(), prin.getjTexbuscarproducto().getText(), "Producto");
        modefatucom.mostrarTablaFactCompra(prin.getjTablefactura(), prin.getjTextbuscarfactura().getText(), "Factura");
    }
}

//        
//    }
//}
////    public void insertUpdate(DocumentEvent e) {
////        modusu.mostrarTablaUsuario(prin.getjTableUsuario(), prin.getTxtbuscarUsuario().getText(), "Usuario");
//    }
//
//    public void removeUpdate(DocumentEvent e) {
//        modusu.mostrarTablaUsuario(prin.getjTableUsuario(), prin.getTxtbuscarUsuario().getText(), "Usuario");
//    }
//
//    public void changedUpdate(DocumentEvent e) {
//        modusu.mostrarTablaUsuario(prin.getjTableUsuario(), prin.getTxtbuscarUsuario().getText(), "Usuario")   }

