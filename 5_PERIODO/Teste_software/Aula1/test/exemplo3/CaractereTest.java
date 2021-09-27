package exemplo3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaractereTest {
	
	Caractere car;
	char[] cadeia = {'a', 'b', 'c', 'd'};
	
	@BeforeEach
	void beforeEach() {
		car = new Caractere();
	}

	@Test
	void testAcharCharactereComprimentoInvalido() {
		var res = car.acharCaracter(25, cadeia, 'a');
		assertEquals("comprimento invalido", res);
	}
	
	@Test
	void testAcharCharactereTamanhoDaCadeiaDiferente() {
		var res = car.acharCaracter(6, cadeia, 'a');
		assertEquals("comprimento fornecido diferente do comprimento real", res);
	}
	
	@Test
	void testAcharCharactereNaoExiste() {
		var res = car.acharCaracter(4, cadeia, 'e');
		assertEquals("caracter nao encontrado", res);
	}

	@Test
	void testAcharCharactereExiste() {
		var res = car.acharCaracter(4, cadeia, 'b');
		assertEquals("1", res);
	}
}