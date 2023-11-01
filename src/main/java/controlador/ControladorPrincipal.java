package controlador;

import Modelo.ModeloUsuario;
import Vista.Nuevos_Usuarios;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControladorPrincipal implements ActionListener, ChangeListener {

    Nuevos_Usuarios usu = new Nuevos_Usuarios();
    Principal princ = new Principal();
    ModeloUsuario modeusu = new ModeloUsuario();

    public void iniciarVista() {
        princ.setLocationRelativeTo(null);
        princ.setVisible(true);
        princ.setExtendedState(JFrame.MAXIMIZED_BOTH);
        princ.setVisible(true);//hace visible la ventana
        getionPestanas();
    }

    public ControladorPrincipal() {
        princ.getBtnNuevo().addActionListener(this);
        usu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void iniciar(int valor){
        princ.setLocationRelativeTo(null);//centra la pantalla
        princ.setTitle("Principal");//le da titulo a la venta
        princ.setExtendedState(JFrame.MAXIMIZED_BOTH);
        princ.getJtPrincipal().setSelectedIndex(valor);
        princ.setVisible(true);//hace visible la ventana
        //gestionPestaña();
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSurce().equals(princ.getBtNuevo())) {

            princ.Visible(false)
            ();
            ContUsu.control();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int seleccion = princ.getJtPrincipal().getSelectedIndex();
        if (seleccion == 0) {
            gestionPestana();

        }
        if (seleccion == 1) {
            ModeloUsuario modUsu = new ModeloUuario();//instancia el modelo
            modUsu.mostrarTablaUsuario(princ.TbUsuario(), valor
            :"");
            
            princ.getTxtbuscarusuario().addActionListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    princ.getTxtbuscar().setText("");
                    princ.getTxtbuscarusuario().setForeground(color.BLACK);
                }
            }
            princ.getTxtbuscarusuario().getDocument().addDocumentListener(new DocumentListener() {
                @Override
                
            
            }
        }

    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public void setPrinc(Principal princ) {
        this.princ = princ;
    }

    public void setModeusu(ModeloUsuario modeusu) {
        this.modeusu = modeusu;
    }

    public Usuario getUsu() {
        return usu;
    }

    public Principal getPrinc() {
        return princ;
    }

    public ModeloUsuario getModeusu() {
        return modeusu;
    }

}
