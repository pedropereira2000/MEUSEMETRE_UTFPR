package exercicio8;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class EmissarioTest {

	//1 e 2
	@Test
	void testNomeNulo() {
		var daoMock = mock(UsuarioDAO.class);
		var emailMock = mock(ServidorDeEmail.class);
		var criptoMock = mock(Criptografia.class);
		var emissario = new Emissario(daoMock, emailMock);
		emissario.setCriptografia(criptoMock);
		
		var res = emissario.enviarPara(null);
		
		assertEquals("nomes nao informados", res);
	}
	
	//1, 3, 4 e 6
	@Test
	void testSemUsuarios() {
		var daoMock = mock(UsuarioDAO.class);
		when(daoMock.getAllUsuarios()).thenReturn(null);
		var emailMock = mock(ServidorDeEmail.class);
		var criptoMock = mock(Criptografia.class);
		var emissario = new Emissario(daoMock, emailMock);
		emissario.setCriptografia(criptoMock);
		
		var res = emissario.enviarPara(new ArrayList<String>());
		assertEquals("nao ha usuarios", res);
	}
	
	//1, 3, 4, 5 e 6
		@Test
		void testSemUsuarios2() {
			var daoMock = mock(UsuarioDAO.class);
			when(daoMock.getAllUsuarios()).thenReturn(new ArrayList<Usuario>());
			var emailMock = mock(ServidorDeEmail.class);
			var criptoMock = mock(Criptografia.class);
			var emissario = new Emissario(daoMock, emailMock);
			emissario.setCriptografia(criptoMock);
			
			var res = emissario.enviarPara(new ArrayList<String>());
			assertEquals("nao ha usuarios", res);
		}
		
	//1, 3, 4, 5, 7, 8, 15 e 17
		@Test
		void testUsuarioNaoEncontrado() {
			var daoMock = mock(UsuarioDAO.class);
			var usuarios = new ArrayList<Usuario>();
			usuarios.add(new Usuario("Pedro","pedropereira@fasf.com"));
			when(daoMock.getAllUsuarios()).thenReturn(usuarios);
			var emailMock = mock(ServidorDeEmail.class);
			var criptoMock = mock(Criptografia.class);
			var emissario = new Emissario(daoMock, emailMock);
			emissario.setCriptografia(criptoMock);
			
			var res = emissario.enviarPara(new ArrayList<String>());
			assertEquals("usuarios nao encontrados", res);
		}
			
	//1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 8, 15 e 16
		@Test
		void test() {
			var daoMock = mock(UsuarioDAO.class);
			var usuarios = new ArrayList<Usuario>();
			usuarios.add(new Usuario("Pedro","pedropereira@fasf.com"));
			when(daoMock.getAllUsuarios()).thenReturn(usuarios);
			var emailMock = mock(ServidorDeEmail.class);
			when(emailMock.enviar(anyString())).thenReturn(true);
			var criptoMock = mock(Criptografia.class);
			when(criptoMock.criptografar(anyString())).thenReturn("yyaaa");
			var emissario = new Emissario(daoMock, emailMock);
			emissario.setCriptografia(criptoMock);
			
			var arr = new ArrayList<String>();
			arr.add("Pedro");
			
			var res = emissario.enviarPara(arr);
			assertEquals("mensagens enviadas", res);
		}

}
