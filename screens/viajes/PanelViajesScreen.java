/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.viajes;

import funciones.CentrarJInternalFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelos.ListaPasajeros;
import modelos.Viaje;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import reportes.ImprimirListaPasajeros;
import screens.pasajeros.ListaPasajerosScreen;
import sistema.MainScreen;
import static sistema.MainScreen.panelEscritorio;

/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class PanelViajesScreen extends javax.swing.JInternalFrame {

    Date fecha_actual = new Date();
    SimpleDateFormat fechabd = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat fechajava = new SimpleDateFormat("dd-MM-yyyy");
    String ultimo_viaje;
    
    public PanelViajesScreen() {
        initComponents();
        buscarultimoViaje();
        if(MainScreen.empleado_dni.equals("32458755")||MainScreen.empleado_dni.equals("14613949")){
            cargar_tabla();
        } else {
            cargar_tabla_empleado();
        }
        obtenerDia();
         
    }
    
public static void cargar_tabla(){
        limpiarTabla(jtViajes);
        Viaje viaje = new Viaje();
        Object[][] datos= viaje.verViajes();
        String [] NombresColumnas = {"N°","FECHA","HORARIO","VEHÍCULO","CONDUCTOR",};
        if(datos.length==0){ 
            
        }
        else{
            DefaultTableModel modelo = new DefaultTableModel(datos,NombresColumnas);
            DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
            cent.setHorizontalAlignment(SwingConstants.CENTER);
        
            jtViajes.setModel(modelo);
            jtViajes.getColumnModel().getColumn(0).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(1).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(2).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(3).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(4).setCellRenderer(cent);
        
            for(int i=0; i<jtViajes.getModel().getRowCount() ;i++){
              String fecha[]= jtViajes.getModel().getValueAt(i, 1).toString().split("-");
              jtViajes.getModel().setValueAt(fecha[2]+"-"+fecha[1]+"-"+fecha[0], i, 1);
              }
           
            //ajustamos ancho de columna
            int[] anchos = {200,200,200,500,500};
            for( int i = 0; i < jtViajes.getColumnCount(); i++) {
            jtViajes.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
        }
    }

public static void cargar_tabla_empleado(){
        limpiarTabla(jtViajes);
        Viaje viaje = new Viaje();
        Object[][] datos= viaje.verViajesEmpleado();
        String [] NombresColumnas = {"N°","FECHA","HORARIO","VEHÍCULO","CONDUCTOR",};
        if(datos.length==0){ 
            
        }
        else{
            DefaultTableModel modelo = new DefaultTableModel(datos,NombresColumnas);
            DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
            cent.setHorizontalAlignment(SwingConstants.CENTER);
        
            jtViajes.setModel(modelo);
            jtViajes.getColumnModel().getColumn(0).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(1).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(2).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(3).setCellRenderer(cent);
            jtViajes.getColumnModel().getColumn(4).setCellRenderer(cent);
        
            for(int i=0; i<jtViajes.getModel().getRowCount() ;i++){
              String fecha[]= jtViajes.getModel().getValueAt(i, 1).toString().split("-");
              jtViajes.getModel().setValueAt(fecha[2]+"-"+fecha[1]+"-"+fecha[0], i, 1);
              }
           
            //ajustamos ancho de columna
            int[] anchos = {200,200,200,500,500};
            for( int i = 0; i < jtViajes.getColumnCount(); i++) {
            jtViajes.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
        }
    }
    
    
    public static void limpiarTabla(JTable tabla){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    public void buscar(String cadena){
        DefaultTableModel   dm = (DefaultTableModel) jtViajes.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtViajes.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(cadena,0,1,2,3,4));
    }
    
    public void obtenerDia(){
        Date fecha_actual = new Date();
        SimpleDateFormat fechabd = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat fechajava = new SimpleDateFormat("dd-MM-YYYY");
        String[] dias={"Domingo","Lunes","Martes", "Miércoles","Jueves","Viernes","Sábado"};
        int numeroDia=0;
        Calendar cal= Calendar.getInstance();
        cal.setTime(fecha_actual);
        numeroDia=cal.get(Calendar.DAY_OF_WEEK);
        System.out.println("SUMAR DIAS:"+fechabd.format(sumarDiasAFecha(fecha_actual, 3)));
        System.out.println("NUMERO DIA:"+numeroDia);
        if(dias[numeroDia - 1].toString().equals("Lunes")){
           Viaje viaje = new Viaje();
            if( !viaje.existeViaje(fechabd.format(sumarDiasAFecha(fecha_actual, 3)))){
                btnCrearViajes.setEnabled(true);
                btnModificar.setEnabled(false);
            } 
        } 
    }
    
    public void buscarultimoViaje(){
        Viaje viaje = new Viaje();
        try{
            String ultimo = Integer.toString(viaje.buscarultimoID()+1);
            ultimo_viaje=ultimo;
        }
        catch(Exception e){
            ultimo_viaje= "1";
        } 
    }
    
    public static Date sumarDiasAFecha(Date fecha, int dias){
      if (dias==0) return fecha;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); 
      calendar.add(Calendar.DAY_OF_YEAR, dias);  
      return calendar.getTime(); 
    }  
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jtViajes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;//Disallow the editing of any cell
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jtfBuscar = new javax.swing.JTextField();
        btnCrearViajes = new org.edisoncor.gui.button.ButtonRect();
        btnModificar = new org.edisoncor.gui.button.ButtonRect();
        btnModificar1 = new org.edisoncor.gui.button.ButtonRect();
        btnPasajeros = new org.edisoncor.gui.button.ButtonRect();
        btnImprimir = new org.edisoncor.gui.button.ButtonRect();

        setClosable(true);
        setTitle("PANEL VIAJES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono-jif.png"))); // NOI18N

        jtViajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No hay viajes creados"
            }
        ));
        jScrollPane2.setViewportView(jtViajes);

        jLabel1.setText("BUSCAR POR FECHA:");

        jtfBuscar.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jtfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarKeyReleased(evt);
            }
        });

        btnCrearViajes.setBackground(new java.awt.Color(0, 102, 102));
        btnCrearViajes.setText("Crear Viajes");
        btnCrearViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearViajesActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(153, 153, 153));
        btnModificar.setText("Modificar Viajes de Hoy");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnModificar1.setBackground(new java.awt.Color(153, 153, 153));
        btnModificar1.setText("Modificar Viajes de Mañana");
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });

        btnPasajeros.setBackground(new java.awt.Color(153, 153, 153));
        btnPasajeros.setText("Lista de Pasajeros");
        btnPasajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasajerosActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(153, 153, 153));
        btnImprimir.setText("Imprimir Ficha");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCrearViajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPasajeros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfBuscar)
                        .addGap(217, 217, 217))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrearViajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPasajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasajerosActionPerformed
        int fila = jtViajes.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(rootPane, "No se seleccionó ninguna fila");
        } else {
           ListaDePasajerosScreen jif = new ListaDePasajerosScreen();
           panelEscritorio.add(jif);
           CentrarJInternalFrame form = new CentrarJInternalFrame(jif);
           form.centrar1();
           jif.setVisible(true);
        }  
    }//GEN-LAST:event_btnPasajerosActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ListaViajesDeHoyScreen jif = new ListaViajesDeHoyScreen();
        panelEscritorio.add(jif);
        CentrarJInternalFrame form = new CentrarJInternalFrame(jif);
        form.centrar1();
        jif.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jtfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarKeyReleased
        buscar(jtfBuscar.getText().toUpperCase());
    }//GEN-LAST:event_jtfBuscarKeyReleased

    private void btnCrearViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearViajesActionPerformed
         //Guardamos viaje 1
        String viaje_id= ultimo_viaje;
        String fecha_viaje = fechabd.format(fecha_actual);
        Viaje viaje = new Viaje();
        //CREAMOS VIAJES DEL JUEVES AL VIERNES
        for(int i=0;i<2;i++){
                    //Creamos viaje de 6hs a La Rioja
                    fecha_viaje=fechabd.format(sumarDiasAFecha(fecha_actual,i+ 3));
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("06:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 7hs a La Rioja
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("07:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 11:30hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("11:30");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 13:00hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("13:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 14 hs a La Rioja
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("14:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 16 hs a La Rioja
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("16:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 20hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("20:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                     //Creamos viaje de 21:30hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("21:30");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
        }
        
                    //Creamos viaje 1 del dia sabado
                    fecha_viaje=fechabd.format(sumarDiasAFecha(fecha_actual, 5));
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("07:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    
                    //Creamos viaje 2 del dia sabado
                    fecha_viaje=fechabd.format(sumarDiasAFecha(fecha_actual, 5));
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("13:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    
                    
                    //Creamos viaje 3 del dia sabado
                    fecha_viaje=fechabd.format(sumarDiasAFecha(fecha_actual, 5));
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("16:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    
                      //Creamos viaje 4 del dia sabado
                    fecha_viaje=fechabd.format(sumarDiasAFecha(fecha_actual, 5));
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("20:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id=ultimo_viaje;
                    
             for(int i=0;i<3;i++){
                    //Creamos viaje de 6hs a La Rioja
                    fecha_viaje=fechabd.format(sumarDiasAFecha(fecha_actual,i+ 7));
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("06:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 7hs a La Rioja
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("07:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 11:30hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("11:30");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 13:00hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("13:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 14 hs a La Rioja
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("14:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 16 hs a La Rioja
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("16:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                    //Creamos viaje de 20hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("20:00");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
                     //Creamos viaje de 21:30hs a Chamical
                    viaje.setViaje_id(viaje_id);
                    viaje.setFecha_viaje(fecha_viaje);
                    viaje.setHorario("21:30");
                    viaje.setVehiculo_id("1");
                    viaje.setChoferes_id("1");
                    viaje.setEstado(1);
                    viaje.guardar();
                    buscarultimoViaje();
                    viaje_id= ultimo_viaje;
        }            
        JOptionPane.showMessageDialog(rootPane,"Viajes creados correctamente");
        btnCrearViajes.setEnabled(false);
        btnModificar.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnCrearViajesActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
            ListaViajesDeHoyScreen jif = new ListaViajesDeHoyScreen();
            panelEscritorio.add(jif);
            CentrarJInternalFrame form = new CentrarJInternalFrame(jif);
            form.centrar1();
            jif.setVisible(true);      
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        ListaViajesDeManScreen jif = new ListaViajesDeManScreen();
        panelEscritorio.add(jif);
        CentrarJInternalFrame form = new CentrarJInternalFrame(jif);
        form.centrar1();
        jif.setVisible(true);
    }//GEN-LAST:event_btnModificar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static org.edisoncor.gui.button.ButtonRect btnCrearViajes;
    private org.edisoncor.gui.button.ButtonRect btnImprimir;
    public static org.edisoncor.gui.button.ButtonRect btnModificar;
    private org.edisoncor.gui.button.ButtonRect btnModificar1;
    private org.edisoncor.gui.button.ButtonRect btnPasajeros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jtViajes;
    private javax.swing.JTextField jtfBuscar;
    // End of variables declaration//GEN-END:variables
}
