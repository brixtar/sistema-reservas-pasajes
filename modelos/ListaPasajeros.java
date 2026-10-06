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
public class ListaPasajeros {
    
    private String listapasajero_id;
    private String viaje_id;
    private String pasajero_dni;
    private String observaciones;
    private String empleado_dni;
    private String cantidad;
    private String pagado;
    private String tiempo;
    private int estado;

    public ListaPasajeros() {
    }

    public ListaPasajeros(String listapasajero_id, String viaje_id, String pasajero_dni, String observaciones, String empleado_dni, String cantidad, String pagado, String tiempo, int estado) {
        this.listapasajero_id = listapasajero_id;
        this.viaje_id = viaje_id;
        this.pasajero_dni = pasajero_dni;
        this.observaciones = observaciones;
        this.empleado_dni = empleado_dni;
        this.cantidad = cantidad;
        this.pagado = pagado;
        this.tiempo = tiempo;
        this.estado = estado;
    }

    

    public String getListapasajero_id() {
        return listapasajero_id;
    }

    public void setListapasajero_id(String listapasajero_id) {
        this.listapasajero_id = listapasajero_id;
    }

    public String getViaje_id() {
        return viaje_id;
    }

    public void setViaje_id(String viaje_id) {
        this.viaje_id = viaje_id;
    }

    public String getPasajero_dni() {
        return pasajero_dni;
    }

    public void setPasajero_dni(String pasajero_dni) {
        this.pasajero_dni = pasajero_dni;
    }

    public String getEmpleado_dni() {
        return empleado_dni;
    }

    public void setEmpleado_dni(String empleado_dni) {
        this.empleado_dni = empleado_dni;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
    

    public void guardar(){
        MySQL conexion = new MySQL();
        if(conexion.existe2variables("vistalistapasajeros", "pasajero_dni","'"+this.pasajero_dni+"'","viaje_id",this.viaje_id)){
              JOptionPane.showMessageDialog(null, "El Pasajero ya tiene pasajes reservados","Mensaje de error",0);
          } 
        else {
            String campos="viaje_id,pasajero_dni,observaciones,empleado_dni,cantidad,pagado,tiempo,estado";
            String valores=this.viaje_id+",'"+this.pasajero_dni+"','"+this.observaciones+"','"+this.empleado_dni+"',"+this.cantidad+",'"+this.pagado+"','"+this.tiempo+"',"+this.estado+"";
            if(conexion.insertar("listapasajeros", campos, valores)){
                    JOptionPane.showMessageDialog(null, "La Reserva se guardó correctamente","Mensaje de confirmación",1);
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
                };
            }
     conexion.desconectar();
    }
    
    
    public Object[][] verListaPasajeros(String viaje_id){
        MySQL conexion = new MySQL();
        Object[][]data;
        String colName []= {"listapasajero_id","viaje_id","pasajero_dni","pasajero","empleado","domicilio","cantidad","pagado"};
        data= conexion.SelectTablaCondicion(colName, "vistaListapasajeros","SELECT * FROM vistaListapasajeros WHERE viaje_id="+viaje_id+"","viaje_id="+viaje_id+"");
        conexion.desconectar();
        return data; 
    }
    
    public void modificarListaPasajero(String listapasajero_id, String observaciones,String cantidad, String pagado){
        MySQL conexion = new MySQL();
         if(conexion.update("UPDATE listapasajeros SET observaciones='"+observaciones+"',cantidad="+cantidad+",pagado='"+pagado+"' WHERE listapasajero_id="+listapasajero_id+"")){
                    JOptionPane.showMessageDialog(null, "La Reserva se guardó correctamente","Mensaje de confirmación",1);
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
                }
        conexion.desconectar();
    }
    
  public void eliminar(String listapasajero_id){
    MySQL conexion = new MySQL();
    if(conexion.update("UPDATE listapasajeros SET estado=0 WHERE listapasajero_id="+listapasajero_id+"")){
                JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar los datos","Mensaje de error",0);
            };
    conexion.desconectar();
    }
  
      public Object[][] imprimirListaPasajeros(String viaje_id){
        MySQL conexion = new MySQL();
        Object[][]data;
        String colName []= {"listapasajero_id","viaje_id","pasajero_dni","nombre","domicilio","telefono","cantidad","pagado"};
        data= conexion.SelectTablaCondicion(colName, "vistaimprimirlistapasajero","SELECT * FROM vistaimprimirlistapasajero WHERE viaje_id="+viaje_id+"","viaje_id="+viaje_id+"");
        conexion.desconectar();
        return data; 
    }
}
