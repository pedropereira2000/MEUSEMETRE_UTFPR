package Dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Bean.Ambiente;

public class AmbienteDAO {
    public Connection conectar;
    
    public AmbienteDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }
    
    public void cadastrarAmbiente(Ambiente ambi){
   
        PreparedStatement inserir = null;
        
         try {
            inserir = conectar.prepareStatement("INSERT INTO Ambiente (bloco_ambi,salaNum_ambi,tipoSala_ambi)VALUES(?,?,?)");
            inserir.setString(1, ambi.getBloco());
            inserir.setString(2, ambi.getSalaNum());
            inserir.setString(3, ambi.getTipoSala());
            

            inserir.execute();
            inserir.close();    

            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
    }
    
    public void editarAmbiente(Ambiente ambi){
        
        PreparedStatement editar = null;
        
        try {
            editar = conectar.prepareStatement("UPDATE Ambiente SET bloco_ambi = ? ,salaNum_ambi = ?,tipoSala_ambi = ? WHERE id_ambi = ?");
            editar.setString(1, ambi.getBloco());
            editar.setString(2, ambi.getSalaNum());
            editar.setString(3, ambi.getTipoSala());
            editar.setInt(4, ambi.getId());

            editar.execute();
            editar.close();

            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
    public void excluirAmbiente(Ambiente ambi){
        
        PreparedStatement excluir = null;
        
        try {
            excluir = conectar.prepareStatement("DELETE FROM Ambiente WHERE id_ambi = ?");
            excluir.setInt(1, ambi.getId());

            excluir.execute();
            excluir.close();

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir o Ambiente: " + ex);
        }

    }
    
    public List<Ambiente> listarAmbientes(){
        
        PreparedStatement listar = null;
        ResultSet resultado = null;

        List<Ambiente> ambientes = new ArrayList<>();

        try {
            listar  = conectar.prepareStatement("SELECT * FROM Ambiente");
            resultado = listar.executeQuery();

            while (resultado.next()) {

                Ambiente ambiente = new Ambiente();

                ambiente.setId(resultado.getInt("id_ambi"));
                ambiente.setBloco(resultado.getString("bloco_ambi"));
                ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                ambientes.add(ambiente);
                
            }
            return ambientes;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
    public List<Ambiente> listarBloco(){
        
        PreparedStatement listarB = null;
        ResultSet resultadoB = null;

        List<Ambiente> ambientes = new ArrayList<>();

        try {
            listarB  = conectar.prepareStatement("SELECT DISTINCT bloco_ambi FROM Ambiente");
            resultadoB = listarB.executeQuery();

            while (resultadoB.next()) {

                Ambiente ambiente = new Ambiente();

                //ambiente.setId(resultado.getInt("id_ambi"));
                ambiente.setBloco(resultadoB.getString("bloco_ambi"));
                //ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                //ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                ambientes.add(ambiente);
                
            }
            return ambientes;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
    public List<Ambiente> listarSala(String desc){
        
        PreparedStatement listarS = null;
        ResultSet resultadoS = null;

        List<Ambiente> ambientes = new ArrayList<>();

        try {
            listarS  = conectar.prepareStatement("SELECT salaNum_ambi FROM ambiente WHERE bloco_ambi = ?");
            listarS.setString(1, desc);
            resultadoS = listarS.executeQuery();

            while (resultadoS.next()) {

                Ambiente ambiente = new Ambiente();

                //ambiente.setId(resultado.getInt("id_ambi"));
                ambiente.setSalaNum(resultadoS.getString("salaNum_ambi"));
                //ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                //ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                ambientes.add(ambiente);
                
            }
            return ambientes;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
    
    public List<Ambiente> consultarAmbiente(int id) {
      
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        List<Ambiente> ambientes = new ArrayList<>();

        try {
            consulta = conectar.prepareStatement("SELECT * FROM Ambiente WHERE id_ambi LIKE ?");
            consulta.setInt(1, id);
            
            resultado = consulta.executeQuery();

            while (resultado.next()) {

                Ambiente ambiente = new Ambiente();

                ambiente.setId(resultado.getInt("id_ambi"));
                ambiente.setBloco(resultado.getString("bloco_ambi"));
                ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                ambientes.add(ambiente);
            }

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } 

        return ambientes;

    }
    
}

