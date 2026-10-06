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
public class ValidacionesEmpleado {

    public ValidacionesEmpleado() {
    }
    
    public boolean validarDNI(String dni) {
        boolean valido=true;
        if(dni != null || !dni.equals("")) {
        char[] characters = dni.toCharArray();
            for (int i = 0; i < dni.length(); i++) {
                if (characters[i] < 48 || characters[i] > 57){
                    JOptionPane.showMessageDialog(null, "El DNI debe tener sólo números","ERROR",0);
                    valido=false;
                }               
            }
            if(dni.trim().length()<7||dni.trim().length()>8){
                JOptionPane.showMessageDialog(null, "El DNI debe tener 7 o 8 números","Mensaje de error",0);      
                valido=false;
                } 
        }
         return valido;
    }
    
    public boolean validarClave(String clave){
       boolean valido=false;
       if(clave.trim().length()>=1&&clave.trim().length()<=16){
           valido=true;
       } else { 
           JOptionPane.showMessageDialog(null,"La clave debe tener entre 1 y 16 caracteres","ERROR",0);
       }
       return valido; 
    }

    
    public boolean validarNombre(String nombre){
       boolean valido=false;
       if(nombre.trim().length()>=1&&nombre.trim().length()<=255){
           valido=true;
       } else { 
           JOptionPane.showMessageDialog(null,"El nombre debe tener entre 1 y 255 caracteres","ERROR",0);
       }
       return valido; 
    }
}
