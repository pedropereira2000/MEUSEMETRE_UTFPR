package Exercicio3_CalculadoraImc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exercicio3_CalculadoraImc.IMCCalculadora;
import Exercicio3_ImcStatus.IMCStatus;
import Exercicio3_Pessoa.Pessoa;

class IMCCalculadoraTest {

	@Test
	void testLancarExcecao() {
		Pessoa p = new Pessoa("pedro",-85.0,-1.75);
		IMCCalculadora calc = new IMCCalculadora();
		assertThrows(Exception.class, () -> {
			calc.calcular(p);
		});
	}
	
	@Test
	void testAbaixoPeso() {
		Pessoa p = new Pessoa("pedro",50,1.75);
		IMCCalculadora calc = new IMCCalculadora();
		IMCStatus stat = calc.calcular(p);
		assertEquals("abaixo do peso",stat.getClassificacao());
		assertEquals(16.3,stat.getIMC(),0.1);
	}
	
	@Test
	void testNormal() {
		Pessoa p = new Pessoa("pedro",60,1.75);
		IMCCalculadora calc = new IMCCalculadora();
		IMCStatus stat = calc.calcular(p);
		assertEquals("normal",stat.getClassificacao());
		assertEquals(19.6,stat.getIMC(),0.1);
	}
	
	@Test
	void testAcimaPeso() {
		Pessoa p = new Pessoa("pedro",80,1.75);
		IMCCalculadora calc = new IMCCalculadora();
		IMCStatus stat = calc.calcular(p);
		assertEquals("acima do peso",stat.getClassificacao());
		assertEquals(26.1,stat.getIMC(),0.1);
	}
	
	@Test
	void testObeso() {
		Pessoa p = new Pessoa("pedro",100,1.75);
		IMCCalculadora calc = new IMCCalculadora();
		IMCStatus stat = calc.calcular(p);
		assertEquals("obeso",stat.getClassificacao());
		assertEquals(32.7,stat.getIMC(),0.1);
	}

}
