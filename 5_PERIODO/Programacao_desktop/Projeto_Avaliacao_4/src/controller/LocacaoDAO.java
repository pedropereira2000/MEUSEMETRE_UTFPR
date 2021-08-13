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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.Filme;
import model.Locadora;

/**
 * @author Pedro Pereira
 */
public class LocacaoDAO {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/bancodados/configuracaobd.properties");
    private static final String sqlconsultabancovazio = "SELECT COUNT(*) AS count_rows FROM locacoes";
    private static final String sqlconsultalocacoes = "SELECT * FROM locacoes order by id";
    private static final String sqlconsultatitulo = "SELECT * FROM locacoes WHERE titulo_filme LIKE ?";
    private static final String sqlconsultagenero = "SELECT * FROM locacoes WHERE genero_filme LIKE ?";
    private static final String sqlconsultastudio = "SELECT * FROM locacoes WHERE studio_filme LIKE ?";
    private static final String sqlconsultacpf = "SELECT * FROM locacoes WHERE cpf_cliente = ?";
    private static final String sqlconsultadata = "SELECT * FROM locacoes WHERE data = ?";
    private static final String sqlconsultaperiodo = "SELECT * FROM locacoes WHERE periodo_alugado = ?";
    private static final String sqlconsultavalor = "SELECT * FROM locacoes WHERE valor = ?";
    private static final String sqlconsultadevolvido = "SELECT * FROM locacoes WHERE devolvido = ?";
    private static final String sqlcountlocadora = "SELECT COUNT(*) AS count_locad FROM locacoes WHERE titulo_filme = ? AND genero_filme = ? AND studio_filme = ? AND devolvido = FALSE";
    private static final String sqlinserir = "INSERT INTO locacoes (funcionario, titulo_filme, genero_filme, studio_filme, cpf_cliente, data, periodo_alugado, valor, devolvido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE locacoes SET funcionario=?, titulo_filme=?, genero_filme=?, studio_filme=?, cpf_cliente=?, data=?, periodo_alugado=?, valor=?, devolvido=?  WHERE id = ?";
    private static final String sqldevolver = "UPDATE locacoes SET devolvido=TRUE  WHERE id = ?";
    private static final String sqlcunsultadevolvido = "SELECT * FROM locacoes WHERE id = ? AND devolvido = ?";
    private static final String sqlaexcluir = "DELETE FROM locacoes WHERE id = ?";
    
    public LocacaoDAO() {

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

    public boolean Inserir(Locadora loc) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            pstdados.setString(1, loc.getFuncionario());
            pstdados.setString(2, loc.getTituloFilme());
            pstdados.setString(3, loc.getGeneroFilme());
            pstdados.setString(4, loc.getStudioFilme());
            pstdados.setString(5, loc.getCpfCliente());
            pstdados.setString(6, loc.getData());
            pstdados.setInt(7, loc.getPeriodoAlugado());
            pstdados.setFloat(8, loc.getValor());
            pstdados.setBoolean(9, loc.getDevolvido());
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

    public boolean Alterar(Locadora loc) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlalterar, tipo, concorrencia);
            pstdados.setString(1, loc.getFuncionario());
            pstdados.setString(2, loc.getTituloFilme());
            pstdados.setString(3, loc.getGeneroFilme());
            pstdados.setString(4, loc.getStudioFilme());
            pstdados.setString(5, loc.getCpfCliente());
            pstdados.setString(6, loc.getData());
            pstdados.setInt(7, loc.getPeriodoAlugado());
            pstdados.setFloat(8, loc.getValor());
            pstdados.setBoolean(9, loc.getDevolvido());
            pstdados.setInt(10, loc.getId());
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

