/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

/**
 *
 * @author Paul Idrovo
 */
public class Cliente {
    private int id;
    private int saldoInicial;
    private int saldo;

    public Cliente(int id, int saldoInicial) {
        this.id = id;
        this.saldoInicial = saldoInicial;
    }

    public Cliente() {
    }
    
    public int getSaldo() {
        return saldo;
    }

    public int getId() {
        return id;
    }    
    

    public void movimientoCuenta(int monto){
        this.saldo = this.saldo + monto;
    }
    
   
   
}
