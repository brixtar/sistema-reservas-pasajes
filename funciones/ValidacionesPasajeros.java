/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class ValidacionesPasajeros {

    public ValidacionesPasajeros() {
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
    
     public boolean validarDomicilio(String domicilio){
       boolean valido=false;
       if(domicilio.trim().length()>=1&&domicilio.trim().length()<=255){
           valido=true;
       } else { 
           JOptionPane.showMessageDialog(null,"El domicilio debe tener entre 1 y 255 caracteres","ERROR",0);
       }
       return valido; 
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
    
    public boolean validarTelefono(String cadena){
       boolean valido=true;
        if(cadena != null || !cadena.equals("")) {
        char[] characters = cadena.toCharArray();
            for (int i = 0; i < cadena.length(); i++) {
                if (characters[i] < 48 || characters[i] > 57){
                    JOptionPane.showMessageDialog(null, "El Telefono debe tener sólo números","ERROR",0);
                    valido=false;
                }               
            }
        }
         return valido;
    }
}
