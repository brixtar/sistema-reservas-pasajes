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
public class Empleado {
    
    private String empleado_dni;
    private String nombre;
    private String clave;
    private int estado;

    public Empleado() {
    }

    public Empleado(String empleado_dni, String nombre, String clave, int estado) {
        this.empleado_dni = empleado_dni;
        this.nombre = nombre;
        this.clave = clave;
        this.estado = estado;
    }

    public String getEmpleado_dni() {
        return empleado_dni;
    }

    public void setEmpleado_dni(String empleado_dni) {
        this.empleado_dni = empleado_dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean login (String dni, String clave){
        MySQL conexion= new MySQL();
        if(conexion.existe2variables("empleado","empleado_dni", dni,"clave","'"+clave+"'")){
            conexion.desconectar();
            return true;
        } else {
            conexion.desconectar();
            return false;
        }
    }
    
    public void guardar(){
        MySQL conexion = new MySQL();
        if(conexion.existe("empleado", "empleado_dni","'"+this.empleado_dni+"'")){
              JOptionPane.showMessageDialog(null, "Ya existe un Empleado con el mismo DNI","Mensaje de error",0);
          } 
        else {
            String campos="empleado_dni,nombre,clave,estado";
            String valores=this.empleado_dni+",'"+this.nombre+"','"+this.clave+"',"+this.estado+"";
            if(conexion.insertar("empleado", campos, valores)){
                    JOptionPane.showMessageDialog(null, "Empleado guardado correctamente","Mensaje de confirmación",1);
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
                };
            }
     conexion.desconectar();
    }
    
    public void modificar(String old_dni){
        MySQL conexion = new MySQL();
        if(conexion.existe("empleado", "empleado_dni",""+this.empleado_dni+"")){
          if(this.empleado_dni.equals(old_dni)){       
            if(conexion.update("UPDATE empleado SET nombre='"+this.nombre+"',clave='"+this.clave+"' WHERE empleado_dni="+old_dni+"")){
                    JOptionPane.showMessageDialog(null, "Empleado modificado correctamente","Mensaje de confirmación",1);
                } 
             else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
            };   
          } else{
            JOptionPane.showMessageDialog(null, "Ya existe un Empleado con el mismo DNI","Mensaje de error",0);
          }
          
        }  
        else {
            if(conexion.update("UPDATE empleado SET empleado_dni="+this.empleado_dni+",nombre='"+this.nombre+"',clave='"+this.clave+"' WHERE empleado_dni="+old_dni+"")){
                    JOptionPane.showMessageDialog(null, "Empleado modificado correctamente","Mensaje de confirmación",1);
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
                };
            }
        conexion.desconectar();
    }
    
    public void eliminar(){
    MySQL conexion = new MySQL();
    if(conexion.update("UPDATE empleado SET estado=0 WHERE empleado_dni="+this.empleado_dni+"")){
                JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar los datos","Mensaje de error",0);
            };
    conexion.desconectar();
}
    
    public Object[][] verEmpleados(){
    MySQL conexion = new MySQL();
    Object[][]data;
    String colName []= {"empleado_dni","nombre","clave","estado"};
    data= conexion.SelectTabla(colName, "vistaEmpleados","SELECT * FROM vistaEmpleados");
    System.out.println(data[0][0]==null? "Error al cargar empleados" : "Empleados cargados correctamente");
    conexion.desconectar();
    return data; 
    }
    
    public void buscarEmpleado(String dni){
        MySQL conexion = new MySQL();
        this.empleado_dni=dni;
        this.nombre=conexion.SelectDato("empleado","empleado_dni",dni,"nombre");
        this.clave=conexion.SelectDato("empleado","empleado_dni",dni,"clave");
        conexion.desconectar();
    }
    
    public void buscarEmpleadoDNI(String nombre){
        MySQL conexion = new MySQL();
        this.nombre=nombre;
        this.empleado_dni=conexion.SelectDato("empleado","nombre","'"+nombre+"'","empleado_dni");
        this.clave=conexion.SelectDato("empleado","nombre","'"+nombre+"'","clave");
        conexion.desconectar();
    }
    
    
    
}
