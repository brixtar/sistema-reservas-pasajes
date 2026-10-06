/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import javax.swing.JOptionPane;

/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class ValidacionesVehiculos {

    public ValidacionesVehiculos() {
    }
    
    public boolean validarNombre(String nombre){
       boolean valido=false;
       if(nombre.trim().length()>=1&&nombre.trim().length()<=255){
           valido=true;
       } else { 
           JOptionPane.showMessageDialog(null,"La descripcion debe tener entre 1 y 255 caracteres","ERROR",0);
       }
       return valido; 
    }
}
