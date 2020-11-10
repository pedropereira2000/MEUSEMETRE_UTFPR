
package View;

import Bean.Ambiente;
import Bean.Reserva;
import Dao.AmbienteDAO;
import Dao.ReservaDAO;
import Connection.ConnectionFactory;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class TelaReservaView extends javax.swing.JFrame {
    
    Connection conexao = null;
    
    public TelaReservaView() {
        initComponents();
        java.util.Date data = new java.util.Date();
        
        conexao = ConnectionFactory.conectar();
        
        lblId.setVisible(false);
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
        btnAdicionar = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        jdcData = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(796, 480));

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

        lblNomeFunc.setText(TelaLogin.userNome);

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

        btnAdicionar.setText("Criar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNomeFunc)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblBloco)
                                .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSala))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblData)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(86, 86, 86)
                                    .addComponent(btnProcura)))
                            .addGap(169, 169, 169))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(92, 92, 92)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(347, 347, 347))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVoltar)
                            .addComponent(lblNomeFunc))
                        .addGap(16, 82, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblBloco)
                                    .addComponent(lblData))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(btnProcura)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSala)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void btnProcuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcuraActionPerformed
        
        int pesquisa =(tabela.getSelectedRow());
        if(pesquisa >= 0){
            try{
                
                
                System.out.println(tabela.getSelectedRow());
                System.out.println(pesquisa);
                
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
        new TelaPrincipal().show();
        dispose();
        ;
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

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        new CadastroReserva().show();
        dispose();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReservaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnProcura;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxBloco;
    private javax.swing.JComboBox<String> cbxSala;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcData;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNomeFunc;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
