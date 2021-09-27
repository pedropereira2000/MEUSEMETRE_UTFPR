/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.Cliente;

/**
 *
 * @author pedropereira
 */
public class ViewCliente extends javax.swing.JFrame {
    private int posicao = 0;
    private ArrayList<Integer> pos = null;
    private controller.ControllerCliente controller = new controller.ControllerCliente();
    
    //Função para limpar os campos e váriáveis utilizadas
    public void limparCampos() {
        jButton_encerra.setVisible(false);
        jButton_confirm.setVisible(false);
        jTextField_nome.setText("");
        jTextField_endereco.setText("");
        jFormattedTextField_rg.setText("");
        jFormattedTextField_telefone.setText("");
        jTextField_nome.setEnabled(true);
        jTextField_endereco.setEnabled(true);
        jFormattedTextField_rg.setEnabled(true);
        jFormattedTextField_telefone.setEnabled(true);
        jButton_novo.setEnabled(true);
        jButton_anterior.setEnabled(true);
        jButton_proximo.setEnabled(true);
        jMenuItem_editar.setEnabled(true);
        jMenuItem_excluir.setEnabled(true);
        jMenu_busca.setEnabled(true);
        jMenuItem_salvar.setEnabled(false);
        jTextField_nome.requestFocus();
        pos = null;
        posicao = 0;
    }

    //Função utilizada para configurar layout de botões e campos de forma a colocar as informações do cliente
    public void atribuirCampos(Cliente cliente) {
        jTextField_nome.setText(cliente.getNome());
        jFormattedTextField_rg.setText(cliente.getRg());
        jTextField_endereco.setText(cliente.getEndereco());
        jFormattedTextField_telefone.setText(cliente.getTelefone());
        jMenuItem_salvar.setEnabled(false);
        jTextField_nome.requestFocus();        
    }

    //Função para criar um novo cliente e chama função da classe controller para atribuir o cliente no sistema
    public void criaCliente() {
        Cliente cliente = new Cliente(
                jTextField_nome.getText(),
                jFormattedTextField_rg.getText(),
                jTextField_endereco.getText(),
                jFormattedTextField_telefone.getText());
        controller.setCliente(cliente); 
    }
    
    //Função para validar a presença de conteúdos nos campos
    public boolean validaCampos(){
        if((jTextField_nome.getText()).equals(""))
            return false;
        else if((jFormattedTextField_rg.getText()).equals("  .   .   - "))
            return false;
        else if(jTextField_endereco.getText().equals(""))
            return false;
        else if(jFormattedTextField_telefone.getText().equals("  -9    -    "))
            return false;
        return true;
    }
    
    //Função para passar para o proximo cliente
    public boolean navegaNextClientes(){
        int cont=posicao;
        if(++cont >= controller.getCliente().size())
            return false;
        return true;
    }
    
    //Função para voltar para o cliente anterior
    public boolean navegaPreviusClientes(){
        int cont=posicao;
        if(--cont < 0)
            return false;
        return true;
    }

