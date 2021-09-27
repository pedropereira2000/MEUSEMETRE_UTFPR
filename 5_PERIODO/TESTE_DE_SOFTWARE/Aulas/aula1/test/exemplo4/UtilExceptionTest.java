package exemplo4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilExceptionTest {

	@Test
	void testGerarNumeroAleatorioInicioNegativo() throws Exception {
		var u = new UtilException();
		var res = u.gerarNumeroAleatorio(1, 10);
		assertTrue(res >=1 && res <= 10);
	}
	
	@Test
	void testExceptionIntervaloInvalido() {
		var u = new UtilException();
		
		var ex = assertThrows(Exception.class, () -> {
			u.gerarNumeroAleatorio(-1, 10);
		});
		assertEquals("intervalo negativo", ex.getMessage());
	}
}
