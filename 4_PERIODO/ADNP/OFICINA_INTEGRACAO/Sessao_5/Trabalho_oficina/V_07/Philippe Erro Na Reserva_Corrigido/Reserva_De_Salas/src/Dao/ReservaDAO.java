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
import View.TelaLogin;

public class ReservaDAO {

    public Connection conectar;

    public ReservaDAO() {
        this.conectar = new ConnectionFactory().conectar();
    }

    public void cadastrarCliente(String nome, String doc, String contato) {

        PreparedStatement inserir = null;
        try {
            inserir = conectar.prepareStatement("INSERT INTO Cliente VALUES ('0', ?, ?, ?)");
            inserir.setString(1, nome);
            inserir.setString(2, doc);
            inserir.setString(3, contato);
     
            inserir.execute();
            inserir.close();


        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
    public void cadastrarReserva(Reserva res) {

        PreparedStatement inserir = null;

        try {
            
            inserir = conectar.prepareStatement("INSERT INTO Reserva (id_res,contribuicao_res,dataInicio_res,periodoReservado_res,observacao_res,servidor_res,cliente_res,ambiente_res) VALUES('0',?,?,?,?, (SELECT id_serv FROM servidor WHERE nome_serv = ? ),(SELECT id_cli FROM cliente WHERE nome_cli = ? AND documento_cli = ? AND contato_Alter_serv = ? ), (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?))");
            if(res.getContribuicao() == true){
                inserir.setInt(1, 1);
            }else{
                inserir.setInt(1, 0);
            }
            inserir.setString(2, res.getDataInicio());
            inserir.setString(3, res.getPeriodoReservado());
            inserir.setString(4, res.getObservacao());
            inserir.setString(5, res.getServ().getNome());
            inserir.setString(6, res.getClienteNome());
            inserir.setString(7, res.getClienteDocumento());
            inserir.setString(8, res.getClienteContato());
            inserir.setString(9, res.getAmbi().getBloco());
            inserir.setString(10, res.getAmbi().getSalaNum());

            inserir.execute();
            
            inserir.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public void editarReserva(Reserva res) {

        PreparedStatement editar = null;

        try {
            editar = conectar.prepareStatement("UPDATE Reserva SET contribuicao_res = ?, periodoReservado_res = ?, observacao_res = ?, clienteNome_res = ?, clienteContato_res = ?, clienteDocumento_res = ,servidor_res = ?, ambiente_res = ? WHERE id_res = ?");
            editar = conectar.prepareStatement("INSERT INTO Reserva (contribuicao_res,periodoReservado_res,observacao_res,clienteNome_res,clienteContato_res,clienteDocumento_res,servidor_res,ambiente_res) VALUES(?,?,?,?,?,?,?,?)");
            editar.setBoolean(1, res.getContribuicao());
            editar.setString(2, res.getPeriodoReservado());
            editar.setString(3, res.getObservacao());
            editar.setString(4, res.getClienteNome());
            editar.setString(5, res.getClienteContato());
            editar.setString(6, res.getClienteDocumento());
            editar.setInt(7, res.getFuncionario());
            editar.setInt(8, res.getAmbiente());

            editar.execute();
            JOptionPane.showMessageDialog(null, "Reserva Editada com Sucesso!");
            editar.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public void excluirReserva(Reserva res) {

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

    public List<Reserva> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> reservas = new ArrayList<>();

        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) ORDER BY dataInicio_res");
            rs = stmt.executeQuery();

            while (rs.next()) {
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

    public List<Reserva> readForDescBloc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> reservas = new ArrayList<>();
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? ORDER BY dataInicio_res");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();

            while (rs.next()) {
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

    public List<Reserva> readForDescDate(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> reservas = new ArrayList<>();
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE dataInicio_res = ? ORDER BY dataInicio_res");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();

            while (rs.next()) {
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

    public List<Reserva> readForDescSala(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> reservas = new ArrayList<>();
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE salaNum_ambi = ? ORDER BY dataInicio_res");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();

            while (rs.next()) {
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

    public List<Reserva> readForDescBlocSala(String desc, String desc2) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> reservas = new ArrayList<>();
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? ORDER BY dataInicio_res");
            stmt.setString(1, desc);
            stmt.setString(2, desc2);
            rs = stmt.executeQuery();

            while (rs.next()) {
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

    public List<Reserva> readForDescBlocSalaDate(String desc, String desc2, String desc3) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> reservas = new ArrayList<>();
        try {
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? AND dataInicio_res = ? ORDER BY dataInicio_res");
            stmt.setString(1, desc);
            stmt.setString(2, desc2);
            stmt.setString(3, desc3);
            rs = stmt.executeQuery();

            while (rs.next()) {
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
