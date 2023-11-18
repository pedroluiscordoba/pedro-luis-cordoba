package controlador;

import Modelo.Modelo_login;
import Vista.Iniciar_sesion;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controladorlogin implements ActionListener {

    Modelo_login modlog = new Modelo_login();
    Iniciar_sesion log = new Iniciar_sesion();
    ControladorPrincipal vista = new ControladorPrincipal();
    Principal princ = new Principal();

    public Controladorlogin() {
        log.getBtniniciar().addActionListener(this);
        log.getBotmostrar().addActionListener(this);
    }

    public void iniciarVista() {
        log.setLocationRelativeTo(null);
        log.setTitle("Iniciar Sesión");
        log.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(log.getBotmostrar())) {

            if (log.getJpasContraseña().getEchoChar() == '\u2022') {
                log.getJpasContraseña().setEchoChar((char) 0);
                log.getBotmostrar().setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojocerrado.jpg")));
            } else {
                log.getJpasContraseña().setEchoChar('\u2022');
                log.getBotmostrar().setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojoabierto.jpg")));
            }
        }
        if (e.getSource() == (log.getBtniniciar())) {
            modlog.setUsu(log.getTxtusuario().getText());
            String pass = new String(log.getJpclave().getPassword());
            modlog.setContra(pass);
            if (modlog.validar(modlog.getUsu(), modlog.getContra())) {
                
                log.setVisible(false);
                vista.iniciarPrincipal();


            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");

            }
        }

    }

}
