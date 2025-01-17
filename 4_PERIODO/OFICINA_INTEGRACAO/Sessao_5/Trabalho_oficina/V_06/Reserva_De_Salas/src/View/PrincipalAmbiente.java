/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Bean.Ambiente;
import Dao.AmbienteDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mvsro
 */
public class PrincipalAmbiente extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalAmbiente
     */
    public void Listar(){
       try{
            AmbienteDAO dao = new AmbienteDAO();
            List<Ambiente> listaAmbientes = dao.listarAmbientes();
            DefaultTableModel model = (DefaultTableModel)tabela.getModel();
            model.setNumRows(0);
            
            for(Ambiente lista : listaAmbientes){
                model.addRow(new Object[]{
                    lista.getId(),
                    lista.getBloco(),
                    lista.getSalaNum(),
                    lista.getTipoSala()
                });
            }
            
       }catch(Exception e){
       
       }
    }
    public PrincipalAmbiente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        tituloEditar = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbBloco = new javax.swing.JComboBox<>();
        cbNumero = new javax.swing.JComboBox<>();
        txtPesquisar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(796, 480));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 130, 40));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 130, 40));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 130, 40));

        tituloEditar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloEditar.setText("Ambientes");
        getContentPane().add(tituloEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, -1, -1));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Bloco", "Numero", "Tipo"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 500, 200));

        jLabel1.setText("Nome do Funcionario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        cbBloco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione o Bloco>", "A", "I", "P", "K", "S" }));
        cbBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBlocoActionPerformed(evt);
            }
        });
        getContentPane().add(cbBloco, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 160, -1));

        cbNumero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione a Sala>", "001", "002", "003", "004", "005", "101", "102", "103", "104", "105", "201", "202", "203", "204", "205" }));
        getContentPane().add(cbNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        txtPesquisar.setEditable(false);
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 30, -1));

        setSize(new java.awt.Dimension(813, 518));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Botão Adicionar
        CadastroAmbiente telaCadastro = new CadastroAmbiente();
        
        telaCadastro.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // Botão Pesquisar
         try{
            String bloco = cbBloco.getSelectedItem().toString();
            String salaNum = cbNumero.getSelectedItem().toString();
            if(bloco == "<Selecione o Bloco>" && salaNum == "<Selecione a Sala>"){
                Listar();
            }
            else{
                AmbienteDAO dao = new AmbienteDAO();
                List<Ambiente> consultarAmbientesId = dao.consultarAmbiente(bloco,salaNum);

                DefaultTableModel model = (DefaultTableModel)tabela.getModel();
                model.setNumRows(0);

                for(Ambiente consulta : consultarAmbientesId){
                    model.addRow(new Object[]{
                        consulta.getId(),
                        consulta.getBloco(),
                        consulta.getSalaNum(),
                        consulta.getTipoSala()
                    });
                }
            }
       }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Erro:"+e);
       }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    
        Listar();
        
    }//GEN-LAST:event_formWindowActivated

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // Botão Excluir
        try{
            Ambiente ambi = new Ambiente();
            AmbienteDAO ambiDao = new AmbienteDAO();
            
            ambi.setId(Integer.parseInt(txtPesquisar.getText()));
           

            ambiDao.excluirAmbiente(ambi);

            JOptionPane.showMessageDialog(null, "Ambiente Excluido com Sucesso!");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Selecione um Ambiente Antes de Excluir");
        }
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // Pegar o id do ambiente
        txtPesquisar.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tabelaMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // Pegar dados do Ambiente 
        try{
            Ambiente amb = new Ambiente();
            amb.setId(Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
            amb.setBloco(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            amb.setSalaNum(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            amb.setTipoSala(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());

            //enviar Dados para outra tela
            EditarAmbiente telaEditar = new EditarAmbiente(amb);

            telaEditar.setVisible(true);
            dispose();
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Selecione um Ambiente Antes de Editar");
        }
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        new TelaPrincipal().show();
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBlocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBlocoActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalAmbiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbBloco;
    private javax.swing.JComboBox<String> cbNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel tituloEditar;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
