
package controlador;

import Modelo.ModeloCliente;
import Vista.Nuevo_Cliente;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



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
                princ.iniciarPrincipal();
            }
        });
    }

    public void controlCliente(){
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
          
        if (e.getSource().equals(clien.getBtnguardarcli())) {
            //validar campos vacios
            if ((clien.getjTexdocumento().getText().isEmpty()) ||(clien.getjComtipodocumen().getSelectedItem().equals("Seleccione..."))|| (clien.getTxtnombre().getText().isEmpty()) || (clien.getTxtnombre().getText().isEmpty())
                    || (clien.getTxtcorreo().getText().isEmpty()) || (clien.getTxttelefono().getText().isEmpty())|| (clien.getJdatefecha() .getDate() == null) 
                    || (clien.getCombsexoclien().getSelectedItem().equals("Seleccione..."))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en todos los campos");
            } else {
                //Convertimos el dato de los combox al que entiende sql
                String valorSexo = clien.getCombsexoclien().getSelectedItem().toString();
                int sexo = modclie.llenarCombo("sexo").get(valorSexo);
                
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
                modclie.setSexo(sexo);
                modclie.setFech(fecha);
                modclie.insertarCliente();
                modclie.limpiar(clien.getJpanelcliente().getComponents());
                
                if (clien.getBtnGuardar().getText().equals("Guardar")) {
                    modclie.insertarCliente();
                    modclie.limpiar(clien.getJpanelcliente().getComponents());
                } else {
//                    modcliente.actualizarUsuario();
//                    usu.setVisible(false);
//                    prin.setVisible(true);
                    modclie.mostrarTablaCliente(princ.getjTableCliente(), "", "Cliente");
//                    prin.getTpPrincipal().setSelectedIndex(0);
                }
            }
        }
    }
}