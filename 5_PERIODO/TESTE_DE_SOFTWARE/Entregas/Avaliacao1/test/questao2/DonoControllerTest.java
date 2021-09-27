package questao2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DonoControllerTest {

	//Testando quando o sobrenome é inválido
	@Test
	void testSobrenomeInvalido() {
		var strValMock = mock(StringValidador.class);
		when( strValMock.validarSobrenome("B12441vavasd") ).thenReturn("invalido");
		
		var visDAOMock = mock(VisitasDAO.class);
		var dnDAOMock = mock(DonoDAO.class);
		var dnControl = new DonoController(strValMock,visDAOMock,dnDAOMock);
			
		assertThrows(Exception.class, () -> dnControl.processarFormBusca("B12441vavasd"));

		verify(strValMock).validarSobrenome(anyString());
	}
	
	//Testando quando não é encontrado um dono para o sobrenome
	@Test
	void testSobrenomeNaoEncontrado() throws Exception {
		var listDon = new ArrayList<Dono> ();
		var strValMock = mock(StringValidador.class);
		when( strValMock.validarSobrenome("Pereira") ).thenReturn(null);
		
		var visDAOMock = mock(VisitasDAO.class);
		
		var dnDAOMock = mock(DonoDAO.class);
		when( dnDAOMock.findBySobrenome("Pereira") ).thenReturn(listDon);
		
		var dnControl = new DonoController(strValMock,visDAOMock,dnDAOMock);
			
		var resultado = dnControl.processarFormBusca("Pereira");
		
		assertEquals("donos/encontrar", resultado.getEndereco());
		assertEquals("ERRO: nao encontrado", resultado.getDados());
		
		verify(strValMock).validarSobrenome(anyString());
		verify(dnDAOMock).findBySobrenome(anyString());
		
	}
	
	//Testando quando é encontrado somente um dono para o sobrenome
	@Test
	void testSobrenomeEncontradoUm() throws Exception {
		var don = new Dono(30, "Silva");
		
		var listDon = new ArrayList<Dono> ();
		listDon.add(don);
		
		var strValMock = mock(StringValidador.class);
		when( strValMock.validarSobrenome("Silva") ).thenReturn(null);
		
		var visDAOMock = mock(VisitasDAO.class);
		when( visDAOMock.incrementarVisitasPara("donos/30") ).thenReturn(1);
		
		var dnDAOMock = mock(DonoDAO.class);
		when( dnDAOMock.findBySobrenome("Silva") ).thenReturn(listDon);
		
		var dnControl = new DonoController(strValMock,visDAOMock,dnDAOMock);
			
		var resultado = dnControl.processarFormBusca("Silva");
		assertEquals("donos/30", resultado.getEndereco());
		assertEquals(1, resultado.getDados());
		
		verify(strValMock).validarSobrenome(anyString());
		verify(visDAOMock).incrementarVisitasPara(anyString());
		verify(dnDAOMock).findBySobrenome(anyString());
		
	}
	
	//Testando quando é encontrado mais de um dono para o sobrenome
	@Test
	void testSobrenomeEncontradoMaisDeUm() throws Exception {
		var listDon = new ArrayList<Dono> ();
		listDon.add(new Dono(1, "Silva"));
		listDon.add(new Dono(2, "Silva"));
		listDon.add(new Dono(3, "Silva"));
		
		var strValMock = mock(StringValidador.class);
		when( strValMock.validarSobrenome("Silva") ).thenReturn(null);
		
		var visDAOMock = mock(VisitasDAO.class);
		when( visDAOMock.incrementarVisitasPara("donos/listar") ).thenReturn(3);
		
		var dnDAOMock = mock(DonoDAO.class);
		when( dnDAOMock.findBySobrenome("Silva") ).thenReturn(listDon);
		
		var dnControl = new DonoController(strValMock,visDAOMock,dnDAOMock);
			
		var resultado = dnControl.processarFormBusca("Silva");
		assertEquals("donos/listar", resultado.getEndereco());
		assertEquals(3, resultado.getDados());
		
		verify(strValMock).validarSobrenome(anyString());
		verify(visDAOMock).incrementarVisitasPara(anyString());
		verify(dnDAOMock).findBySobrenome(anyString());
		
	}
	
	

}
