/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Locadora;

/**
 *
 * @author pedropereira
 */
public class ViewLocadora extends javax.swing.JFrame {
    controller.ControllerLocadora control = new controller.ControllerLocadora();
    controller.ControllerCliente controlCli = new controller.ControllerCliente();
    controller.ControllerFilmes controlFilm = new controller.ControllerFilmes();
    
    private void OpemCadCli(){
        //jButton_encerrar.setText("Encerrar Busca");
        //jButton_confirma.setVisible(false);
        //jButton_encerrar.setVisible(false);
        jFormattedTextField_data.setEnabled(false);
        control = new controller.ControllerLocadora();
        control.setArquivo("locadora");
        if(control.ler()==true){
            jTextArea1.setText(control.getTexto());
        }
    }
    
    private void ClearFields(){
        //jLabel_tituloAntigo.setText("");
        //jLabel_studioAntigo.setText("");
        jTextField_tituloFilme.setText("");
        jTextField_studio.setText("");
        jTextField_funcionario.setText("");
        jComboBox_periodo.setSelectedIndex(0);
        jTextField_valor.setText("");
        jCheckBox_devolvido.setSelected(false);
        jFormattedTextField_data.setText("");
        jFormattedTextField_rg.setText("");
        OpemCadCli();
    }
    
