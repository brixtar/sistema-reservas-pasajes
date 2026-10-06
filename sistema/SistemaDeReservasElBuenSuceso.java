/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import database.MySQL;
import funciones.CentrarJInternalFrame;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import static sistema.MainScreen.panelEscritorio;

/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class SistemaDeReservasElBuenSuceso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MySQL conexion = new MySQL();
        conexion.desconectar();
         
        LoginScreen jframe = new LoginScreen();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
    
}
