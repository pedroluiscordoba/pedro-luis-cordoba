/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Modelo.ModeloCliente;
import Modelo.ModeloUsuario;
import Modelo.Modeloventa;
import Vista.Buscarcliente;
import Vista.Buscarusuario;
import Vista.Nueva_venta;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Peter Louis
 */
public class Controladorventa implements ActionListener, DocumentListener {
    Nueva_venta factventa = new Nueva_venta();
    Principal prin = new Principal();
    Buscarusuario buscarusu = new Buscarusuario();
    Buscarcliente buscarcli = new Buscarcliente();
    ModeloUsuario modusu = new ModeloUsuario();
    ModeloCliente modclient = new ModeloCliente();
    Modeloventa modventas = new Modeloventa();

    //Constructor
    public Controladorventa() {
        factventa.getButonguardarventa().addActionListener(this);
        factventa.getButonguardarventa().addActionListener(this);
        factventa.getjButidentificacioncliente().addActionListener(this);
        factventa.getjButidentificacionusuario().addActionListener(this);
        buscarusu.getjTextbuscarusuario().getDocument().addDocumentListener(this);
        buscarcli.getjTexbuscarcliente().getDocument().addDocumentListener(this);
        factventa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Desactiva la x que cierra el programa para que permita abrir o volcer a la ventana principal
        factventa.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princ = new ControladorPrincipal();
//                princ.iniciarPrincipal();
            }
        });
    }

    public void controlVenta() {
        prin.setVisible(false);
        factventa.setLocationRelativeTo(null);
        factventa.setTitle("Nueva Venta");
        factventa.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(factventa.getjButidentificacioncliente())) {
            factventa.setVisible(false);
            buscarcli.setLocationRelativeTo(null);
            try {
                modclient.mostrarTablaCliente(buscarcli.getjTablebuscarcliente(), "", "Factura Venta");
            } catch (SQLException ex) {
                Logger.getLogger(Controladorventa.class.getName()).log(Level.SEVERE, null, ex);
            }
            buscarcli.setVisible(true);
        }

        if (e.getSource().equals(factventa.getjButidentificacionusuario())) {
            factventa.setVisible(false);
            buscarusu.setLocationRelativeTo(null);
            modusu.mostrarTablaUsuario(buscarusu.getjTablebuscarusuario(), "", "Factura Venta");
            buscarusu.setVisible(true);
            Border borde = BorderFactory.createTitledBorder(null, "Buscar Usuario",
                    javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Yu Gothic UI", 1, 36),
                    new java.awt.Color(204, 0, 204));
            buscarusu.getjPanebuscarusuario().setBorder(borde);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
    

