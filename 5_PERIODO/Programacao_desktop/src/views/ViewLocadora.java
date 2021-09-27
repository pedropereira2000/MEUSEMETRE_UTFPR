/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Filmes;
import model.Locadora;

/**
 *
 * @author pedropereira
 */
public class ViewLocadora extends javax.swing.JFrame {
    //Atributos para manter informações da locacao
    private int posicao = 0;
    private ArrayList<Integer> pos = null;
    private String tituloEditado = null;
    private controller.ControllerLocadora controller = new controller.ControllerLocadora();

    //Função para limpar os campos e váriáveis utilizadas
    public void limparCampos() {
        jButton_encerrar.setVisible(false);
        jButton_confirma.setVisible(false);
        jTextField_tituloFilme.setText("");
        jTextField_studio.setText("");
        jTextField_funcionario.setText("");
        jTextField_valor.setText("");
        jFormattedTextField_rg.setText("");
        jFormattedTextField_data.setText("");
        jCheckBox_devolvido.setSelected(false);
        jComboBox_periodo.setSelectedIndex(0);
        jTextField_tituloFilme.setEnabled(true);
        jTextField_studio.setEnabled(true);
        jTextField_funcionario.setEnabled(true);
        jTextField_valor.setEnabled(true);
        jFormattedTextField_rg.setEnabled(true);
        jFormattedTextField_data.setEnabled(true);
        jCheckBox_devolvido.setEnabled(true);
        jComboBox_periodo.setEnabled(true);
        jButton_novo.setEnabled(true);
        jButton_anterior.setEnabled(true);
        jButton_proximo.setEnabled(true);
        jMenuItem_editar.setEnabled(true);
        jMenuItem_Excluir.setEnabled(true);
        jMenu_buscar.setEnabled(true);
        jMenuItem_Salvar.setEnabled(false);
        jTextField_tituloFilme.requestFocus();
        pos = null;
        posicao = 0;
    }

    //Função utilizada para configurar layout de botões e campos de forma a colocar as informações da locacao
    public void atribuirCampos(Locadora locadora) {
        jTextField_funcionario.setText(locadora.getFuncionario());
        jTextField_tituloFilme.setText(locadora.getTituloFilme());
        jTextField_studio.setText(locadora.getStudioFilme());
        jFormattedTextField_rg.setText(locadora.getRgCliente());
        jFormattedTextField_data.setText(locadora.getData());
        jComboBox_periodo.setSelectedItem(locadora.getPeriodoAlugado());
        jTextField_valor.setText(Float.toString(locadora.getValor()));
        if (locadora.getDevolvido()) {
            jCheckBox_devolvido.setSelected(true);
        } else {
            jCheckBox_devolvido.setSelected(false);
        }
        jMenuItem_Salvar.setEnabled(false);
        jTextField_tituloFilme.requestFocus();
    }

    //Função para cliar uma nova locacao e chama função da classe controller para atribuir a locacao no sistema
    public void criaLocadora() {
        boolean op = false;
        if (jCheckBox_devolvido.isSelected()) {
            op = true;
        }
        Locadora locadora = new Locadora(
                jTextField_funcionario.getText(),
                jTextField_tituloFilme.getText(),
                jTextField_studio.getText(),
                jFormattedTextField_rg.getText(),
                jFormattedTextField_data.getText(),
                Integer.parseInt(jComboBox_periodo.getSelectedItem().toString()),
                Float.parseFloat(jTextField_valor.getText().toString()),
                op
        );
        controller.setLocadora(locadora);
    }

