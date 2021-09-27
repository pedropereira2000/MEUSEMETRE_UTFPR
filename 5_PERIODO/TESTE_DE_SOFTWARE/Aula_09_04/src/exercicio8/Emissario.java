package exercicio8;

import java.util.ArrayList;

public class Emissario {
	private UsuarioDAO usuarioDAO;
	private ServidorDeEmail servidorEmail;
	private Criptografia criptografia;

	public Emissario(UsuarioDAO usuarioDAO, ServidorDeEmail servidorEmail) {
		this.usuarioDAO = usuarioDAO;
		this.servidorEmail = servidorEmail;
	}

	public void setCriptografia(Criptografia criptografia) {
		this.criptografia = criptografia;
	}

	
	public String enviarPara(ArrayList<String> nomes) {
		/*1*/if (nomes == null)
			/*2*/return "nomes nao informados";

		/*3*/ArrayList<Usuario> usuarios = usuarioDAO.getAllUsuarios();
		/*4*/if (usuarios == null ||/*5*/ usuarios.size() == 0)
			/*6*/return "nao ha usuarios";

		/*7*/boolean msgsEnviadas = false;
		/*8*/for (String nome : nomes) {
			/*9*/for (Usuario usuario : usuarios) {
				/*10*/if (usuario.getNome().equals(nome)) {
					/*11*/String mensagem = criptografia.criptografar("mensagem secreta");
					/*11*/boolean foiEnviado = servidorEmail.enviar("TO: " + usuario.getEmail() + " " + mensagem);
					/*12*/if (foiEnviado) {
						/*13*/msgsEnviadas = true;
						/*13*/break;
					} /*14*/else
						/*14*/return "servidor de email offline";
				}
			}
		}

		/*15*/if (msgsEnviadas)
			/*16*/return "mensagens enviadas";
		/*17*/else
			/*17*/return "usuarios nao encontrados";
	}

}
