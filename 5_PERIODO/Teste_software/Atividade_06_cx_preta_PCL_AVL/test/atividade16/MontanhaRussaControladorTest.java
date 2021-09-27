package atividade16;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class MontanhaRussaControladorTest {

	@Test
	void testCT1() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		var res = controller.autorizar("Pedro Pereira", 10);
		
		assertEquals("acompanhado dos pais", res);
		
		
	}

	@Test
	void testCT2() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		var res = controller.autorizar("Pedro Pereira", 55);
		
		assertEquals("autorizado", res);
		
		
	}

	@Test
	void testCT3() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		var res = controller.autorizar("Pedro Pereira", 102);
		
		assertEquals("acompanhado do responsavel legal", res);
		
		
	}
	
	@Test
	void testCT4() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Jorge Ro$$i")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("Jorge Ro$$i", 102));		
		
	}
	
	@Test
	void testCT5() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("Pedro Pereira", -15));		
		
	}
	
	@Test
	void testCT6() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("Pedro Pereira", 152));		
		
	}
	
	@Test
	void testCT7() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(false);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("Pedro Pereira", 12));		
		
	}
	
	@Test
	void testCT8() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		var res = controller.autorizar("Pedro Pereira", 1);
		
		assertEquals("acompanhado dos pais", res);
		
		
	}
	
	@Test
	void testCT9() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		var res = controller.autorizar("Pedro Pereira", 120);
		
		assertEquals("acompanhado do responsavel legal", res);
		
		
	}
	
	@Test
	void testCT10() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		var res = controller.autorizar("Pedro", 12);
		
		assertEquals("acompanhado dos pais", res);
		
		
	}
	
	@Test
	void testCT12() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("Pedro Pereira", 0));		
		
	}
	
	@Test
	void testCT13() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("Pedro Pereira", 121));		
		
	}
	
	@Test
	void testCT14() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("", 12));		
		
	}
	
	@Test
	void testCT15() throws Exception{
		var cliDaoMock = mock(ClienteDAO.class);
		
		when(cliDaoMock.ehCliente("Pedro Pereira")).thenReturn(true);
		
		var controller = new MontanhaRussaControlador(cliDaoMock);
		assertThrows(Exception.class, () -> controller.autorizar("Pedro Henrique Pereira", 12));		
		
	}
	
}