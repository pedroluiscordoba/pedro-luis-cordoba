
package controlador;

import Modelo.Modeloproducto;
import Vista.Nuevo_Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;



public class Cntrolador_producto  implements ActionListener {
    Modeloproducto md= new Modeloproducto();
    Nuevo_Producto np= new Nuevo_Producto();
   

    public Cntrolador_producto() {
//       
    }

    public Modeloproducto getMd() {
        return md;
    }

    public void setMd(Modeloproducto md) {
        this.md = md;
    }

    public Nuevo_Producto getNp() {
        return np;
    }

    public void setNp(Nuevo_Producto np) {
        this.np = np;
    }
    
  


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(np.getButbuscarimagen())){
            md.buscarImagen();
            File file= new File(md.getRuta());
            
        }
    }
    
}

 