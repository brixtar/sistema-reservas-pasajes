/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class ImprimirListaComisiones {
    
    private String numero;
    private String nombre;
    private String nombre2;
    private String monto;
    private String forma;
    private String fecha;
    private String empleado;

    public ImprimirListaComisiones(String numero, String nombre, String nombre2, String monto, String forma, String fecha, String empleado) {
        this.numero = numero;
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.monto = monto;
        this.forma = forma;
        this.fecha = fecha;
        this.empleado = empleado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    
    
    
    

  
    
   
    
    
}
