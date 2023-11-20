package controlador;

import Modelo.ModeloUsuario;
import Vista.Nuevos_Usuarios;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controladorusuario implements ActionListener {

    Nuevos_Usuarios usua = new Nuevos_Usuarios();
    Principal princ = new Principal();
    ModeloUsuario modusuario = new ModeloUsuario();


    //constructor
    public Controladorusuario() {
        usua.getJbotguardar().addActionListener(this);
        usua.getBotnmostrarclave().addActionListener(this);
        usua.getJbotocancelar().addActionListener(this);
        usua.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usua.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princ = new ControladorPrincipal();
                princ.iniciarPrincipal();
            }
        });
    }

    public void controladorusuario() {
        princ.setVisible(false);
        usua.setLocationRelativeTo(null);
        usua.setTitle("Nuevos Usuario");
        usua.setVisible(true);

        //lleno combo box sexo
        usua.getCombSexo().addItem("Seleccione...");
        Map<String, Integer> comboSexo = modusuario.llenarCombo("sexo");
        for (String sexo : comboSexo.keySet()) {
            usua.getCombSexo().addItem(sexo);
        }
        //lleno combo box cargo
        usua.getjCombcargo().addItem("Seleccione...");
        Map<String, Integer> jCombcargo =  modusuario.llenarCombo("Cargo");
        for (String cargo : jCombcargo.keySet()) {
            usua.getjCombcargo().addItem(cargo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(usua.getBotnmostrarclave())) {

            if (usua.getjPassclave().getEchoChar()== '\u2022') {
                usua.getjPassclave().setEchoChar((char) 0);
                usua.getBotnmostrarclave().setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo-cruzado.png")));
            } else {
                usua.getjPassclave().setEchoChar('\u2022');
                usua.getBotnmostrarclave().setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png")));
            }
        }
        if (e.getSource().equals(usua.getJbotguardar())) {
            //validar campos vacios
            if ((usua.getLbldocumento().getText().isEmpty()) || (usua.getJcomtipodocu().getSelectedItem().equals("Seleccione...")) || (usua.getTxtNombre().getText().isEmpty()) || (usua.getTxtDireccion().getText().isEmpty())
                    || (usua.getTxtCorreo().getText().isEmpty()) || (usua.getTxtlogin().getText().isEmpty()) || (usua.getTxtTelefono().getText().isEmpty())
                    || (usua.getjPassclave().getPassword() == null) || (usua.getjDateChfechanaci().getDate() == null) || (usua.getjCombcargo().getSelectedItem().equals("Seleccione..."))
                    || (usua.getCombSexo().getSelectedItem().equals("Seleccione..."))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en todos los campos");
            }else {
                //Convertimos el dato de los combox al que entiende sql
                String valorSexo = usua.getCombSexo().getSelectedItem().toString();
                int sexo = modusuario.llenarCombo("sexo").get(valorSexo);
                String valorRol = usua.getjCombcargo().getSelectedItem().toString();
                int Cargo = modusuario.llenarCombo("cargo").get(valorRol);

                // seleccion de fecha, cambia al formato de fecha al que entiende sql
                java.util.Date fec = usua.getjDateChfechanaci().getDate();
                long fe = fec.getTime();
                java.sql.Date fecha = new Date(fe);

                //Contraseña
                char[] clave = usua.getjPassclave().getPassword();
                String contrasena = String.valueOf(clave);

                modusuario.setDocum(Integer.parseInt(usua.getTxtdocumento().getText()));
                modusuario.setTip_docum(usua.getJcomtipodocu().getSelectedItem().toString());
                modusuario.setNomb(usua.getTxtNombre() .getText());
                modusuario.setDirecc(usua.getTxtDireccion().getText());
                modusuario.setCorre(usua.getTxtCorreo().getText());
                modusuario.setTelef(usua.getTxtTelefono().getText());
                modusuario.setLogi(usua.getTxtlogin().getText());
                modusuario.setFech(fecha);
                modusuario.setClave(contrasena);
                modusuario.setSex(sexo);
                modusuario.setCargo(Cargo);

                if (usua.getJbotguardar().getText().equals("Guardar")) {
                    modusuario.llenarnuevousuario();
                    //modusuario.limpiar(usua.getJpNuevoUsuario().getComponents());
                         } else {
                    modusuario.actualizarUsuario();
                    usua.setVisible(false);
                    princ.setVisible(true);
//                  modusuario.mostrarTablaUsuario(princ.getjTableUsuario(), "", "Usuario");
//                    prin.getTpPrincipal().setSelectedIndex(0);
                }

            }
        }

    }
//Actualizar Usuario

    void actualizarUsuario(int doc) {
        modusuario.buscarUsuario(doc);
        usua.getTxtdocumento().setEnabled(false);
        usua.getTxtlogin() .setEnabled(false);
        usua.getJcomtipodocu().setEnabled(false);
        usua.getTxtdocumento().setText(String.valueOf(doc));
        usua.getTxtNombre().setText(modusuario.getNomb());
        usua.getTxtTelefono().setText(modusuario. getTelef());
        usua.getTxtCorreo().setText(modusuario.getCorre());
        usua.getTxtDireccion().setText(modusuario.getDirecc());
        usua.getTxtlogin() .setText(modusuario.getLogi());
//        usua.setClave.setText(modusuario.getContra());
        usua.getjDateChfechanaci().setDate(modusuario.getFech());

        //llenar Sexo
        Map<String, Integer> info = modusuario.llenarCombo("sexo");
        for (String sexo : info.keySet()) {
            usua.getCombSexo().addItem(sexo);
        }
        //obtener el valor de la base de datos
        String valoSexo = modusuario.obtenerSeleccion(info, modusuario.getSex());
        usua.getCombSexo().setSelectedItem(valoSexo);

        //llenar Rol
        Map<String, Integer> info2 = modusuario.llenarCombo("rol");
        for (String rol : info2.keySet()) {
            usua.getjCombcargo().addItem(rol);
        }

        //obtener el valor de la base de datos
        String valoRol = modusuario.obtenerSeleccion(info2, modusuario.getCargo());
        usua.getjCombcargo().setSelectedItem(valoRol);

        //Cambiar Titulo
        
        princ.setVisible(false);
        usua.setLocationRelativeTo(null);
        usua.getJbotguardar().setText("Actualizar");
        usua.setVisible(true);

    }
    //Eliminar Usuario

    void eliminarUsuario(int doc) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al usuario? \n" + doc,
                 "Eliminar Usuario", JOptionPane.YES_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            modusuario.setDocum(doc);
            modusuario.eliminarUsuario();
        }
     }

}
    

