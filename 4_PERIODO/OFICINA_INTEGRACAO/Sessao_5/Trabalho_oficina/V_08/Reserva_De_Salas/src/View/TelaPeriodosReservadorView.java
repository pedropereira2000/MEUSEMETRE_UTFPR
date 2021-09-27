/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Bean.Reserva;
import Dao.PeriodoReservadoDAO;
import Dao.ReservaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Pedro Pereira
 */
public class TelaPeriodosReservadorView extends javax.swing.JFrame {

    /**
     * Creates new form TelaPeriodosReservadorView
     */
    public TelaPeriodosReservadorView(String data, String bloco, String sala){
        initComponents();
        
        lblInfoBloco.setText(data);
        
        lblInfoSala.setText(bloco);
        
        lblInfoData.setText(sala);
                
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
    
    public void excluirReserva(){
        PeriodoReservadoDAO rdao = new PeriodoReservadoDAO();
        ReservaDAO rsdao = new ReservaDAO();
        int pesquisa =(tablePeriodo.getSelectedRow());
        //System.out.println(pesquisa);
        
        if(pesquisa >= 0){
            String periodo = (tablePeriodo.getValueAt(pesquisa,0)).toString();
            for(Reserva id: rdao.buscarId(lblInfoData.getText(), lblInfoBloco.getText(), lblInfoSala.getText(), periodo)){
                rsdao.excluirReserva(id);
                new TelaReservaView().show();
                dispose();
            }
            
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
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblInfoBloco = new javax.swing.JLabel();
        lblInfoSala = new javax.swing.JLabel();
        lblInfoData = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 570));

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblNomeFunc.setText("NOME FUNCIONÁRIO");

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

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

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
                        .addGap(29, 29, 29)
                        .addComponent(btnEditar)
                        .addGap(30, 30, 30)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNomeFunc)))
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInfoBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPeriodos)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBloco)
                        .addGap(49, 49, 49)
                        .addComponent(lblSala)
                        .addGap(60, 60, 60)
                        .addComponent(lblData)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInfoSala, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(lblInfoData, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(lblNomeFunc)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSala)
                    .addComponent(lblInfoBloco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoSala, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblData)
                        .addComponent(lblInfoData, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblBloco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int pesquisa =(tablePeriodo.getSelectedRow());
        //System.out.println(pesquisa);
        
        if(pesquisa >= 0){
            String periodo = (tablePeriodo.getValueAt(pesquisa,0)).toString();
            try {
                new EditarReserva(lblInfoData.getText(), lblInfoBloco.getText(), lblInfoSala.getText(), periodo).show();
            } catch (ParseException ex) {
                Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
            try{
                
            }
            catch (Exception erro){
                JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                //readJTable();
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirReserva();
    }//GEN-LAST:event_btnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
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
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblInfoBloco;
    private javax.swing.JLabel lblInfoData;
    private javax.swing.JLabel lblInfoSala;
    private javax.swing.JLabel lblNomeFunc;
    private javax.swing.JLabel lblPeriodos;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tablePeriodo;
    // End of variables declaration//GEN-END:variables
}