    //Função para salvar os clientes do sistema no arquivo de clientes
    public boolean salvarArquivo() {
        controller.setArquivo("clientes");
        if (controller.getArquivo() != null) {
            if(controller.getCliente().isEmpty()){
                if(validaCampos()){
                    criaCliente();
                    if(controller.validaCliente()){
                        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        controller.escrever(false);
                        abrirArquivo();
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(this, "RG já está cadastrado","ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Campos não preenchidos","ERROR", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }else{
                if(validaCampos()){
                    criaCliente();
                    if(controller.validaCliente()){
                        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        controller.escrever(true);
                        String rg = jFormattedTextField_rg.getText();
                        abrirArquivo();
                        posicao = controller.encontraCliente(rg);
                        atribuirCampos(controller.getCliente().get(posicao));
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(this, "RG já está cadastrado","ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Campos não preenchidos","ERROR", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
        return false;
    }
    
    //Função para configurar o layout de botões e campos para cadastrar um novo cliente
    public void abilitaCriacao(){
        limparCampos();
        jMenuItem_editar.setEnabled(false);
        jMenuItem_excluir.setEnabled(false);
        jMenu_busca.setEnabled(false);
        jButton_anterior.setEnabled(false);
        jButton_proximo.setEnabled(false);
        jButton_novo.setEnabled(false);
        jMenuItem_salvar.setEnabled(true);
        if(controller.getCliente().size()>0){
            jButton_encerra.setVisible(true);
        }
    }
    
    //Função para configurar o layout de botões e campos para editar um cliente
    public void abilitaEdicao(){
        jButton_confirm.setVisible(true);
        jButton_encerra.setVisible(true);
        jButton_anterior.setEnabled(false);
        jButton_proximo.setEnabled(false);
        jButton_novo.setEnabled(false);
        jTextField_nome.setEnabled(true);
        jTextField_endereco.setEnabled(true);
        jFormattedTextField_telefone.setEnabled(true);
        jMenu_busca.setEnabled(false);
        jMenuItem_editar.setEnabled(false);
        jMenuItem_excluir.setEnabled(false);
    }
    
    //Função para aplicar a alteração do cadastro do cliente
    public boolean editCliente() {
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente validar a edição do cliente","INFORMATION",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            Cliente cli = controller.getCliente().get(controller.encontraCliente(jFormattedTextField_rg.getText()));
            cli.setNome(jTextField_nome.getText());
            cli.setEndereco(jTextField_endereco.getText());
            cli.setTelefone(jFormattedTextField_telefone.getText());
            controller.editarCliente(cli);
            String rg = jFormattedTextField_rg.getText();
            abrirArquivo();
            posicao = controller.encontraCliente(rg);
            atribuirCampos(controller.getCliente().get(posicao));
            return true;
        }else{
            return false;
        }
    }
    
    //Função para deletar um cliente
    public boolean delCliente(){
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o: "
                                                +"\ncliente: "+jTextField_nome.getText()
                                                +"\nrg: "+jFormattedTextField_rg.getText()
                                                +"\nendereço: "+jTextField_endereco.getText()
                                                +"\ntelefone: "+jFormattedTextField_telefone.getText(),"INFORMATION",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            Cliente cli = controller.getCliente().get(controller.encontraCliente(jFormattedTextField_rg.getText()));
            cli.setNome(jTextField_nome.getText());
            cli.setEndereco(jTextField_endereco.getText());
            cli.setTelefone(jFormattedTextField_telefone.getText());
            controller.excluiCliente(cli);
            //if(controller.getCliente().size()==0){
                //limparCampos();
                //abilitaCriacao();
            //}else{
                posicao = 0;
                abrirArquivo();
            //}
            return true;
        }else{
            return false;
        }
    }
    
    //Função para realizar a busca de clientes por um determinado parâmetro (nome, rg, endereço ou telefone)
    public boolean buscCli(String tipo){
        posicao=0;
        try{
            if(tipo.equals("nome")){
                pos=controller.buscarClientes(JOptionPane.showInputDialog(this, "Informe o NOME do cliente","buscando cliente", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getCliente().get(pos.get(0)));
            }

            if(tipo.equals("rg")){
                String rg = JOptionPane.showInputDialog(this, "Informe o RG do cliente","buscando cliente", JOptionPane.INFORMATION_MESSAGE);
                jFormattedTextField_rg.setText(rg);
                pos=controller.buscarClientes(jFormattedTextField_rg.getText(), tipo);
                atribuirCampos(controller.getCliente().get(pos.get(0)));
            }

            if(tipo.equals("endereco")){
                pos=controller.buscarClientes(JOptionPane.showInputDialog(this, "Informe o ENDEREÇO do cliente","buscando cliente", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getCliente().get(pos.get(0)));
            }

            if(tipo.equals("telefone")){
                String tel = JOptionPane.showInputDialog(this, "Informe o TELEFONE do cliente","buscando cliente", JOptionPane.INFORMATION_MESSAGE);
                jFormattedTextField_telefone.setText(tel);
                pos=controller.buscarClientes(jFormattedTextField_telefone.getText(), tipo);
                atribuirCampos(controller.getCliente().get(pos.get(0)));
            }
            jButton_encerra.setVisible(true);
            jButton_novo.setEnabled(false);
        }catch(IndexOutOfBoundsException err){
            System.err.println(err.getMessage() + "Não encontrou clientes compátiveis");
            abrirArquivo();
        }
        
        return true;
    }

    //Função para abrir e carregar o cliente
    public boolean abrirArquivo() {
        limparCampos();
        controller.setArquivo("clientes");
        if (controller.getArquivo() != null) {
            if (controller.ler()){
                if(controller.getCliente().size()>0){
                    atribuirCampos(controller.getCliente().get(posicao));
                    jTextField_nome.setEnabled(false);
                    jTextField_endereco.setEnabled(false);
                    jFormattedTextField_rg.setEnabled(false);
                    jFormattedTextField_telefone.setEnabled(false);   
                }else{
                    abilitaCriacao();
                }
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    /**
     * Creates new form Cliente
     */
    public ViewCliente() {
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

        jLabel_nome = new javax.swing.JLabel();
        jLabel_rg = new javax.swing.JLabel();
        jLabel_endereco = new javax.swing.JLabel();
        jLabel_telefone = new javax.swing.JLabel();
        jTextField_nome = new javax.swing.JTextField();
        jTextField_endereco = new javax.swing.JTextField();
        jButton_confirm = new javax.swing.JButton();
        jButton_encerra = new javax.swing.JButton();
        jFormattedTextField_rg = new javax.swing.JFormattedTextField();
        jFormattedTextField_telefone = new javax.swing.JFormattedTextField();
        jButton_novo = new javax.swing.JButton();
        jButton_anterior = new javax.swing.JButton();
        jButton_proximo = new javax.swing.JButton();
        jMenuBar_arquivos = new javax.swing.JMenuBar();
        jMenu_file = new javax.swing.JMenu();
        jMenu_busca = new javax.swing.JMenu();
        jMenuItem_nome = new javax.swing.JMenuItem();
        jMenuItem_rg = new javax.swing.JMenuItem();
        jMenuItem_endereco = new javax.swing.JMenuItem();
        jMenuItem_telefone = new javax.swing.JMenuItem();
        jMenuItem_salvar = new javax.swing.JMenuItem();
        jMenuItem_editar = new javax.swing.JMenuItem();
        jMenuItem_excluir = new javax.swing.JMenuItem();
        jMenuItem_fechar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciamento Cliente");
        setAlwaysOnTop(true);
        setFocusTraversalPolicyProvider(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel_nome.setText("NOME");

        jLabel_rg.setText("RG");
        jLabel_rg.setToolTipText("");

        jLabel_endereco.setText("ENDEREÇO");

        jLabel_telefone.setText("TELEFONE");

        jButton_confirm.setText(" Confirmar Edição");
        jButton_confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmActionPerformed(evt);
            }
        });

        jButton_encerra.setText("Cancelar");
        jButton_encerra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_encerraActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField_rg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextField_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-9####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton_novo.setText("Novo");
        jButton_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_novoActionPerformed(evt);
            }
        });

        jButton_anterior.setText("Anterior");
        jButton_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_anteriorActionPerformed(evt);
            }
        });

        jButton_proximo.setText("Próximo");
        jButton_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_proximoActionPerformed(evt);
            }
        });

        jMenu_file.setText("Arquivo");

        jMenu_busca.setText("Buscar");

        jMenuItem_nome.setText("Por Nome");
        jMenuItem_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_nomeActionPerformed(evt);
            }
        });
        jMenu_busca.add(jMenuItem_nome);

        jMenuItem_rg.setText("Por RG");
        jMenuItem_rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_rgActionPerformed(evt);
            }
        });
        jMenu_busca.add(jMenuItem_rg);

        jMenuItem_endereco.setText("Por Endereço");
        jMenuItem_endereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_enderecoActionPerformed(evt);
            }
        });
        jMenu_busca.add(jMenuItem_endereco);

        jMenuItem_telefone.setText("Por Telefone");
        jMenuItem_telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_telefoneActionPerformed(evt);
            }
        });
        jMenu_busca.add(jMenuItem_telefone);

        jMenu_file.add(jMenu_busca);

        jMenuItem_salvar.setText("Salvar");
        jMenuItem_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_salvarActionPerformed(evt);
            }
        });
        jMenu_file.add(jMenuItem_salvar);

        jMenuItem_editar.setText("Editar");
        jMenuItem_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_editarActionPerformed(evt);
            }
        });
        jMenu_file.add(jMenuItem_editar);

        jMenuItem_excluir.setText("Excluir");
        jMenuItem_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_excluirActionPerformed(evt);
            }
        });
        jMenu_file.add(jMenuItem_excluir);

        jMenuItem_fechar.setText("Fechar");
        jMenuItem_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_fecharActionPerformed(evt);
            }
        });
        jMenu_file.add(jMenuItem_fechar);

        jMenuBar_arquivos.add(jMenu_file);

        setJMenuBar(jMenuBar_arquivos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jFormattedTextField_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel_nome))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel_endereco))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel_rg))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jFormattedTextField_rg, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel_telefone))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton_anterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_proximo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_novo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton_confirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_encerra)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel_nome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_endereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_rg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_telefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextField_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_anterior)
                    .addComponent(jButton_proximo)
                    .addComponent(jButton_novo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_confirm)
                    .addComponent(jButton_encerra))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(361, 420));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_salvarActionPerformed
        salvarArquivo();
    }//GEN-LAST:event_jMenuItem_salvarActionPerformed

    private void jMenuItem_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_fecharActionPerformed
        ViewLocadora loc = new ViewLocadora();
        loc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem_fecharActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        abrirArquivo();
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_editarActionPerformed
        abilitaEdicao();
    }//GEN-LAST:event_jMenuItem_editarActionPerformed

    private void jButton_confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmActionPerformed
        editCliente();
    }//GEN-LAST:event_jButton_confirmActionPerformed

    private void jMenuItem_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_excluirActionPerformed
        delCliente();
    }//GEN-LAST:event_jMenuItem_excluirActionPerformed

    private void jMenuItem_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_nomeActionPerformed
        buscCli("nome");
    }//GEN-LAST:event_jMenuItem_nomeActionPerformed

    private void jMenuItem_enderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_enderecoActionPerformed
        buscCli("endereco");
    }//GEN-LAST:event_jMenuItem_enderecoActionPerformed

    private void jMenuItem_telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_telefoneActionPerformed
       buscCli("telefone");
    }//GEN-LAST:event_jMenuItem_telefoneActionPerformed

    private void jButton_encerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_encerraActionPerformed
        abrirArquivo();
    }//GEN-LAST:event_jButton_encerraActionPerformed

    private void jMenuItem_rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_rgActionPerformed
        buscCli("rg");
    }//GEN-LAST:event_jMenuItem_rgActionPerformed

    private void jButton_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_novoActionPerformed
        abilitaCriacao();
    }//GEN-LAST:event_jButton_novoActionPerformed

    private void jButton_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_proximoActionPerformed
        if(navegaNextClientes()&&pos == null)
            atribuirCampos(controller.getCliente().get(++posicao));
        
        if(pos != null){
            if(++posicao < pos.size()){
                atribuirCampos(controller.getCliente().get(pos.get(posicao)));
            }else{
                posicao=pos.size()-1;
            }
        }
    }//GEN-LAST:event_jButton_proximoActionPerformed

    private void jButton_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_anteriorActionPerformed
        if(navegaPreviusClientes()&&pos == null)
            atribuirCampos(controller.getCliente().get(--posicao));
        
        if(pos != null){
            if(--posicao >= 0){
                atribuirCampos(controller.getCliente().get(pos.get(posicao)));
            }else{
                posicao=0;
            }
        }
    }//GEN-LAST:event_jButton_anteriorActionPerformed

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
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_anterior;
    private javax.swing.JButton jButton_confirm;
    private javax.swing.JButton jButton_encerra;
    private javax.swing.JButton jButton_novo;
    private javax.swing.JButton jButton_proximo;
    private javax.swing.JFormattedTextField jFormattedTextField_rg;
    private javax.swing.JFormattedTextField jFormattedTextField_telefone;
    private javax.swing.JLabel jLabel_endereco;
    private javax.swing.JLabel jLabel_nome;
    private javax.swing.JLabel jLabel_rg;
    private javax.swing.JLabel jLabel_telefone;
    private javax.swing.JMenuBar jMenuBar_arquivos;
    private javax.swing.JMenuItem jMenuItem_editar;
    private javax.swing.JMenuItem jMenuItem_endereco;
    private javax.swing.JMenuItem jMenuItem_excluir;
    private javax.swing.JMenuItem jMenuItem_fechar;
    private javax.swing.JMenuItem jMenuItem_nome;
    private javax.swing.JMenuItem jMenuItem_rg;
    private javax.swing.JMenuItem jMenuItem_salvar;
    private javax.swing.JMenuItem jMenuItem_telefone;
    private javax.swing.JMenu jMenu_busca;
    private javax.swing.JMenu jMenu_file;
    private javax.swing.JTextField jTextField_endereco;
    private javax.swing.JTextField jTextField_nome;
    // End of variables declaration//GEN-END:variables
}
