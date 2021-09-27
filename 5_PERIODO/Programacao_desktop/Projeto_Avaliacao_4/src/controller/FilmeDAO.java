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
import model.Filme;

/**
 * @author Pedro Pereira
 */
public class FilmeDAO {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/bancodados/configuracaobd.properties");
    private static final String sqlconsultabancovazio = "SELECT COUNT(*) AS count_rows FROM filmes";
    private static final String sqlconsultafilmes = "SELECT * FROM filmes order by id";
    private static final String sqlconsultatitulo = "SELECT * FROM filmes WHERE titulo LIKE ?";
    private static final String sqlconsultagenero = "SELECT * FROM filmes WHERE genero LIKE ?";
    private static final String sqlconsultaduracao = "SELECT * FROM filmes WHERE duracao = ?";
    private static final String sqlconsultaclassifica = "SELECT * FROM filmes WHERE classificacao = ?";
    private static final String sqlconsultastudio = "SELECT * FROM filmes WHERE studio LIKE ?";
    private static final String sqlcountfilme = "SELECT COUNT(*) AS count_film FROM filmes WHERE titulo = ? AND genero = ? AND studio = ?";
    private static final String sqlinserir = "INSERT INTO filmes (titulo, genero, duracao, classificacao, studio) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE filmes SET titulo=?, genero=?, duracao=?, classificacao=?, studio=? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM filmes WHERE id = ?";
    
    public FilmeDAO() {

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

    public boolean Inserir(Filme film) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            pstdados.setString(1, film.getTitulo());
            pstdados.setString(2, film.getGenero());
            pstdados.setInt(3, film.getDuracao());
            pstdados.setString(4, film.getClassificacao());
            pstdados.setString(5, film.getStudio());
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

    public boolean Alterar(Filme film) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlalterar, tipo, concorrencia);
            pstdados.setString(1, film.getTitulo());
            pstdados.setString(2, film.getGenero());
            pstdados.setInt(3, film.getDuracao());
            pstdados.setString(4, film.getClassificacao());
            pstdados.setString(5, film.getStudio());
            pstdados.setInt(6, film.getId());
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

    public boolean Excluir(Filme film) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlaexcluir, tipo, concorrencia);
            pstdados.setInt(1, film.getId());
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
                pstdados = connection.prepareStatement(sqlconsultafilmes, tipo, concorrencia);
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
    
    public boolean ConsultarTitulo(String titulo) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultatitulo, tipo, concorrencia);
            pstdados.setString(1, "%"+titulo+"%");
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
            System.out.println("Não há mais filmes");
        }
        return false;
    }
    
    public boolean ConsultarGenero(String genero) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultagenero, tipo, concorrencia);
            pstdados.setString(1, "%"+genero+"%");
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
    
    public boolean ConsultarDuracao(int duracao) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaduracao, tipo, concorrencia);
            pstdados.setInt(1, duracao);
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
    
    public boolean ConsultarClassifica(String classifica) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaclassifica, tipo, concorrencia);
            pstdados.setString(1, classifica);
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
    
    public boolean ConsultarStudio(String studio) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultastudio, tipo, concorrencia);
            pstdados.setString(1, "%"+studio+"%");
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
    
    /*public boolean ConsultarAninhada (String titulo, String genero, int duracao, String classe, String studio) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            
            if(!titulo.equals("")&&!genero.equals("")){
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo LIKE ? AND genero LIKE ?", tipo, concorrencia);
                pstdados.setString(1, "%"+titulo+"%");
                pstdados.setString(2, "%"+genero+"%");
            }
            
            if(!titulo.equals("")&&duracao!=0){
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo LIKE ? AND duracao = ?", tipo, concorrencia);
                pstdados.setString(1, "%"+titulo+"%");
                pstdados.setInt(2, duracao);
            }
            
            if(!titulo.equals("")&&!classe.equals("")){
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo LIKE ? AND classificacao = ?", tipo, concorrencia);
                pstdados.setString(1, "%"+titulo+"%");
                pstdados.setString(2, classe);
            }
            
            if(!titulo.equals("")&&!studio.equals("")){
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo LIKE ? AND studio LIKE ?", tipo, concorrencia);
                pstdados.setString(1, "%"+titulo+"%");
                pstdados.setString(2, "%"+studio+"%");
            }
            
            if(!titulo.equals("")&&!genero.equals("")&&duracao!=0){
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo LIKE ? AND genero LIKE ? AND duracao = ?", tipo, concorrencia);
                pstdados.setString(1, "%"+titulo+"%");
                pstdados.setString(2, "%"+genero+"%");
                pstdados.setInt(3, duracao);
            }
            
            if(!titulo.equals("")&&!genero.equals("")&&duracao!=0&&!classe.equals("")){    
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo LIKE ? AND genero LIKE ? AND duracao = ? AND classificacao = ?", tipo, concorrencia);
                pstdados.setString(1, "%"+titulo+"%");
                pstdados.setString(2, "%"+genero+"%");
                pstdados.setInt(3, duracao);
                pstdados.setString(4, classe);
            }
            
            if(!titulo.equals("")&&!genero.equals("")&&duracao!=0&&!classe.equals("")&&!studio.equals("")){
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo LIKE ? AND genero LIKE ? AND duracao = ? AND classificacao = ? AND studio LIKE ?", tipo, concorrencia);
                pstdados.setString(1, "%"+titulo+"%");
                pstdados.setString(2, "%"+genero+"%");
                pstdados.setInt(3, duracao);
                pstdados.setString(4, classe);
                pstdados.setString(5,"%"+studio+"%");
            }
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
    }*/
    
    /*public boolean ConsultarAninhadaNomeTelefone(String nome, String telefone) {
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
    }*/
    
    

    public Filme getFilme() {
        Filme film = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("id");
                String titulo = rsdados.getString("titulo");
                String genero = rsdados.getString("genero");
                int duracao = rsdados.getInt("duracao");
                String classificacao = rsdados.getString("classificacao");
                String studio = rsdados.getString("studio");
                film = new Filme(id ,titulo, genero, duracao, classificacao, studio);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return film;
    }
    
    public int validaFilme(Filme film){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_READ_ONLY;
            pstdados = connection.prepareStatement(sqlcountfilme, tipo, concorrencia);
            pstdados.setString(1, film.getTitulo());
            pstdados.setString(2, film.getGenero());
            pstdados.setString(3, film.getStudio());
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getInt("count_film");
            if(resposta == 0){
                if(!film.getTitulo().equals("")){
                    if(!film.getGenero().equals("")){
                        if(film.getDuracao()!=0){
                            if(!film.getStudio().equals("")){
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
            System.out.println("Erro ao validar filme = " + erro);
            return 6;
        }
    }
    
    public boolean getValClasse(String classe) {
        try{
            if(classe.equals("10")||classe.equals("12")||classe.equals("14")||classe.equals("16")||classe.equals("18")||classe.equals("L")||classe.equals("l"))
                return true;
        } catch (NullPointerException err){
            System.out.println("Erro");
        }
        return false;
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
