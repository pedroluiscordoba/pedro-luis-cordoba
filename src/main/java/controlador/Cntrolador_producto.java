
package controlador;

import Modelo.Modeloproducto;
import Vista.Nuevo_Producto;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;



public class Cntrolador_producto  implements ActionListener {
    Modeloproducto md= new Modeloproducto();
    Nuevo_Producto np= new Nuevo_Producto();
     Principal princ = new Principal();
    
     public Cntrolador_producto() {
        np.getBtnbuscarimagen().addActionListener(this);
        np.getJbtnGuardarproducto().addActionListener(this);
        np.getCancelar() .addActionListener(this);
        np.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        np.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princ = new ControladorPrincipal();
                princ.iniciarPrincipal(2);
                np.setVisible(false);
            }
        });
    }

    public void controlProducto() {
        princ.setVisible(false);
        np.setLocationRelativeTo(null);
        np.setTitle("Nuevo Producto");
        np.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(np.getBtnbuscarimagen())) {
            md.buscarImagen();
            File file= new File(md.getRuta());
            String archivo= file.getName();//obtiene solo el nombre de la ruta
            np.getTxtbuscarimagenproducto().setText(archivo);
        }

        if (e.getSource().equals(np.getJbtnGuardarproducto())) {
            //validar campos vacios
            if ((np.getTxtnombre().getText().isEmpty()) || (np.getTxtdescr().getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en los campos de Nombre y Descripción");
            } else {
                md.setNom(np.getTxtnombre().getText());
                md.setDes(np.getTxtdescr().getText());
                md.setImagen(md.convertirimagen(md.getRuta()));

                if (np.getGuardar().getText().equals("Guardar")) {
//                    md.insertarproducto();
                    md.limpiarProducut(np.getjPanenuevoproducto().getComponents());
                } else {
//                    md.insertarproducto();
                    np.setVisible(false);
                    np.dispose();
//                    np.mostrarTablaProducto(princ. getjButbuscarproducto(),  "Producto");
                }
            }
        }
        if (e.getSource().equals(np.getCancelar())) {
            np.dispose();
        }
    }

    //Actualizar producto
    void actualizarProducto(int doc) {
        md.buscarProducto(doc);
        np.getTxtnombre().setText(md.getNom());
        np.getTxtdescr().setText(md.getDes());
        np.getTxtimagenproducto().setText(md.getRuta() );

        File file = new File(md.getRuta());
        String archivo = file.getName();//obtiene solo el nombre de la ruta
        np.getTxtimagen().setText(archivo);

        //Cambiar Titulo
        Border borde = BorderFactory.createTitledBorder(null, "Actualizar Producto",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Yu Gothic UI", 1, 36),
                new java.awt.Color(204, 0, 204));
        np.getjPanenuevoproducto().setBorder(borde);
        np.setVisible(false);
        np.setLocationRelativeTo(null);
        np.getJbtnGuardarproducto().setText("Actualizar");
        np.setVisible(true);
    }
    //Eliminar cliente
    void eliminarProducto(int doc) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Producto? \n" + doc,
                "Eliminar Producto", JOptionPane.YES_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
//            md.eliminarProducto();
        }
    }
}

    
        
    

        
    
    


 