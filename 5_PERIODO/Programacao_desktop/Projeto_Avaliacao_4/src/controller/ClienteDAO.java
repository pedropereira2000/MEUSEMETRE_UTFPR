package controller;

import bancodados.JDBCUtil;
import model.Cliente;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 * @author Pedro Pereira
 */
public class ClienteDAO {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/bancodados/configuracaobd.properties");
    private static final String sqlconsultabancovazio = "SELECT COUNT(*) AS count_rows FROM clientes";
    private static final String sqlconsultaclientes = "SELECT * FROM clientes order by id";
    private static final String sqlconsultacpf = "SELECT * FROM clientes WHERE cpf = ?";
    private static final String sqlconsultanome = "SELECT * FROM clientes WHERE nome LIKE ?";
    private static final String sqlconsultaendereco = "SELECT * FROM clientes WHERE endereco LIKE ?";
    private static final String sqlconsultatelefone = "SELECT * FROM clientes WHERE telefone LIKE ?";
    private static final String sqlconsultanomeendereco = "SELECT * FROM clientes WHERE nome LIKE ? AND endereco LIKE ?";
    private static final String sqlconsultanometelefone = "SELECT * FROM clientes WHERE nome LIKE ? AND telefone LIKE ?";
    private static final String sqlconsultaenderecotelefone = "SELECT * FROM clientes WHERE endereco LIKE ? AND telefone LIKE ?";
    private static final String sqlcountcpf = "SELECT COUNT(cpf) AS count_cpf FROM clientes WHERE cpf = ?";
    private static final String sqlinserir = "INSERT INTO clientes (cpf, nome, endereco, telefone) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE clientes SET cpf = ?, nome = ?, endereco = ?, telefone = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM clientes WHERE cpf = ?";
    
    public ClienteDAO() {

    }

    public Connection getConnection(){
        try{
            return JDBCUtil.getConnection();
        }catch(SQLException err){
            System.err.println("erro na conexao");
        }
        return null;
    }
    
    public boolean CriaConexao() {
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.

            DatabaseMetaData dbmt = connection.getMetaData();
            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());
            System.out.println("Versao do BD: " + dbmt.getDatabaseProductVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Versao Driver: " + dbmt.getDriverVersion());
            System.out.println("Usuario: " + dbmt.getUserName());

            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
        return false;
    }

    public boolean FechaConexao() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException erro) {
                System.err.println("Erro ao fechar a conexão = " + erro);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean Inserir(Cliente cli) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            pstdados.setString(1, cli.getCpf());
            pstdados.setString(2, cli.getNome());
            pstdados.setString(3, cli.getEndereco());
            pstdados.setString(4, cli.getTelefone());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da inserção = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da inserção = " + erro);
        }
        return false;
    }

    public boolean Alterar(Cliente cli) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlalterar, tipo, concorrencia);
            pstdados.setString(1, cli.getCpf());
            pstdados.setString(2, cli.getNome());
            pstdados.setString(3, cli.getEndereco());
            pstdados.setString(4, cli.getTelefone());
            pstdados.setInt(5, cli.getId());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da atualização = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da atualização = " + erro);
        }
        return false;
    }

    public boolean Excluir(Cliente cli) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlaexcluir, tipo, concorrencia);
            pstdados.setString(1, cli.getCpf());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da exclusão = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
            
        } catch (SQLException erro) {
            System.out.println("Erro na execução da exclusão = " + erro);
        }
        return false;
    }

    public boolean VerficaBancoVazio() {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultabancovazio, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getInt("count_rows");
            if(resposta != 0){
                pstdados.close();
                rsdados.close();
                return true;
            }else{
                pstdados.close();
                rsdados.close();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há registros na base de dados");
        }
        return false;
    }
    
    public boolean ConsultarTodos() {
        try {
            if(VerficaBancoVazio()){  
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(sqlconsultaclientes, tipo, concorrencia);
                rsdados = pstdados.executeQuery();
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há registros na base de dados");
        }
        return false;
    }
    
    public Cliente ConsultarCpf(String cpf) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultacpf, tipo, concorrencia);
            pstdados.setString(1, cpf);
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return getCliente();
            else
                return null;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return null;
    }
    
    public boolean ConsultarNome(String nome) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultanome, tipo, concorrencia);
            pstdados.setString(1, "%"+nome+"%");
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return true;
            else
                return false;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return false;
    }
    
    public boolean ConsultarEndereco(String endereco) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaendereco, tipo, concorrencia);
            pstdados.setString(1, "%"+endereco+"%");
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return true;
            else
                return false;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return false;
    }
    
    public boolean ConsultarTelefone(String telefone) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultatelefone, tipo, concorrencia);
            pstdados.setString(1, "%"+telefone+"%");
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return true;
            else
                return false;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return false;
    }
    
    public boolean ConsultarAninhadaNomeEndereco (String nome, String endereco) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultanomeendereco, tipo, concorrencia);
            pstdados.setString(1, "%"+nome+"%");
            pstdados.setString(2, "%"+endereco+"%");
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return true;
            else
                return false;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return false;
    }
    
    public boolean ConsultarAninhadaNomeTelefone(String nome, String telefone) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultanometelefone, tipo, concorrencia);
            pstdados.setString(1, "%"+nome+"%");
            pstdados.setString(2, "%"+telefone+"%");
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return true;
            else
                return false;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return false;
    }
    
    public boolean ConsultarAninhadaEnderecoTelefone(String endereco, String telefone) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaenderecotelefone, tipo, concorrencia);
            pstdados.setString(1, "%"+endereco+"%");
            pstdados.setString(2, "%"+telefone+"%");
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return true;
            else
                return false;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return false;
    }
    
    

    public Cliente getCliente() {
        Cliente cli = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("id");
                String cpf = rsdados.getString("cpf");
                String nome = rsdados.getString("nome");
                String endereco = rsdados.getString("endereco");
                String telefone = rsdados.getString("telefone");
                cli = new Cliente(id ,cpf, nome, endereco, telefone);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return cli;
    }
    
    public int validaCliente(Cliente cli){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_READ_ONLY;
            pstdados = connection.prepareStatement(sqlcountcpf, tipo, concorrencia);
            pstdados.setString(1, cli.getCpf());
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getInt("count_cpf");
            if(resposta == 0){
                if(!cli.getCpf().equals("   .   .   -  ")){
                    if(!cli.getNome().equals("")){
                        if(!cli.getEndereco().equals("")){
                            if(!cli.getTelefone().equals("  -     -    ")){
                                return 0;
                            }else{
                                return 5;
                            }
                        }else{
                            return 4;
                        }
                    }else{
                        return 3;
                    }
                }else{
                    return 2;
                }
            }else{
                return 1;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
            return 6;
        }
    }

    /**
     * @return the rsdados
     */
    public ResultSet getRsdados() {
        return rsdados;
    }

    private String format(MaskFormatter formatadorNumero, String op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
