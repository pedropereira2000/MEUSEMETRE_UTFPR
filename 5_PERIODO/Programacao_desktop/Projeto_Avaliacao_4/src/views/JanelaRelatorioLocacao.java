/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import bancodados.JDBCUtil;
import controller.ClienteDAO;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author user
 */
public class JanelaRelatorioLocacao extends javax.swing.JFrame {
    
    ClienteDAO dao = new ClienteDAO();
    
    public static final String relatorio_pdf = System.getProperty("user.dir")+"/src/relatorios/relatorio_locacao.pdf";
    public static final String relatorio_fonte = System.getProperty("user.dir")+"/src/relatorios/relatorio_locacao.jrxml";
    public static final String relatorio_compilado = System.getProperty("user.dir")+"/src/relatorios/relatorio_locacao.jasper";
    public static final String relatorio_busca = System.getProperty("user.dir")+"/src/relatorios/relatorio_locacao_devolvido.jasper";
    
    private Map controiParametrosCliente(){
        Map params = new HashMap();
        boolean op = false;
        if(rbt_alugado.isSelected()){
            params.put("devolvido", op);
        }else if (rbt_devolvido.isSelected()){
            op = true;
            params.put("devolvido", op);
        }
        return params;
    }
    
    /**
     * Creates new form JanelaRelatorioCli
     */
    public JanelaRelatorioLocacao(){
        dao.CriaConexao();
        initComponents();
        System.out.println(System.getProperties());
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
        btn_relatCliGeral = new javax.swing.JButton();
        btn_gerarPdf = new javax.swing.JButton();
        btn_Busca_filmes = new javax.swing.JButton();
        rbt_alugado = new javax.swing.JRadioButton();
        rbt_devolvido = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btn_relatCliGeral.setText("Visualizar Relatório");
        btn_relatCliGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relatCliGeralActionPerformed(evt);
            }
        });

        btn_gerarPdf.setText("Gerar PDF");
        btn_gerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gerarPdfActionPerformed(evt);
            }
        });

        btn_Busca_filmes.setText("Busca FIlmes Alugados/Devolvidos");
        btn_Busca_filmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Busca_filmesActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbt_alugado);
        rbt_alugado.setText("Ainda Alugado");
        rbt_alugado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonGroup1.add(rbt_devolvido);
        rbt_devolvido.setText("Já Devolvido");
        rbt_devolvido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_relatCliGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_gerarPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Busca_filmes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbt_alugado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbt_devolvido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_relatCliGeral)
                .addGap(18, 18, 18)
                .addComponent(btn_gerarPdf)
                .addGap(18, 18, 18)
                .addComponent(btn_Busca_filmes)
                .addGap(18, 18, 18)
                .addComponent(rbt_alugado)
                .addGap(18, 18, 18)
                .addComponent(rbt_devolvido)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_relatCliGeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relatCliGeralActionPerformed
        JasperPrint impressao;
        try {

            // caso seja necessário, compila o relatório (caso deseja usar o jrxml)
            JasperReport JR_compilado
                    = JasperCompileManager.compileReport(relatorio_fonte);
            
            //preenchimento do relatorio com a conexao e parametros
            impressao = JasperFillManager.fillReport(
                    JR_compilado,
                    null,
                   dao.getConnection());

            //opcao de visualizar o relatorio
            JasperViewer.viewReport(impressao, false);

        } catch (JRException erro) {
            System.err.println("Não foi possível exportar o relatório.\n\n" + erro);
        }
    }//GEN-LAST:event_btn_relatCliGeralActionPerformed

    private void btn_gerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gerarPdfActionPerformed
        JasperPrint impressao;
        try {
            //preenchimento do relatorio com a conexao e parametros
            impressao = JasperFillManager.fillReport(
                    relatorio_compilado,
                    null,
                    dao.getConnection());

            //opcao de exportar o relatorio diretamente para arquivo
            JasperExportManager.exportReportToPdfFile(impressao, relatorio_pdf);

            JOptionPane.showMessageDialog(this, "Gerado o arquivo com sucesso: " + "relatorio_locacao.pdf");
        } catch (JRException erro) {
            System.err.println("Não foi possível exportar o relatório.\n\n" + erro);
        }
    }//GEN-LAST:event_btn_gerarPdfActionPerformed

    private void btn_Busca_filmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Busca_filmesActionPerformed
        JasperPrint impressao;
        try {
            impressao = JasperFillManager.fillReport(
                    relatorio_busca,
                    controiParametrosCliente(),
                    dao.getConnection());
            //opcao de visualizar o relatorio
            JasperViewer.viewReport(impressao);

        } catch (JRException erro) {
            System.err.println("Não foi possível exportar o relatório.\n\n" + erro);
        }
    }//GEN-LAST:event_btn_Busca_filmesActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaRelatorioLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaRelatorioLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaRelatorioLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaRelatorioLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaRelatorioLocacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Busca_filmes;
    private javax.swing.JButton btn_gerarPdf;
    private javax.swing.JButton btn_relatCliGeral;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton rbt_alugado;
    private javax.swing.JRadioButton rbt_devolvido;
    // End of variables declaration//GEN-END:variables
}
