package Dao;

import Connection.ConnectionFactory;
import Bean.Administrador;
import Bean.Ambiente;
import Bean.Servidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AdministradorDAO {

    public Connection conectar;

    public AdministradorDAO() {
        this.conectar = new ConnectionFactory().conectar();
    }
    //Cadastrar

    //Cadastrar
    public void cadastrarServidor(Administrador admi) {
        PreparedStatement inserir = null;

        try {
            inserir = conectar.prepareStatement("INSERT INTO Servidor (id_serv,nome_serv,email_serv,ramal_serv,permissao_serv,contato_Pessoal_serv,login_serv,senha_serv)VALUES(?,?,?,?,?,?,?,?)");
            inserir.setInt(1, admi.getSiape());
            inserir.setString(2, admi.getNome());
            inserir.setString(3, admi.getEmail());
            inserir.setInt(4, admi.getRamal());
            inserir.setString(5, admi.getPermissao());
            inserir.setString(6, admi.getConPessoal());
            inserir.setString(7, admi.getLogin());
            inserir.setString(8, admi.getSenha());

            inserir.execute();
            inserir.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public void editarServidor(Administrador admi) {

        PreparedStatement editar = null;

        try {
            editar = conectar.prepareStatement("UPDATE Servidor SET  nome_serv = ?,email_serv = ?,ramal_serv = ? ,permissao_serv = ?,contato_Pessoal_serv = ?,login_serv = ?,senha_serv = ? WHERE id_serv = ?");

            editar.setString(1, admi.getNome());
            editar.setString(2, admi.getEmail());
            editar.setInt(3, admi.getRamal());
            editar.setString(4, admi.getPermissao());
            editar.setString(5, admi.getConPessoal());
            editar.setString(6, admi.getLogin());
            editar.setString(7, admi.getSenha());
            editar.setInt(8, admi.getSiape());

            editar.execute();
            editar.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public void excluirServidor(Administrador admi) {

        PreparedStatement excluir = null;

        try {
            excluir = conectar.prepareStatement("DELETE FROM Servidor WHERE id_serv = ?");
            excluir.setInt(1, admi.getSiape());

            excluir.execute();
            excluir.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir o Cadastro: " + ex);
        }

    }

    public List<Administrador> consultarServidor(int siape) {

        PreparedStatement consulta = null;
        ResultSet resultado = null;

        List<Administrador> admi = new ArrayList<>();

        try {
            consulta = conectar.prepareStatement("SELECT * FROM Servidor WHERE id_serv LIKE ?");
            consulta.setInt(1, siape);

            resultado = consulta.executeQuery();

            while (resultado.next()) {

                Administrador admin = new Administrador();

                admin.setSiape(resultado.getInt("id_serv"));
                admin.setNome(resultado.getString("nome_serv"));
                admin.setEmail(resultado.getString("email_serv"));
                admin.setRamal(resultado.getInt("ramal_serv"));
                admin.setEmail(resultado.getString("email_serv"));
                admin.setPermissao(resultado.getString("permissao_serv"));
                admin.setConPessoal(resultado.getString("contato_Pessoal_serv"));
                admin.setLogin(resultado.getString("login_serv"));
                admin.setSenha(resultado.getString("senha_serv"));

                admi.add(admin);
            }

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return admi;

    }

    public List<Administrador> listarServidor() {

        PreparedStatement listar = null;
        ResultSet resultado = null;

        List<Administrador> Servidor = new ArrayList<>();

        try {
            listar = conectar.prepareStatement("SELECT * FROM Servidor");
            resultado = listar.executeQuery();

            while (resultado.next()) {

                Administrador admin = new Administrador();

                admin.setSiape(resultado.getInt("id_serv"));
                admin.setNome(resultado.getString("nome_serv"));
                admin.setEmail(resultado.getString("email_serv"));
                admin.setRamal(resultado.getInt("ramal_serv"));
                admin.setPermissao(resultado.getString("permissao_serv"));
                admin.setConPessoal(resultado.getString("contato_Pessoal_serv"));
                admin.setLogin(resultado.getString("login_serv"));
                admin.setSenha(resultado.getString("senha_serv"));
                Servidor.add(admin);

            }
            return Servidor;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public List<Servidor> listarTodosServidor() {
        System.out.println("1");
        PreparedStatement listarS = null;
        ResultSet resultadoS = null;

        List<Servidor> servidores = new ArrayList<>();

        try {
            System.out.println("1");
            listarS = conectar.prepareStatement("SELECT nome_serv FROM Servidor");
            resultadoS = listarS.executeQuery();

            while (resultadoS.next()) {
                System.out.println("2");
                Servidor servidor;
                servidor = new Servidor();

                servidor.setNome(resultadoS.getString("nome_serv"));

                servidores.add(servidor);

            }
            return servidores;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

}
