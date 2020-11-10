/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Bean.Ambiente;
import Bean.Reserva;
import Connection.ConnectionFactory;
import Dao.AmbienteDAO;
import Dao.ReservaDAO;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Pedro Pereira
 */
public class TelaReservaView extends javax.swing.JFrame {
    
    public Connection conexao;
    
    public TelaReservaView() {
        initComponents();
        java.util.Date data = new java.util.Date();
        
        this.conexao = new ConnectionFactory().conectar();
        //jdcData.setDate(data);
        
        readBlocs();
        readJTable();
        
    }
    
    public void readJTable(){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        for(Reserva r: rdao.read()){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    public void readJTableBlocSala(String desc, String desc2){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        for(Reserva r: rdao.readForDescBlocSala(desc, desc2)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    public void readJTableBlocSalaDate(String desc, String desc2, String desc3){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        for(Reserva r: rdao.readForDescBlocSalaDate(desc, desc2, desc3)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    public void readJTableBloc(String desc){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        for(Reserva r: rdao.readForDescBloc(desc)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    public void readJTableSala(String desc){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        for(Reserva r: rdao.readForDescSala(desc)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    public void readJTableDate(String desc){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        for(Reserva r: rdao.readForDescDate(desc)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    public void removeModel(DefaultTableModel model){
        int i = 0;
        while(model.getRowCount()!=0){
            model.removeRow(i);
            i = i + 1;
        }
    }
    
    public void readBlocs(){
        cbxBloco.removeAllItems();
        cbxBloco.addItem(" ");
        cbxSala.removeAllItems();
        cbxSala.addItem(" ");
        //DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        //model.setRowCount(0);
        AmbienteDAO rdao = new AmbienteDAO();
        
        for(Ambiente r: rdao.listarBloco()){
            cbxBloco.addItem(r.getBloco());
        } 
    }
    
    public void readSalas(String desc){
        cbxSala.removeAllItems();
        cbxSala.addItem(" ");
        //DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        //model.setRowCount(0);
        AmbienteDAO rdao = new AmbienteDAO();
        
        for(Ambiente r: rdao.listarSala(desc)){
            cbxSala.addItem(r.getSalaNum());
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

        lblBloco = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        btnProcura = new javax.swing.JButton();
        cbxBloco = new javax.swing.JComboBox<>();
        lblSala = new javax.swing.JLabel();
        cbxSala = new javax.swing.JComboBox<>();
        btnVoltar = new javax.swing.JButton();
        lblNomeFunc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jdcData = new com.toedter.calendar.JDateChooser();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 600));

        lblBloco.setText("Bloco");

        lblData.setText("Data");

        btnProcura.setLabel("Procurar");
        btnProcura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcuraActionPerformed(evt);
            }
        });

        cbxBloco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "P", "G", "H", "I", "K", "A" }));
        cbxBloco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBlocoItemStateChanged(evt);
            }
        });
        cbxBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBlocoActionPerformed(evt);
            }
        });

        lblSala.setText("Sala");

        cbxSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "001", "002", "003", "101", "102", "103", " " }));
        cbxSala.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbxSalaPopupMenuWillBecomeVisible(evt);
            }
        });
        cbxSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxSalaMouseClicked(evt);
            }
        });

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblNomeFunc.setText("NOME FUNCIONÁRIO");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BLOCO", "SALA", "DATA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setColumnSelectionAllowed(true);
        tabela.setMaximumSize(new java.awt.Dimension(225, 225));
        tabela.setPreferredSize(new java.awt.Dimension(225, 225));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setResizable(false);
            tabela.getColumnModel().getColumn(1).setResizable(false);
            tabela.getColumnModel().getColumn(2).setResizable(false);
        }

        btnImprimir.setText("Imprimir");
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
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBloco)
                                    .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSala)))
                            .addComponent(btnVoltar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblData)
                                    .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnProcura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNomeFunc)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeFunc)
                    .addComponent(btnVoltar))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblBloco)
                                .addComponent(lblData))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblSala)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(btnImprimir))
                        .addComponent(btnProcura)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void btnProcuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcuraActionPerformed
        
        int pesquisa =(tabela.getSelectedRow());
        if(pesquisa >= 0){
            try{
                
                
                //System.out.println(tabela.getSelectedRow());
                //System.out.println(pesquisa);
                
                String data = (tabela.getValueAt(pesquisa,0)).toString();
                String bloco = (tabela.getValueAt(pesquisa,1)).toString();
                String sala = (tabela.getValueAt(pesquisa,2)).toString();

                TelaPeriodosReservadorView frame = new TelaPeriodosReservadorView(data, bloco, sala);

                frame.show();
                dispose();
            }
            catch (Exception erro){
                JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                readJTable();
            }
        }else{
            if((cbxBloco.getSelectedItem().toString())!=" "){
                try{
                    readJTableBloc(cbxBloco.getSelectedItem().toString());
                    //System.out.println("test");
                }
                catch (Exception erro){
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            if((cbxSala.getSelectedItem().toString())!=" "){
                try{
                    readJTableSala(cbxSala.getSelectedItem().toString());
                    //System.out.println("test");
                }
                catch (Exception erro){
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            if((jdcData.getDate())!=null){
                try{
                    SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd"); 
                    String data = s.format(jdcData.getDate());
                    //System.out.println(data);
                    readJTableDate(data);
                }
                catch (Exception erro){
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            if((cbxBloco.getSelectedItem().toString())!=" "&&(cbxSala.getSelectedItem().toString())!=" "){
                try{
                    readJTableBlocSala(cbxBloco.getSelectedItem().toString(),cbxSala.getSelectedItem().toString());
                    //System.out.println("test");
                }
                catch (Exception erro){
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            
            if((cbxBloco.getSelectedItem().toString())!=" "&&(cbxSala.getSelectedItem().toString())!=" " && (jdcData.getDate())!=null){
                
                SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd"); 
                String dataf = s.format(jdcData.getDate());
                readJTableBlocSalaDate(cbxBloco.getSelectedItem().toString(),cbxSala.getSelectedItem().toString(),dataf);
            }
            //JOptionPane.showConfirmDialog(null, "Teste","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
        }
        
        //tabela.getSelectedRow()
        //System.out.println(dataFormata);
        //readJTableForDesc(dataFormata);
    }//GEN-LAST:event_btnProcuraActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbxBlocoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBlocoItemStateChanged
        
    }//GEN-LAST:event_cbxBlocoItemStateChanged

    private void cbxBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBlocoActionPerformed
        //readSalas(cbxBloco.getSelectedItem().toString());
    }//GEN-LAST:event_cbxBlocoActionPerformed

    private void cbxSalaPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxSalaPopupMenuWillBecomeVisible
        readSalas(cbxBloco.getSelectedItem().toString());
    }//GEN-LAST:event_cbxSalaPopupMenuWillBecomeVisible

    private void cbxSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxSalaMouseClicked

    }//GEN-LAST:event_cbxSalaMouseClicked

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked

    }//GEN-LAST:event_tabelaMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        //Gerando um relatório de reservas
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório?","Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            //Imprimindo relatório com o framework JasperReports
            try {
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("C:/reports/Reservas.jasper",null,conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReservaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnProcura;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxBloco;
    private javax.swing.JComboBox<String> cbxSala;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcData;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNomeFunc;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
