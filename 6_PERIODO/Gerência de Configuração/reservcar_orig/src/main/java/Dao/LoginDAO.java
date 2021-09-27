
package Dao;

import Bean.Administrador;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Bean.Servidor;


public class LoginDAO {
    public Connection conectar;
    
    public LoginDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }
    
    //Função para verificar se o login está correto
    public boolean checkLogin(String login, String senha) throws SQLException{
        
        PreparedStatement listar = null;
        ResultSet resultado = null;
        boolean check = false;
        if((login.length()>1)&&(senha.length()>1)) {
            //Query para busca e comparação do login
            listar  = conectar.prepareStatement("SELECT * FROM servidor WHERE login_serv = ? and senha_serv = ?");
            //Passando o login como parâmetro
            listar.setString(1,login);
            //Passando a senha como parâmetro
            listar.setString(2,senha);
            //Executar query
            resultado = listar.executeQuery();
            //Verificação de se o login é válido
            if (resultado.next()) {
                check = true;
            }
            //retornando resposta
            return check;

        } else {
            //Lançando exceção
            throw new RuntimeException("ERRO! Login e senha nao preenchidos");
        }

    }
    
    //Pegando nome a partir do login do servidor
    public List<Administrador> consultarLogin(String login) throws SQLException {

        PreparedStatement consulta = null;
        ResultSet resultado = null;

        List<Administrador> admi = new ArrayList<Administrador>();

        if((login.length()>1)) {
            //Query de pesquisa pelo login
            consulta = conectar.prepareStatement("SELECT * FROM servidor WHERE login_serv LIKE ?");
            //Passando login por parâmetro
            consulta.setString(1, login);
            //Executando query
            resultado = consulta.executeQuery();
            
            //Laço de repetição para pegar o nome e a permissão do servidor
            while (resultado.next()) {

                Administrador admin = new Administrador();

                admin.setNome(resultado.getString("nome_serv"));
                admin.setPermissao(resultado.getString("permissao_serv"));
                
                admi.add(admin);
            }
            return admi;
        } else {
            //Lançando exceção
            throw new RuntimeException("ERRO! Login inserido esta invalido");
        }
    }
}
