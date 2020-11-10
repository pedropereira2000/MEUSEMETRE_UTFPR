
package Dao;

import Bean.Servidor;
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

public class LoginDAO {
    public Connection conectar;
    
    public LoginDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }
    
    public boolean checkLogin(String id, String senha){
        
        PreparedStatement listar = null;
        ResultSet resultado = null;
        boolean check = false;
        try {
            listar  = conectar.prepareStatement("SELECT * FROM servidor WHERE id_serv = ? and senha_serv = ?");
            listar.setInt(1,Integer.parseInt(id));
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
