package Inteiro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InteiroTest {

	@Test
	void testSoma() {
		var inte = new Inteiro();
		inte.carregaValor(511);
		assertEquals(600,inte.soma(89));
	}
	
	@Test
	void testSub() {
		var inte = new Inteiro();
		inte.carregaValor(511);
		assertEquals(500,inte.subtrai(11));
	}
	
	@Test
	void testMulti() {
		var inte = new Inteiro();
		inte.carregaValor(200);
		assertEquals(400,inte.multiplicaPor(2));
	}
	
	@Test
	void testDiv() {
		var inte = new Inteiro();
		inte.carregaValor(5);
		assertEquals(2.5,inte.dividePor(2));
	}

}
