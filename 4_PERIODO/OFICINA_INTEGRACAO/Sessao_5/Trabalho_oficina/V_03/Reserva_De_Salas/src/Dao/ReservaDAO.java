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
import Bin.Reserva;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ReservaDAO {
    public Connection conectar;
    
    /*public ReservaDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }*/
   
    public List<Reserva> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            stmt = con.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) ORDER BY dataInicio_res");            
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
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = con.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? ORDER BY dataInicio_res");
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
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = con.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE dataInicio_res = ? ORDER BY dataInicio_res");
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
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = con.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE salaNum_ambi = ? ORDER BY dataInicio_res");
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
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = con.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? ORDER BY dataInicio_res");
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
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            stmt = con.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? AND dataInicio_res = ? ORDER BY dataInicio_res");
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

