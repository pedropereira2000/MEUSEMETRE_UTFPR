package Exercicio9;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import Exercicio9.CalcularDesconto;
import Exercicio9.DescontoDAO;

class CalcularDescontoTest {

	@Test
	void testCT1() {
		var descontoDaoMock = mock(DescontoDAO.class);
		when(descontoDaoMock.getDesconto("novo", false)).thenReturn(10);
		
		var calcDesc = new CalcularDesconto(descontoDaoMock);
		var res = calcDesc.realizarCalculo("novo", false); 
				
		assertEquals("10% de desconto na Anuidade", res);
	}
	
	@Test
	void testCT2() {
		var descontoDaoMock = mock(DescontoDAO.class);
		when(descontoDaoMock.getDesconto("ouro", false)).thenReturn(12);
		
		var calcDesc = new CalcularDesconto(descontoDaoMock);
		var res = calcDesc.realizarCalculo("ouro", false); 
				
		assertEquals("12% de desconto na Anuidade", res);
	}

	@Test
	void testCT3() {
		var descontoDaoMock = mock(DescontoDAO.class);
		when(descontoDaoMock.getDesconto("ouro", true)).thenReturn(37);
		
		var calcDesc = new CalcularDesconto(descontoDaoMock);
		var res = calcDesc.realizarCalculo("ouro", true); 
				
		assertEquals("37% de desconto na Anuidade", res);
	}
	
	@Test
	void testCT4() {
		var descontoDaoMock = mock(DescontoDAO.class);
		when(descontoDaoMock.getDesconto("", true)).thenReturn(25);
		
		var calcDesc = new CalcularDesconto(descontoDaoMock);
		var res = calcDesc.realizarCalculo("", true); 
				
		assertEquals("25% de desconto", res);
	}
}
