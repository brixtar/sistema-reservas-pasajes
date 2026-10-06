/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.viajes;

import funciones.CentrarJInternalFrame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelos.Empleado;
import modelos.ListaPasajeros;
import modelos.Pasajero;
import modelos.Vehiculo;
import screens.pasajeros.ListaPasajerosScreen;
import sistema.MainScreen;
import static sistema.MainScreen.panelEscritorio;


/**
 *
 * @author Lic. Alejandro Gabriel Lartigue
 */
public class ListaDePasajerosScreen extends javax.swing.JInternalFrame {

    public static int asientos,asientos_disponibles;
    Date fecha = new Date();
    public static SimpleDateFormat fechatimebd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat fechatimejava = new SimpleDateFormat("HH:mm dd-MM-yyyy");
    
    
    public ListaDePasajerosScreen() {
        initComponents();
        
        int fila = PanelViajesScreen.jtViajes.getSelectedRow();
        jtfNumeroViaje.setText(PanelViajesScreen.jtViajes.getValueAt(fila, 0).toString());
        jtfFecha.setText(PanelViajesScreen.jtViajes.getValueAt(fila, 1).toString());
        jtfHorario.setText(PanelViajesScreen.jtViajes.getValueAt(fila, 2).toString());
        String vehiculo=PanelViajesScreen.jtViajes.getValueAt(fila, 3).toString();
        cargar_tabla();
        cargar_asientos(vehiculo);
        calcular_asientos_disponibles();
        cargar_tabla_pasajeros();
    }
    
    public void cargar_asientos(String vehiculo_nombre){
        Vehiculo vehiculo = new Vehiculo();
        asientos=vehiculo.buscarAsientos(vehiculo_nombre);
    }
    
    public static void calcular_asientos_disponibles(){
        int asientos_vendidos=0;
        for(int i=0; i<jtListapasajero.getRowCount();i++){
            asientos_vendidos= asientos_vendidos+Integer.parseInt(jtListapasajero.getValueAt(i, 6).toString());
        }
        String asientos_disponibles=Integer.toString(asientos-asientos_vendidos);
        jtfAsientos.setText(asientos_disponibles);
    }
    
    
    public void cargar_tabla_pasajeros(){
        limpiarTabla(jtPasajeros);
        Pasajero pasajero = new Pasajero();
        Object [][] datos= pasajero.verPasajeros();
        String [] NombresColumnas = {"DNI","NOMBRE","DOMICILIO"};
        DefaultTableModel modelo = new DefaultTableModel(datos,NombresColumnas);
        DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
        cent.setHorizontalAlignment(SwingConstants.CENTER);
        
        jtPasajeros.setModel(modelo);
        jtPasajeros.getColumnModel().getColumn(0).setCellRenderer(cent);
        jtPasajeros.getColumnModel().getColumn(1).setCellRenderer(cent);
        
        //ajustamos ancho de columna
        int[] anchos = {210,1000,200};
        for( int i = 0; i < jtPasajeros.getColumnCount(); i++) {
        jtPasajeros.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
        
        jtListapasajero.getColumnModel().getColumn(2).setMaxWidth(0);
        jtListapasajero.getColumnModel().getColumn(2).setMinWidth(0);
        jtListapasajero.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        jtListapasajero.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);     
    }
    
