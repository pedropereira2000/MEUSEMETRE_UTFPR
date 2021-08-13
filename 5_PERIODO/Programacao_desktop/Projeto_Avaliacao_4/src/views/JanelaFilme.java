/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import bancodados.JDBCUtil;
import controller.FilmeDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Filme;
import utils.SoNumeros;

/**
 *
 * @author Pedro Pereira
 */
public class JanelaFilme extends javax.swing.JFrame {

    FilmeDAO dao = new FilmeDAO();
    private int contEdit = 0;
    private int contNew = 0;
    private String titulo = "";
    private String genero = "";
    private int duracao = 0;
    private String classe = "";
    private String studio = "";
    
    public void BlockFields(String opcao){
        switch (opcao){
            case "edit":
                btn_anterior.setEnabled(false);
                btn_proximo.setEnabled(false);
                btn_primeiro.setEnabled(false);
                btn_ultimo.setEnabled(false);
                btn_inserir.setEnabled(false);
                btn_excluir.setEnabled(false);
                btn_cancel.setEnabled(true);
                jTF_titulo.setEnabled(true);
                jTF_genero.setEnabled(true);
                jTF_duracao.setEnabled(true);
                jTF_studio.setEnabled(true);
                jCB_class.setEnabled(true);
                jM_busca.setEnabled(false);
                contEdit = 1;
                break;
                
            case "new":
                btn_anterior.setEnabled(false);
                btn_proximo.setEnabled(false);
                btn_primeiro.setEnabled(false);
                btn_ultimo.setEnabled(false);
                btn_editar.setEnabled(false);
                btn_excluir.setEnabled(false);
                btn_cancel.setEnabled(true);
                jTF_titulo.setEnabled(true);
                jTF_genero.setEnabled(true);
                jTF_duracao.setEnabled(true);
                jTF_studio.setEnabled(true);
                jCB_class.setEnabled(true);
                jM_busca.setEnabled(false);
                jTF_id.setText("0");
                contNew = 1;
                break;
                
            case "empty":
                btn_anterior.setEnabled(false);
                btn_proximo.setEnabled(false);
                btn_primeiro.setEnabled(false);
                btn_ultimo.setEnabled(false);
                btn_editar.setEnabled(false);
                btn_excluir.setEnabled(false);
                btn_cancel.setEnabled(false);
                btn_inserir.setEnabled(true);
                jM_busca.setEnabled(false);
                jTF_titulo.setEnabled(true);
                jTF_genero.setEnabled(true);
                jTF_duracao.setEnabled(true);
                jTF_studio.setEnabled(true);
                jCB_class.setEnabled(true);
                jTF_id.setEnabled(false);
                jTF_id.setText("0");
                jTF_titulo.setText("");
                jTF_genero.setText("");
                jTF_duracao.setText("");
                jTF_studio.setText("");
                jCB_class.setSelectedIndex(0);
                contNew = 1;
                break;
                
            case "search":
                btn_cancel.setEnabled(true);
                break;
                
            default:
                btn_anterior.setEnabled(true);
                btn_proximo.setEnabled(true);
                btn_primeiro.setEnabled(true);
                btn_ultimo.setEnabled(true);
                btn_inserir.setEnabled(true);
                btn_editar.setEnabled(true);
                btn_excluir.setEnabled(true);
                btn_cancel.setEnabled(false);
                jTF_titulo.setEnabled(false);
                jTF_genero.setEnabled(false);
                jTF_duracao.setEnabled(false);
                jTF_studio.setEnabled(false);
                jCB_class.setEnabled(false);
                jTF_id.setEnabled(false);
                jM_busca.setEnabled(true);
                titulo = "";
                genero = "";
                duracao = 0;
                classe = "";
                studio = "";
                contEdit = 0;
                contNew = 0;
                break;
        }
             
    }
    
