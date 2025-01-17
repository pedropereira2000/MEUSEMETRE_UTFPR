package Exercicio12;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import Exercicio12.Calculadora;

import Exercicio12.CustoDAO;

class CalculadoraTest {

	@Test
	void testCT1() throws Exception{
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("norte")).thenReturn(1);
		
		var calc = new Calculadora(custoDaoMock);
		var res = calc.calcularFrete("norte", 150);
		
		assertEquals(150, res);

	}
	
	@Test
	void testCT2() throws Exception{
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("leste")).thenReturn(-10);
		
		var calc = new Calculadora(custoDaoMock);
		assertThrows(Exception.class, () -> {
			calc.calcularFrete("leste", 150);
		});

	}
	
	@Test
	void testCT3(){
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("norte")).thenReturn(1);
		
		var calc = new Calculadora(custoDaoMock);
		assertThrows(Exception.class, () -> {
			calc.calcularFrete("norte", -200);
		});

	}
	
	@Test
	void testCT4(){
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("norte")).thenReturn(1);
		
		var calc = new Calculadora(custoDaoMock);
		assertThrows(Exception.class, () -> {
			calc.calcularFrete("norte", 3000000);
		});

	}
	
	@Test
	void testCT5() throws Exception{
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("sul")).thenReturn(2);
		
		var calc = new Calculadora(custoDaoMock);
		var res = calc.calcularFrete("sul", 1);
		
		assertEquals(2, res);
	}
	
	@Test
	void testCT6() throws Exception{
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("norte")).thenReturn(1);
		
		var calc = new Calculadora(custoDaoMock);
		var res = calc.calcularFrete("norte", 2000000);
		
		assertEquals(2000000, res);
	}
	
	@Test
	void testCT7(){
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("norte")).thenReturn(1);
		
		var calc = new Calculadora(custoDaoMock);
		assertThrows(Exception.class, () -> {
			calc.calcularFrete("norte", 0);
		});

	}
	
	@Test
	void testCT8(){
		var custoDaoMock = mock(CustoDAO.class);
		
		when(custoDaoMock.getCustoPorGrama("sul")).thenReturn(2);
		
		var calc = new Calculadora(custoDaoMock);
		assertThrows(Exception.class, () -> {
			calc.calcularFrete("sul", 2000001);
		});

	}

}

