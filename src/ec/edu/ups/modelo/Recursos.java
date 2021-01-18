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
public class Recursos {

    private int numeroProcesosIngreso100;
    private int numeroProcesosIngreso50;
    private int numeroProcesosIngreso20;
    private int numeroProcesosRetiro100;
    private int numeroProcesosRetiro50;
    private int numeroProcesosRetiro20;
    private int total;
    
    public Recursos() {
    }

    public Recursos(int numeroProcesosIngreso100, int numeroProcesosIngreso50, int numeroProcesosIngreso20, int numeroProcesosRetiro100, int numeroProcesosRetiro50, int numeroProcesosRetiro20) {
        this.numeroProcesosIngreso100 = numeroProcesosIngreso100;
        this.numeroProcesosIngreso50 = numeroProcesosIngreso50;
        this.numeroProcesosIngreso20 = numeroProcesosIngreso20;
        this.numeroProcesosRetiro100 = numeroProcesosRetiro100;
        this.numeroProcesosRetiro50 = numeroProcesosRetiro50;
        this.numeroProcesosRetiro20 = numeroProcesosRetiro20;
        this.total = numeroProcesosIngreso100+numeroProcesosIngreso50+numeroProcesosIngreso20+numeroProcesosRetiro100+numeroProcesosRetiro50+numeroProcesosRetiro20;
    }

    public int getTotal() {
        return total;
    }
    
    public void setNumeroProcesosIngreso100(int numeroProcesosIngreso100) {
        this.numeroProcesosIngreso100 = numeroProcesosIngreso100;
    }

    public void setNumeroProcesosIngreso50(int numeroProcesosIngreso50) {
        this.numeroProcesosIngreso50 = numeroProcesosIngreso50;
    }

    public void setNumeroProcesosIngreso20(int numeroProcesosIngreso20) {
        this.numeroProcesosIngreso20 = numeroProcesosIngreso20;
    }

    public void setNumeroProcesosRetiro100(int numeroProcesosRetiro100) {
        this.numeroProcesosRetiro100 = numeroProcesosRetiro100;
    }

    public void setNumeroProcesosRetiro50(int numeroProcesosRetiro50) {
        this.numeroProcesosRetiro50 = numeroProcesosRetiro50;
    }

    public void setNumeroProcesosRetiro20(int numeroProcesosRetiro20) {
        this.numeroProcesosRetiro20 = numeroProcesosRetiro20;
    }

    public int getNumeroProcesosIngreso100() {
        return numeroProcesosIngreso100;
    }

    public int getNumeroProcesosIngreso50() {
        return numeroProcesosIngreso50;
    }

    public int getNumeroProcesosIngreso20() {
        return numeroProcesosIngreso20;
    }

    public int getNumeroProcesosRetiro100() {
        return numeroProcesosRetiro100;
    }

    public int getNumeroProcesosRetiro50() {
        return numeroProcesosRetiro50;
    }

    public int getNumeroProcesosRetiro20() {
        return numeroProcesosRetiro20;
    }

    
    //METODOS TRANSACCIONES
    public synchronized boolean ingresos100(Cliente cliente) {
        if (numeroProcesosIngreso100 != 0) {
            cliente.movimientoCuenta(100);
            numeroProcesosIngreso100--;
            total--;
            return false;
        } else {
            return true;
        }
    }

    public synchronized boolean ingresos50(Cliente cliente) {
        if (numeroProcesosIngreso50 != 0) {
            cliente.movimientoCuenta(50);
            numeroProcesosIngreso50--;
            total--;
            return false;
        } else {
            return true;
        }
    }
    
    public synchronized boolean ingresos20(Cliente cliente) {
        if (numeroProcesosIngreso20 != 0) {
            cliente.movimientoCuenta(20);
            numeroProcesosIngreso20--;
            total--;
            return false;
        } else {
            return true;
        }
    }

    public synchronized boolean retiro100(Cliente cliente) {
        if (numeroProcesosRetiro100 != 0) {
            cliente.movimientoCuenta(-100);
            numeroProcesosRetiro100--;
            total--;
            return false;
        } else {
            return true;
        }
    }

    public synchronized boolean retiro50(Cliente cliente) {
        if (numeroProcesosRetiro50 != 0) {
            cliente.movimientoCuenta(-50);
            numeroProcesosRetiro50--;
            total--;
            return false;
        } else {
            return true;
        }
    }

    public synchronized boolean retiro20(Cliente cliente) {
        if (numeroProcesosRetiro20 != 0) {
            cliente.movimientoCuenta(-20);
            numeroProcesosRetiro20--;
            total--;
            return false;
        } else {
            return true;
        }
    }

}
