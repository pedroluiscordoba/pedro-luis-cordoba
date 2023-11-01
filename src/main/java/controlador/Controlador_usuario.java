package controlador;

import Modelo.ModeloUsuario;
import Vista.Nuevos_Usuarios;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Controlador_usuario implements ActionListener {

    Nuevos_Usuarios usua = new Nuevos_Usuarios();
    Principal prin = new Principal();
    ModeloUsuario usu = new ModeloUsuario();

    public Controlador_usuario() {
    }

    public void conntrol_usua(){
        usua.setLocationRelativeTo(null);
        usua.setVisible(true);
        
        //Llenar Combo sexo
        usua.getCbSexo().addItem("Seleccione...");
        Map<String,Integer>dato =usu.llenarCombo("sexo");
        for(String sexo:dato.keySet()){
            usua.getCbSexo().addItem(sexo);
        }
        
        usua.getCbCargo().addItem("Seleccione...");
        Map<String,Integer>dato =usu.llenarCombo(cargo);
        for(String cargo:dato.keySet()){
            usua.getCbcargo().addItem(cargo);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

public class Controlador_usuario implements ActionListener {

    public Controlador_usuario() {
    }

    public void control_usua() {
        prin.addWindowListener(now WindowAdapter()
        { 
        

        

    public void windowClosed(windowEvent e) {
        prin.setVisible(true);
    }
}

prin.setVisible (

    false);
    usua.setLocationRelativeTo (

    null);
    usua.setVisible (

    true);
    usua.getJcvsexo.addItem (
    "seleccione");
Map<string, Integer> dato = usua.llenarCombo("sexo");
    for (String sexo

    : dato.keySet () 
        ) {
    usua.getJcvsexo().addItem(sexo);
    }

    usua.getCbxCargo ()
    .addItem("Seleccione...");
        Map<String, Integer> datos = usua.llenarCombo("cargo");
    for (String rol

    : datos.keySet () 
        ) {
            usua.getCbxCargo().addItem(rol);
    }
}
@Override
public void actionPerformed(ActionEvent e) {
