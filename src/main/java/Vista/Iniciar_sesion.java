/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author user
 */
public class Iniciar_sesion extends javax.swing.JFrame {

    /**
     * Creates new form Iniciar_seccion
     */
    public Iniciar_sesion() {
        initComponents();
    }

    public JButton getBotIniciar() {
        return botIniciarseccion;
    }

    public void setBotIniciar(JButton botIniciar) {
        this.botIniciarseccion = botIniciar;
    }

    public JButton getBotmostrar() {
        return btnmostrarcontra;
    }

    public void setBotmostrar(JButton botmostrar) {
        this.btnmostrarcontra = botmostrar;
    }

    public JTextPane getjTextPane2() {
        return jTextPane2;
    }

    public JButton getBotIniciarsecc() {
        return botIniciarseccion;
    }

    public JButton getBtnmostrarcontra() {
        return btnmostrarcontra;
    }

    public void setjTextPane2(JTextPane jTextPane2) {
        this.jTextPane2 = jTextPane2;
    }

    public JPasswordField getJpasContraseña() {
        return jpasContraseña;
    }

    public void setJpasContraseña(JPasswordField jpasContraseña) {
        this.jpasContraseña = jpasContraseña;
    }

    public JButton getBtniniciar() {
        return botIniciarseccion;
    }

    public void setBtniniciar(JButton btniniciar) {
        this.botIniciarseccion = btniniciar;
    }

    public JPasswordField getJpclave() {
        return jpasContraseña;
    }

    public void setJpclave(JPasswordField jpclave) {
        this.jpasContraseña = jpclave;
    }

    public JTextField getTxtusuario() {
        return txtusuario;
    }

    public void setTxtusuario(JTextField txtusuario) {
        this.txtusuario = txtusuario;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabiniciarsesion = new javax.swing.JLabel();
        labUsuario = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        lblcontraseña = new javax.swing.JLabel();
        jpasContraseña = new javax.swing.JPasswordField();
        botIniciarseccion = new javax.swing.JButton();
        btnmostrarcontra = new javax.swing.JButton();

        jScrollPane2.setViewportView(jTextPane2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabiniciarsesion.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabiniciarsesion.setText("Iniciar Seccion");

        labUsuario.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        labUsuario.setText("Usuario");

        lblcontraseña.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblcontraseña.setText("Contraseña");

        botIniciarseccion.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        botIniciarseccion.setText(" Iniciar Seccion");

        btnmostrarcontra.setText("Mostrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botIniciarseccion)
                    .addComponent(jLabiniciarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusuario)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jpasContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnmostrarcontra, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabiniciarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblcontraseña)
                    .addComponent(btnmostrarcontra, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jpasContraseña))
                .addGap(57, 57, 57)
                .addComponent(botIniciarseccion)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botIniciarseccion;
    private javax.swing.JButton btnmostrarcontra;
    private javax.swing.JLabel jLabiniciarsesion;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JPasswordField jpasContraseña;
    private javax.swing.JLabel labUsuario;
    private javax.swing.JLabel lblcontraseña;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
