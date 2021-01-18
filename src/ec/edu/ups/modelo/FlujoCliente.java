/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Paul Idrovo
 */
public class FlujoCliente extends Thread {

    private List<Cliente> listaClientes;
    private List<Cliente> filaBanco;
    private Recursos transaccionesDisponibles;
    private List<JLabel> lblClientes;

    public FlujoCliente(List<Cliente> listaClientes, List<Cliente> filaBanco, Recursos transaccionesDisponibles, List<JLabel> lblClientes) {
        this.listaClientes = listaClientes;
        this.filaBanco = filaBanco;
        this.transaccionesDisponibles = transaccionesDisponibles;
        this.lblClientes = lblClientes;
    }

    @Override
    public void run() {
        while (transaccionesDisponibles.getTotal() > filaBanco.size()) {
            esperarXsegundos();
            filaBanco.add(listaClientes.get(0));
            int posicionY = 0;
            if (listaClientes.get(0).getId() == 0) {
                posicionY = lblClientes.get(99).getY();
            } else {
                posicionY = lblClientes.get(listaClientes.get(0).getId() - 1).getY();
            }

            posicionY += 40;
            lblClientes.get(listaClientes.get(0).getId()).setBounds(30, posicionY, 30, 30);
            lblClientes.get(listaClientes.get(0).getId()).setVisible(true);
            listaClientes.remove(0);
            System.out.println("AGREGANDO NUEVO CLIENTE A LA FILA TOTAL: " + filaBanco.size() + " OTRA LISTA: " + listaClientes.size());
        }
    }

    private void esperarXsegundos() {
        try {
            //CADA 30 - 50 segundos se realizara 0.5 - 1.5segundos 
            int tiempo = (int) (Math.random() * 500) + 1000;
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
