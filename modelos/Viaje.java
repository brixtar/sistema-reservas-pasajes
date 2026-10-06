/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import database.MySQL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class Viaje {
    
    private String viaje_id;
    private String fecha_viaje;
    private String horario;
    private String vehiculo_id;
    private String choferes_id;
    private int estado;

    public Viaje() {
    }

    public Viaje(String viaje_id, String fecha_viaje, String horario, String vehiculo_id, String choferes_id, int estado) {
        this.viaje_id = viaje_id;
        this.fecha_viaje = fecha_viaje;
        this.horario = horario;
        this.vehiculo_id = vehiculo_id;
        this.choferes_id = choferes_id;
        this.estado = estado;
    }

    public String getViaje_id() {
        return viaje_id;
    }

    public void setViaje_id(String viaje_id) {
        this.viaje_id = viaje_id;
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

    public String getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(String vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public String getChoferes_id() {
        return choferes_id;
    }

    public void setChoferes_id(String choferes_id) {
        this.choferes_id = choferes_id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public void guardar(){
    MySQL conexion = new MySQL();
        String campos="viaje_id,fecha_viaje,horario,vehiculo_id,choferes_id,estado";
        String valores=this.viaje_id+",'"+this.fecha_viaje+"','"+this.horario+"',"+this.vehiculo_id+","+this.choferes_id+","+this.estado;
        if(conexion.insertar("viaje", campos, valores)){
                System.out.println("Viaje guardado correctamente");
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
            };
        conexion.desconectar();
    }
    
     public Object[][] verViajes(){
        MySQL conexion = new MySQL();
        Object[][]data;
        String colName []= {"viaje_id","fecha_viaje","horario","vehiculo","chofer"};
        data= conexion.SelectTabla(colName, "vistaviajes","SELECT * FROM vistaviajes ORDER BY viaje_id ASC");
        System.out.println(data.length==0? "Error al cargar viajes" : "Viajes cargados correctamente");
        conexion.desconectar();
        return data; 
    }
     
    public Object[][] verViajesEmpleado(){
        MySQL conexion = new MySQL();
        Object[][]data;
        Date fecha_actual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha_actual); 
        calendar.add(Calendar.DAY_OF_YEAR, -2);  
        SimpleDateFormat fechabd = new SimpleDateFormat("yyyy-MM-dd");
        String fecha= fechabd.format(calendar.getTime());
        
        String colName []= {"viaje_id","fecha_viaje","horario","vehiculo","chofer"};
        data= conexion.SelectTablaCondicion(colName, "vistaviajes","SELECT * FROM vistaviajes WHERE fecha_viaje>='"+fecha+"' ORDER BY viaje_id ASC","fecha_viaje>='"+fecha+"'");
        System.out.println(data.length==0? "Error al cargar viajes" : "Viajes cargados correctamente");
        conexion.desconectar();
        return data; 
    }
     
    public Object[][] verViajesdeHoy(String fecha_viaje){
        MySQL conexion = new MySQL();
        Object[][]data;
        String colName []= {"viaje_id","horario","vehiculo","chofer"};
        data= conexion.SelectTablaCondicion(colName, "vistaviajes","SELECT * FROM vistaviajes WHERE fecha_viaje='"+fecha_viaje+"'","fecha_viaje='"+fecha_viaje+"'");
        System.out.println(data.length==0? "Error al cargar viajes" : "Viajes cargados correctamente");
        conexion.desconectar();
        return data; 
    } 
     
     public int buscarultimoID(){
        MySQL conexion = new MySQL();
        Object[] max;
        max = conexion.SelectMax("viaje", "viaje_id");
        int ultimo= Integer.parseInt(max[0].toString());
        System.out.println("maximo:"+ultimo);
        conexion.desconectar();
        return ultimo;
    } 
    
    public boolean existeViaje( String fecha_viaje) {
        MySQL conexion = new MySQL();
        boolean aux = false;
        if(conexion.existe("viaje","fecha_viaje", "'"+fecha_viaje+"'")) {
         aux=true;
        }
        conexion.desconectar();
        return aux;
    }
    
    public void modificar(String viaje_id, String chofer_id,String vehiculo_id){
        MySQL conexion = new MySQL();
        if(conexion.update("UPDATE viaje SET choferes_id="+chofer_id+",vehiculo_id="+vehiculo_id+" WHERE viaje_id="+viaje_id+"")){
                JOptionPane.showMessageDialog(null, "Vehiculo modificado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
            };
        conexion.desconectar();    
        }
}
