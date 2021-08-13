/*
 * @author  fabricio@utfpr.edu.br
 */
//package bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class AcessaBD extends javax.swing.JFrame {
    
    private Connection connection = null;
    private Statement stdados = null;
    private ResultSet rsdados = null;

    public AcessaBD() {
        initComponents();
        
    }
    
    public AcessaBD(int i) {
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        labelid = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        labelnome = new javax.swing.JLabel();
        labelidade = new javax.swing.JLabel();
        labelemail = new javax.swing.JLabel();
        labelfone = new javax.swing.JLabel();
        btn_primeiro = new javax.swing.JButton();
        btn_proximo = new javax.swing.JButton();
        btn_anterior = new javax.swing.JButton();
        btn_ultimo = new javax.swing.JButton();
        btn_abrebd = new javax.swing.JButton();
        btn_executaconsulta = new javax.swing.JButton();
        btn_exibetabela = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_commit = new javax.swing.JButton();
        btn_rollback = new javax.swing.JButton();
        scroller = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea1 = new javax.swing.JTextArea();

        setTitle("Acessando Banco de Dados");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(null);

        label1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label1.setText("Id:");
        getContentPane().add(label1);
        label1.setBounds(10, 80, 60, 20);

        labelid.setName("labelid"); // NOI18N
        getContentPane().add(labelid);
        labelid.setBounds(80, 80, 100, 20);

        label3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label3.setText("nome:");
        getContentPane().add(label3);
        label3.setBounds(4, 110, 70, 20);

        label4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label4.setText("Idade:");
        getContentPane().add(label4);
        label4.setBounds(2, 140, 70, 20);

        label5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label5.setText("fone:");
        getContentPane().add(label5);
        label5.setBounds(407, 140, 60, 20);

        label6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label6.setText("e-mail:");
        getContentPane().add(label6);
        label6.setBounds(7, 170, 70, 20);

        labelnome.setName("labelnome"); // NOI18N
        getContentPane().add(labelnome);
        labelnome.setBounds(80, 110, 320, 20);

        labelidade.setName("labelidade"); // NOI18N
        getContentPane().add(labelidade);
        labelidade.setBounds(80, 140, 340, 20);

        labelemail.setName("labelemail"); // NOI18N
        getContentPane().add(labelemail);
        labelemail.setBounds(80, 170, 270, 20);

        labelfone.setName("labelfone"); // NOI18N
        getContentPane().add(labelfone);
        labelfone.setBounds(470, 140, 150, 20);

        btn_primeiro.setLabel("Primeiro");
        btn_primeiro.setName("btnprimeiro"); // NOI18N
        btn_primeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_primeiroActionPerformed(evt);
            }
        });
        getContentPane().add(btn_primeiro);
        btn_primeiro.setBounds(140, 40, 110, 29);

        btn_proximo.setLabel("Próximo");
        btn_proximo.setName("btnproximo"); // NOI18N
        btn_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proximoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_proximo);
        btn_proximo.setBounds(360, 40, 110, 29);

        btn_anterior.setLabel("Anterior");
        btn_anterior.setName("btnanterior"); // NOI18N
        btn_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anteriorActionPerformed(evt);
            }
        });
        getContentPane().add(btn_anterior);
        btn_anterior.setBounds(250, 40, 110, 29);

        btn_ultimo.setLabel("Último");
        btn_ultimo.setName("btnultimo"); // NOI18N
        btn_ultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ultimoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ultimo);
        btn_ultimo.setBounds(470, 40, 100, 29);

        btn_abrebd.setLabel("Abre BD");
        btn_abrebd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrebdActionPerformed(evt);
            }
        });
        getContentPane().add(btn_abrebd);
        btn_abrebd.setBounds(10, 280, 90, 29);

        btn_executaconsulta.setLabel("Executa Consulta");
        btn_executaconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_executaconsultaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_executaconsulta);
        btn_executaconsulta.setBounds(540, 280, 180, 29);

        btn_exibetabela.setText("exibe tabela");
        btn_exibetabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exibetabelaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_exibetabela);
        btn_exibetabela.setBounds(100, 280, 140, 29);

        btn_update.setLabel("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update);
        btn_update.setBounds(240, 280, 90, 29);

        btn_commit.setLabel("Commit");
        btn_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_commitActionPerformed(evt);
            }
        });
        getContentPane().add(btn_commit);
        btn_commit.setBounds(330, 280, 100, 29);

        btn_rollback.setLabel("Rollback");
        btn_rollback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rollbackActionPerformed(evt);
            }
        });
        getContentPane().add(btn_rollback);
        btn_rollback.setBounds(430, 280, 110, 29);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scroller.setViewportView(tabela);

        getContentPane().add(scroller);
        scroller.setBounds(10, 320, 710, 180);

        textArea1.setText("SELECT * from clientes order by idade");
        jScrollPane1.setViewportView(textArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 200, 710, 60);

        setSize(new java.awt.Dimension(734, 536));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_rollbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rollbackActionPerformed
        try {
            connection.rollback();
        } catch (SQLException erro) {
            System.out.println("Erro Roolback = " + erro);
        }
    }//GEN-LAST:event_btn_rollbackActionPerformed
    
    private void btn_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_commitActionPerformed
        try {
            connection.commit();
        } catch (SQLException erro) {
            System.out.println("Erro commit = " + erro);
        }
    }//GEN-LAST:event_btn_commitActionPerformed
    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        ExecutaUpdate();
    }//GEN-LAST:event_btn_updateActionPerformed
    
    private void btn_exibetabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exibetabelaActionPerformed
        try {
            ExibeTabela();
            MovPrimeiro();
        } catch (SQLException erro) {
            System.out.println("Erro Exibe Tabela = " + erro);
        }
    }//GEN-LAST:event_btn_exibetabelaActionPerformed
    
    private void btn_executaconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_executaconsultaActionPerformed
        ExecutaQuery();
    }//GEN-LAST:event_btn_executaconsultaActionPerformed
    
    private void btn_abrebdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abrebdActionPerformed
        if (AcessaBD()) {
            ExecutaQuery();
        }
    }//GEN-LAST:event_btn_abrebdActionPerformed
    
    private void btn_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anteriorActionPerformed
        MovAnterior();
    }//GEN-LAST:event_btn_anteriorActionPerformed
    
    private void btn_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proximoActionPerformed
        MovProximo();
    }//GEN-LAST:event_btn_proximoActionPerformed
    
    private void btn_ultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ultimoActionPerformed
        MovUltimo();
    }//GEN-LAST:event_btn_ultimoActionPerformed
    
    private void btn_primeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_primeiroActionPerformed
        MovPrimeiro();
    }//GEN-LAST:event_btn_primeiroActionPerformed
    
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        Sair();
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    public void MovProximo() {
        try {
            if (rsdados != null) {
                if (!rsdados.isLast()) {
                    rsdados.next();
                    ExibeRegistro();
                } else {
                    JOptionPane.showMessageDialog(this, "Nao existe proximo elemento.");
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
    }
    
    public void MovAnterior() {
        try {
            if (rsdados != null) {
                if (!rsdados.isFirst()) {
                    rsdados.previous();
                    ExibeRegistro();
                } else {
                    JOptionPane.showMessageDialog(this, "Nao existe registro anterior.");
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
    }
    
    public void MovUltimo() {
        try {
            if (rsdados != null) {
                if (!rsdados.isLast()) {
                    rsdados.last();
                    ExibeRegistro();
                } else {
                    JOptionPane.showMessageDialog(this, "O ultimo registro ja esta selecionado.");
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
    }
    
    public void MovPrimeiro() {
        try {
            if (rsdados != null) {
                if (!rsdados.isFirst()) {
                    rsdados.first();
                    ExibeRegistro();
                } else {
                    JOptionPane.showMessageDialog(this, "O primeiro registro ja esta selecionado.");
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
    }
    
    public void ExibeRegistro() {
        try {
            //faz a leitura do registro corrento do ResutSet e atribui os valores lidos aos objetos visuais (Textfields)
            labelid.setText(String.valueOf(
                    rsdados.getInt("id")
            ));
            labelnome.setText(
                    rsdados.getString("nome")
            );
            labelidade.setText(String.valueOf(rsdados.getInt("idade")));
            labelfone.setText(rsdados.getString("fone"));
            labelemail.setText(rsdados.getString("email"));
        } catch (SQLException erro) {
            System.out.println(erro);
        }
    }
    
    public boolean AcessaBD() {
        try {
            String usuario = "postgres";
            String senha = "123456";
            
            Class.forName("org.postgresql.Driver"); //para acesso ao banco de dados Postgres
            String urlconexao = "jdbc:postgresql://localhost:5432/postgres"; //para acesso ao banco de dados postgres, usando o banco de dados PostgreSQL.

            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//ODBC DRIVER
            //String urlconexao = "jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ="+arquivo.getAbsolutePath(); //para criar o DSN em tempo de execucao do Access
            //ou
            //Class.forName("org.hsqldb.jdbcDriver");//hypersonicSQL DRIVER
            //String urlconexao = "jdbc:hsqldb:C:/dados/dbexper";//para acesso ao banco dbexper usando o banco HypersonicSQL (http://www.hsqldb.org/)
            //ou
            //Class.forName("com.mysql.jdbc.Driver");//MySQL DRIVER
            //String urlconexao = "jdbc:mysql://localhost:3306/meubd";//para acesso ao banco meudb (https://www.mysql.com/)
            //ou ...
            
            connection = DriverManager.getConnection(
                    urlconexao, 
                    usuario, 
                    senha
            );
            
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.

        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC/ODBC." + erro);
            return false;
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
            return false;
        }
        return true;
    }
    
    public void ExecutaUpdate() {
        try {
            String querydados = textArea1.getText();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;//(c)
            //a) ResultSet.TYPE_FORWARD_ONLY = O conjunto de dados nao eh rolavel, isto e, soh anda para frente uma unica vez.
            //b) ResultSet.TYPE_SCROLL_INSENSITIVE = O conjunto de dados eh rolavel, mas nao eh sensivel as alteracoes do banco de dados.
            //c) ResultSet.TYPE_SCROLL_SENSITIVE = O conjunto de dados eh rolavel sendo sensivel as alteracoes do banco de dados.

            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            //ResultSet.CONCUR_READ_ONLY => O conjunto de resultados nao pode ser usado para atualizar o banco de dados.
            //ResultSet.CONCUR_UPDATABLE tabela => O conjunto de resultados pode ser usado para atualizar o banco de dados.

            //stdados.execute("SET SEARCH_PATH TO nome_esquema");//necessario para selecionar o esquema no banco de dados Postgre.
            //ou se utiliza o nome do esquema.nomedatabela

            stdados = connection.createStatement(tipo, concorrencia);
            //connection.prepareStatement(sql)
            int resposta = stdados.executeUpdate(querydados);//DML
            //boolean resposta2 = stdados.execute(querydados);//DDL
            //rsdados = stdados.executeQuery(querydados);//DQL
            System.out.println("Resposta do Update = " + resposta);
            
        } catch (SQLException erro) {
            System.out.println("Erro Executa Update = " + erro);
        }
    }
    
    public void ExecutaQuery() {
        try {
            String querydados = textArea1.getText();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            //ResultSet.TYPE_FORWARD_ONLY = O conjunto de dados é rolável apenas para frente uma única vez.
            //ResultSet.TYPE_SCROLL_INSENSITIVE = O conjunto de dados é rolável, mas nao é sensível às alterações realizadas por outros no banco de dados.
            //ResultSet.TYPE_SCROLL_SENSITIVE = O conjunto de dados é rolável, sendo sensível às alterações realizadas por outros no banco de dados.
            int concorrencia = ResultSet.CONCUR_READ_ONLY;
            //ResultSet.CONCUR_READ_ONLY = O conjunto de resultados nao pode ser usado para atualizar o banco de dados.
            //ResultSet.CONCUR_UPDATABLE = O conjunto de resultados pode ser usado para atualizar o banco de dados.
            stdados = connection.createStatement(tipo, concorrencia);
            //stdados.execute("SET SEARCH_PATH TO nome_esquema");//necessario para selecionar o esquema no banco de dados Postgre.
            //ou se utiliza o nome do esquema.nomedatabela
            rsdados = stdados.executeQuery(querydados);
            //stdados.executeQuery = retorna um resultSet
            MovPrimeiro();
        } catch (SQLException erro) {
            System.out.println("Erro Executa Query = " + erro);
        }
    }
    
    public void Sair() {
        try {
            if (rsdados != null) {
                rsdados.close();
                stdados.close();
                connection.close();
            }
        } catch (SQLException erro) {
            System.out.println("Nao foi possivel a desconexao." + erro);
        }
    }
    
    private void ExibeTabela() throws SQLException {
        if (!rsdados.first()) {// Se nao houver registros, exibe uma mensagem e termina a execucao do metodo
            JOptionPane.showMessageDialog(this, "O ResultSet esta vazio.");
            return;
        }
        
        final Vector cabecalhos = new Vector();
        final Vector registros = new Vector();
        //comentario sobre a classe Vector

        try {
            // obtem titulos de coluna
            ResultSetMetaData rsmd = rsdados.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                cabecalhos.addElement(rsmd.getColumnName(i));
            }
            // obtem dados da linha
            do {
                registros.addElement(ProximoRegistro(rsmd));
            } while (rsdados.next());
            
            if (tabela != null) {
                remove(tabela);
                remove(scroller);
            }
            tabela = new JTable(registros, cabecalhos);
            // exibe a tabela com conteudos de ResultSet
            scroller = new JScrollPane(tabela);
            add(scroller);
            scroller.setBounds(10, 320, 710, 180);
            validate();
            this.repaint();
        } catch (SQLException erro) {
            System.out.println("Erro Exibe Tabela = " + erro);
        }
    }
    
    private Vector ProximoRegistro(ResultSetMetaData rsmd) throws SQLException {
        Vector registro = new Vector();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            //A classe Types eh uma classo do pacote java.sql
            if (rsmd.getColumnType(i) == Types.VARCHAR
                    || rsmd.getColumnClassName(i).equalsIgnoreCase("java.lang.String")) {//para string
                registro.addElement(rsdados.getString(i));
            } else if (rsmd.getColumnType(i) == Types.INTEGER) {//para inteiros
                registro.addElement(new Long(rsdados.getLong(i)));
            } else {
                //tratamento para os tipos que serao lidos do banco de dados.
            }
        }
        return registro;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_abrebd;
    private javax.swing.JButton btn_anterior;
    private javax.swing.JButton btn_commit;
    private javax.swing.JButton btn_executaconsulta;
    private javax.swing.JButton btn_exibetabela;
    private javax.swing.JButton btn_primeiro;
    private javax.swing.JButton btn_proximo;
    private javax.swing.JButton btn_rollback;
    private javax.swing.JButton btn_ultimo;
    private javax.swing.JButton btn_update;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel labelemail;
    private javax.swing.JLabel labelfone;
    private javax.swing.JLabel labelid;
    private javax.swing.JLabel labelidade;
    private javax.swing.JLabel labelnome;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JTable tabela;
    private javax.swing.JTextArea textArea1;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        new AcessaBD().show();
    }
}
