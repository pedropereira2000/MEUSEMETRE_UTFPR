/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connection.ConnectionFactory;
import Bean.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AdministradorComDAO {
    public Connection conectar;
    
     public AdministradorComDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }
     
     //Cadastrar
      public void cadastrarServidorCom(Administrador admi){
          PreparedStatement inserir = null;
          
          try {
            inserir = conectar.prepareStatement("INSERT INTO Informacao_Servidor (id_info_serv,emailAlternativo_info, contato_info, contatoAlt_info)VALUES(?,?,?,?)");
             inserir.setInt(1, admi.getSiape());
	     inserir.setString(2, admi.getEmailAl());
             inserir.setString(3, admi.getConPessoal());
             inserir.setString(4, admi.getConPessoal2());

             inserir.execute();
             inserir.close();

             JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
            } catch (SQLException erro) {
              throw new RuntimeException(erro);
        }

      }
      //Verificar Com os outros colegas
      public void editarServidorCom(Administrador admi){
        
        PreparedStatement editar = null;
        
        try {
            editar = conectar.prepareStatement("UPDATE Informacao_Servidor SET  emailAlternativo_info = ?,contato_info = ?,contatoAlt_info = ? WHERE id_info_serv = ?");
	     editar.setString(2, admi.getEmailAl());
             editar.setString(3, admi.getConPessoal());
             editar.setString(4, admi.getConPessoal2());
             editar.setInt(7, admi.getSiape());
            
             editar.execute();
             JOptionPane.showMessageDialog(null, "Cadastro Atualizado com Sucesso!");
             editar.close();

            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
      
    public void excluirServidorCom(Administrador admi){
        
        PreparedStatement excluir = null;
        
        try {
            excluir = conectar.prepareStatement("DELETE FROM Informacao_Servidor WHERE id_info_serv = ?");
            excluir.setInt(1, admi.getSiape());

            excluir.execute();
            excluir.close();

            JOptionPane.showMessageDialog(null, "Cadastro Excluido com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir o Cadastro: " + ex);
        }

    }
    
    public List<Administrador> consultarServidorCom(int siape) {
      
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        List<Administrador> admi = new ArrayList<>();

        try {
            consulta = conectar.prepareStatement("SELECT * FROM Informacao_Servidor WHERE id_info_serv LIKE ?");
            consulta.setInt(1, siape);
            
            resultado = consulta.executeQuery();

            while (resultado.next()) {

                Administrador admin = new Administrador();

                admin.setSiape(resultado.getInt("id_info_serv"));
                admin.setEmailAl(resultado.getString("emailAlternativo_info"));
                admin.setConPessoal(resultado.getString("contato_info"));
                admin.setConPessoal2(resultado.getString("contatoAlt_info"));
                        
                admi.add(admin);
            }

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } 

        return admi;

    }
    
    public List<Administrador> listarServidorCom(){
        
        PreparedStatement listar = null;
        ResultSet resultado = null;

        List<Administrador> Servidor = new ArrayList<>();

        try {
            listar  = conectar.prepareStatement("SELECT * FROM Informacao_Servidor");
            resultado = listar.executeQuery();

            while (resultado.next()) {

                Administrador admin = new Administrador();

                admin.setSiape(resultado.getInt("id_info_serv"));
                admin.setNome(resultado.getString("emailAlternativo_info"));
                admin.setNome(resultado.getString("contato_info"));
                admin.setNome(resultado.getString("contatoAlt_info"));
                
                Servidor.add(admin);
                
            }
            return Servidor;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
}
