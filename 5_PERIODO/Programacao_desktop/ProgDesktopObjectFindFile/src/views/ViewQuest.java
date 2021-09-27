/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JOptionPane;
import model.Cliente;

/**
 *
 * @author Pedro Pereira
 */
public class ViewQuest extends javax.swing.JFrame {
    controller.ControllerCliente control = new controller.ControllerCliente();
    
    /**
     * Creates new form ViewQuest
     */
    public ViewQuest() {
        initComponents();
    }
    
    private void OpemCadCli(){
        jTextField_nome.setEditable(false);
        jTextField_rg.setEditable(false);
        jButton_pesquisar.setVisible(false);
        jButton_fechar.setVisible(false);
        control = new controller.ControllerCliente();
        control.setArquivo("Abrir");
        control.ler();
        jTextArea_busca.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton_nome = new javax.swing.JRadioButton();
        jRadioButton_rg = new javax.swing.JRadioButton();
        jTextField_nome = new javax.swing.JTextField();
        jTextField_rg = new javax.swing.JTextField();
        jButton_pesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_busca = new javax.swing.JTextArea();
        jButton_fechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        buttonGroup1.add(jRadioButton_nome);
        jRadioButton_nome.setText("Pesquisar por nome");
        jRadioButton_nome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRadioButton_nomeFocusGained(evt);
            }
        });
        jRadioButton_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_nomeActionPerformed(evt);
            }
        });
        jRadioButton_nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioButton_nomeKeyPressed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_rg);
        jRadioButton_rg.setText("Pesquisar por RG");
        jRadioButton_rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_rgActionPerformed(evt);
            }
        });

        jButton_pesquisar.setText("Pesquisar");
        jButton_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_pesquisarActionPerformed(evt);
            }
        });

        jTextArea_busca.setEditable(false);
        jTextArea_busca.setColumns(20);
        jTextArea_busca.setRows(5);
        jScrollPane1.setViewportView(jTextArea_busca);

        jButton_fechar.setText("Fechar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_nome)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton_pesquisar)
                        .addComponent(jRadioButton_rg)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(jTextField_rg))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jButton_fechar)
                        .addGap(90, 90, 90))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_nome)
                    .addComponent(jTextField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_rg)
                    .addComponent(jTextField_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_pesquisar)
                    .addComponent(jButton_fechar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_nomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRadioButton_nomeFocusGained
        
    }//GEN-LAST:event_jRadioButton_nomeFocusGained

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        OpemCadCli();
    }//GEN-LAST:event_formWindowOpened

    private void jRadioButton_nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButton_nomeKeyPressed
        
    }//GEN-LAST:event_jRadioButton_nomeKeyPressed

    private void jRadioButton_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_nomeActionPerformed
        jButton_pesquisar.setVisible(true);
        jTextField_nome.setEditable(true);
        jTextField_rg.setEditable(false);
        jTextField_rg.setText("");
    }//GEN-LAST:event_jRadioButton_nomeActionPerformed

    private void jRadioButton_rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_rgActionPerformed
        jButton_pesquisar.setVisible(true);
        jTextField_nome.setEditable(false);
        jTextField_rg.setEditable(true);
        jTextField_nome.setText("");
    }//GEN-LAST:event_jRadioButton_rgActionPerformed

    private void jButton_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_pesquisarActionPerformed
        if(jTextField_nome.getText().equals("")&&jTextField_rg.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Não foi passado dados para pesquisa","ERROR",JOptionPane.WARNING_MESSAGE);
        }else{
            if(!jTextField_nome.getText().equals("")){
                Cliente cli = control.getCliText(jTextField_nome.getText(),"nome");
                if(cli == null){
                    JOptionPane.showMessageDialog(this, "RG informado não encontrado", "ERRO DE BUSCA", JOptionPane.WARNING_MESSAGE);
                }else{
                    jTextArea_busca.setText(control.getTexto());
                }
            }
             
            if(!jTextField_rg.getText().equals("")){
                
            }
        }
    
        
    }//GEN-LAST:event_jButton_pesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(ViewQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewQuest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_fechar;
    private javax.swing.JButton jButton_pesquisar;
    private javax.swing.JRadioButton jRadioButton_nome;
    private javax.swing.JRadioButton jRadioButton_rg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_busca;
    private javax.swing.JTextField jTextField_nome;
    private javax.swing.JTextField jTextField_rg;
    // End of variables declaration//GEN-END:variables
}
