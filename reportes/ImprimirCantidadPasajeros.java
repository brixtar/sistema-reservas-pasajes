/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import database.MySQL;

public class ImprimirCantidadPasajeros {
    private String fecha_viaje;
    private String horario;
    private String pasajero_dni;
    private String cantidad;

    public ImprimirCantidadPasajeros(String fecha_viaje, String horario, String pasajero_dni, String cantidad) {
        this.fecha_viaje = fecha_viaje;
        this.horario = horario;
        this.pasajero_dni = pasajero_dni;
        this.cantidad = cantidad;
    }

    public ImprimirCantidadPasajeros() {
    }
    
    
    public String getFecha_viaje() {
        return fecha_viaje;
    }

    public void setFecha_viaje(String fecha_viaje) {
        this.fecha_viaje = fecha_viaje;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPasajero_dni() {
        return pasajero_dni;
    }

    public void setPasajero_dni(String pasajero_dni) {
        this.pasajero_dni = pasajero_dni;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    
    
    public String sumarCantidadPasajeros(String fecha_inicial,String fecha_final){
        MySQL conexion = new MySQL();
        String data=conexion.Sumar("vistaimprimircantidadpasajeros","cantidad","fecha_viaje BETWEEN '"+fecha_inicial+"' AND '"+fecha_final+"'");
        conexion.desconectar();
        return data;
    }
    
}
