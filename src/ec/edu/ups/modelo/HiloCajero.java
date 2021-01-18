/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.awt.Color;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Paul Idrovo
 */
public class HiloCajero extends Thread {

    private List<Cliente> listaClientes;
    private List<Cliente> filaBanco;
    private Recursos transaccionesDisponibles;
    private int numeroCajero;
    private JList listaAcciones;
    private DefaultListModel<String> modeloLista;
    private List<JLabel> lblCajeros;
    private List<JLabel> lblClientes;

    public HiloCajero(List<Cliente> listaClientes, List<Cliente> filaBanco, Recursos transaccionesDisponibles, int numeroCajero, JList listaAcciones, DefaultListModel<String> modeloLista, List<JLabel> lblCajeros, List<JLabel> lblClientes) {
        this.listaClientes = listaClientes;
        this.filaBanco = filaBanco;
        this.transaccionesDisponibles = transaccionesDisponibles;
        this.numeroCajero = numeroCajero;
        this.listaAcciones = listaAcciones;
        this.modeloLista = modeloLista;
        this.lblCajeros = lblCajeros;
        this.lblClientes = lblClientes;
    }

    @Override
    public void run() {
        while (transaccionesDisponibles.getNumeroProcesosIngreso100() != 0 || transaccionesDisponibles.getNumeroProcesosIngreso20() != 0 || transaccionesDisponibles.getNumeroProcesosIngreso50() != 0
                || transaccionesDisponibles.getNumeroProcesosRetiro100() != 0 || transaccionesDisponibles.getNumeroProcesosRetiro20() != 0 || transaccionesDisponibles.getNumeroProcesosRetiro50() != 0) {
            Cliente clienteAtencion = null;
            synchronized (filaBanco) {
                if (!filaBanco.isEmpty()) {
                    clienteAtencion = filaBanco.get(0);
                    filaBanco.remove(0);
                }
            }
            if (clienteAtencion != null) {
                //COLOR AMARILLO MOVIMIENTO
                lblClientes.get(clienteAtencion.getId()).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 204, 0)));
                tiempoFilaCajero(clienteAtencion.getId());
                //CAJERO ROJO OCUPADO
                lblClientes.get(clienteAtencion.getId()).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 0, 51)));
                lblCajeros.get(numeroCajero).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 0, 51)));
                boolean aleatorio = true;
                while (aleatorio) {
                    int tipoTransaccion = (int) (Math.random() * 10);
                    if (tipoTransaccion > 5) {
                        tipoTransaccion = 1;
                    } else {
                        tipoTransaccion = 0;
                    }
                    int montoTransaccion = (int) (Math.random() * 10);
                    if (montoTransaccion < 3) {
                        montoTransaccion = 0;
                    } else if (montoTransaccion > 3 && montoTransaccion < 7) {
                        montoTransaccion = 1;
                    } else {
                        montoTransaccion = 2;
                    }
                    if (tipoTransaccion == 0) {
                        //DEPOSITO
                        switch (montoTransaccion) {
                            case 0:
                                aleatorio = transaccionesDisponibles.ingresos100(clienteAtencion);
                                break;
                            case 1:
                                aleatorio = transaccionesDisponibles.ingresos50(clienteAtencion);
                                break;
                            case 2:
                                aleatorio = transaccionesDisponibles.ingresos20(clienteAtencion);
                                break;
                        }
                    } else {
                        //Retiro
                        switch (montoTransaccion) {
                            case 0:
                                aleatorio = transaccionesDisponibles.retiro100(clienteAtencion);
                                break;
                            case 1:
                                aleatorio = transaccionesDisponibles.retiro50(clienteAtencion);
                                break;
                            case 2:
                                aleatorio = transaccionesDisponibles.retiro20(clienteAtencion);
                                break;
                        }
                    }
                }
                synchronized (modeloLista) {
                    modeloLista.addElement("ATENDIENDO CLIENTE " + (clienteAtencion.getId() + 1) + " CAJERO " + (numeroCajero + 1));
                    listaAcciones.setModel(modeloLista);
                }
                System.out.println("ATENDIENDO CLIENTE " + (clienteAtencion.getId() + 1) + " CAJERO " + (numeroCajero + 1));
                tiempoAtencionCajero(clienteAtencion);                            
                listaClientes.add(clienteAtencion);
            }
        }
        System.out.println(transaccionesDisponibles.getNumeroProcesosIngreso100() + " - " + transaccionesDisponibles.getNumeroProcesosIngreso20() + " - " + transaccionesDisponibles.getNumeroProcesosIngreso50() + " - "
                + transaccionesDisponibles.getNumeroProcesosRetiro100() + " - " + transaccionesDisponibles.getNumeroProcesosRetiro20() + " - " + transaccionesDisponibles.getNumeroProcesosRetiro50());
    }

    private void tiempoAtencionCajero(Cliente clienteAtencion) {
        try {
            //CADA 15 - 20 segundos se realizara 1.5 - 2 segundos 
            int tiempo = (int) (Math.random() * 500) + 2500;
            Thread.sleep(tiempo);                 
            new Thread(new HiloMovimientoClientes(clienteAtencion.getId(), numeroCajero, "salirCaja", lblClientes, lblCajeros, tiempo, filaBanco)).start();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void tiempoFilaCajero(int idCliente) {
        try {
            //CADA 2 - 5 segundos se realizara 0.2 - 0.5 segundos 
            synchronized (modeloLista) {
                modeloLista.addElement("MOVIENDO CLIENTE " + (idCliente + 1) + " CAJERO " + (numeroCajero + 1));
                listaAcciones.setModel(modeloLista);
            }
            System.out.println("MOVIENDO CLIENTE " + (idCliente + 1) + " CAJERO " + (numeroCajero + 1));
            int tiempo = (int) (Math.random() * 300) + 1000;
            new Thread(new HiloMovimientoClientes(idCliente, numeroCajero, "irCaja", lblClientes, lblCajeros, tiempo, filaBanco)).start();
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
