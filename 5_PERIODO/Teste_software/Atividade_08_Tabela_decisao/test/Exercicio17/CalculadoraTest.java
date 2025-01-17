package Exercicio17;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculadoraTest {

	@Test
	void testCT1() {
		var calc = new Calculadora();
		var res = calc.calcularSalario("assalariado", 40);
		
		assertEquals(4000, res.getValorSalario());	
		
	}

	@Test
	void testCT2() {
		var calc = new Calculadora();
		var res = calc.calcularSalario("assalariado", 20);
		
		assertEquals(1500, res.getValorSalario());	
		
	}
	
	@Test
	void testCT3() {
		var calc = new Calculadora();
		var res = calc.calcularSalario("horista", 40);
		
		assertEquals(600, res.getValorSalario());
		assertEquals("", res.getPendencia());
		
	}
	
	@Test
	void testCT4() {
		var calc = new Calculadora();
		var res = calc.calcularSalario("horista", 20);
		
		assertEquals(300, res.getValorSalario());
		assertEquals("relatorio de ausencia", res.getPendencia());
		
	}
	
	@Test
	void testCT5() {
		var calc = new Calculadora();
		var res = calc.calcularSalario("horista", 70);
		
		assertEquals(1050, res.getValorSalario());
		assertEquals("autorizacao de hora extra", res.getPendencia());
		
	}
	
	
}
