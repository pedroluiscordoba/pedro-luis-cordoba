package controlador;

import Modelo.Modeloproveedor;
import Vista.Nuevo_proveedor;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Controladorproveedor implements ActionListener {

    Nuevo_proveedor provee = new Nuevo_proveedor();
    Principal princ = new Principal();
    Modeloproveedor modprovee = new Modeloproveedor();

    public Controladorproveedor() {
        provee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        provee.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princ = new ControladorPrincipal();
//                princ.iniciarPrincipal();
            }
        });
    }

    public void controlProveedor() throws SQLException {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(provee.getBotguardar())) {
            //validar campos vacios
            if ((provee.getjTexdocumento().getText().isEmpty()) || (provee.getjComtipodedocumento().getSelectedItem().equals("Seleccione...")) || (provee.getjTextnombre().getText().isEmpty()) || (provee.getjTextdireccion().getText().isEmpty())
                    || (provee.getjTexcorreo().getText().isEmpty()) || (provee.getjTextelefono().getText().isEmpty()) || (provee.getjDateChfehanaci().getDate() == null)
                    || (provee.getjCombsexo().getSelectedItem().equals("Seleccione..."))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en todos los campos");
            } else {
                //Convertimos el dato de los combox al que entiende sql
                String valorSexo = provee.getjCombsexo().getSelectedItem().toString();

                int sexo = 0;
                try {
                    sexo = modprovee.llenarCombo("sexo").get(valorSexo);
                } catch (SQLException ex) {
                    Logger.getLogger(Controladorproveedor.class.getName()).log(Level.SEVERE, null, ex);
                }


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
                modprovee.setFecha((Date) fech);
                modprovee.setSexo(sexo);

                ControladorPrincipal princi = new ControladorPrincipal();
                if (princi.validarcorreo(modprovee.getCorreo()) == true) {

                    if (provee.getBotguardar().getText().equals("Guardar")) {
                        try {
                            modprovee.insertarProveedor();
                        } catch (SQLException ex) {
                            Logger.getLogger(Controladorproveedor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        modprovee.limpiar(provee.getjPanenuevoproveedor().getComponents());
                    } else {

                        try {
                            modprovee.actualizarProveedor();
                        } catch (SQLException ex) {
                            Logger.getLogger(Controladorproveedor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        provee.setVisible(false);
                        princ.getJtableprincipal().setSelectedIndex(1);
                        try {
                            modprovee.mostrarTablaProveedor(princ.getjTableproveedor(), "", "Proveedor");
                        } catch (SQLException ex) {
                            Logger.getLogger(Controladorproveedor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        provee.dispose();

                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Correo Invalido");
                }
            }
        }
        if (e.getSource().equals(provee.getBotcancelar())) {
            provee.dispose();
        }
    }
    //Actualizar proveedor

    public void actualizarProveedor(int iddoc) throws SQLException {
        modprovee.buscarProveedor(iddoc);
        provee.getjTexdocumento().setEnabled(false);
        provee.getjComtipodedocumento().setEnabled(false);
        provee.getjTexdocumento().setText(String.valueOf(iddoc));
        provee.getjTextnombre().setText(modprovee.getNomb());
        provee.getjTextelefono().setText(modprovee.getTelefo());
        provee.getjTexcorreo().setText(modprovee.getCorreo());
        provee.getjTextdireccion().setText(modprovee.getDirec());
        provee.getjDateChfehanaci().setDate(modprovee.getFecha());

        //llenar Sexo
        Map<String, Integer> info = modprovee.llenarCombo("sexo");
        for (String sexo : info.keySet()) {
            provee.getjCombsexo().addItem(sexo);
        }
        //obtener el valor de la base de datos
        String valoSexo = modprovee.obtenerSeleccion(info, modprovee.getSexo());
        provee.getjCombsexo().setSelectedItem(valoSexo);

        //Llenar tipo de documento y de persona
        provee.getjComtipodedocumento().setSelectedItem(modprovee.getTipo_docume());
//            provee.getCmbtipersona().setSelectedItem(modprovee.getTipo_perso());

        //Cambiar Titulo
        Border borde = BorderFactory.createTitledBorder(null, "Actualizar Proveedor",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Yu Gothic UI", 1, 36),
                new java.awt.Color(204, 0, 204));
        provee.getjPanenuevoproveedor().setBorder(borde);
        princ.setVisible(false);
        provee.setLocationRelativeTo(null);
        provee.getBotguardar().setText("Actualizar");
        provee.setVisible(true);
    }
    //Eliminar cliente

    void eliminarproveedor(int idprooveedor
    ) throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al Proveedor? \n" + idprooveedor,
                "Eliminar Proveedor", JOptionPane.YES_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            modprovee.setDocume(idprooveedor);
            modprovee.eliminarProveedor();
        }
    }
}