    //Função para validar a presença de conteúdos nos campos
    public boolean validaCampos(String obj, String tipo) {
        try{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date atual = new Date();
            Date cad = formato.parse(jFormattedTextField_data.getText());
            
            //Validando se os campos foram preenchidos(para a criacao de uma nova locacao)
            if (obj.equals("") && tipo.equals("")) {
                if ((jTextField_funcionario.getText()).equals("")) {
                    return false;
                } else if ((jTextField_tituloFilme.getText()).equals("")) {
                    return false;
                } else if ((jTextField_studio.getText()).equals("")) {
                    return false;
                } else if (jFormattedTextField_rg.getText().equals("")) {
                    return false;
                } else if (jFormattedTextField_data.getText().equals("")){
                    return false;
                //Se data cadastrada for antes da data atual retorna false    
                }else if(cad.before(atual)){
                    return false;
                //Se data cadastrada for depois da atual
                }else if(cad.after(atual)){
                    //Se a diferença da data cadastrada e da atual for maior que 3 meses retorna false
                    if((((((cad.getTime()-atual.getTime())/1000)/60)/60)/24)>93)
                        return false;
                } else if (jTextField_valor.getText().equals("")) {
                    return false;
                }
            //Validação da edição dos campos
            } else {
                if (obj.equals("devolvido")) {
                    if (!tipo.equals("FALSE") && !tipo.equals("TRUE")) {
                        return false;
                    }
                }
                if (obj.equals("periodo")) {
                    if (tipo.substring(0).matches("[A-Z]*") || tipo.substring(0).matches("[a-z]*") || (tipo != "1" && tipo != "2" && tipo != "3")) {
                        return false;
                    }
                }
                if (obj.equals("valor")) {
                    if (tipo.substring(0).matches("[A-Z]*") || tipo.substring(0).matches("[a-z]*")) {
                        return false;
                    }
                }
                if (obj.equals("rg")) {
                    if (tipo.substring(0).matches("[A-Z]*") || tipo.substring(0).matches("[a-z]*")) {
                        return false;
                    }
                }
                if (obj.equals("data")) {
                    if (tipo.substring(0).matches("[A-Z]*") || tipo.substring(0).matches("[a-z]*")) {
                        return false;
                    //Se data cadastrada for antes da data atual retorna false    
                    }else if(cad.before(atual)){
                        return false;
                    //Se data cadastrada for depois da atual
                    }else if(cad.after(atual)){
                        //Se a diferença da data cadastrada e da atual for maior que 3 meses retorna false
                        if((((((cad.getTime()-atual.getTime())/1000)/60)/60)/24)>93)
                            return false;
                    }
                }

            }
            
        }catch (ParseException err){
            System.err.println(err.getMessage());
        }
        return true;
    }

    //Função para passar para a proxima locacao
    public boolean navegaNextLocadora() {
        int cont = posicao;
        if (++cont >= controller.getLocadora().size()) {
            return false;
        }
        return true;
    }

    //Função para voltar para a locacao anterior
    public boolean navegaPreviusLocadora() {
        int cont = posicao;
        if (--cont < 0) {
            return false;
        }
        return true;
    }

