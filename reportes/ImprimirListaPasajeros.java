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
public class ImprimirListaPasajeros {
    
    private String dni;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String cantidad;
    private String pagado;

    public ImprimirListaPasajeros(String dni, String nombre, String domicilio, String telefono, String cantidad, String pagado) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.pagado = pagado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
   
    
    
}
