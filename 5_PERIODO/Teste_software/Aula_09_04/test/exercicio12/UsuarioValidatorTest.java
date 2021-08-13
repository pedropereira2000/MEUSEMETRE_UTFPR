package exercicio12;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class UsuarioValidatorTest {
	//1, 2
	@Test
	void testNomeMenorIgualTres() {
		var userDaoMock = mock(UsuarioDAO.class);
		var user = new UsuarioValidator(userDaoMock);
		var usuario = new Usuario("Ped", "123", "123");
		var senhaMock = mock(SenhaValidator.class);
		var res = assertThrows(Exception.class, () -> {
			user.ehUsuarioValido(usuario);
		}).getMessage();
		
		assertEquals("nome do usuario precisa de pelo menos 3 caracteres", res);
	}

	//1, 3, 4
	@Test
	void testSenhaIgualValida() {
		var userDaoMock = mock(UsuarioDAO.class);
		var user = new UsuarioValidator(userDaoMock);
		var usuario = new Usuario("Pedro", "123", "1234");
		var senhaMock = mock(SenhaValidator.class);
		var res = assertThrows(Exception.class, () -> {
			user.ehUsuarioValido(usuario);
		}).getMessage();
		
		assertEquals("senhas diferentes", res);
	}
	
	//1, 3, 5 e 6
	@Test
	void testUsuarioJaExiste() {
		var userDaoMock = mock(UsuarioDAO.class);
		when(userDaoMock.existe(anyString())).thenReturn(true);;
		var user = new UsuarioValidator(userDaoMock);
		var usuario = new Usuario("Pedro", "123", "123");
		var senhaMock = mock(SenhaValidator.class);
		var res = assertThrows(Exception.class, () -> {
			user.ehUsuarioValido(usuario);
		}).getMessage();
		
		assertEquals("usuario ja existe", res);
	}
	
	//1, 3, 5, 7 e 8
	@Test
	void testSenhaInvalida(){
		var userDaoMock = mock(UsuarioDAO.class);
		when(userDaoMock.existe(anyString())).thenReturn(false);;
		var user = new UsuarioValidator(userDaoMock);
		var usuario = new Usuario("Pedro", "123", "123");
		var senhaMock = mock(SenhaValidator.class);
		when(senhaMock.verificar(anyString())).thenReturn(true);
		user.setSenhaValidator(senhaMock);
		var res = assertThrows(Exception.class, () -> {
			user.ehUsuarioValido(usuario);
		}).getMessage();
		
		assertEquals("senha invalida", res);
	}
	
	//1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 16, ..., 11, 17
	@Test
	void testNomeValido() throws Exception {
		var userDaoMock = mock(UsuarioDAO.class);
		when(userDaoMock.existe(anyString())).thenReturn(false);;
		var user = new UsuarioValidator(userDaoMock);
		var usuario = new Usuario("Pedro", "123", "123");
		var senhaMock = mock(SenhaValidator.class);
		when(senhaMock.verificar(anyString())).thenReturn(false);
		user.setSenhaValidator(senhaMock);
		var res = user.ehUsuarioValido(usuario);
		assertEquals(true, res);
	}
	
	//1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 16, ..., 11, 17
	@Test
	void testNomeInvalido() throws Exception {
		var userDaoMock = mock(UsuarioDAO.class);
		when(userDaoMock.existe(anyString())).thenReturn(false);;
		var user = new UsuarioValidator(userDaoMock);
		var usuario = new Usuario("Pedr@$ 0", "123", "123");
		var senhaMock = mock(SenhaValidator.class);
		when(senhaMock.verificar(anyString())).thenReturn(false);
		user.setSenhaValidator(senhaMock);
		var res = user.ehUsuarioValido(usuario);
		assertEquals(false, res);
	}
		
}
