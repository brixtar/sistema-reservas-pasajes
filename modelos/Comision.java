/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import database.MySQL;
import javax.swing.JOptionPane;

/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class Comision {
    
    private String comision_id;
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
    private String empleado_dni;
    private String estado;

    public Comision() {
    }

    public Comision(String comision_id, String nombre, String calle, String localidad, String nombre2, String calle2, String localidad2, String observaciones, String monto, String forma, String fecha, String empleado_dni, String estado) {
        this.comision_id = comision_id;
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
        this.empleado_dni = empleado_dni;
        this.estado = estado;
    }

    public String getComision_id() {
        return comision_id;
    }

    public void setComision_id(String comision_id) {
        this.comision_id = comision_id;
    }

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

    public String getEmpleado_dni() {
        return empleado_dni;
    }

    public void setEmpleado_dni(String empleado_dni) {
        this.empleado_dni = empleado_dni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String buscarMaximoID(){
        MySQL conexion = new MySQL();
        Object[] data = conexion.SelectMax("comision", "comision_id");
        String max = data[0].toString();
        return max;
    }

    public Object[][] verComisiones(){
        MySQL conexion = new MySQL();
        Object[][]data;
        String colName []= {"comision_id","fecha","nombre","nombre2","forma","monto","empleado"};
        data= conexion.SelectTabla(colName, "vistacomision","SELECT * FROM vistacomision ORDER BY comision_id DESC");
        System.out.println(data.length==0? "Error al cargar comisiones" : "Comisiones cargadas correctamente");
        conexion.desconectar();
        return data; 
    }
    
    public void buscarComision(String comision_id){
        MySQL conexion = new MySQL();
        String colName []= {"comision_id","nombre","calle","localidad","nombre2","calle2","localidad2","observaciones","monto","forma","fecha"};
        Object[][] data = conexion.SelectTablaCondicion(colName, "comision","SELECT * FROM comision WHERE comision_id="+comision_id+"", "comision_id="+comision_id+"");
        this.comision_id= data[0][0].toString();
        this.nombre= data[0][1].toString();
        this.calle= data[0][2].toString();
        this.localidad= data[0][3].toString();
        this.nombre2= data[0][4].toString();
        this.calle2= data[0][5].toString();
        this.localidad2= data[0][6].toString();
        this.observaciones= data[0][7].toString();
        this.monto= data[0][8].toString();
        this.forma= data[0][9].toString();
        this.fecha= data[0][10].toString();
    }
    
    
    public void guardarComision(){
        MySQL conexion = new MySQL();
        String campos="nombre,calle,localidad,nombre2,calle2,localidad2,observaciones,monto,forma,fecha,empleado_dni,estado";
        String valores="'"+this.nombre+"','"+this.calle+"','"+this.localidad+"','"+this.nombre2+"','"+this.calle2+"','"+this.localidad2+"','"+this.observaciones+"',"+this.monto+",'"+this.forma+"','"+this.fecha+"','"+this.empleado_dni+"',"+this.estado;
        if(conexion.insertar("comision", campos, valores)){
                JOptionPane.showMessageDialog(null, "Comision guardada correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
            };
        conexion.desconectar();
        } 
}
