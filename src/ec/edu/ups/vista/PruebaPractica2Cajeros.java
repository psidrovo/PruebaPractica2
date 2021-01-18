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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul Idrovo
 */
public class PruebaPractica2Cajeros {

    private static Recursos transaccionesDisponibles;
    private static FlujoCliente flujoCliente;
    private static List<Cliente> listaClientes;
    private static List<Cliente> filaBanco;
    private static List<Thread> listaCajeros;

    public static void Inicio() {

        transaccionesDisponibles = new Recursos(40, 20, 60, 40, 20, 60);
        listaClientes = new ArrayList<>();
        filaBanco = new ArrayList<>();
        listaCajeros = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                filaBanco.add(new Cliente(i, 100));
            } else {
                listaClientes.add(new Cliente(i, 100));
            }
        }
        //flujoCliente = new FlujoCliente(listaClientes, filaBanco, transaccionesDisponibles);
        Thread flujoClientes = new Thread(flujoCliente);
        flujoClientes.start();

        for (int i = 0; i < 3; i++) {
            ///listaCajeros.add(new Thread(new HiloCajero(transaccionesDisponibles, listaClientes, filaBanco, i)));
        }

        for (Thread listaCajero : listaCajeros) {
            listaCajero.start();
        }

    }

}
