/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Filmes;

/**
 *
 * @author pedropereira
 */
public class ViewFilmes extends javax.swing.JFrame {
    private int posicao = 0;
    private ArrayList<Integer> pos = null;
    private String tituloEditado = null;
    private String studioEditado = null;
    private controller.ControllerFilmes controller = new controller.ControllerFilmes();
    
    //Função para limpar os campos e váriáveis utilizadas
    public void limparCampos() {
        jButton_encerrar.setVisible(false);
        jButton_confirma.setVisible(false);
        jTextField_titulo.setText("");
        jTextField_diretor.setText("");
        jTextField_genero.setText("");
        jTextField_duracao.setText("");
        jCheckBox_filmeAlugado.setSelected(false);
        jComboBox_classificacao.setSelectedIndex(0);
        jTextField_titulo.setEnabled(true);
        jTextField_diretor.setEnabled(true);
        jTextField_genero.setEnabled(true);
        jTextField_duracao.setEnabled(true);
        jCheckBox_filmeAlugado.setEnabled(true);
        jComboBox_classificacao.setEnabled(true);
        jButton_novo.setEnabled(true);
        jButton_anterior.setEnabled(true);
        jButton_proximo.setEnabled(true);
        jMenuItem_editar.setEnabled(true);
        jMenuItem_excluir.setEnabled(true);
        jMenu_buscar.setEnabled(true);
        jMenuItem_salvar.setEnabled(false);
        jTextField_titulo.requestFocus();
        pos = null;
        posicao = 0;
    }

    //Função utilizada para configurar layout de botões e campos de forma a colocar as informações do filme
    public void atribuirCampos(Filmes filme) {
        jTextField_titulo.setText(filme.getTitulo());
        jTextField_diretor.setText(filme.getStudio());
        jTextField_genero.setText(filme.getGenero());
        jTextField_duracao.setText((filme.getDuracao()).toString());
        if(filme.getAlugado())
            jCheckBox_filmeAlugado.setSelected(true);
        else
            jCheckBox_filmeAlugado.setSelected(false);
        jComboBox_classificacao.setSelectedItem(filme.getClassificacao());
        jMenuItem_salvar.setEnabled(false);
        jTextField_titulo.requestFocus();        
    }

    //Função para criar um novo filme e chama função da classe controller para atribuir o filme no sistema
    public void criaFilme() {
        boolean op = false;
        if(jCheckBox_filmeAlugado.isSelected())
            op = true;
        Filmes filme = new Filmes(
                jTextField_titulo.getText(),
                jTextField_genero.getText(),
                Integer.parseInt(jTextField_duracao.getText()),
                jComboBox_classificacao.getSelectedItem().toString(),
                jTextField_diretor.getText(),
                op 
        );
        controller.setFilme(filme); 
    }
    
    //Função para validar a presença de conteúdos nos campos
    public boolean validaCampos(String obj, String tipo){
        //Verificação para cadastrar, se os campos nao sao vazios
        if(obj.equals("")&&tipo.equals("")){
            if((jTextField_titulo.getText()).equals(""))
                return false;
            else if((jTextField_diretor.getText()).equals(""))
                return false;
            else if(jTextField_duracao.getText().equals(""))
                return false;
            else if(jTextField_genero.getText().equals(""))
                return false;
        //Verificacao para edição, se as informações passadas sao validas
        }else{
            if(obj.equals("alugado"))
                if(!tipo.equals("FALSE")&&!tipo.equals("TRUE"))
                    return false;
            if(obj.equals("classificacao"))
                if(!tipo.equals("L")&&!tipo.equals("l")&&!tipo.equals("10")&&!tipo.equals("12")&&!tipo.equals("14")&&!tipo.equals("16")&&!tipo.equals("18"))
                    return false;
            if(obj.equals("duracao"))
                if(tipo.substring(0).matches("[A-Z]*")||tipo.substring(0).matches("[a-z]*"))
                    return false;
                
        }
        return true;
    }
    
