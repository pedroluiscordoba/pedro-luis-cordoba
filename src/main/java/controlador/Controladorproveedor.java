
package controlador;

import Modelo.Modeloproveedor;
import Vista.Nuevo_proveedor;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Controladorproveedor implements ActionListener  {
     Nuevo_proveedor provee = new Nuevo_proveedor();
    Principal princ = new Principal();
    Modeloproveedor modprovee = new Modeloproveedor();

    public Controladorproveedor() {
        provee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        provee.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princ = new ControladorPrincipal();
                princ.iniciarPrincipal();
            }
        });
    }

    

 public void controlProveedor() {
        princ.setVisible(false);
        provee.setLocationRelativeTo(null);
        provee.setTitle("Nuevo Proveedor");
        provee.setVisible(true);

        //lleno combo box sexo
        provee.getjCombsexo().addItem("Seleccione...");
        Map<String, Integer> combosexo = modprovee.llenarCombo("sexo");
        for (String sexo : combosexo.keySet()) {
            provee.getjCombsexo().addItem(sexo);
        }
    }
    public void actionPerformed(ActionEvent e, Date fecha) {
                if (e.getSource().equals(provee.getBotguardar())) {
            //validar campos vacios
            if ((provee.getjTexdocumento().getText().isEmpty()) ||(provee.getjComtipodedocumento().getSelectedItem().equals("Seleccione..."))|| (provee.getjTextnombre().getText().isEmpty()) || (provee.getjTextdireccion().getText().isEmpty())
                    || (provee.getjTexcorreo().getText().isEmpty()) || (provee.getjTextelefono().getText().isEmpty())|| (provee.getjDateChfehanaci().getDate() == null) 
                    || (provee.getjCombsexo().getSelectedItem().equals("Seleccione..."))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en todos los campos");
            } else {
              //Convertimos el dato de los combox al que entiende sql
                String valorSexo = provee.getjCombsexo().getSelectedItem().toString();
                int sexo = modprovee.llenarCombo("sexo").get(valorSexo);
                
                // seleccion de fecha, cambia al formato de fecha al que entiende sql
                java.util.Date fech = provee.getjDateChfehanaci().getDate();
               // long fec = fec.getTime();
               // java.sql.Date fecha = new Date(fec);
                        
                modprovee.setDocume(Integer.parseInt(provee.getjTexdocumento().getText()));
                modprovee.setTipo_perso(provee.getjComtipodedocumento().getSelectedItem().toString());
                modprovee.setNomb(provee.getjTextnombre().getText());
                modprovee.setDirec(provee.getjTextdireccion().getText());
                modprovee.setTipo_perso(provee.getjComtipodedocumento().getSelectedItem().toString());
                modprovee.setCorreo(provee.getjTexcorreo().getText());
                modprovee.setTelefo(provee.getjTextelefono().getText());
                modprovee.setFecha(fecha);
                modprovee.setSexo(sexo);
                modprovee.insertarProveedor();
                //modprovee.limpiar(provee.getJpProvee().getComponents());

            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

