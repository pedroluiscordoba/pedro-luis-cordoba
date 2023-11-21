/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Modelo.ModeloFacturaCompra;
import Modelo.ModeloUsuario;
import Modelo.Modeloproveedor;
import Vista.Buscarcliente;
import Vista.Buscarproveedor;
import Vista.Buscarusuario;
import Vista.Nuevafactura;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Peter Louis
 */
public class CotroladorFacturaCompra implements ActionListener, DocumentListener  {
    Nuevafactura nuefac = new Nuevafactura();
    Principal prin = new Principal();
    Buscarcliente buscarcli = new Buscarcliente();
    Buscarusuario buscarusuario = new Buscarusuario();
    ModeloUsuario modusu = new ModeloUsuario();
    Modeloproveedor modprovee = new Modeloproveedor();
    ModeloFacturaCompra modfactnue = new ModeloFacturaCompra();
    Buscarproveedor busprovee = new Buscarproveedor();

    public void ControladorFacturaCompra() {
        nuefac.getjButoGuardar().addActionListener(this);
        nuefac.getjButcancelar().addActionListener(this);
        nuefac.getButguardarnuefactu().addActionListener(this);
        nuefac.getButguardarnuefactu().addActionListener(this);
        buscarcli.getjTexbuscarcliente().getDocument().addDocumentListener(this);
        nuefac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Desactiva la x que cierra el programa para que permita abrir o volcer a la ventana principal
        buscarcli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuefac.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal prin = new ControladorPrincipal();
                prin.iniciarPrincipal(0);
            }
        });
        buscarcli.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                nuefac.setVisible(true);
                buscarcli.setVisible(false);
            }
        });
    }

    public void controlFacturaCompra() {
        prin.setVisible(false);
        nuefac.setLocationRelativeTo(null);
        nuefac.setTitle("Nueva Factura Compra");
        nuefac.setVisible(true);
        buscar();
    }
     //Actualizar factura compra
    void actualizarFactcompra(int doc) throws SQLException {
        modfactnue.buscarFactcompra(doc);
        nuefac.getjTexnumerodecomprovante().setEnabled(false);
        nuefac.getjTexnumerodecomprovante().setText(String.valueOf(modfactnue.getDocprovee()));
        nuefac.getjTexnumerodecomprovante().setText(String.valueOf( modfactnue.getDocusu()));
        nuefac.getjTexnumerodecomprovante().setText(String.valueOf(modfactnue.getComprovan()));

        //Llenar tipo de pago
        nuefac.getjCombtipodepago().setSelectedItem(modfactnue.getTipo_pag());

        //Cambiar Titulo
        Border borde = BorderFactory.createTitledBorder(null, "Actualizar Factura Compra",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Yu Gothic UI", 1, 36),
                new java.awt.Color(204, 0, 204));
        nuefac.getjPanenuevafactura().setBorder(borde);
        prin.setVisible(false);
        nuefac.setLocationRelativeTo(null);
        nuefac.getButguardarnuefactu().setText("Actualizar");
        nuefac.setVisible(true);
    }
   
        
        
    

    public void buscar() {
        buscarcli.getjTablebuscarcliente().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = buscarcli.getjTablebuscarcliente().rowAtPoint(e.getPoint());
               int colum = buscarcli.getjTablebuscarcliente().columnAtPoint(e.getPoint());

                if (buscarcli.getjLabuscarcliente().getText().equals("Usuario")) {
                    modusu.setDocum(Integer.parseInt(buscarcli.getjTablebuscarcliente().getValueAt(fila, 0).toString()));
                    if (colum == 9) {
                        buscarcli.setVisible(false);
                        nuefac.setVisible(true);
                        Object idusua = modusu.getDocum();
                        nuefac.getjTexidenusuario().setText(idusua.toString());
                        JOptionPane.showMessageDialog(null, "Usuario Agregado");
                    }
                } else {
//                    modprovee.setDoc(Integer.parseInt(buscarcli.getjLabuscarcliente().getValueAt(fila, 0).toString()));
                    if (colum == 9) {
                        buscarcli.setVisible(false);
                        nuefac.setVisible(true);
                        Object idprovee = modprovee.getDocume();
                        nuefac.getjTexidenproveedor().setText(idprovee.toString());
                        JOptionPane.showMessageDialog(null, "Proveedor Agregado");
                    }
                }
            }
        });

        buscarcli.getjTexbuscarcliente().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarcli.getjTexbuscarcliente().setText("");
                buscarcli.getjTexbuscarcliente().setForeground(new java.awt.Color(0, 0, 0));

            }
        });
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nuefac.getButguardarnuefactu())) {
            buscarcli.getjTexbuscarcliente().setText("Usuario");
            nuefac.setVisible(false);
            buscarcli.setLocationRelativeTo(null);
            modusu.mostrarTablaUsuario(buscarcli.getjTablebuscarcliente(), "", "Nueva Factura");
            buscarcli.setVisible(true);
            Border borde = BorderFactory.createTitledBorder(null, "Buscar Usuario",
                    javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Yu Gothic UI", 1, 36),
                    new java.awt.Color(204, 0, 204));
            buscarcli.getjPanebuscarcliente().setBorder(borde);
        }

        if (e.getSource().equals(nuefac.getButguardarnuefactu())) {
            buscarcli.getjLabuscarcliente().setText("cliente");
            nuefac.setVisible(false);
            buscarcli.setLocationRelativeTo(null);
            try {
                modprovee.mostrarTablaProveedor(buscarcli.getjTablebuscarcliente(), "", "Nueva Factura");
            } catch (SQLException ex) {
                Logger.getLogger(CotroladorFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            buscarcli.setVisible(true);
            Border borde = BorderFactory.createTitledBorder(null, "Buscar cliente",
                    javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Yu Gothic UI", 1, 36),
                    new java.awt.Color(204, 0, 204));
            buscarcli.getjPanebuscarcliente().setBorder(borde);
        }
        
        if (e.getSource().equals(nuefac.getjButoGuardar())){
            //validar campos vacios
            if ((nuefac.getjTexidenproveedor().getText().isEmpty()) || (nuefac.getjTexidenusuario().getText().isEmpty()) || (nuefac.getjCombtipodepago().getSelectedItem().equals("Seleccione..."))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en los campos de Nombre y Descripción");
            } else {
                modfactnue.setDocprovee(Integer.parseInt(nuefac.getjTexidenproveedor().getText()));
                modfactnue.setDocusu((Integer.parseInt(nuefac.getjTexidenusuario().getText())));
                modfactnue.setTipo_pag(nuefac.getjCombtipodepago().getSelectedItem().toString());

                if (nuefac.getjButoGuardar().getText().equals("Guardar")) {
                    try {
                        modfactnue.insertarFactcompra();
                    } catch (SQLException ex) {
                        Logger.getLogger(CotroladorFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    modfactnue.limpiar(nuefac.getjPanenuevafactura().getComponents());
                } else {
                    try {
                        modfactnue.actualizarProducto();
                    } catch (SQLException ex) {
                        Logger.getLogger(CotroladorFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    nuefac.setVisible(false);
                    nuefac.dispose();
                    try {
                        modfactnue.mostrarTablaFactCompra(prin.getjTablefactura(), "", "Factura");
                    } catch (SQLException ex) {
                        Logger.getLogger(CotroladorFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }

        if (e.getSource().equals(nuefac.getjButcancelar())) {
            nuefac.dispose();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
            modusu.mostrarTablaUsuario(buscarusuario.getjTablebuscarusuario(), buscarusuario.getjTextbuscarusuario().getText(), "Nueva Factura");
        try {
            modprovee.mostrarTablaProveedor(busprovee.getjTablebuscarproveedor(), busprovee.getjTextbuscarproveedor().getText(), "Nueva Factura");
        } catch (SQLException ex) {
            Logger.getLogger(CotroladorFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    

    @Override
    public void removeUpdate(DocumentEvent e) {
       
            modusu.mostrarTablaUsuario(buscarusuario.getjTablebuscarusuario(), buscarusuario.getjTextbuscarusuario().getText(), "Nueva Factura");
  
        try {
            modprovee.mostrarTablaProveedor(busprovee.getjTablebuscarproveedor(), busprovee.getjTextbuscarproveedor().getText(), "Nueva Factura");
        } catch (SQLException ex) {
            Logger.getLogger(CotroladorFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    

    @Override
    public void changedUpdate(DocumentEvent e) {
  
            modusu.mostrarTablaUsuario(buscarusuario.getjTablebuscarusuario(), buscarusuario.getjTextbuscarusuario().getText(), "Nueva Factura");
        try {
            modprovee.mostrarTablaProveedor(busprovee.getjTablebuscarproveedor(), busprovee.getjTextbuscarproveedor().getText(), "Nueva Factura");
        } catch (SQLException ex) {
            Logger.getLogger(CotroladorFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    

}
    