    public void AtualizaBaseDados(){
        if (dao.ConsultarTodos()) {
            BlockFields("");
            if (JDBCUtil.MovPrimeiro(dao.getRsdados())) {
                ExibeRegistro(dao.getFilme());
            }
        }else{
            BlockFields("empty");
            JOptionPane.showMessageDialog(this, "Não há filmes registrados");
        }
    }
    

    /**
     * Creates new form JanelaCliente
     */
    public JanelaFilme() {
        initComponents();
        jTF_duracao.setDocument(new SoNumeros());
        if (dao.CriaConexao()) {
            AtualizaBaseDados();
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

        label1 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        btn_primeiro = new javax.swing.JButton();
        btn_anterior = new javax.swing.JButton();
        btn_proximo = new javax.swing.JButton();
        btn_ultimo = new javax.swing.JButton();
        jTF_genero = new javax.swing.JTextField();
        jTF_duracao = new javax.swing.JTextField();
        btn_inserir = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTF_id = new javax.swing.JTextField();
        jTF_titulo = new javax.swing.JTextField();
        jCB_class = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTF_studio = new javax.swing.JTextField();
        btn_relatorio = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jM_busca = new javax.swing.JMenu();
        jMI_titulo = new javax.swing.JMenuItem();
        jMI_genero = new javax.swing.JMenuItem();
        jMI_duracao = new javax.swing.JMenuItem();
        jMI_classef = new javax.swing.JMenuItem();
        jMI_studio = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("TITULO:");

        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("GENERO:");

        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("DURACAO:");

        label5.setText("CLASSIFICACAO:");

        btn_primeiro.setLabel("Primeiro");
        btn_primeiro.setName("btnprimeiro"); // NOI18N
        btn_primeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_primeiroActionPerformed(evt);
            }
        });

        btn_anterior.setText("<-");
        btn_anterior.setName("btnanterior"); // NOI18N
        btn_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anteriorActionPerformed(evt);
            }
        });

        btn_proximo.setText("->");
        btn_proximo.setName("btnproximo"); // NOI18N
        btn_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proximoActionPerformed(evt);
            }
        });

        btn_ultimo.setLabel("Último");
        btn_ultimo.setName("btnultimo"); // NOI18N
        btn_ultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ultimoActionPerformed(evt);
            }
        });

        btn_inserir.setText("Inserir");
        btn_inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inserirActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancelar");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        jLabel1.setText("ID:");

        jCB_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "10", "12", "14", "16", "18" }));

        jLabel2.setText("STUDIO:");

        btn_relatorio.setText("Relatorio");
        btn_relatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relatorioActionPerformed(evt);
            }
        });

        jM_busca.setText("Buscar");
        jM_busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_buscaActionPerformed(evt);
            }
        });

        jMI_titulo.setText("TITULO");
        jMI_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_tituloActionPerformed(evt);
            }
        });
        jM_busca.add(jMI_titulo);

        jMI_genero.setText("GENERO");
        jMI_genero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_generoActionPerformed(evt);
            }
        });
        jM_busca.add(jMI_genero);

        jMI_duracao.setText("DURACAO");
        jMI_duracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_duracaoActionPerformed(evt);
            }
        });
        jM_busca.add(jMI_duracao);

        jMI_classef.setText("CLASSIFICACAO");
        jMI_classef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_classefActionPerformed(evt);
            }
        });
        jM_busca.add(jMI_classef);

        jMI_studio.setText("STUDIO");
        jMI_studio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_studioActionPerformed(evt);
            }
        });
        jM_busca.add(jMI_studio);

        jMenuBar1.add(jM_busca);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTF_genero, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTF_duracao, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2)
                        .addComponent(jTF_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTF_titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCB_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTF_studio))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_primeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_anterior)
                        .addGap(18, 18, 18)
                        .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ultimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_relatorio)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_primeiro)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_anterior)
                        .addComponent(btn_proximo)
                        .addComponent(btn_ultimo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTF_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTF_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTF_genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTF_duracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCB_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTF_studio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_relatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_inserir)
                    .addComponent(btn_editar)
                    .addComponent(btn_excluir)
                    .addComponent(btn_cancel))
                .addGap(13, 13, 13))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_primeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_primeiroActionPerformed
        if (JDBCUtil.MovPrimeiro(dao.getRsdados())) {
            ExibeRegistro(dao.getFilme());
        } else {
            JOptionPane.showMessageDialog(this, "O primeiro registro ja esta selecionado.");
        }
    }//GEN-LAST:event_btn_primeiroActionPerformed

    private void btn_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anteriorActionPerformed
        if (JDBCUtil.MovAnterior(dao.getRsdados())) {
            ExibeRegistro(dao.getFilme());
        } else {
            JOptionPane.showMessageDialog(this, "Nao existe registro anterior.");
        }
    }//GEN-LAST:event_btn_anteriorActionPerformed

    private void btn_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proximoActionPerformed
        if (JDBCUtil.MovProximo(dao.getRsdados())) {
            ExibeRegistro(dao.getFilme());
        } else {
            JOptionPane.showMessageDialog(this, "Nao existe proximo elemento.");
        }
    }//GEN-LAST:event_btn_proximoActionPerformed

    private void btn_ultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ultimoActionPerformed
        if (JDBCUtil.MovUltimo(dao.getRsdados())) {
            ExibeRegistro(dao.getFilme());
        } else {
            JOptionPane.showMessageDialog(this, "O ultimo registro ja esta selecionado.");
        }
    }//GEN-LAST:event_btn_ultimoActionPerformed

    private void btn_inserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inserirActionPerformed
        Filme film = CarregaFilme();
        if(contNew==1){
            int resp = dao.validaFilme(film);
            if(resp == 0){
                if (dao.Inserir(film)) {
                    JOptionPane.showMessageDialog(this, "Filme inserido com sucesso.");
                    AtualizaBaseDados();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao inserir filme.");
                }
            }else if(resp == 1){
                JOptionPane.showMessageDialog(this, "Filme inserido já está cadastrado.");
            }else if(resp == 2){
                JOptionPane.showMessageDialog(this, "Titulo inserido é inválido.");
            }
            else if(resp == 3){
                JOptionPane.showMessageDialog(this, "Gênero inserido é inválido.");
            }
            else if(resp == 4){
                JOptionPane.showMessageDialog(this, "Duração inserida é inválida.");
            }
            else if(resp == 5){
                JOptionPane.showMessageDialog(this, "Studio inserido é inválido.");
            }
        }else{
            jTF_titulo.setText("");
            jTF_genero.setText("");
            jTF_duracao.setText("");
            jCB_class.setSelectedIndex(0);
            jTF_studio.setText("");
            jTF_titulo.requestFocus();
            BlockFields("new");
        }
    }//GEN-LAST:event_btn_inserirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dao.FechaConexao();
        JFrame jpri = new JanelaPrincipal();
        jpri.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        Filme film = CarregaFilme();
        if(contEdit==1){
            int resp = dao.validaFilme(film);
            if(resp == 0){
                if (dao.Alterar(film)) {
                    JOptionPane.showMessageDialog(this, "Filme alterado com sucesso.");
                    AtualizaBaseDados();
                    BlockFields("");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao alterar o filme.");
                }
            }else if(resp == 1){
                JOptionPane.showMessageDialog(this, "Filme inserido já está cadastrado.");
            }else if(resp == 2){
                JOptionPane.showMessageDialog(this, "Titulo inserido é inválido.");
            }
            else if(resp == 3){
                JOptionPane.showMessageDialog(this, "Gênero inserido é inválido.");
            }
            else if(resp == 4){
                JOptionPane.showMessageDialog(this, "Duração inserida é inválida.");
            }
            else if(resp == 5){
                JOptionPane.showMessageDialog(this, "Studio inserido é inválido.");
            }
        }else{
            BlockFields("edit");
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        Filme film = CarregaFilme();
        if (dao.Excluir(film)) {
            JOptionPane.showMessageDialog(this, "Filme excluído com sucesso.");
            AtualizaBaseDados();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o filme.");
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        AtualizaBaseDados();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void jM_buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_buscaActionPerformed
        
    }//GEN-LAST:event_jM_buscaActionPerformed

    private void jMI_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_tituloActionPerformed
        titulo = JOptionPane.showInputDialog(this, "Informe o TITULO do filme","buscando filme", JOptionPane.INFORMATION_MESSAGE);
        if(dao.ConsultarTitulo(titulo)){
            JOptionPane.showMessageDialog(this, "Filmes(s) encontrado(s) com sucesso.");
            ExibeRegistro(dao.getFilme());
            btn_primeiroActionPerformed(evt);
            BlockFields("search");
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum filme foi encontrado.");
            AtualizaBaseDados();
        }
            
    }//GEN-LAST:event_jMI_tituloActionPerformed

    private void jMI_generoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_generoActionPerformed
        genero = JOptionPane.showInputDialog(this, "Informe o GENERO do filme","buscando filme", JOptionPane.INFORMATION_MESSAGE);
        //if(endereco.equals("")&&telefone.equals("")){
            if(dao.ConsultarGenero(genero)){
                JOptionPane.showMessageDialog(this, "Filmes(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getFilme());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum filme foi encontrado.");
                AtualizaBaseDados();
            }
        /*}else if(!endereco.equals("")){
            if(dao.ConsultarAninhadaNomeEndereco(nome, endereco)){
                JOptionPane.showMessageDialog(this, "Cliente(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getCliente());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum cliente foi encontrado.");
                AtualizaBaseDados();
            }
        }else if(!telefone.equals("")){
            if(dao.ConsultarAninhadaNomeTelefone(nome, endereco)){
                JOptionPane.showMessageDialog(this, "Cliente(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getCliente());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum cliente foi encontrado.");
                AtualizaBaseDados();
            }
        }*/
    }//GEN-LAST:event_jMI_generoActionPerformed

    private void jMI_duracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_duracaoActionPerformed
        
        int dur = 0;
        //if(nome.equals("")&&telefone.equals("")){
            try{
                duracao = Integer.valueOf(JOptionPane.showInputDialog(this, "Informe a DURACAO do filme","buscando cliente", JOptionPane.INFORMATION_MESSAGE));
                if(dao.ConsultarDuracao(duracao)){
                    JOptionPane.showMessageDialog(this, "Filme(s) encontrado(s) com sucesso.");
                    ExibeRegistro(dao.getFilme());
                    btn_primeiroActionPerformed(evt);
                    BlockFields("search");
                }else{
                    JOptionPane.showMessageDialog(this, "Nenhum filme foi encontrado.");
                    AtualizaBaseDados();
                }
            } catch(NumberFormatException err){
                JOptionPane.showMessageDialog(this, "Insira só números no campos DURACAO");
            }
        /*}else if(!nome.equals("")){
            if(dao.ConsultarAninhadaNomeEndereco(nome, endereco)){
                JOptionPane.showMessageDialog(this, "Cliente(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getCliente());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum cliente foi encontrado.");
                AtualizaBaseDados();
            }
        }else if(!telefone.equals("")){
            if(dao.ConsultarAninhadaEnderecoTelefone(endereco, telefone)){
                JOptionPane.showMessageDialog(this, "Cliente(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getCliente());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum cliente foi encontrado.");
                AtualizaBaseDados();
            }
        }*/
    }//GEN-LAST:event_jMI_duracaoActionPerformed

    private void jMI_classefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_classefActionPerformed
        classe = JOptionPane.showInputDialog(this, "Informe a CLASSIFICACAO do filme","buscando cliente", JOptionPane.INFORMATION_MESSAGE);
        //if(nome.equals("")&&endereco.equals("")){
            if(dao.ConsultarClassifica(classe)){
                JOptionPane.showMessageDialog(this, "Filme(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getFilme());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum filme foi encontrado.");
                AtualizaBaseDados();
            }
        /*}else if(!nome.equals("")){
            if(dao.ConsultarAninhadaNomeTelefone(nome, telefone)){
                JOptionPane.showMessageDialog(this, "Cliente(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getCliente());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum cliente foi encontrado.");
                AtualizaBaseDados();
            }
        }else if(!endereco.equals("")){
            if(dao.ConsultarAninhadaEnderecoTelefone(endereco, telefone)){
                JOptionPane.showMessageDialog(this, "Cliente(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getCliente());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum cliente foi encontrado.");
                AtualizaBaseDados();
            }
        }*/
    }//GEN-LAST:event_jMI_classefActionPerformed

    private void jMI_studioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_studioActionPerformed
        studio = JOptionPane.showInputDialog(this, "Informe a STUDIO do filme","buscando cliente", JOptionPane.INFORMATION_MESSAGE);
        //if(nome.equals("")&&endereco.equals("")){
            if(dao.ConsultarStudio(studio)){
                JOptionPane.showMessageDialog(this, "Filme(s) encontrado(s) com sucesso.");
                ExibeRegistro(dao.getFilme());
                btn_primeiroActionPerformed(evt);
                BlockFields("search");
            }else{
                JOptionPane.showMessageDialog(this, "Nenhum filme foi encontrado.");
                AtualizaBaseDados();
            }
    }//GEN-LAST:event_jMI_studioActionPerformed

    private void btn_relatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relatorioActionPerformed
        JFrame jrelat = new JanelaRelatorioFilme();
        jrelat.setVisible(true);
    }//GEN-LAST:event_btn_relatorioActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaFilme().setVisible(true);
            }
        });
    }

    public void ExibeRegistro(Filme film) {
        //faz a leitura do objeto cliente e atribui os valores lidos aos objetos visuais (Textfields)
        jTF_id.setText(String.valueOf(film.getId()));
        jTF_titulo.setText(film.getTitulo());
        jTF_genero.setText(film.getGenero());
        jTF_duracao.setText(String.valueOf(film.getDuracao()));
        jCB_class.setSelectedItem(film.getClassificacao());
        jTF_studio.setText(film.getStudio());
    }

    public Filme CarregaFilme() {
        //faz a leitura dos valores da janela em um objeto Cliente
        Filme film = null;
        int id = Integer.parseInt(jTF_id.getText());
        String titulo = jTF_titulo.getText();
        String genero = jTF_genero.getText();
        int duracao = Integer.valueOf(jTF_duracao.getText());
        String classificacao = String.valueOf(jCB_class.getSelectedItem());
        String studio = jTF_studio.getText();
        film = new Filme(id, titulo, genero, duracao, classificacao, studio);
        return film;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anterior;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_inserir;
    private javax.swing.JButton btn_primeiro;
    private javax.swing.JButton btn_proximo;
    private javax.swing.JButton btn_relatorio;
    private javax.swing.JButton btn_ultimo;
    private javax.swing.JComboBox<String> jCB_class;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMI_classef;
    private javax.swing.JMenuItem jMI_duracao;
    private javax.swing.JMenuItem jMI_genero;
    private javax.swing.JMenuItem jMI_studio;
    private javax.swing.JMenuItem jMI_titulo;
    private javax.swing.JMenu jM_busca;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField jTF_duracao;
    private javax.swing.JTextField jTF_genero;
    private javax.swing.JTextField jTF_id;
    private javax.swing.JTextField jTF_studio;
    private javax.swing.JTextField jTF_titulo;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    // End of variables declaration//GEN-END:variables
}
