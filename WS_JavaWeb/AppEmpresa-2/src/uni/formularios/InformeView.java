package uni.formularios;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import uni.database.AccesoDB;

public class InformeView extends javax.swing.JInternalFrame {
//CONSTRUCTORES
 public InformeView() {
  super("Reporteador Interno", true, true, true, true);
  initComponents();
  setBounds(10, 10, 600, 500);
  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 }

 public InformeView(String fileName) {
  this(fileName, null);
 }
 
   public InformeView(String fileName, HashMap parameter) { 
        this(); 
        try{ 
            Connection con=AccesoDB.getConnection(); 
            JasperPrint print=JasperFillManager.fillReport(fileName, parameter,con); 
            JRViewer viewer =new JRViewer(print); 
            Container c=getContentPane(); 
            c.setLayout(new BorderLayout()); 
            c.add(viewer); 
        } 
        catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | JRException jre){ 
        } 
    } 

 /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 497, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