    public static void cargar_tabla(){
        limpiarTabla(jtListapasajero);
        ListaPasajeros lista = new ListaPasajeros();
        Object[][] datos= lista.verListaPasajeros(jtfNumeroViaje.getText());
        String [] NombresColumnas = {"N°","VIAJE","DNI","PASAJERO","EMPLEADO","DOMICILIO","CANT.","PAGADO"};
        DefaultTableModel modelo = new DefaultTableModel(datos,NombresColumnas);
        DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
        cent.setHorizontalAlignment(SwingConstants.CENTER);
        
        jtListapasajero.setModel(modelo);
        jtListapasajero.getColumnModel().getColumn(0).setCellRenderer(cent);
        
        
        jtListapasajero.getColumnModel().getColumn(1).setCellRenderer(cent);
        jtListapasajero.getColumnModel().getColumn(2).setCellRenderer(cent);
        jtListapasajero.getColumnModel().getColumn(3).setCellRenderer(cent);
        jtListapasajero.getColumnModel().getColumn(4).setCellRenderer(cent);
        jtListapasajero.getColumnModel().getColumn(5).setCellRenderer(cent);
        jtListapasajero.getColumnModel().getColumn(6).setCellRenderer(cent);
        jtListapasajero.getColumnModel().getColumn(7).setCellRenderer(cent);
            
        //ajustamos ancho de columna
        int[] anchos = {200,200,200,500,500,750,200,200};
        for( int i = 0; i < jtListapasajero.getColumnCount(); i++) {
        jtListapasajero.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
        
        jtListapasajero.getColumnModel().getColumn(0).setMaxWidth(0);
        jtListapasajero.getColumnModel().getColumn(0).setMinWidth(0);
        jtListapasajero.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jtListapasajero.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        jtListapasajero.getColumnModel().getColumn(1).setMaxWidth(0);
        jtListapasajero.getColumnModel().getColumn(1).setMinWidth(0);
        jtListapasajero.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        jtListapasajero.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        
      /*  for(int i=0; i<jtListapasajero.getModel().getRowCount() ;i++){
            String fecha= jtListapasajero.getModel().getValueAt(i, 6).toString();
            Date tiempo= new Date();
            try {
                tiempo = fechatimebd.parse(fecha);
            } catch (ParseException ex) {
                Logger.getLogger(ListaDePasajerosScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            jtListapasajero.getModel().setValueAt(fechatimejava.format(tiempo), i, 6);
        }*/
   
        
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
        DefaultTableModel   dm = (DefaultTableModel) jtPasajeros.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtPasajeros.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(cadena,0,1));
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jmMenuModificar = new javax.swing.JMenuItem();
        jmMenuEliminar = new javax.swing.JMenuItem();
        jLabel2 = new javax.swing.JLabel();
        jtfFecha = new javax.swing.JTextField();
        jtfHorario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfNumeroViaje = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtListapasajero = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;//Disallow the editing of any cell
            }
        };
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jtfBuscar = new javax.swing.JTextField();
        jScrollPanel = new javax.swing.JScrollPane();
        jtPasajeros = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;//Disallow the editing of any cell
            }
        };
        jLabel7 = new javax.swing.JLabel();
        jcbCantidad = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jcbPagado = new javax.swing.JComboBox<>();
        btnCancelar = new org.edisoncor.gui.button.ButtonRect();
        btnConfirmar = new org.edisoncor.gui.button.ButtonRect();

        jmMenuModificar.setText("Modificar Reserva");
        jmMenuModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMenuModificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jmMenuModificar);

        jmMenuEliminar.setText("Eliminar Reserva");
        jmMenuEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMenuEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jmMenuEliminar);

        setClosable(true);
        setTitle("LISTA DE PASAJEROS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono-jif.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel2.setText("FECHA:");

        jtfFecha.setEditable(false);
        jtfFecha.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N

        jtfHorario.setEditable(false);
        jtfHorario.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel3.setText("HORARIO:");

        jtfNumeroViaje.setEditable(false);
        jtfNumeroViaje.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel4.setText("N°:");

        jtListapasajero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtListapasajero.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(jtListapasajero);

        jLabel8.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel8.setText("ASIENTOS DISPONIBLES:");

        jtfAsientos.setEditable(false);
        jtfAsientos.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel1.setText("BUSCAR PASAJERO:");

        jtfBuscar.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jtfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarKeyReleased(evt);
            }
        });

        jtPasajeros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPanel.setViewportView(jtPasajeros);

        jLabel7.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel7.setText("CANTIDAD:");

        jcbCantidad.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jcbCantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel6.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel6.setText("¿PASAJE PAGADO?:");

        jcbPagado.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jcbPagado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        btnCancelar.setBackground(new java.awt.Color(153, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(0, 102, 102));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
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
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNumeroViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfBuscar))
                            .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jtfNumeroViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jtfHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jtfAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jcbPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarKeyReleased
        buscar(jtfBuscar.getText().toUpperCase());
    }//GEN-LAST:event_jtfBuscarKeyReleased

    private void jmMenuModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMenuModificarActionPerformed
     int fila = jtListapasajero.getSelectedRow();
     if(fila==-1){
         JOptionPane.showMessageDialog(rootPane, "No se seleccionó ninguna fila");
     } else {
        ModificarReservaScreen jif = new ModificarReservaScreen();
        panelEscritorio.add(jif);
        CentrarJInternalFrame form = new CentrarJInternalFrame(jif);
        form.centrar1();
        jif.setVisible(true);
     }
    }//GEN-LAST:event_jmMenuModificarActionPerformed

    private void jmMenuEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMenuEliminarActionPerformed
     int fila = jtListapasajero.getSelectedRow();
     if(fila==-1){
         JOptionPane.showMessageDialog(rootPane, "No se seleccionó ninguna fila");
     } else {
        int reply = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar el Chofer?", "ATENCIÓN", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String listapasajero_id= jtListapasajero.getValueAt(fila, 0).toString();
            ListaPasajeros lista = new ListaPasajeros();
            lista.eliminar(listapasajero_id);
            cargar_tabla();
            calcular_asientos_disponibles();
        }
        else {
           System.out.println("No se eliminó la Reserva");
        }
     }
    }//GEN-LAST:event_jmMenuEliminarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        int fila_pasajero= jtPasajeros.getSelectedRow();
        if(fila_pasajero==-1){
            JOptionPane.showMessageDialog(rootPane, "No se seleccionó ningún pasajero");
        } else {
            String viaje_id= jtfNumeroViaje.getText();
            String pasajero_dni=jtPasajeros.getValueAt(fila_pasajero, 0).toString();
            String observaciones= jtPasajeros.getValueAt(fila_pasajero, 2).toString();
            Empleado empleado = new Empleado();
            empleado.buscarEmpleadoDNI(jtPasajeros.getValueAt(fila_pasajero,1).toString());
            String empleado_dni= MainScreen.empleado_dni;
            String cantidad= jcbCantidad.getSelectedItem().toString();
            String pagado= jcbPagado.getSelectedItem().toString();
            String tiempo = fechatimebd.format(fecha);
            int estado=1;
            
            if(Integer.parseInt(jtfAsientos.getText())-Integer.parseInt(cantidad)<00){
                JOptionPane.showMessageDialog(rootPane,"No hay asientos disponibles para realizar la reserva");
            }
            else {
                ListaPasajeros lista = new ListaPasajeros(pasajero_dni, viaje_id, pasajero_dni,observaciones, empleado_dni, cantidad, pagado, tiempo, estado);
                lista.guardar();
                cargar_tabla();
                calcular_asientos_disponibles();
            }
        
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRect btnCancelar;
    private org.edisoncor.gui.button.ButtonRect btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> jcbCantidad;
    private javax.swing.JComboBox<String> jcbPagado;
    private javax.swing.JMenuItem jmMenuEliminar;
    private javax.swing.JMenuItem jmMenuModificar;
    public static javax.swing.JTable jtListapasajero;
    private javax.swing.JTable jtPasajeros;
    public static final javax.swing.JTextField jtfAsientos = new javax.swing.JTextField();
    private javax.swing.JTextField jtfBuscar;
    private javax.swing.JTextField jtfFecha;
    private javax.swing.JTextField jtfHorario;
    public static javax.swing.JTextField jtfNumeroViaje;
    // End of variables declaration//GEN-END:variables
}
