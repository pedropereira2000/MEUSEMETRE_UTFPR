/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Reserva;
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
    
    public PeriodoReservadoDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }
   
    public List<Reserva> read(String data, String bloco, String sala){
              
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            stmt = conectar.prepareStatement("SELECT periodoReservado_res as PERIODO, nome_cli as CLIENTE, nome_serv as SERVIDOR, contribuicao_res as CONTRIBUICAO, observacao_res as OBSERVACOES FROM reserva INNER JOIN cliente ON (reserva.cliente_res = cliente.id_cli) INNER JOIN servidor ON (reserva.servidor_res = servidor.id_serv) WHERE dataInicio_res = ? AND ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) ORDER BY PERIODO");
            stmt.setString(1, sala);
            stmt.setString(2, data);
            stmt.setString(3, bloco);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.setPeriodoReservado(rs.getString("PERIODO"));
                res.setClienteNome(rs.getString("CLIENTE"));
                res.getServ().setNome(rs.getString("SERVIDOR"));
                if(rs.getInt("CONTRIBUICAO")==1){
                    res.setContribuicao(true);
                }else{
                    res.setContribuicao(false);
                }
                res.setObservacao(rs.getString("OBSERVACOES"));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    public List<Reserva> readEdit(String data, String bloco, String sala, String periodo){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            stmt = conectar.prepareStatement("SELECT id_res as ID, periodoReservado_res as PERIODO, nome_cli as NOME, contato_Alter_serv as CONTATO, documento_cli AS DOCUMENTO, contribuicao_res as CONTRIBUICAO, dataInicio_res as DATAS, observacao_res as OBSERVACAO, nome_serv as SERVIDOR FROM reserva INNER JOIN cliente ON (reserva.cliente_res = cliente.id_cli) INNER JOIN servidor ON (reserva.servidor_res = servidor.id_serv) WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) AND periodoReservado_res = ? AND dataInicio_res = ?");            
            stmt.setString(1,bloco);
            stmt.setString(2,sala);
            stmt.setString(3,periodo);
            stmt.setString(4,data);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva res = new Reserva();
                
                res.setPeriodoReservado(rs.getString("PERIODO"));
                res.setClienteNome(rs.getString("NOME"));
                res.setClienteContato(rs.getString("CONTATO"));
                res.setClienteDocumento(rs.getString("DOCUMENTO"));
                if((Integer.parseInt(rs.getString("CONTRIBUICAO")))==0){
                    res.setContribuicao(true);
                }else{
                    res.setContribuicao(false);
                }
                res.setDataInicio(rs.getString("DATAS"));
                res.setObservacao(rs.getString("OBSERVACAO"));
                res.getServ().setNome(rs.getString("SERVIDOR"));
                res.setId(rs.getInt("ID"));
                                
                //res.setServ(rs.getInt(""));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return reservas;

    }
    
    
    public List<Reserva> readForDescData(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        JOptionPane.showMessageDialog(null, "Erro 1: ");
        try {
            stmt = conectar.prepareStatement("SELECT * FROM reserva WHERE dataInicio_res = ?");
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
                res.setObservacao(rs.getString("observacao_res"));
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
