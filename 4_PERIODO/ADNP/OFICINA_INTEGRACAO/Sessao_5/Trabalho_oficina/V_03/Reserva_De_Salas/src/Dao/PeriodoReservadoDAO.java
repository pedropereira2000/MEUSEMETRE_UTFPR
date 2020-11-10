/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bin.Reserva;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Pereira
 */
public class PeriodoReservadoDAO {
    public Connection conectar;
    
    /*public ReservaDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }*/
   
    public List<Reserva> read(String data, String bloco, String sala){
              
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            stmt = con.prepareStatement("SELECT periodoReservado_res as PERIODO, nome_cli as CLIENTE, nome_serv as SERVIDOR, contribuicao_res as CONTRIBUICAO, observacao_res as OBSERVACOES FROM reserva INNER JOIN cliente ON (reserva.cliente_res = cliente.id_cli) INNER JOIN servidor ON (reserva.servidor_res = servidor.id_serv) WHERE dataInicio_res = ? AND ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) ORDER BY PERIODO");
            stmt.setString(1, sala);
            stmt.setString(2, data);
            stmt.setString(3, bloco);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.setPeriodoReservado(rs.getString("PERIODO"));
                res.setCliNome(rs.getString("CLIENTE"));
                res.getServ().setNome(rs.getString("SERVIDOR"));
                if((Integer.parseInt(rs.getString("CONTRIBUICAO")))==0){
                    res.setContribuicaoReserva(true);
                }else{
                    res.setContribuicaoReserva(false);
                }
                res.setObservacoes(rs.getString("OBSERVACOES"));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    
    public List<Reserva> readForDescData(String desc){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        JOptionPane.showMessageDialog(null, "Erro 1: ");
        try {
            stmt = con.prepareStatement("SELECT * FROM reserva WHERE dataInicio_res = ?");
            stmt.setString(1,desc);
            rs = stmt.executeQuery();
            
            //"SELECT * FROM reserva WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?)"
            //stmt.setString(1,desc1);
            //stmt.setString(2,desc2);
            JOptionPane.showMessageDialog(null, "Erro 2: ");
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.setDataInicio(rs.getString("dataInicio_res"));
                res.setPeriodoReservado(rs.getString("periodoReservado_res"));
                res.setObservacoes(rs.getString("observacao_res"));
                //res.setServ(rs.getInt(""));
                reservas.add(res);
                JOptionPane.showMessageDialog(null, "Erro 3: ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro 4: " + ex);
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
}