    //Função para passar para o proximo filme
    public boolean navegaNextFilmes(){
        int cont=posicao;
        if(++cont >= controller.getFilme().size())
            return false;
        return true;
    }
    
    //Função para voltar para o filme anterior
    public boolean navegaPreviusFilmes(){
        int cont=posicao;
        if(--cont < 0)
            return false;
        return true;
    }

    //Função para salvar o filme do sistema no arquivo de filmes
    public boolean salvarArquivo() {
        controller.setArquivo("filmes");
        //Se o arquivo existe
        if (controller.getArquivo() != null) {
            ///Se a lista de filme é vazia
            if(controller.getFilme().isEmpty()){
                //Se os campos sao validos
                if(validaCampos("","")){
                    criaFilme();
                    //Se o filme nao ja esta cadastrado
                    if(controller.validaFilme()){
                        JOptionPane.showMessageDialog(this, "Filme cadastrado com sucesso","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        controller.escrever(false);
                        abrirArquivo();
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(this, "Titulo já está cadastrado para esse studio","ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Campos não preenchidos","ERROR", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            //Se ja existe filmes cadastrados
            }else{
                //Se os campos nao estao vazios
                if(validaCampos("","")){
                    criaFilme();
                    //Se o filme nao ja esta cadastrado
                    if(controller.validaFilme()){
                        JOptionPane.showMessageDialog(this, "Filme cadastrado com sucesso","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        controller.escrever(true);
                        String titulo = jTextField_titulo.getText();
                        String studio = jTextField_diretor.getText();
                        abrirArquivo();
                        posicao = controller.encontraFilme(titulo, studio);
                        atribuirCampos(controller.getFilme().get(posicao));
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(this, "Titulo já está cadastrado nesse studio","ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Campos não preenchidos","ERROR", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
        return false;
    }
    
    //Função para configurar o layout de botões e campos para cadastrar um novo filme
    public void abilitaCriacao(){
        limparCampos();
        jMenuItem_editar.setEnabled(false);
        jMenuItem_excluir.setEnabled(false);
        jMenu_buscar.setEnabled(false);
        jButton_anterior.setEnabled(false);
        jButton_proximo.setEnabled(false);
        jButton_novo.setEnabled(false);
        jMenuItem_salvar.setEnabled(true);
        if(controller.getFilme().size()>0){
            jButton_encerrar.setVisible(true);
        }
    }
    
    //Função para configurar o layout de botões e campos para editar um filme
    public void abilitaEdicao(){
        jButton_confirma.setVisible(true);
        jButton_encerrar.setVisible(true);
        jButton_anterior.setEnabled(false);
        jButton_proximo.setEnabled(false);
        jButton_novo.setEnabled(false);
        jTextField_titulo.setEnabled(true);
        jTextField_genero.setEnabled(true);
        jTextField_duracao.setEnabled(true);
        jTextField_diretor.setEnabled(true);
        jCheckBox_filmeAlugado.setEnabled(true);
        jComboBox_classificacao.setEnabled(true);
        jMenu_buscar.setEnabled(false);
        jMenuItem_editar.setEnabled(false);
        jMenuItem_excluir.setEnabled(false);
        tituloEditado = jTextField_titulo.getText();
        studioEditado = jTextField_diretor.getText();
    }
    
    //Função para aplicar a alteração do cadastro do filme
    public boolean editFilme() {
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente validar a edição do filme","INFORMATION",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            Filmes filme = controller.getFilme().get(controller.encontraFilme(tituloEditado,studioEditado));
            filme.setTitulo(jTextField_titulo.getText());
            filme.setStudio(jTextField_diretor.getText());
            filme.setGenero(jTextField_genero.getText());
            filme.setDuracao(Integer.parseInt(jTextField_duracao.getText()));
            filme.setClassificacao(jComboBox_classificacao.getSelectedItem().toString());
            if(jCheckBox_filmeAlugado.isSelected())
                filme.setAlugado(true);
            else
                filme.setAlugado(false);
            controller.editarFilme(filme);
            String titulo = jTextField_titulo.getText();
            String studio = jTextField_diretor.getText();
            abrirArquivo();
            posicao = controller.encontraFilme(titulo, studio);
            atribuirCampos(controller.getFilme().get(posicao));
            return true;
        }else{
            return false;
        }
    }
    
    //Função para deletar um filme
    public boolean delFilme(){
        boolean op = false;
        if(jCheckBox_filmeAlugado.isSelected())
            op = true;
        else
            op = false;
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o: "
                                                +"\ntitulo: "+jTextField_titulo.getText()
                                                +"\ngenêro: "+jTextField_genero.getText()
                                                +"\nduração: "+jTextField_duracao.getText()
                                                +"\nclassificação: "+jComboBox_classificacao.getSelectedItem().toString()
                                                +"\nstudio: "+jTextField_diretor.getText()
                                                +"\nalugado: "+op,"INFORMATION",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            Filmes filme = controller.getFilme().get(controller.encontraFilme(jTextField_titulo.getText(),jTextField_diretor.getText()));
            filme.setTitulo(jTextField_titulo.getText());
            filme.setGenero(jTextField_genero.getText());
            filme.setDuracao(Integer.parseInt(jTextField_duracao.getText()));
            filme.setClassificacao(jComboBox_classificacao.getSelectedItem().toString());
            filme.setStudio(jTextField_diretor.getText());
            filme.setAlugado(op);
            controller.excluiFilme(filme);
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
    
    //Função para realizar a busca de filmes por um determinado parâmetro (titulo, genero, duracao, classificacao, studio ou alugado)
    public boolean buscFilme(String tipo){
        posicao=0;
        try{
            if(tipo.equals("titulo")){
                pos=controller.buscarFilme(JOptionPane.showInputDialog(this, "Informe o TITULO do filme","buscando filme", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getFilme().get(pos.get(0)));
                jButton_encerrar.setVisible(true);
                jButton_novo.setEnabled(false);
            }

            if(tipo.equals("genero")){
                pos=controller.buscarFilme(JOptionPane.showInputDialog(this, "Informe o GENERO do filme","buscando filme", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getFilme().get(pos.get(0)));
                jButton_encerrar.setVisible(true);
                jButton_novo.setEnabled(false);
            }

            if(tipo.equals("duracao")){
                String verif = JOptionPane.showInputDialog(this, "Informe a DURACAO do filme","buscando Filme", JOptionPane.INFORMATION_MESSAGE);
                if(validaCampos("duracao", verif)){
                    pos=controller.buscarFilme(verif, tipo);
                    atribuirCampos(controller.getFilme().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            if(tipo.equals("classificacao")){
                String verif = JOptionPane.showInputDialog(this, "Informe a CLASSIFICAÇÃO do filme","buscando Filme", JOptionPane.INFORMATION_MESSAGE);
                if(validaCampos("classificacao", verif)){
                    if(verif.equals("l"))
                        verif="L";
                    pos=controller.buscarFilme(verif, tipo);
                    atribuirCampos(controller.getFilme().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            if(tipo.equals("studio")){
                pos=controller.buscarFilme(JOptionPane.showInputDialog(this, "Informe a STUDIO do filme","buscando Filme", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getFilme().get(pos.get(0)));
                jButton_encerrar.setVisible(true);
                jButton_novo.setEnabled(false);
            }
            
            if(tipo.equals("alugado")){
                String verif = JOptionPane.showInputDialog(this, "Informe a ALUGADO do filme","buscando Filme", JOptionPane.INFORMATION_MESSAGE);
                if(validaCampos("alugado", verif.toUpperCase())){
                    pos=controller.buscarFilme(verif.toLowerCase(), tipo);
                    atribuirCampos(controller.getFilme().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
        }catch(IndexOutOfBoundsException err){
            System.err.println(err.getMessage() + "Não encontrou clientes compátiveis");
            abrirArquivo();
        } catch(NumberFormatException err){
            System.err.println(err.getMessage() + "Não foi inserido numeros");
        }
        
        return true;
    }

    //Função para abrir e carregar os filmes
    public boolean abrirArquivo() {
        limparCampos();
        controller.setArquivo("filmes");
        if (controller.getArquivo() != null) {
            if (controller.ler()){
                if(controller.getFilme().size()>0){
                    atribuirCampos(controller.getFilme().get(posicao));
                    jTextField_titulo.setEnabled(false);
                    jTextField_genero.setEnabled(false);
                    jTextField_duracao.setEnabled(false);
                    jTextField_diretor.setEnabled(false);
                    jCheckBox_filmeAlugado.setEnabled(false);
                    jComboBox_classificacao.setEnabled(false);
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
     * Creates new form Filmes
     */
    public ViewFilmes() {
        initComponents();
        jTextField_duracao.setDocument(new utils.SoNumeros());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel_titulo = new javax.swing.JLabel();
        jLabel_genero = new javax.swing.JLabel();
        jLabel_duracao = new javax.swing.JLabel();
        jLabel_classificacao = new javax.swing.JLabel();
        jLabel_diretor = new javax.swing.JLabel();
        jTextField_titulo = new javax.swing.JTextField();
        jTextField_genero = new javax.swing.JTextField();
        jTextField_duracao = new javax.swing.JTextField();
        jComboBox_classificacao = new javax.swing.JComboBox<>();
        jTextField_diretor = new javax.swing.JTextField();
        jCheckBox_filmeAlugado = new javax.swing.JCheckBox();
        jButton_confirma = new javax.swing.JButton();
        jButton_encerrar = new javax.swing.JButton();
        jLabel_tituloAntigo = new javax.swing.JLabel();
        jLabel_studioAntigo = new javax.swing.JLabel();
        jButton_anterior = new javax.swing.JButton();
        jButton_proximo = new javax.swing.JButton();
        jButton_novo = new javax.swing.JButton();
        jMenuBar_arquivo = new javax.swing.JMenuBar();
        jMenu_arquivos = new javax.swing.JMenu();
        jMenu_buscar = new javax.swing.JMenu();
        jMenuItem_titulo = new javax.swing.JMenuItem();
        jMenuItem_genero = new javax.swing.JMenuItem();
        jMenuItem_duracao = new javax.swing.JMenuItem();
        jMenuItem_classifica = new javax.swing.JMenuItem();
        jMenuItem_studio = new javax.swing.JMenuItem();
        jMenuItem_alugado = new javax.swing.JMenuItem();
        jMenuItem_salvar = new javax.swing.JMenuItem();
        jMenuItem_editar = new javax.swing.JMenuItem();
        jMenuItem_excluir = new javax.swing.JMenuItem();
        jMenuItem_fechar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciamento Filmes");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel_titulo.setText("TÍTULO");

        jLabel_genero.setText("GÊNERO");

        jLabel_duracao.setText("DURAÇÃO");

        jLabel_classificacao.setText("CLASSIFICAÇÃO");

        jLabel_diretor.setText("STUDIO");

        jTextField_duracao.setToolTipText("");
        jTextField_duracao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_duracaoFocusLost(evt);
            }
        });
        jTextField_duracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_duracaoActionPerformed(evt);
            }
        });
        jTextField_duracao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_duracaoKeyTyped(evt);
            }
        });

        jComboBox_classificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "10", "12", "14", "16", "18" }));
        jComboBox_classificacao.setMaximumSize(new java.awt.Dimension(216, 216));
        jComboBox_classificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_classificacaoActionPerformed(evt);
            }
        });

        jCheckBox_filmeAlugado.setText("Filme Alugado");

        jButton_confirma.setText("Confirmar Edição");
        jButton_confirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmaActionPerformed(evt);
            }
        });

        jButton_encerrar.setText("Cancelar");
        jButton_encerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_encerrarActionPerformed(evt);
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

        jButton_novo.setText("Novo");
        jButton_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_novoActionPerformed(evt);
            }
        });

        jMenu_arquivos.setText("Arquivos");

        jMenu_buscar.setText("Buscar");

        jMenuItem_titulo.setText("Por Título");
        jMenuItem_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_tituloActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_titulo);

        jMenuItem_genero.setText("Por Gênero");
        jMenuItem_genero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_generoActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_genero);

        jMenuItem_duracao.setText("Por Duração");
        jMenuItem_duracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_duracaoActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_duracao);

        jMenuItem_classifica.setText("Por  Classificação");
        jMenuItem_classifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_classificaActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_classifica);

        jMenuItem_studio.setText("Por Studio");
        jMenuItem_studio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_studioActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_studio);

        jMenuItem_alugado.setText("Por Alugado");
        jMenuItem_alugado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_alugadoActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_alugado);

