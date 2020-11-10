
package Dao;

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
    
    public boolean checkLogin(String login, String senha){
        
        PreparedStatement listar = null;
        ResultSet resultado = null;
        boolean check = false;
        try {
            listar  = conectar.prepareStatement("SELECT * FROM Servidor WHERE login_serv = ? and senha_serv = ?");
            listar.setString(1,login);
            listar.setString(2,senha);
            resultado = listar.executeQuery();

            if (resultado.next()) {

                check = true;
                
            }
            return check;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
}
