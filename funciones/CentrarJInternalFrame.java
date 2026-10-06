/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.awt.Dimension;
import javax.swing.JInternalFrame;
import static sistema.MainScreen.panelEscritorio;


/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class CentrarJInternalFrame {
    JInternalFrame frame;

    public CentrarJInternalFrame(JInternalFrame frame) {
        this.frame=frame;
        }
    
    public void centrar(){
        Dimension desktopSize = panelEscritorio.getSize();
        Dimension FrameSize = this.frame.getSize();
        this.frame.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        this.frame.show();
        }
    
     public void centrar1(){
        Dimension desktopSize = panelEscritorio.getSize();
        Dimension FrameSize = this.frame.getSize();
        this.frame.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/6);
        this.frame.show();
        }
}
       