        jMenu_arquivos.add(jMenu_buscar);

        jMenuItem_salvar.setText("Salvar");
        jMenuItem_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_salvarActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_salvar);

        jMenuItem_editar.setText("Editar");
        jMenuItem_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_editarActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_editar);

        jMenuItem_excluir.setText("Excluir");
        jMenuItem_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_excluirActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_excluir);

        jMenuItem_fechar.setText("Fechar");
        jMenuItem_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_fecharActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_fechar);

        jMenuBar_arquivo.add(jMenu_arquivos);

        setJMenuBar(jMenuBar_arquivo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel_titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_classificacao)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(538, 538, 538)
                                .addComponent(jLabel_tituloAntigo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jComboBox_classificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_studioAntigo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton_anterior)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_proximo, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_novo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton_confirma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_encerrar))
                            .addComponent(jTextField_duracao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_titulo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_genero, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_diretor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel_diretor))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel_duracao))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel_genero))
                            .addComponent(jCheckBox_filmeAlugado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_tituloAntigo)
                    .addComponent(jLabel_studioAntigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_classificacao)
                    .addComponent(jComboBox_classificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel_genero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_diretor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_diretor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_duracao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_duracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_filmeAlugado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_anterior)
                    .addComponent(jButton_proximo)
                    .addComponent(jButton_novo))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_confirma)
                    .addComponent(jButton_encerrar))
                .addGap(36, 36, 36))
        );

        setSize(new java.awt.Dimension(298, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_classificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_classificacaoActionPerformed
        
    }//GEN-LAST:event_jComboBox_classificacaoActionPerformed

    private void jMenuItem_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_fecharActionPerformed
        ViewLocadora loc = new ViewLocadora();
        loc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem_fecharActionPerformed

    private void jMenuItem_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_salvarActionPerformed
        salvarArquivo();
    }//GEN-LAST:event_jMenuItem_salvarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        abrirArquivo();
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_editarActionPerformed
        abilitaEdicao();
    }//GEN-LAST:event_jMenuItem_editarActionPerformed

    private void jButton_encerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_encerrarActionPerformed
        abrirArquivo();
    }//GEN-LAST:event_jButton_encerrarActionPerformed

    private void jButton_confirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmaActionPerformed
        editFilme();
    }//GEN-LAST:event_jButton_confirmaActionPerformed

    private void jMenuItem_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_excluirActionPerformed
        delFilme();
    }//GEN-LAST:event_jMenuItem_excluirActionPerformed

    private void jMenuItem_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_tituloActionPerformed
        buscFilme("titulo");
    }//GEN-LAST:event_jMenuItem_tituloActionPerformed

    private void jMenuItem_generoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_generoActionPerformed
        buscFilme("genero");
    }//GEN-LAST:event_jMenuItem_generoActionPerformed

    private void jMenuItem_duracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_duracaoActionPerformed
       buscFilme("duracao");
    }//GEN-LAST:event_jMenuItem_duracaoActionPerformed

    private void jMenuItem_classificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_classificaActionPerformed
        buscFilme("classificacao");
    }//GEN-LAST:event_jMenuItem_classificaActionPerformed

    private void jMenuItem_studioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_studioActionPerformed
        buscFilme("studio");
    }//GEN-LAST:event_jMenuItem_studioActionPerformed

    private void jMenuItem_alugadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_alugadoActionPerformed
        buscFilme("alugado");
    }//GEN-LAST:event_jMenuItem_alugadoActionPerformed

    private void jTextField_duracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_duracaoActionPerformed
        
    }//GEN-LAST:event_jTextField_duracaoActionPerformed

    private void jTextField_duracaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_duracaoKeyTyped
        
    }//GEN-LAST:event_jTextField_duracaoKeyTyped

    private void jTextField_duracaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_duracaoFocusLost
        
    }//GEN-LAST:event_jTextField_duracaoFocusLost

    private void jButton_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_novoActionPerformed
        abilitaCriacao();
    }//GEN-LAST:event_jButton_novoActionPerformed

    private void jButton_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_proximoActionPerformed
        if(navegaNextFilmes()&&pos == null)
            atribuirCampos(controller.getFilme().get(++posicao));
        
        if(pos != null){
            if(++posicao < pos.size()){
                atribuirCampos(controller.getFilme().get(pos.get(posicao)));
            }else{
                posicao=pos.size()-1;
            }
        }
    }//GEN-LAST:event_jButton_proximoActionPerformed

    private void jButton_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_anteriorActionPerformed
        if(navegaPreviusFilmes()&&pos == null)
            atribuirCampos(controller.getFilme().get(--posicao));
        
        if(pos != null){
            if(--posicao >= 0){
                atribuirCampos(controller.getFilme().get(pos.get(posicao)));
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
            java.util.logging.Logger.getLogger(ViewFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewFilmes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_anterior;
    private javax.swing.JButton jButton_confirma;
    private javax.swing.JButton jButton_encerrar;
    private javax.swing.JButton jButton_novo;
    private javax.swing.JButton jButton_proximo;
    private javax.swing.JCheckBox jCheckBox_filmeAlugado;
    private javax.swing.JComboBox<String> jComboBox_classificacao;
    private javax.swing.JLabel jLabel_classificacao;
    private javax.swing.JLabel jLabel_diretor;
    private javax.swing.JLabel jLabel_duracao;
    private javax.swing.JLabel jLabel_genero;
    private javax.swing.JLabel jLabel_studioAntigo;
    private javax.swing.JLabel jLabel_titulo;
    private javax.swing.JLabel jLabel_tituloAntigo;
    private javax.swing.JMenuBar jMenuBar_arquivo;
    private javax.swing.JMenuItem jMenuItem_alugado;
    private javax.swing.JMenuItem jMenuItem_classifica;
    private javax.swing.JMenuItem jMenuItem_duracao;
    private javax.swing.JMenuItem jMenuItem_editar;
    private javax.swing.JMenuItem jMenuItem_excluir;
    private javax.swing.JMenuItem jMenuItem_fechar;
    private javax.swing.JMenuItem jMenuItem_genero;
    private javax.swing.JMenuItem jMenuItem_salvar;
    private javax.swing.JMenuItem jMenuItem_studio;
    private javax.swing.JMenuItem jMenuItem_titulo;
    private javax.swing.JMenu jMenu_arquivos;
    private javax.swing.JMenu jMenu_buscar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField_diretor;
    private javax.swing.JTextField jTextField_duracao;
    private javax.swing.JTextField jTextField_genero;
    private javax.swing.JTextField jTextField_titulo;
    // End of variables declaration//GEN-END:variables
}
