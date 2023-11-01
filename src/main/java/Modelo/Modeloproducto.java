
package Modelo;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Modeloproducto {
    private String nom, des,ruta;
    private byte imagen[];

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public void buscarImagen(){
        JFileChooser archivos = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
        "JPG,PNG,& GIF","jpg","png","gif");
        archivos.setFileFilter(filtro);
        if(archivos.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            setRuta(archivos.getSelectedFile().getAbsolutePath());
       
    }
                
    }
    
}