    public boolean Excluir(Locadora loc) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlaexcluir, tipo, concorrencia);
            pstdados.setInt(1, loc.getId());
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
    
    public boolean Devolver(Locadora loc) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqldevolver, tipo, concorrencia);
            pstdados.setInt(1, loc.getId());
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
            System.out.println("Erro na execução da devolucao = " + erro);
        }
        return false;
    }
    
    public boolean BuscaDevolvidos(int id, boolean op) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlcunsultadevolvido, tipo, concorrencia);
            pstdados.setInt(1, id);
            pstdados.setBoolean(2, op);
            rsdados = pstdados.executeQuery();
            rsdados.next();
            int resposta = rsdados.getRow();
            if(resposta!=0)
                return true;
            else
                return false;
        } catch (SQLException erro) {
            System.out.println("Erro na busca do item = " + erro);
        }
        return false;
    }
    
    public String getData(){
        String data = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Calendar gc = Calendar.getInstance();
        return data=df.format(gc.getTime());
    }
    
    public String getDataDevolucao(String data, int periodo){
        String dataDevolv = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Calendar gc = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(df.parse(data));
            gc.add((cal.DAY_OF_MONTH), (7*periodo));
	} catch (ParseException e) {
		e.printStackTrace();
	}
        return dataDevolv=df.format(gc.getTime());
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
                pstdados = connection.prepareStatement(sqlconsultalocacoes, tipo, concorrencia);
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
    

    public Locadora getLocadora() {
        Locadora loc = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("id");
                String funcionario = rsdados.getString("funcionario");
                String titulo = rsdados.getString("titulo_filme");
                String genero = rsdados.getString("genero_filme");
                String studio = rsdados.getString("studio_filme");
                String cpf = rsdados.getString("cpf_cliente");
                String data = rsdados.getString("data");
                String dataDevolucao = getDataDevolucao(rsdados.getString("data"), rsdados.getInt("periodo_alugado"));
                int periodo = rsdados.getInt("periodo_alugado");
                float valor = rsdados.getFloat("valor");
                boolean devolvido = rsdados.getBoolean("devolvido");
                loc = new Locadora(id , funcionario, titulo, genero, studio, cpf, data, dataDevolucao, periodo, valor, devolvido);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return loc;
    }
    
    public boolean getFilmePreenche(String titulo){
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            if(!titulo.equals(" ")){
                pstdados = connection.prepareStatement("SELECT * FROM filmes WHERE titulo = ?", tipo, concorrencia);
                pstdados.setString(1, titulo);
            }
            rsdados = pstdados.executeQuery();
            rsdados.next();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há mais clientes");
        }
        return false;
    }
    
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
                rsdados.next();
            } catch (SQLException erro) {
                System.out.println("Erro aqui = "+erro);
            }
        }
        return film;
    }
    
    public String getTitulo() {
        String film = "";
        if (rsdados != null) {
            try {
                film = rsdados.getString("titulo");
                rsdados.next();
            } catch (SQLException erro) {
                System.out.println("Erro aqui = "+erro);
            }
        }
        return film;
    } 
    
    public String getCpf() {
        String cli = "";
        if (rsdados != null) {
            try {
                cli = rsdados.getString("cpf");
                rsdados.next();
            } catch (SQLException erro) {
                System.out.println("Erro aqui = "+erro);
            }
        }
        return cli;
    }
    
    public boolean getTodosTitulos() {
        
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement("SELECT DISTINCT titulo FROM filmes order by titulo", tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            rsdados.next();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        } catch (NullPointerException erro) {
            System.out.println("Não há registros na base de dados");
        }
        return false;
    }
    
    public boolean getTodosClientes() {
        
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement("SELECT cpf FROM clientes order by cpf", tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            rsdados.next();
            return true;
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
    
    public boolean ConsultarCliente(String cpf) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultacpf, tipo, concorrencia);
            pstdados.setString(1, cpf);
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
    
    public boolean ConsultarData(String data) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultadata, tipo, concorrencia);
            pstdados.setString(1, data);
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
    
    public boolean ConsultarPeriodo(int periodo) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaperiodo, tipo, concorrencia);
            pstdados.setInt(1, periodo);
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
    
    public boolean ConsultarValor(float valor) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultavalor, tipo, concorrencia);
            pstdados.setFloat(1, valor);
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
    
    public boolean ConsultarDevolvido(boolean devolvido) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultadevolvido, tipo, concorrencia);
            pstdados.setBoolean(1, devolvido);
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
    
    public String TrataCpf(String cpf){
        JFormattedTextField jTF_cpf = new JFormattedTextField();
        try{
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            jTF_cpf.setFormatterFactory( new DefaultFormatterFactory(mask));
            jTF_cpf.setText(cpf);
        } catch(ParseException err){
            System.out.println(err);
        }
        return cpf=jTF_cpf.getText();
    }
    
    public String TrataData(String data){
        JFormattedTextField jTF_data = new JFormattedTextField();
        try{
            MaskFormatter mask = new MaskFormatter("##/##/####");
            jTF_data.setFormatterFactory( new DefaultFormatterFactory(mask));
            jTF_data.setText(data);
        } catch(ParseException err){
            System.out.println(err);
        }
        return data=jTF_data.getText();
    }
    
    public int validaLocadora(Locadora loc, int op){
        try {
            if(op==0){
                if(!loc.getFuncionario().equals("")){
                    if(!loc.getTituloFilme().equals("")){
                        if(!loc.getGeneroFilme().equals("")){
                            if(!loc.getStudioFilme().equals("")){
                                if(!loc.getCpfCliente().equals("")){
                                    if(loc.getValor()!=0.0){
                                        return 0;
                                    }else{
                                        return 7;
                                    }
                                }else{
                                    return 6;
                                }
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
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_READ_ONLY;
                pstdados = connection.prepareStatement(sqlcountlocadora, tipo, concorrencia);
                pstdados.setString(1, loc.getTituloFilme());
                pstdados.setString(2, loc.getGeneroFilme());
                pstdados.setString(3, loc.getStudioFilme());
                rsdados = pstdados.executeQuery();
                rsdados.next();
                int resposta = rsdados.getInt("count_locad");
                System.out.println(resposta);
                if(resposta == 0){
                    if(!loc.getFuncionario().equals("")){
                        if(!loc.getTituloFilme().equals("")){
                            if(!loc.getGeneroFilme().equals("")){
                                if(!loc.getStudioFilme().equals("")){
                                    if(!loc.getCpfCliente().equals("")){
                                        if(loc.getValor()!=0.0){
                                            return 0;
                                        }else{
                                            return 7;
                                        }
                                    }else{
                                        return 6;
                                    }
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
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao validar locacao = " + erro);
            return 8;
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
