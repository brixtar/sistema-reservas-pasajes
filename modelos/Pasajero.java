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
public class Pasajero {
    
    private String pasajero_dni;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String fecha_alta;
    private String fecha_cumple;
    private int estado;

    public Pasajero() {
    }

    public Pasajero(String pasajero_dni, String nombre, String domicilio, String telefono, String fecha_alta, String fecha_cumple, int estado) {
        this.pasajero_dni = pasajero_dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.fecha_alta = fecha_alta;
        this.fecha_cumple = fecha_cumple;
        this.estado = estado;
    }

    public String getPasajero_dni() {
        return pasajero_dni;
    }

    public void setPasajero_dni(String pasajero_dni) {
        this.pasajero_dni = pasajero_dni;
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

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getFecha_cumple() {
        return fecha_cumple;
    }

    public void setFecha_cumple(String fecha_cumple) {
        this.fecha_cumple = fecha_cumple;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    
    
    public void guardar(){
        MySQL conexion = new MySQL();
        if(conexion.existe("pasajero", "pasajero_dni","'"+this.pasajero_dni+"'")){
              JOptionPane.showMessageDialog(null, "Ya existe un Pasajero con el mismo DNI","Mensaje de error",0);
          } 
        else {
            String campos="pasajero_dni,nombre,domicilio,telefono,fecha_alta,fecha_cumple,estado";
            String valores=this.pasajero_dni+",'"+this.nombre+"','"+this.domicilio+"','"+this.telefono+"','"+this.fecha_alta+"','"+this.fecha_cumple+"',"+this.estado+"";
            if(conexion.insertar("pasajero", campos, valores)){
                    JOptionPane.showMessageDialog(null, "Pasajero guardado correctamente","Mensaje de confirmación",1);
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
                };
            }
        conexion.desconectar();
    }
    
    public void modificar(String old_dni){
        MySQL conexion = new MySQL();
        if(conexion.existe("pasajero", "pasajero_dni",""+this.pasajero_dni+"")){
          if(this.pasajero_dni.equals(old_dni)){       
            if(conexion.update("UPDATE pasajero SET nombre='"+this.nombre+"',domicilio='"+this.domicilio+"',telefono='"+this.telefono+"',fecha_alta='"+this.fecha_alta+"',fecha_cumple='"+this.fecha_cumple+"' WHERE pasajero_dni="+old_dni+"")){
                    JOptionPane.showMessageDialog(null, "Pasajero modificado correctamente","Mensaje de confirmación",1);
                } 
             else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
            };   
          } else{
            JOptionPane.showMessageDialog(null, "Ya existe un Pasajero con el mismo DNI","Mensaje de error",0);
          }
        }  
        else {
            if(conexion.update("UPDATE pasajero SET pasajero_dni="+this.pasajero_dni+",nombre='"+this.nombre+"',domicilio='"+this.domicilio+"',telefono='"+this.telefono+"',fecha_alta='"+this.fecha_alta+"',fecha_cumple='"+this.fecha_cumple+"' WHERE pasajero_dni="+old_dni+"")){
                    JOptionPane.showMessageDialog(null, "Pasajero modificado correctamente","Mensaje de confirmación",1);
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
                };
            }
        conexion.desconectar();
    }
    
    public void eliminar(){
    MySQL conexion = new MySQL();
    if(conexion.update("UPDATE pasajero SET estado=0 WHERE pasajero_dni="+this.pasajero_dni+"")){
                JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar los datos","Mensaje de error",0);
            };
    conexion.desconectar();
}
    
    public Object[][] verPasajeros(){
    MySQL conexion = new MySQL();
    Object[][]data;
    String colName []= {"pasajero_dni","nombre","domicilio","telefono","fecha_alta","fecha_cumple","estado"};
    data= conexion.SelectTabla(colName, "vistaPasajeros","SELECT * FROM vistaPasajeros");
    System.out.println(data.length==0 ? "Error al cargar pasajeros" : "Pasajeros cargados correctamente");
    conexion.desconectar();
    return data; 
    }
    
    
}
