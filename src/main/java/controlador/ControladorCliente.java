package controlador;

import Modelo.ModeloCliente;
import Vista.Nuevo_Cliente;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.get;
import javax.swing.border.Border;

public class ControladorCliente implements ActionListener {

    Nuevo_Cliente clien = new Nuevo_Cliente();
    Principal princ = new Principal();
    ModeloCliente modclie = new ModeloCliente();

    public ControladorCliente() {
        clien.getBtnGuardar().addActionListener(this);
        clien.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clien.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princ = new ControladorPrincipal();
//                princ.iniciarPrincipal();
            }
        });
    }

    public void controlCliente() throws SQLException {
        princ.setVisible(false);
        clien.setLocationRelativeTo(null);
        clien.setTitle("Nuevo Cliente");
        clien.setVisible(true);

        //lleno combo box sexo
        clien.getCombsexoclien().addItem("Seleccione...");
        Map<String, Integer> combosexo = modclie.llenarCombo("sexo"); //modcliente.llenarCombo("sexo");
        for (String sexo : combosexo.keySet()) {
            clien.getCombsexoclien().addItem(sexo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(clien.getBtnGuardar())) {
            //validar campos vacios
            if ((clien.getjTexdocumento().getText().isEmpty()) || (clien.getjComtipodocumen().getSelectedItem().equals("Seleccione...")) || (clien.getTxtnombre().getText().isEmpty()) || (clien.getTxtnombre().getText().isEmpty())
                    || (clien.getTxtcorreo().getText().isEmpty()) || (clien.getTxttelefono().getText().isEmpty()) || (clien.getJdatefecha().getDate() == null)
                    || (clien.getCombsexoclien().getSelectedItem().equals("Seleccione..."))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en todos los campos");
            } else {
                //Convertimos el dato de los combox al que entiende sql
                String valorSexo = clien.getCombsexoclien().getSelectedItem().toString();
                int sexo;
                try {
                    sexo = modclie.llenarCombo("sexo").get(valorSexo);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

                // seleccion de fecha, cambia al formato de fecha al que entiende sql
                java.util.Date fec = clien.getJdatefecha().getDate();
                long fe = fec.getTime();
                java.sql.Date fecha = new Date(fe);

                modclie.setDocum(Integer.parseInt(clien.getjTexdocumento().getText()));
                modclie.setTipodoc(clien.getjComtipodocumen().getSelectedItem().toString());
                modclie.setNomb(clien.getTxtnombre().getText());
                modclie.setTele(clien.getTxttelefono().getText());
                modclie.setCorr(clien.getTxtcorreo().getText());
                modclie.setDirr(clien.getTxtdireccion().getText());
//                modclie.setSexo(sexo);
                modclie.setFech(fecha);
                try {
                    modclie.insertarCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (clien.getBtnGuardar().getText().equals("Guardar")) {
                    try {
                        modclie.actualizarCliente();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    modclie.limpiar(clien.getJpanelcliente().getComponents());
                } else {

                    try {
                        modclie.actualizarCliente();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clien.setVisible(false);
                    princ.setVisible(true);
                    try {
                        modclie.mostrarTablaCliente(princ.getJtablecliente(), "", "Usuario");
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    princ.getJtableprincipal().setSelectedIndex(0);
                }

                if (clien.getBtnGuardar().getText().equals("Guardar")) {
                    try {
                        modclie.insertarCliente();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    modclie.limpiar(clien.getJpanelcliente().getComponents());
                } else {
                    try {
                        modclie.actualizarCliente();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clien.setVisible(false);
                    princ.setVisible(true);
                    try {
                        modclie.mostrarTablaCliente(princ.getTablecliente(), "", "Cliente");
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    princ.getJtableprincipal().setSelectedIndex(0);
                }
            }
        }
        if (e.getSource().equals(clien.getBtnCancelar())) {
            clien.dispose();
        }
    }

//Actualizar cliente
void actualizarCliente(int doc) throws SQLException {
        modclie.buscarCliente(doc);
        clien.getjTexdocumento().setEnabled(false);
        clien.getjComtipodocumen().setEnabled(false);
        clien.getjTexdocumento().setText(String.valueOf(doc));
        clien.getTxtnombre().setText(modclie.getNomb());
        clien.getTxttelefono().setText(modclie.getTele());
        clien.getTxtcorreo().setText(modclie.getCorr());
        clien.getTxtdireccion().setText(modclie.getDirr());
        clien.getJdatefecha().setDate(modclie.getFech());

        //llenar Sexo
        Map<String, Integer> info = modclie.llenarCombo("sexo");
        for (String sexo : info.keySet()) {
            clien.getCombsexoclien().addItem(sexo);
        }
        //obtener el valor de la base de datos
//        String valoSexo = modclie.obtenerSeleccion(info, modclie.getSexo());
//        clien.getCombsexoclien().setSelectedItem(valoSexo);

        //Llenar tipo de documento
        clien. getjComtipodocumen() .setSelectedItem(modclie.getTipodoc());

        //Cambiar Titulo
        Border borde = BorderFactory.createTitledBorder(null, "Actualizar Cliente",
        javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
        new java.awt.Font("Yu Gothic UI", 1, 36),
         new java.awt.Color(204, 0, 204));
        clien.getJpanelcliente().setBorder(borde);
        princ.setVisible(false);
        clien.setLocationRelativeTo(null);
        clien.getBtnGuardar().setText("Actualizar");
        clien.setVisible(true);
    }
//Eliminar cliente

    void eliminarCliente(int doc) throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al Cliente? \n" + doc,
        "Eliminar Cliente", JOptionPane.YES_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
//         modclie.set(doc);
         modclie.eliminarCliente();
        }
    }
}

