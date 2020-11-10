
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
import Bean.Reserva;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservaDAO {
    public Connection conectar;
    
    public ReservaDAO(){
        this.conectar = new ConnectionFactory().conectar();
    } 
    
    public void cadastrarReserva(Reserva res){
   
        PreparedStatement inserir = null;
        
         try {
            inserir = conectar.prepareStatement("INSERT INTO Reserva (contribuicao_res,periodoReservado_res,observacao_res,clienteNome_res,clienteContato_res,clienteDocumento_res,servidor_res,ambiente_res) VALUES(?,?,?,?,?,?,?)");
            inserir.setBoolean(1, res.getContribuicao());
            inserir.setString(2, res.getPeriodoReservado());
            inserir.setString(3, res.getObservacao());
            inserir.setString(4, res.getClienteNome());
            inserir.setString(5, res.getClienteContato());
            inserir.setString(6, res.getClienteDocumento());
            inserir.setInt(7, res.getFuncionario());
            inserir.setInt(8, res.getAmbiente());
            
            

            inserir.execute();
            JOptionPane.showMessageDialog(null, "Reserva Realizada com Sucesso!");
            inserir.close();

            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
    }
    
    public String validarReserva(String bloco, String sala, String periodo, String data){
        
        //ResultSet result = null;
        PreparedStatement validar = null;
        String cont = " ";
        
        try {
            //System.out.println("teste"+(validar.executeQuery("SELECT COUNT(periodoReservado_res) as periodo FROM reserva WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) AND periodoReservado_res = ? AND dataInicio_res = ?")));
            //validar.setString(1, bloco);
            //validar.setString(2, sala);
            //validar.setString(3, periodo);
            //validar.setString(4, data);
            String selectSql = ("SELECT COUNT(*) FROM reserva WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = '" + bloco + "' AND salaNum_ambi = '" + sala + "') AND periodoReservado_res = '" + periodo + "' AND dataInicio_res = '" + data + "'");
            
            //String selectSql = ("SELECT count(*) FROM reserva WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = 'P' AND salaNum_ambi = '001') AND periodoReservado_res = 'M2' AND dataInicio_res = '2020-10-15'");
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);

            ResultSet rs = Stmt.executeQuery();
            
            //JOptionPane.showMessageDialog(null, "Reserva Excluida com Sucesso!");
            //while(rs.next()){
                //cont++;
                //System.out.println(cont);
            //}
            rs.next();
            cont=(rs.getString(1));
            
            Stmt.close();
            //rs = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //System.out.println(cont);
        
        return cont;
    }
    
    
    public void editarReserva(Reserva res) throws ParseException{
        
        PreparedStatement editar = null;
        
        try {
            editar = conectar.prepareStatement("UPDATE reserva SET contribuicao_res = ?, dataInicio_res = ?, periodoReservado_res = ?, observacao_res = ?, cliente_res = (SELECT id_cli FROM cliente WHERE nome_cli = ? AND documento_cli = ? AND contato_Alter_serv = ?) ,servidor_res = (SELECT id_serv FROM servidor WHERE nome_serv = ?), ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) WHERE id_res = ?");
            //editar = conectar.prepareStatement("INSERT INTO Reserva (contribuicao_res,periodoReservado_res,observacao_res,clienteNome_res,clienteContato_res,clienteDocumento_res,servidor_res,ambiente_res) VALUES(?,?,?,?,?,?,?,?)");
            System.out.println(res.getContribuicao());
            if(res.getContribuicao() == true){
                editar.setInt(1, 1);
            }else{
                editar.setInt(1, 0);
            }
            //System.out.println(res.getDataInicio());
            editar.setString(2, res.getDataInicio());
            //System.out.println(res.getPeriodoReservado());
            editar.setString(3, res.getPeriodoReservado());
            //System.out.println(res.getObservacao());
            editar.setString(4, res.getObservacao());
            //System.out.println(res.getClienteNome());
            editar.setString(5, res.getClienteNome());
            //System.out.println(res.getClienteDocumento());
            editar.setString(6, res.getClienteDocumento());
            //System.out.println(res.getClienteContato());
            editar.setString(7, res.getClienteContato());
            //System.out.println(res.getServ().getNome());
            editar.setString(8, res.getServ().getNome());
            //System.out.println(res.getAmbi().getBloco());
            editar.setString(9, res.getAmbi().getBloco());
            //System.out.println(res.getAmbi().getSalaNum());
            editar.setString(10, res.getAmbi().getSalaNum());
            System.out.println(res.getId());
            editar.setInt(11, res.getId());
            

            editar.execute();
            JOptionPane.showMessageDialog(null, "Reserva Editada com Sucesso!");
            editar.close();

            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
    public void excluirReserva(Reserva res){
        
        PreparedStatement excluir = null;
        
        try {
            excluir = conectar.prepareStatement("DELETE FROM Reserva WHERE id_res = ?");
            excluir.setInt(1, res.getId());

            excluir.execute();
            excluir.close();

            JOptionPane.showMessageDialog(null, "Reserva Excluida com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir a Reserva: " + ex);
        }

    }
    
    public List<Reserva> read(){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) ORDER BY dataInicio_res");            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                res.setDataInicio(rs.getString("DATAS"));
                                
                //res.setServ(rs.getInt(""));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    
    
    
    public List<Reserva> readForDescBloc(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? ORDER BY dataInicio_res");
            stmt.setString(1,desc);
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                res.setDataInicio(rs.getString("DATAS"));
                                
                //res.setServ(rs.getInt(""));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    public List<Reserva> readForDescDate(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE dataInicio_res = ? ORDER BY dataInicio_res");
            stmt.setString(1,desc);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                res.setDataInicio(rs.getString("DATAS"));
                                
                //res.setServ(rs.getInt(""));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    public List<Reserva> readForDescSala(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE salaNum_ambi = ? ORDER BY dataInicio_res");
            stmt.setString(1,desc);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                res.setDataInicio(rs.getString("DATAS"));
                                
                //res.setServ(rs.getInt(""));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    public List<Reserva> readForDescBlocSala(String desc, String desc2){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? ORDER BY dataInicio_res");
            stmt.setString(1,desc);
            stmt.setString(2,desc2);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                res.setDataInicio(rs.getString("DATAS"));
                                
                //res.setServ(rs.getInt(""));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    public List<Reserva> readForDescBlocSalaDate(String desc, String desc2, String desc3){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? AND dataInicio_res = ? ORDER BY dataInicio_res");
            stmt.setString(1,desc);
            stmt.setString(2,desc2);
            stmt.setString(3,desc3);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                res.setDataInicio(rs.getString("DATAS"));
                                
                //res.setServ(rs.getInt(""));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
}
