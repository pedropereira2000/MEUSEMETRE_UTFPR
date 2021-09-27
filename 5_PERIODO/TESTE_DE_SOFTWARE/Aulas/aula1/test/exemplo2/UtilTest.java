package exemplo2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilTest {

	@Test
	void testGerarNumeroAleatorioInicioNegativo() {
		Util util = new Util();
		assertEquals(-1, util.gerarNumeroAleatorio(-10, 200));
	}

	@Test
	void testGerarNumeroAleatorioNoQualFimNegativo() {
		Util util = new Util();
		assertEquals(-1, util.gerarNumeroAleatorio(10, -200));
	}
	
	@Test
	void testGerarNumeroAleatorioIntervalo1A10() {
		Util util = new Util();
		var res = util.gerarNumeroAleatorio(1, 10);
		assertTrue(res >= 1 && res <= 10);
	}
}
