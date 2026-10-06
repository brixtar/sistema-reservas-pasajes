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
public class Vehiculo {
    
    private String vehiculo_id;
    private String descripcion;
    private int asientos;
    private int estado;

    public Vehiculo() {
    }

    public Vehiculo(String vehiculo_id, String descripcion, int asientos, int estado) {
        this.vehiculo_id = vehiculo_id;
        this.descripcion = descripcion;
        this.asientos = asientos;
        this.estado = estado;
    }

    public String getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(String vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
public int buscarultimoID(){
    MySQL conexion = new MySQL();
    Object[] max;
    max = conexion.SelectMax("vehiculo", "vehiculo_id");
    int ultimo= Integer.parseInt(max[0].toString());
    System.out.println("maximo:"+ultimo);
    conexion.desconectar();
    return ultimo;
}

public void guardar(){
    MySQL conexion = new MySQL();
    if(conexion.existe("vehiculo", "descripcion","'"+this.descripcion+"'")){
          JOptionPane.showMessageDialog(null, "Ya existe un Vehiculo con la misma descripción","Mensaje de error",0);
      } 
    else {
        String campos="descripcion,asientos,estado";
        String valores="'"+this.descripcion+"',"+this.asientos+","+this.estado;
        if(conexion.insertar("vehiculo", campos, valores)){
                JOptionPane.showMessageDialog(null, "Vehiculo guardado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
            };
        } 
     conexion.desconectar();
}

public void modificar(){
    MySQL conexion = new MySQL();
    if(conexion.existe("vehiculo", "descripcion","'"+this.descripcion+"'")){
          if(conexion.SelectDato("vehiculo","descripcion","'"+this.descripcion+"'","vehiculo_id").equals(this.vehiculo_id)){       
            if(conexion.update("UPDATE vehiculo SET descripcion='"+this.descripcion+"',asientos="+this.asientos+" WHERE vehiculo_id="+this.vehiculo_id+"")){
                JOptionPane.showMessageDialog(null, "Vehiculo modificado correctamente","Mensaje de confirmación",1);
            } 
             else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
            };   
          } else{
            JOptionPane.showMessageDialog(null, "Ya existe un Vehiculo con la misma descripción","Mensaje de error",0);
          }
          
      } 
    else {
        if(conexion.update("UPDATE vehiculo SET descripcion='"+this.descripcion+"',asientos="+this.asientos+" WHERE vehiculo_id="+this.vehiculo_id+"")){
                JOptionPane.showMessageDialog(null, "Vehiculo modificado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
            };
        }
    conexion.desconectar();
}

    public void eliminar(){
    MySQL conexion = new MySQL();
    if(conexion.update("UPDATE vehiculo SET estado=0 WHERE vehiculo_id="+this.vehiculo_id+"")){
                JOptionPane.showMessageDialog(null, "Vehiculo eliminado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar los datos","Mensaje de error",0);
            };
    conexion.desconectar();
    }

    public Object[][] verVehiculos(){
        MySQL conexion = new MySQL();
        Object[][]data;
        String colName []= {"vehiculo_id","descripcion","asientos","estado"};
        data= conexion.SelectTabla(colName, "vistaVehiculos","SELECT * FROM vistavehiculos");
        System.out.println(data.length==0 ? "Error al cargar vehiculos" : "Vehiculos cargados correctamente");
        conexion.desconectar();
        return data; 
    }
    
    public Object[] cargarCombo(){
        MySQL conexion = new MySQL();
        Object data[];
        data = conexion.SelectCol("vistavehiculos","descripcion", "descripcion");
        conexion.desconectar();
        return data;
    }
    
    public String buscarVehiculo_ID(String descripcion){
        MySQL conexion = new MySQL();
        String vehiculo_id="";
        vehiculo_id= conexion.SelectDato("vistavehiculos","descripcion","'"+descripcion+"'","vehiculo_id");
        conexion.desconectar();
        return vehiculo_id;
    }
    
     public int buscarAsientos(String vehiculo_nombre){
        MySQL conexion = new MySQL();
        String dato= conexion.SelectDato("vistavehiculos","descripcion","'"+vehiculo_nombre+"'","asientos");
        conexion.desconectar();
        return Integer.parseInt(dato);
    }
}
