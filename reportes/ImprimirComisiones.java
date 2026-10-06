/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import database.MySQL;

public class ImprimirComisiones {   
    
    private String nombre;
    private String calle;
    private String localidad;
    private String nombre2;
    private String calle2;
    private String localidad2;
    private String observaciones;
    private String monto;
    private String forma;
    private String fecha;
    private String empleado;

    public ImprimirComisiones(String nombre, String calle, String localidad, String nombre2, String calle2, String localidad2, String observaciones, String monto, String forma, String fecha, String empleado) {
        this.nombre = nombre;
        this.calle = calle;
        this.localidad = localidad;
        this.nombre2 = nombre2;
        this.calle2 = calle2;
        this.localidad2 = localidad2;
        this.observaciones = observaciones;
        this.monto = monto;
        this.forma = forma;
        this.fecha = fecha;
        this.empleado = empleado;
    }

    public ImprimirComisiones(){}; 
       

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getLocalidad2() {
        return localidad2;
    }

    public void setLocalidad2(String localidad2) {
        this.localidad2 = localidad2;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
    
    public String sumarComisionesGenerales(String fecha_inicial,String fecha_final){
        MySQL conexion = new MySQL();
        String data=conexion.Sumar("vistacomision","monto","fecha BETWEEN '"+fecha_inicial+"' AND '"+fecha_final+"'");
        conexion.desconectar();
        return data;
    }

    
    
    public String sumarComisionesDiarias(String fecha_inicial,String fecha_final){
        MySQL conexion = new MySQL();
        String data=conexion.Sumar("vistacomision","monto","fecha BETWEEN '"+fecha_inicial+"' AND '"+fecha_final+"' AND forma= 'CTDO'");
        conexion.desconectar();
        return data;
    }
    
    public String sumarListaComisiones(String fecha_inicial, String fecha_final) {
        MySQL conexion = new MySQL();
        String data = conexion.Sumar("vistacomision", "monto", "fecha BETWEEN '" + fecha_inicial + "' AND '" + fecha_final + "' AND forma= 'CTDO'");
        conexion.desconectar();
        return data;
    }
    
}
