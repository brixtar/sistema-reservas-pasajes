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
public class Chofer {
    
    private String choferes_id;
    private String nombre;
    private int estado;

    public Chofer() {
    }

    public Chofer(String choferes_id, String nombre, int estado) {
        this.choferes_id = choferes_id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getChoferes_id() {
        return choferes_id;
    }

    public void setChoferes_id(String chofer_id) {
        this.choferes_id = chofer_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        max = conexion.SelectMax("choferes", "choferes_id");
        int ultimo= Integer.parseInt(max[0].toString());
        System.out.println("maximo:"+ultimo);
        conexion.desconectar();
        return ultimo;
    }
    
    
    public void guardar(){
    MySQL conexion = new MySQL();
    if(conexion.existe("choferes", "nombre","'"+this.nombre+"'")){
          JOptionPane.showMessageDialog(null, "Ya existe un Conductor con el mismo nombre","Mensaje de error",0);
      } 
    else {
        String campos="nombre,estado";
        String valores="'"+this.nombre+"',"+this.estado;
        if(conexion.insertar("choferes", campos, valores)){
                JOptionPane.showMessageDialog(null, "Conductor guardado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar guardar los datos","Mensaje de error",0);
            };
        } 
      conexion.desconectar();
    }
    
    public void modificar(){
    MySQL conexion = new MySQL();
    if(conexion.existe("choferes", "nombre","'"+this.nombre+"'")){
          if(conexion.SelectDato("choferes","nombre","'"+this.nombre+"'","choferes_id").equals(this.choferes_id)){       
            if(conexion.update("UPDATE choferes SET nombre='"+this.nombre+"' WHERE choferes_id="+this.choferes_id+"")){
                JOptionPane.showMessageDialog(null, "Chofer modificado correctamente","Mensaje de confirmación",1);
            } 
             else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
            };   
          } else{
            JOptionPane.showMessageDialog(null, "Ya existe un Conductor con el mismo nombre","Mensaje de error",0);
          }
          
      } 
    else {
        if(conexion.update("UPDATE choferes SET nombre='"+this.nombre+"' WHERE choferes_id="+this.choferes_id+"")){
                JOptionPane.showMessageDialog(null, "Conductor modificado correctamente","Mensaje de confirmación",1);
            } 
        else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar los datos","Mensaje de error",0);
            };
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        MySQL conexion = new MySQL();
        if(conexion.update("UPDATE choferes SET estado=0 WHERE choferes_id="+this.choferes_id+"")){
                    JOptionPane.showMessageDialog(null, "Conductor eliminado correctamente","Mensaje de confirmación",1);
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Error al intentar eliminar los datos","Mensaje de error",0);
                };
        conexion.desconectar();
    }
    
    public Object[][] verChoferes(){
        MySQL conexion = new MySQL();
        Object[][]data;
        String colName []= {"choferes_id","nombre"};
        data= conexion.SelectTabla(colName, "vistaChoferes","SELECT * FROM vistachoferes");
        System.out.println(data.length==0 ? "Error al cargar choferes" : "Choferes cargados correctamente");
        conexion.desconectar();
        return data; 
    }
    
    public Object[] cargarCombo(){
        MySQL conexion = new MySQL();
        Object data[];
        data = conexion.SelectCol("vistachoferes","nombre", "nombre");
        conexion.desconectar();
        return data;
    }
    
      public String buscarChofer_ID(String nombre){
        MySQL conexion = new MySQL();
        String choferes_id="";
        choferes_id= conexion.SelectDato("vistachoferes","nombre","'"+nombre+"'","choferes_id");
        conexion.desconectar();
        return choferes_id;
    }
    
    
}
