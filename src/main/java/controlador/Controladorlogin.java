package controlador;

import Modelo.Modelo_login;
import Vista.Iniciar_sesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Controladorlogin implements ActionListener {

    Modelo_login modlog = new Modelo_login();
    Iniciar_sesion logi = new Iniciar_sesion();
//    Principal princ = new Principal();
    

    public Controladorlogin() {
        logi.getBtniniciar().addActionListener(this);
        logi.getBotmostrar().addActionListener(this);
    }

    public void validar() {
        modlog.setUsu(logi.getTxtusuario().getText());
        String pass = new String(logi.getJpclave().getPassword());
        modlog.setContra(pass);
        try {
            if (modlog.validar(modlog.getUsu(), modlog.getContra())) {
                ControladorPrincipal vista = new ControladorPrincipal();
                logi.setVisible(false);
                vista.iniciarPrincipal(0);
                
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Controladorlogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarVista() {
        logi.setLocationRelativeTo(null);
        logi.setTitle("Iniciar Sesión");
        logi.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(logi.getBotmostrar())) {

            if (logi.getJpasContraseña().getEchoChar() == '\u2022') {
                logi.getJpasContraseña().setEchoChar((char) 0);
                logi.getBotmostrar().setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojocerrado.jpg")));
            } else {
                logi.getJpasContraseña().setEchoChar('\u2022');
                logi.getBotmostrar().setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojoabierto.jpg")));
            }
        }
        if (e.getSource() == (logi.getBtniniciar())) {
            if (e.getSource() == (logi.getBotIniciar())) {
                modlog.setUsu(logi.getTxtusuario().getText());
                String pass = new String(logi.getJpasContraseña().getPassword());
                modlog.setContra(pass);
                if (modlog.getUsu().isEmpty() && (modlog.getContra().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Porfavor, Ingresar un usuario y una contraseña.");
                } else {
                    if (modlog.getUsu().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Porfavor, Ingresar un usuario.");
                    } else if (modlog.getContra().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Porfavor, Ingresar la contraseña.");
                    } else {
                        try {
                            if (modlog.validar(modlog.getUsu(), modlog.getContra())) {
                                logi.setVisible(false);
                                ControladorPrincipal controprin = new ControladorPrincipal();
                                controprin.iniciarPrincipal(0);
                                
                            } else {
                                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                                
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Controladorlogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        }

    }
}