    //Função para salvar as locacoes do sistema no arquivo de locacoes
    public boolean salvarArquivo() {
        controller.setArquivo("locadoras");
        //Verificando se o arquivo existe
        if (controller.getArquivo() != null) {
            //Verificando se é a primeira locação cadastrada
            if (controller.getLocadora().isEmpty()) {
                //Chamando validação de campos vazios
                if (validaCampos("", "")) {
                    criaLocadora();
                    //Chamando validação da controller para verificar se o filme já está alugado
                    if (controller.validaLocadora()) {
                        JOptionPane.showMessageDialog(this, "Locacao cadastrada com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        controller.escrever(false);
                        abrirArquivo();
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Titulo já está alugado!!", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Campos não preenchidos, ou preenchido incorretamente\nALERTA a data cadastrada deve ser de no máximo 3 meses a frente da atual\nE não pode ser uma data antes da atual", "ERROR", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            //Se já houver alguma locação
            } else {
                //Validando campos vazios
                if (validaCampos("", "")) {
                    criaLocadora();
                    //Chamando validação da controller para verificar se o filme já está alugado
                    if (controller.validaLocadora()) {
                        JOptionPane.showMessageDialog(this, "Locacao cadastrada com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        controller.escrever(true);
                        String titulo = jTextField_tituloFilme.getText();
                        String rg = jFormattedTextField_rg.getText();
                        String data = jFormattedTextField_data.getText();
                        abrirArquivo();
                        posicao = controller.encontraLocadora(titulo, rg, data);
                        atribuirCampos(controller.getLocadora().get(posicao));
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Filme já foi alugado", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Campos não preenchidos, ou preenchido incorretamente\n*ALERTA a data cadastrada deve ser de no máximo 3 meses a frente da atual\n*E não pode ser uma data antes da atual", "ERROR", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
        return false;
    }

    //Função para configurar o layout de botões e campos para cadastrar uma nova locacao
    public void abilitaCriacao() {
        limparCampos();
        jMenuItem_editar.setEnabled(false);
        jMenuItem_Excluir.setEnabled(false);
        jMenu_buscar.setEnabled(false);
        jButton_anterior.setEnabled(false);
        jButton_proximo.setEnabled(false);
        jButton_novo.setEnabled(false);
        jMenuItem_devolucao.setEnabled(false);
        jMenuItem_Salvar.setEnabled(true);
        if (controller.getLocadora().size() > 0) {
            jButton_encerrar.setVisible(true);
            jMenuItem_devolucao.setEnabled(false);
        }
        jTextField_funcionario.requestFocus();
    }

    //Função para configurar o layout de botões e campos para editar uma locacao
    public void abilitaEdicao() {
        jButton_confirma.setVisible(true);
        jButton_encerrar.setVisible(true);
        jButton_anterior.setEnabled(false);
        jButton_proximo.setEnabled(false);
        jButton_novo.setEnabled(false);
        jTextField_funcionario.setEnabled(true);
        jTextField_tituloFilme.setEnabled(true);
        jTextField_studio.setEnabled(true);
        jComboBox_periodo.setEnabled(true);
        jTextField_valor.setEnabled(true);
        jCheckBox_devolvido.setEnabled(true);
        jMenu_buscar.setEnabled(false);
        jMenuItem_editar.setEnabled(false);
        jMenuItem_Excluir.setEnabled(false);
        jMenuItem_devolucao.setEnabled(false);
        tituloEditado = jTextField_tituloFilme.getText();
    }

    //Função para aplicar a alteração do cadastro da locacao
    public boolean editLocadora() {
        //Pegunta se o usuário deseja realmente editar as informações daquela locacao
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente validar a edição da locacao", "INFORMATION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try{
                Locadora locadora = controller.getLocadora().get(controller.encontraLocadora(tituloEditado, jFormattedTextField_rg.getText(), jFormattedTextField_data.getText()));
                locadora.setFuncionario(jTextField_funcionario.getText());
                locadora.setTituloFilme(jTextField_tituloFilme.getText());
                locadora.setStudioFilme(jTextField_studio.getText());
                locadora.setRgCliente(jFormattedTextField_rg.getText());
                locadora.setData(jFormattedTextField_data.getText());
                locadora.setPeriodoAlugado(Integer.parseInt(jComboBox_periodo.getSelectedItem().toString()));
                locadora.setValor(Float.parseFloat(jTextField_valor.getText()));
                if (jCheckBox_devolvido.isSelected()) {
                    locadora.setDevolvido(true);
                } else {
                    locadora.setDevolvido(false);
                }
                controller.editarLocadora(locadora);
                String titulo = jTextField_tituloFilme.getText();
                String rg = jFormattedTextField_rg.getText();
                String data = jFormattedTextField_data.getText();
                abrirArquivo();
                posicao = controller.encontraLocadora(titulo, rg, data);
                atribuirCampos(controller.getLocadora().get(posicao));
            }catch(ArrayIndexOutOfBoundsException err){
                System.err.println(err.getMessage()+ " não localizou a locacao");
            }
            
            return true;
        } else {
            return false;
        }
    }

    //Função para deletar uma locacao
    public boolean delLocadora() {
        boolean op = false;
        if (jCheckBox_devolvido.isSelected()) {
            op = true;
        } else {
            op = false;
        }
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o: "
                + "\nfuncionario: " + jTextField_funcionario.getText()
                + "\ntitulo: " + jTextField_tituloFilme.getText()
                + "\nstudio: " + jTextField_studio.getText()
                + "\nrg-cli: " + jFormattedTextField_rg.getText()
                + "\ndata: " + jFormattedTextField_data.getText()
                + "\nperiodo: " + jComboBox_periodo.getSelectedItem().toString()
                + "\nvalor: " + jTextField_valor.getText()
                + "\ndevolvido: " + op, "INFORMATION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Locadora locadora = controller.getLocadora().get(controller.encontraLocadora(jTextField_tituloFilme.getText(), jFormattedTextField_rg.getText(), jFormattedTextField_data.getText()));
            locadora.setFuncionario(jTextField_funcionario.getText());
            locadora.setTituloFilme(jTextField_tituloFilme.getText());
            locadora.setStudioFilme(jTextField_studio.getText());
            locadora.setRgCliente(jFormattedTextField_rg.getText());
            locadora.setData(jFormattedTextField_data.getText());
            locadora.setPeriodoAlugado(Integer.parseInt(jComboBox_periodo.getSelectedItem().toString()));
            locadora.setValor(Float.parseFloat(jTextField_valor.getText()));
            locadora.setDevolvido(op);
            controller.excluiLocadora(locadora);
            //if(controller.getCliente().size()==0){
            //limparCampos();
            //abilitaCriacao();
            //}else{
            posicao = 0;
            abrirArquivo();
            //}
            return true;
        } else {
            return false;
        }
    }
    
    //Funcao para colocar um filme como devolvido
    public boolean setDevoluc() {
        Locadora locadora = controller.getLocadora().get(controller.encontraLocadora(jTextField_tituloFilme.getText(), jFormattedTextField_rg.getText(), jFormattedTextField_data.getText()));
        if(controller.setDevolucao(locadora))
            abrirArquivo();
        return true;
    }

    //Função para realizar a busca de locacoes por um determinado parâmetro (funcionario, titulo-filme, studio-filme rg-cliente, data, periodo-reservado, valor e devolvido)
    public boolean buscLocadora(String tipo) {
        posicao = 0;
        try {
            if (tipo.equals("funcionario")) {
                pos = controller.buscarLocadora(JOptionPane.showInputDialog(this, "Informe o nome do FUNCIONARIO que alugou o filme", "buscando locacao", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getLocadora().get(pos.get(0)));
                jButton_encerrar.setVisible(true);
                jButton_novo.setEnabled(false);
            }

            if (tipo.equals("titulo")) {
                pos = controller.buscarLocadora(JOptionPane.showInputDialog(this, "Informe o TITULO do filme alugado", "buscando locacao", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getLocadora().get(pos.get(0)));
                jButton_encerrar.setVisible(true);
                jButton_novo.setEnabled(false);
            }

            if (tipo.equals("studio")) {
                pos = controller.buscarLocadora(JOptionPane.showInputDialog(this, "Informe o STUDIO do filme alugado", "buscando locacao", JOptionPane.INFORMATION_MESSAGE), tipo);
                atribuirCampos(controller.getLocadora().get(pos.get(0)));
                jButton_encerrar.setVisible(true);
                jButton_novo.setEnabled(false);
            }

            if (tipo.equals("rg")) {
                String rg = JOptionPane.showInputDialog(this, "Informe o RG do cliente", "buscando locacao", JOptionPane.INFORMATION_MESSAGE);
                if (validaCampos("rg", rg)) {
                    jFormattedTextField_rg.setText(rg);
                    pos = controller.buscarLocadora(jFormattedTextField_rg.getText(), tipo);
                    atribuirCampos(controller.getLocadora().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }

            if (tipo.equals("data")) {
                String data = JOptionPane.showInputDialog(this, "Informe a DATA da locacao", "buscando locacao", JOptionPane.INFORMATION_MESSAGE);
                if (validaCampos("data", data)) {
                    jFormattedTextField_data.setText(data);
                    pos = controller.buscarLocadora(jFormattedTextField_data.getText(), tipo);
                    atribuirCampos(controller.getLocadora().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }

            if (tipo.equals("periodo")) {
                String periodo = JOptionPane.showInputDialog(this, "Informe o PERIODO de locacao", "buscando locacao", JOptionPane.INFORMATION_MESSAGE);
                if (validaCampos("periodo", periodo)) {
                    pos = controller.buscarLocadora(periodo, tipo);
                    atribuirCampos(controller.getLocadora().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }

            if (tipo.equals("valor")) {
                String valor = JOptionPane.showInputDialog(this, "Informe o VALOR do locacao", "buscando locacao", JOptionPane.INFORMATION_MESSAGE);
                if (validaCampos("valor", valor)) {
                    pos = controller.buscarLocadora(valor, tipo);
                    atribuirCampos(controller.getLocadora().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }

            if (tipo.equals("devolucao")) {
                String verif = JOptionPane.showInputDialog(this, "Informe se foi feita a DEVOLUCAO da locacao", "buscando locacao", JOptionPane.INFORMATION_MESSAGE);
                if (validaCampos("devolvido", verif.toUpperCase())) {
                    pos = controller.buscarLocadora(verif.toLowerCase(), tipo);
                    atribuirCampos(controller.getLocadora().get(pos.get(0)));
                    jButton_encerrar.setVisible(true);
                    jButton_novo.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "O valor informado está incorreto", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IndexOutOfBoundsException err) {
            System.err.println(" Não encontrou locacao compátiveis");
            abrirArquivo();
        } catch (NumberFormatException err) {
            System.err.println(" Não foi inserido numeros");
        } catch(NullPointerException err){
            System.err.println(" Busca cancelada");
        }

        return true;
    }

    //Função para abrir e carregar as locacoes
    public boolean abrirArquivo() {
        limparCampos();
        controller.setArquivo("locadoras");
        if (controller.getArquivo() != null) {
            if (controller.ler()) {
                //Se já houverem locacoes cadastradas
                if (controller.getLocadora().size() > 0) {
                    atribuirCampos(controller.getLocadora().get(posicao));
                    jTextField_funcionario.setEnabled(false);
                    jTextField_tituloFilme.setEnabled(false);
                    jTextField_studio.setEnabled(false);
                    jFormattedTextField_rg.setEnabled(false);
                    jFormattedTextField_data.setEnabled(false);
                    jComboBox_periodo.setEnabled(false);
                    jTextField_valor.setEnabled(false);
                    jCheckBox_devolvido.setEnabled(false);
                    jMenuItem_devolucao.setEnabled(true);
                //Se não houverem locacoes cadastradas inicia em mode de cadastro
                } else {
                    abilitaCriacao();
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Creates new form Cliente
     */
    public ViewLocadora() {
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
        jComboBox_periodo = new javax.swing.JComboBox<>();
        jLabel_studio = new javax.swing.JLabel();
        jTextField_studio = new javax.swing.JTextField();
        jFormattedTextField_rg = new javax.swing.JFormattedTextField();
        jButton_encerrar = new javax.swing.JButton();
        jButton_confirma = new javax.swing.JButton();
        jLabel_tituloAntigo = new javax.swing.JLabel();
        jLabel_studioAntigo = new javax.swing.JLabel();
        jLabel_dataAntiga = new javax.swing.JLabel();
        jButton_anterior = new javax.swing.JButton();
        jButton_proximo = new javax.swing.JButton();
        jButton_novo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_arquivos = new javax.swing.JMenu();
        jMenu_buscar = new javax.swing.JMenu();
        jMenuItem_func = new javax.swing.JMenuItem();
        jMenuItem_titulo = new javax.swing.JMenuItem();
        jMenuItem_studio = new javax.swing.JMenuItem();
        jMenuItem_rg = new javax.swing.JMenuItem();
        jMenuItem_data = new javax.swing.JMenuItem();
        jMenuItem_valor = new javax.swing.JMenuItem();
        jMenuItem_ordDevolucao = new javax.swing.JMenuItem();
        jMenuItem_Salvar = new javax.swing.JMenuItem();
        jMenuItem_devolucao = new javax.swing.JMenuItem();
        jMenuItem_editar = new javax.swing.JMenuItem();
        jMenuItem_Excluir = new javax.swing.JMenuItem();
        jMenuItem_fechar = new javax.swing.JMenuItem();
        jMenu_cliente = new javax.swing.JMenu();
        jMenuItem_abrirCli = new javax.swing.JMenuItem();
        jMenu_catalogo = new javax.swing.JMenu();
        jMenuItem_abrirCatalogo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alugar Filmes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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

        jButton_encerrar.setText("Cancelar");
        jButton_encerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_encerrarActionPerformed(evt);
            }
        });

        jButton_confirma.setText("Confirmar Edição");
        jButton_confirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmaActionPerformed(evt);
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

        jMenuItem_func.setText("Por Funcionário");
        jMenuItem_func.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_funcActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_func);

        jMenuItem_titulo.setText("Por Titulo");
        jMenuItem_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_tituloActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_titulo);

        jMenuItem_studio.setText("Por Studio");
        jMenuItem_studio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_studioActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_studio);

        jMenuItem_rg.setText("Por RG");
        jMenuItem_rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_rgActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_rg);

        jMenuItem_data.setText("Por Data");
        jMenuItem_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_dataActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_data);

        jMenuItem_valor.setText("Por Valor");
        jMenuItem_valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_valorActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_valor);

        jMenuItem_ordDevolucao.setText("Por Devolução");
        jMenuItem_ordDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ordDevolucaoActionPerformed(evt);
            }
        });
        jMenu_buscar.add(jMenuItem_ordDevolucao);

        jMenu_arquivos.add(jMenu_buscar);

        jMenuItem_Salvar.setText("Salvar");
        jMenuItem_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SalvarActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_Salvar);

        jMenuItem_devolucao.setText("Devolução");
        jMenuItem_devolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_devolucaoActionPerformed(evt);
            }
        });
        jMenu_arquivos.add(jMenuItem_devolucao);

        jMenuItem_editar.setText("Editar");
        jMenuItem_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_editarActionPerformed(evt);
            }
        });
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
                        .addGap(109, 109, 109)
                        .addComponent(jLabel_nomeCli))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel_data)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(519, 519, 519)
                        .addComponent(jLabel_tituloAntigo)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel_studioAntigo)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel_dataAntiga))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextField_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_studio, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField_rg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_tituloFilme, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton_confirma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_encerrar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox_devolvido)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton_proximo)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_novo))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel_funcionario))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel_tituloFilme))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel_studio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel_periodo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jFormattedTextField_data, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel_valor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jComboBox_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_funcionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_tituloFilme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_tituloFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_tituloAntigo)
                    .addComponent(jLabel_studioAntigo)
                    .addComponent(jLabel_dataAntiga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_studio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_studio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_nomeCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_data)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextField_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel_periodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_valor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_devolvido)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_anterior)
                    .addComponent(jButton_proximo)
                    .addComponent(jButton_novo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_confirma)
                    .addComponent(jButton_encerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(267, 620));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SalvarActionPerformed
        salvarArquivo();
    }//GEN-LAST:event_jMenuItem_SalvarActionPerformed

    private void jMenuItem_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_fecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem_fecharActionPerformed

    private void jMenu_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_clienteActionPerformed
    }//GEN-LAST:event_jMenu_clienteActionPerformed

    private void jMenu_catalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_catalogoActionPerformed

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
        delLocadora();
    }//GEN-LAST:event_jMenuItem_ExcluirActionPerformed

    private void jMenuItem_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_editarActionPerformed
        abilitaEdicao();
    }//GEN-LAST:event_jMenuItem_editarActionPerformed

    private void jButton_encerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_encerrarActionPerformed
        abrirArquivo();
    }//GEN-LAST:event_jButton_encerrarActionPerformed

    private void jButton_confirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmaActionPerformed
        editLocadora();
    }//GEN-LAST:event_jButton_confirmaActionPerformed

    private void jMenuItem_devolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_devolucaoActionPerformed
        setDevoluc();
    }//GEN-LAST:event_jMenuItem_devolucaoActionPerformed

    private void jMenuItem_funcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_funcActionPerformed
        buscLocadora("funcionario");
    }//GEN-LAST:event_jMenuItem_funcActionPerformed

    private void jMenuItem_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_tituloActionPerformed
        buscLocadora("titulo");
    }//GEN-LAST:event_jMenuItem_tituloActionPerformed

    private void jMenuItem_studioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_studioActionPerformed
        buscLocadora("studio");
    }//GEN-LAST:event_jMenuItem_studioActionPerformed

    private void jMenuItem_rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_rgActionPerformed
        buscLocadora("rg");
    }//GEN-LAST:event_jMenuItem_rgActionPerformed

    private void jMenuItem_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_dataActionPerformed
        buscLocadora("data");
    }//GEN-LAST:event_jMenuItem_dataActionPerformed

    private void jMenuItem_valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_valorActionPerformed
        buscLocadora("valor");
    }//GEN-LAST:event_jMenuItem_valorActionPerformed

    private void jMenuItem_ordDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ordDevolucaoActionPerformed
        buscLocadora("devolucao");
    }//GEN-LAST:event_jMenuItem_ordDevolucaoActionPerformed

    private void jButton_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_proximoActionPerformed
        if (navegaNextLocadora() && pos == null) {
            atribuirCampos(controller.getLocadora().get(++posicao));
        }

        if (pos != null) {
            if (++posicao < pos.size()) {
                atribuirCampos(controller.getLocadora().get(pos.get(posicao)));
            } else {
                posicao = pos.size() - 1;
            }
        }
    }//GEN-LAST:event_jButton_proximoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        abrirArquivo();
    }//GEN-LAST:event_formWindowOpened

    private void jButton_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_novoActionPerformed
        abilitaCriacao();
    }//GEN-LAST:event_jButton_novoActionPerformed

    private void jButton_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_anteriorActionPerformed
        if (navegaPreviusLocadora() && pos == null) {
            atribuirCampos(controller.getLocadora().get(--posicao));
        }

        if (pos != null) {
            if (--posicao >= 0) {
                atribuirCampos(controller.getLocadora().get(pos.get(posicao)));
            } else {
                posicao = 0;
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
    private javax.swing.JButton jButton_anterior;
    private javax.swing.JButton jButton_confirma;
    private javax.swing.JButton jButton_encerrar;
    private javax.swing.JButton jButton_novo;
    private javax.swing.JButton jButton_proximo;
    private javax.swing.JCheckBox jCheckBox_devolvido;
    private javax.swing.JComboBox<String> jComboBox_periodo;
    private javax.swing.JFormattedTextField jFormattedTextField_data;
    private javax.swing.JFormattedTextField jFormattedTextField_rg;
    private javax.swing.JLabel jLabel_data;
    private javax.swing.JLabel jLabel_dataAntiga;
    private javax.swing.JLabel jLabel_funcionario;
    private javax.swing.JLabel jLabel_nomeCli;
    private javax.swing.JLabel jLabel_periodo;
    private javax.swing.JLabel jLabel_studio;
    private javax.swing.JLabel jLabel_studioAntigo;
    private javax.swing.JLabel jLabel_tituloAntigo;
    private javax.swing.JLabel jLabel_tituloFilme;
    private javax.swing.JLabel jLabel_valor;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_Excluir;
    private javax.swing.JMenuItem jMenuItem_Salvar;
    private javax.swing.JMenuItem jMenuItem_abrirCatalogo;
    private javax.swing.JMenuItem jMenuItem_abrirCli;
    private javax.swing.JMenuItem jMenuItem_data;
    private javax.swing.JMenuItem jMenuItem_devolucao;
    private javax.swing.JMenuItem jMenuItem_editar;
    private javax.swing.JMenuItem jMenuItem_fechar;
    private javax.swing.JMenuItem jMenuItem_func;
    private javax.swing.JMenuItem jMenuItem_ordDevolucao;
    private javax.swing.JMenuItem jMenuItem_rg;
    private javax.swing.JMenuItem jMenuItem_studio;
    private javax.swing.JMenuItem jMenuItem_titulo;
    private javax.swing.JMenuItem jMenuItem_valor;
    private javax.swing.JMenu jMenu_arquivos;
    private javax.swing.JMenu jMenu_buscar;
    private javax.swing.JMenu jMenu_catalogo;
    private javax.swing.JMenu jMenu_cliente;
    private javax.swing.JTextField jTextField_funcionario;
    private javax.swing.JTextField jTextField_studio;
    private javax.swing.JTextField jTextField_tituloFilme;
    private javax.swing.JTextField jTextField_valor;
    // End of variables declaration//GEN-END:variables
}
