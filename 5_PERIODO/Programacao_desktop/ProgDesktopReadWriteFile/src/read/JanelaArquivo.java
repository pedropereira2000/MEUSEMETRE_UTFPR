
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Pereira
 */
public class JanelaArquivo extends javax.swing.JFrame {

    /**
     * Creates new form JanelaArquivo
     */
    public JanelaArquivo() {
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

        jButton_ListaPasta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_ListaPasta = new javax.swing.JTextArea();
        jButton_ClearTela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_ListaPasta.setText("Lista Pasta");
        jButton_ListaPasta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ListaPastaMouseClicked(evt);
            }
        });

        jTextArea_ListaPasta.setColumns(20);
        jTextArea_ListaPasta.setRows(5);
        jScrollPane1.setViewportView(jTextArea_ListaPasta);

        jButton_ClearTela.setText("Limpar Tela");
        jButton_ClearTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ClearTelaMouseClicked(evt);
            }
        });
        jButton_ClearTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearTelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_ListaPasta, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ClearTela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_ListaPasta)
                    .addComponent(jButton_ClearTela))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ListaPastaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ListaPastaMouseClicked
        String arquivo = JOptionPane.showInputDialog(this,"Digite a extensao para o procurarmos arquivos", "Seleciona Tipo Arquivo", JOptionPane.QUESTION_MESSAGE);
        
        String result = ControllFile.buscaArquivo(arquivo);
        /*for(var i = 0 ;i < result.size(); i++){
            jTextArea_ListaPasta.setText(jTextArea_ListaPasta.getText() + ControllFile.leituraArquivo(result.get(i)));
        }*/
        
        jTextArea_ListaPasta.setText(jTextArea_ListaPasta.getText() +result);
        
    }//GEN-LAST:event_jButton_ListaPastaMouseClicked

    private void jButton_ClearTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ClearTelaActionPerformed

    private void jButton_ClearTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ClearTelaMouseClicked
        jTextArea_ListaPasta.setText("");
    }//GEN-LAST:event_jButton_ClearTelaMouseClicked

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
            java.util.logging.Logger.getLogger(JanelaArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaArquivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ClearTela;
    private javax.swing.JButton jButton_ListaPasta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_ListaPasta;
    // End of variables declaration//GEN-END:variables
}
