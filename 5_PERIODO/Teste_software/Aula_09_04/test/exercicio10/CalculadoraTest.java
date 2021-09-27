package exercicio10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculadoraTest {

	// 1, 2, 3, 4, 5, 6, 7 e 9
	@Test
	void testNumeroNegativo() throws Exception {
		var cal = new Calculadora();
		
		var res = cal.somatoriaLimitada(-1, 5);
		assertEquals(1,res);
	}
	
	// 1, 2, 3, 4, 5, 6, 7 e 9
	@Test
	void testNumeroPositivoMenor() throws Exception {
		var cal = new Calculadora();
		
		var res = cal.somatoriaLimitada(2, 5);
		assertEquals(3,res);
	}
	
	// 1, 2, 3, 4, 5, 6, 7 e 9
		@Test
		void testNumeroPositivoMaior() throws Exception {
			var cal = new Calculadora();
			
			assertThrows(Exception.class,() -> {
				cal.somatoriaLimitada(10, 5);
			});
		}

}
