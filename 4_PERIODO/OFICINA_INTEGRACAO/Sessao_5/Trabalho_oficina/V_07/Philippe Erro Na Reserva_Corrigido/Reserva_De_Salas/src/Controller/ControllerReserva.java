package Controller;

import Connection.ConnectionFactory;
import java.sql.Connection;
import javax.swing.JOptionPane;
import Bean.Ambiente;
import Bean.Administrador;
import Bean.Leitura;
import Dao.AmbienteDAO;
import Dao.AdministradorDAO;
import java.util.List;


public class ControllerReserva {

    
    public static void main(String[] args) {
        
        Leitura l = new Leitura();
        Ambiente ambiente = new Ambiente();
        Administrador admi = new Administrador();
        AmbienteDAO dao = new AmbienteDAO();
        AdministradorDAO admiDao = new AdministradorDAO();
        
        //admi.setSiape(Integer.parseInt(l.inData("ID:")));
        //admi.setNome(l.inData("Nome:"));
        //admi.setSenha(l.inData("Senha: "));
        //admi.setEmail(l.inData("Email: "));
        //admi.setRamal(Integer.parseInt(l.inData("Ramal:")));
        //admi.setFoto(l.inData("Imagem:"));
        //admi.setPermissao(Integer.parseInt(l.inData("Tipo Permissao:")));
        
        //admiDao.cadastrarServidor(admi);
        //admiDao.editarServidor(admi);
        //admiDao.excluirServidor(admi);
        /*
        List<Administrador> listaServidores = admiDao.listarServidor();
        
        
        for(Administrador admin : listaServidores){
            System.out.println("ID:"+admin.getSiape());
            System.out.println("Nome:"+admin.getNome());
            System.out.println("Email:"+admin.getEmail());
            System.out.println("Ramal:"+admin.getRamal());
            System.out.println("Tipo Permissão"+admin.getPermissao());
        }
        */
        List<Administrador> listaServidores = admiDao.consultarServidor(2102692);
        
        
        for(Administrador admin : listaServidores){
            System.out.println("ID:"+admin.getSiape());
            System.out.println("Nome:"+admin.getNome());
            System.out.println("Email:"+admin.getEmail());
            System.out.println("Ramal:"+admin.getRamal());
            System.out.println("Tipo Permissão"+admin.getPermissao());
        }
        
        
        /*LISTAR AMBIENTES*/
        /*
        List<Ambiente> listaAmbientes = dao.listarAmbientes();
        
        for(Ambiente ambi : listaAmbientes){
            System.out.println("ID:"+ambi.getId());
            System.out.println("BLOCO:"+ambi.getBloco());
            System.out.println("NUMERO:"+ambi.getSalaNum());
            System.out.println("TIPO"+ambi.getTipoSala());
        }
        */
        
        /*Consultar Ambiente por id*/
        /*
        List<Ambiente> consultarAmbientes = dao.consultarAmbiente(2);
        
        
        for(Ambiente ambi : consultarAmbientes){
            System.out.println("ID:"+ambi.getId());
            System.out.println("BLOCO:"+ambi.getBloco());
            System.out.println("NUMERO:"+ambi.getSalaNum());
            System.out.println("TIPO"+ambi.getTipoSala());
        }
        
        */

        //ambiente.setId(Integer.parseInt(l.inData("Id:")));
        //ambiente.setBloco(l.inData("Bloco:"));
        //ambiente.setSalaNum(l.inData("Numero:"));
        //ambiente.setTipoSala(l.inData("Tipo:"));
        
        //dao.cadastrarAmbiente(ambiente);
        //dao.editarAmbiente(ambiente);
        //dao.excluirAmbiente(ambiente);
        
        
        
        
    }
    
}
