
package View;

import Bean.Reserva;
import Dao.PeriodoReservadoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class TelaPeriodosReservadorView extends javax.swing.JFrame {

    
    public TelaPeriodosReservadorView(String data, String bloco, String sala){
        initComponents();
        
        lblBloco.setText(lblBloco.getText()+" "+data);
        
        lblSala.setText(lblSala.getText()+" "+bloco);
        
        lblData.setText(lblData.getText()+" "+sala);
                
        readJTable(data, bloco, sala);
                    
                
    }

    public void readJTable(String data, String bloco, String sala){
        DefaultTableModel model = (DefaultTableModel)tablePeriodo.getModel();
        model.setRowCount(0);
        PeriodoReservadoDAO rdao = new PeriodoReservadoDAO();
        
        for(Reserva r: rdao.read(data, bloco, sala)){
            
            model.addRow(new Object[]{
                r.getPeriodoReservado(),
                r.getClienteNome(),
                r.getServ().getNome(),
                r.getContribuicao(),
                r.getObservacao()
            });
        }
       
    }
    
    
    public void readJTableForDesc(String desc){
        DefaultTableModel model = (DefaultTableModel)tablePeriodo.getModel();
        model.setRowCount(0);
        PeriodoReservadoDAO rdao = new PeriodoReservadoDAO();
        
        for(Reserva r: rdao.readForDescData(desc)){
            
            model.addRow(new Object[]{
                r.getDataInicio(),
                r.getPeriodoReservado(),
                r.getObservacao()
            });
        }
            
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVoltar = new javax.swing.JButton();
        lblNomeFunc = new javax.swing.JLabel();
        lblBloco = new javax.swing.JLabel();
        lblSala = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePeriodo = new javax.swing.JTable();
        lblData = new javax.swing.JLabel();
        lblPeriodos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 570));

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblNomeFunc.setText(TelaLogin.userNome);

        lblBloco.setText("BLOCO:");

        lblSala.setText("SALA:");

        tablePeriodo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Periodo", "Cliente", "Servidor", "Contribuição", "Observações"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePeriodo.setColumnSelectionAllowed(true);
        tablePeriodo.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablePeriodo);
        tablePeriodo.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablePeriodo.getColumnModel().getColumnCount() > 0) {
            tablePeriodo.getColumnModel().getColumn(0).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(0).setPreferredWidth(4);
            tablePeriodo.getColumnModel().getColumn(1).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablePeriodo.getColumnModel().getColumn(2).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(2).setPreferredWidth(10);
            tablePeriodo.getColumnModel().getColumn(3).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(4).setResizable(false);
        }

        lblData.setText("DATA:");

        lblPeriodos.setText("PERIODOS RESERVADOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 421, Short.MAX_VALUE)
                        .addComponent(lblNomeFunc)))
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(lblPeriodos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(lblBloco)
                        .addGap(64, 64, 64)
                        .addComponent(lblSala)
                        .addGap(88, 88, 88)
                        .addComponent(lblData)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(lblNomeFunc))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBloco)
                    .addComponent(lblSala)
                    .addComponent(lblData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(lblPeriodos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        lblBloco.setText("BLOCO:");
        lblSala.setText("SALA:");
        lblData.setText("DATA:");
        
        this.setVisible(false);
        
        new TelaReservaView().show();
        dispose();
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new TelaPeriodosReservadorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNomeFunc;
    private javax.swing.JLabel lblPeriodos;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tablePeriodo;
    // End of variables declaration//GEN-END:variables
}
