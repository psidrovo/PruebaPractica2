/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Paul Idrovo
 */
public class HiloMovimientoClientes implements Runnable {

    private int idCliente;
    private int idCajero;
    private String tipoMovimiento;
    private List<JLabel> lblClientes;
    private List<JLabel> lblCajero;
    private List<Cliente> filaBanco;
    private List<Cliente> listaClientes;
    private int tiemppo;

    public void setFilaBanco(List<Cliente> filaBanco) {
        this.filaBanco = filaBanco;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public HiloMovimientoClientes(int idCliente, int idCajero, String tipoMovimiento, List<JLabel> elementosClientes, List<JLabel> lblCajero, int tiempo, List<Cliente> filaBanco) {
        this.idCliente = idCliente;
        this.idCajero = idCajero;
        this.tipoMovimiento = tipoMovimiento;
        this.lblClientes = elementosClientes;
        this.lblCajero = lblCajero;
        this.tiemppo = tiempo;
        this.filaBanco = filaBanco;
    }

    @Override
    public void run() {
        switch (tipoMovimiento) {
            case "irCaja":
                movimientoSuperior(filaBanco);
                movimientoLateral();
            case "salirCaja":
                movimientoLateralSalida();
        }
    }

    public synchronized void movimientoSuperior(List<Cliente> filaBanco) {
        synchronized (filaBanco) {
            int posicion = 100;
            for (int j = 0; j < filaBanco.size(); j++) {
                lblClientes.get(filaBanco.get(j).getId()).setBounds(30, posicion, 30, 30);
                posicion += 40;
            }
        }
    }

    private void movimientoLateral() {
        try {
            int espacio = lblCajero.get(idCajero).getX();
            espacio -= lblClientes.get(idCliente).getX();
            int tiempoPausa = tiemppo / 10;
            int salto = espacio / 10;
            espacio += salto;
            for (int i = 0; i < espacio; i += salto) {
                lblClientes.get(idCliente).setBounds(30 + i, 100, 30, 30);
                Thread.sleep(tiempoPausa);
            }
            lblClientes.get(idCliente).setBounds(lblCajero.get(idCajero).getX() + 30, 100, 30, 30);

        } catch (InterruptedException ex) {
            Logger.getLogger(HiloMovimientoClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void movimientoLateralSalida() {
        try {
            Thread.sleep(tiemppo);
            lblCajero.get(idCajero).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(51, 255, 0)));         
            lblClientes.get(idCliente).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(51, 255, 0)));
            int espacio = 500;
            int posicionX = lblClientes.get(idCliente).getX();
            int salto = espacio / 10;
            espacio += salto;
            for (int i = 0; i < espacio; i += salto) {
                lblClientes.get(idCliente).setBounds(posicionX + i, 100, 30, 30);
                Thread.sleep(100);
            }
            lblClientes.get(idCliente).setVisible(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloMovimientoClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
