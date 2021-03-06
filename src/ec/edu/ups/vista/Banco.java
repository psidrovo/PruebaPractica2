/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.FlujoCliente;
import ec.edu.ups.modelo.HiloCajero;
import ec.edu.ups.modelo.Recursos;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

/**
 *
 * @author Paul Idrovo
 */
public class Banco extends javax.swing.JFrame {

    private Recursos transaccionesDisponibles;
    private FlujoCliente flujoCliente;
    private List<Cliente> listaClientes;
    private List<Cliente> filaBanco;
    private List<Thread> listaCajeros;
    private DefaultListModel<String> modeloLista;
    private List<JLabel> lblCajeros;
    private List<JLabel> lblClientes;

    public Banco() {
        initComponents();
        lblCajeros = new ArrayList<>();
        lblClientes = new ArrayList<>();
        transaccionesDisponibles = new Recursos(40, 20, 60, 40, 20, 60);
        listaClientes = new ArrayList<>();
        filaBanco = new ArrayList<>();
        listaCajeros = new ArrayList<>();
        modeloLista = new DefaultListModel<>();
        
        int posicion = 100;
        for (int i = 1; i < 4; i++) {
            JLabel labelCajero = new JLabel("CAJERO " + (i), (int) CENTER_ALIGNMENT);
            labelCajero.setBounds(posicion, 20, 90, 60);
            posicion=posicion+150;
            labelCajero.setFont(new Font("Verdana", Font.BOLD, 10)); //ESTILO TEXTO
            labelCajero.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(51, 255, 0)));
            pnlBanco.add(labelCajero);
            lblCajeros.add(labelCajero);
        }        
        posicion = 100;
        for (int i = 0; i < 100; i++) {
            JLabel labelCliente = new JLabel("C " + (i+1), (int) CENTER_ALIGNMENT);
            labelCliente.setFont(new Font("Verdana", Font.BOLD, 8)); //ESTILO TEXTO
            labelCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(51, 255, 0)));
            if (i < 10) {
                filaBanco.add(new Cliente(i, 100));
                labelCliente.setBounds(30, posicion, 30, 30);
                posicion=posicion+40;
            } else {
                labelCliente.setBounds(30, 0, 30, 30);
                labelCliente.setVisible(false);
                listaClientes.add(new Cliente(i, 100));
            }
            pnlBanco.add(labelCliente);
            lblClientes.add(labelCliente);
        }
        pnlBanco.updateUI();
        flujoCliente = new FlujoCliente(listaClientes, filaBanco, transaccionesDisponibles,lblClientes);
        Thread flujoClientes = new Thread(flujoCliente);
        flujoClientes.start();

        for (int i = 0; i < 3; i++) {
            listaCajeros.add(new Thread(new HiloCajero(listaClientes,filaBanco,transaccionesDisponibles,i,this.lstAcciones,modeloLista,lblCajeros,lblClientes)));
        }
        
        listaCajeros.forEach(listaCajero -> {
            listaCajero.start();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstAcciones = new javax.swing.JList<>();
        pnlBanco = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(lstAcciones);

        javax.swing.GroupLayout pnlBancoLayout = new javax.swing.GroupLayout(pnlBanco);
        pnlBanco.setLayout(pnlBancoLayout);
        pnlBancoLayout.setHorizontalGroup(
            pnlBancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );
        pnlBancoLayout.setVerticalGroup(
            pnlBancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Banco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstAcciones;
    private javax.swing.JPanel pnlBanco;
    // End of variables declaration//GEN-END:variables
}