    /**
     * Creates new form Locadora
     */
    public ViewLocadora() {
        initComponents();
        ClearFields();
        jFormattedTextField_data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        jTextField_valor.setDocument(new utils.SoNumeros());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_funcionario = new javax.swing.JLabel();
        jLabel_tituloFilme = new javax.swing.JLabel();
        jLabel_nomeCli = new javax.swing.JLabel();
        jLabel_data = new javax.swing.JLabel();
        jLabel_periodo = new javax.swing.JLabel();
        jLabel_valor = new javax.swing.JLabel();
        jTextField_funcionario = new javax.swing.JTextField();
        jTextField_tituloFilme = new javax.swing.JTextField();
        jCheckBox_devolvido = new javax.swing.JCheckBox();
        jFormattedTextField_data = new javax.swing.JFormattedTextField();
        jTextField_valor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox_periodo = new javax.swing.JComboBox<>();
        jLabel_studio = new javax.swing.JLabel();
        jTextField_studio = new javax.swing.JTextField();
        jFormattedTextField_rg = new javax.swing.JFormattedTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_arquivos = new javax.swing.JMenu();
        jMenu_buscar = new javax.swing.JMenu();
        jMenuItem_func = new javax.swing.JMenuItem();
        jMenuItem_titulo = new javax.swing.JMenuItem();
        jMenuItem_studio = new javax.swing.JMenuItem();
        jMenuItem_rg = new javax.swing.JMenuItem();
        jMenuItem_data = new javax.swing.JMenuItem();
        jMenuItem_valor = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem_Salvar = new javax.swing.JMenuItem();
        jMenuItem_editar = new javax.swing.JMenuItem();
        jMenuItem_Excluir = new javax.swing.JMenuItem();
        jMenuItem_fechar = new javax.swing.JMenuItem();
        jMenu_cliente = new javax.swing.JMenu();
        jMenuItem_abrirCli = new javax.swing.JMenuItem();
        jMenu_catalogo = new javax.swing.JMenu();
        jMenuItem_abrirCatalogo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alugar Filmes");

        jLabel_funcionario.setText("FUNCIONÁRIO");

        jLabel_tituloFilme.setText("TÍTULO FILME");

        jLabel_nomeCli.setText("RG");

        jLabel_data.setText("DATA");

        jLabel_periodo.setText("SEMANAS ALUGADO");

        jLabel_valor.setText("VALOR");

        jCheckBox_devolvido.setText("DEVOLVIDO");

        try {
            jFormattedTextField_data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jComboBox_periodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        jComboBox_periodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_periodoActionPerformed(evt);
            }
        });

        jLabel_studio.setText("STUDIO");

        try {
            jFormattedTextField_rg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jMenu_arquivos.setText("Arquivos");

        jMenu_buscar.setText("Buscar");

        jMenuItem_func.setText("Por Funcionário");
        jMenu_buscar.add(jMenuItem_func);

        jMenuItem_titulo.setText("Por Titulo");
        jMenu_buscar.add(jMenuItem_titulo);

        jMenuItem_studio.setText("Por Studio");
        jMenu_buscar.add(jMenuItem_studio);

        jMenuItem_rg.setText("Por RG");
        jMenu_buscar.add(jMenuItem_rg);

        jMenuItem_data.setText("Por Data");
        jMenu_buscar.add(jMenuItem_data);

        jMenuItem_valor.setText("Por Valor");
        jMenu_buscar.add(jMenuItem_valor);

        jMenuItem1.setText("jMenuItem1");
        jMenu_buscar.add(jMenuItem1);

        jMenu_arquivos.add(jMenu_buscar);

        jMenuItem_Salvar.setText("Salvar");
        jMenuItem_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SalvarActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_Salvar);

        jMenuItem_editar.setText("Editar");
        jMenu_arquivos.add(jMenuItem_editar);

        jMenuItem_Excluir.setText("Excluir");
        jMenuItem_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ExcluirActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_Excluir);

        jMenuItem_fechar.setText("Fechar");
        jMenuItem_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_fecharActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_fechar);

        jMenuBar1.add(jMenu_arquivos);

        jMenu_cliente.setText("Clientes");
        jMenu_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_clienteActionPerformed(evt);
            }
        });

        jMenuItem_abrirCli.setText("Abrir");
        jMenuItem_abrirCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_abrirCliActionPerformed(evt);
            }
        });
        jMenu_cliente.add(jMenuItem_abrirCli);

        jMenuBar1.add(jMenu_cliente);

        jMenu_catalogo.setText("Catálogo");
        jMenu_catalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_catalogoActionPerformed(evt);
            }
        });

        jMenuItem_abrirCatalogo.setText("Abrir");
        jMenuItem_abrirCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_abrirCatalogoActionPerformed(evt);
            }
        });
        jMenu_catalogo.add(jMenuItem_abrirCatalogo);

        jMenuBar1.add(jMenu_catalogo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_funcionario, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_data)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel_data))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel_funcionario)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel_tituloFilme)
                                .addGap(100, 100, 100)
                                .addComponent(jLabel_studio)
                                .addGap(122, 122, 122)
                                .addComponent(jLabel_nomeCli))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jComboBox_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addComponent(jTextField_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_tituloFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_periodo))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel_valor)
                                                .addGap(85, 85, 85)
                                                .addComponent(jCheckBox_devolvido))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField_studio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jFormattedTextField_rg, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_funcionario)
                    .addComponent(jLabel_tituloFilme)
                    .addComponent(jLabel_nomeCli)
                    .addComponent(jLabel_studio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_tituloFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_studio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_data)
                    .addComponent(jLabel_periodo)
                    .addComponent(jLabel_valor)
                    .addComponent(jCheckBox_devolvido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(723, 514));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SalvarActionPerformed
        boolean op = false;
        if(!jTextField_tituloFilme.getText().equals("")&&!jTextField_studio.getText().equals("")&&!jTextField_funcionario.getText().equals("")&&!jTextField_valor.getText().equals("")&&!jFormattedTextField_data.getText().equals("  /  /    ")&&!jFormattedTextField_rg.getText().equals("  .   .   - ")){
            if(jCheckBox_devolvido.isSelected()){
                op=true;
            }else{
                op=false;
            }
            if(control.getTexto()!=null){
                controlFilm.setArquivo("filmes");
                controlFilm.ler();
                if(controlFilm.getFilmText(jTextField_tituloFilme.getText(), jTextField_studio.getText())!=null){
                    controlCli.setArquivo("clientes");
                    controlCli.ler();
                    if(controlCli.getCliText(jFormattedTextField_rg.getText())!=null){
                        OpemCadCli();
                        if(control.getLocaText(jTextField_tituloFilme.getText(),jTextField_studio.getText(), jFormattedTextField_data.getText())==null){
                            control.setLocadora(jTextField_funcionario.getText(),jTextField_tituloFilme.getText(),jTextField_studio.getText(),jFormattedTextField_rg.getText(),jFormattedTextField_data.getText(),Integer.parseInt(jComboBox_periodo.getSelectedItem().toString()),Float.parseFloat(jTextField_valor.getText()),op);
                            if(control.escrever(true)){
                                jTextArea1.setText(control.getTexto());
                                JOptionPane.showMessageDialog(this, "Arquivo escrito com sucesso","Escrita arquivo texto", JOptionPane.INFORMATION_MESSAGE);
                                ClearFields();
                            }else{
                                JOptionPane.showMessageDialog(this, "Escrita errada","Escrita arquivo texto", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(this, "FILME já Cadastrado","ERROR", JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Esse CLIENTE não está Cadastrado","ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Esse FILME não foi Cadastrado","ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                if(control.escrever(true)){
                    jTextArea1.setText(control.getTexto());
                    JOptionPane.showMessageDialog(this, "Arquivo escrito com sucesso","Escrita arquivo texto", JOptionPane.INFORMATION_MESSAGE);
                    ClearFields();
                }else{
                    JOptionPane.showMessageDialog(this, "Escrita errada","Escrita arquivo texto", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Todos Campos devem ser preenchidos","ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem_SalvarActionPerformed

    private void jMenuItem_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_fecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem_fecharActionPerformed

    private void jMenu_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_clienteActionPerformed
    }//GEN-LAST:event_jMenu_clienteActionPerformed

    private void jMenu_catalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_catalogoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu_catalogoActionPerformed

    private void jMenuItem_abrirCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_abrirCliActionPerformed
        ViewCliente cli = new ViewCliente();
        cli.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem_abrirCliActionPerformed

    private void jMenuItem_abrirCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_abrirCatalogoActionPerformed
        ViewFilmes film = new ViewFilmes();
        film.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem_abrirCatalogoActionPerformed

    private void jComboBox_periodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_periodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_periodoActionPerformed

    private void jMenuItem_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ExcluirActionPerformed
        String titulo = JOptionPane.showInputDialog(this, "Informe o TÍTULO do Filme","buscando Filmes", JOptionPane.INFORMATION_MESSAGE);
        String studio = JOptionPane.showInputDialog(this, "Informe o STUDIO que Lançou","buscando Filmes", JOptionPane.INFORMATION_MESSAGE);
        String data = JOptionPane.showInputDialog(this, "Informe a DATA","buscando Filmes", JOptionPane.INFORMATION_MESSAGE);
        Locadora loca = control.getLocaText(titulo,studio,data);
        if(loca != null&&loca.getDevolvido()==true){
            if(JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a LOCAÇÃO do Filme: "+loca.getTituloFilme(),"EXCLUIR FILME",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    control.excluiLocadora(loca.getTituloFilme(),loca.getStudioFilme(),loca.getRgCliente(),loca.getData());
                    ClearFields();
                    JOptionPane.showMessageDialog(this, "Filme Excluido com sucesso","AVISO",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Ação cancelada","AVISO",JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(loca != null&&loca.getDevolvido()==false){
            JOptionPane.showMessageDialog(this, "A LOCAÇÃO PRECISA SER DEVOLVIDA\nANTES DE SER EXCLUIDA!!", "ERRO DE EXCLUSÃO", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "LOCAÇÃO informada não encontrada", "ERRO DE BUSCA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem_ExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(ViewLocadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLocadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLocadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLocadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewLocadora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox_devolvido;
    private javax.swing.JComboBox<String> jComboBox_periodo;
    private javax.swing.JFormattedTextField jFormattedTextField_data;
    private javax.swing.JFormattedTextField jFormattedTextField_rg;
    private javax.swing.JLabel jLabel_data;
    private javax.swing.JLabel jLabel_funcionario;
    private javax.swing.JLabel jLabel_nomeCli;
    private javax.swing.JLabel jLabel_periodo;
    private javax.swing.JLabel jLabel_studio;
    private javax.swing.JLabel jLabel_tituloFilme;
    private javax.swing.JLabel jLabel_valor;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem_Excluir;
    private javax.swing.JMenuItem jMenuItem_Salvar;
    private javax.swing.JMenuItem jMenuItem_abrirCatalogo;
    private javax.swing.JMenuItem jMenuItem_abrirCli;
    private javax.swing.JMenuItem jMenuItem_data;
    private javax.swing.JMenuItem jMenuItem_editar;
    private javax.swing.JMenuItem jMenuItem_fechar;
    private javax.swing.JMenuItem jMenuItem_func;
    private javax.swing.JMenuItem jMenuItem_rg;
    private javax.swing.JMenuItem jMenuItem_studio;
    private javax.swing.JMenuItem jMenuItem_titulo;
    private javax.swing.JMenuItem jMenuItem_valor;
    private javax.swing.JMenu jMenu_arquivos;
    private javax.swing.JMenu jMenu_buscar;
    private javax.swing.JMenu jMenu_catalogo;
    private javax.swing.JMenu jMenu_cliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField_funcionario;
    private javax.swing.JTextField jTextField_studio;
    private javax.swing.JTextField jTextField_tituloFilme;
    private javax.swing.JTextField jTextField_valor;
    // End of variables declaration//GEN-END:variables
}
