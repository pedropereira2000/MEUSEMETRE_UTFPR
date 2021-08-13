package exe4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MutantesTest {

	@Test
	void testM1() {
		var orig = new Original();
		assertEquals("MCD",orig.coverterParaRomano(1400));
		
		var ciclop = new Mutante1();
		assertEquals("MCD",ciclop.coverterParaRomano(1400));
	}
	
	@Test
	void testM2() {
		var orig = new Original();
		assertEquals("MMMMMDCCC",orig.coverterParaRomano(5800));
		
		var mutano = new Mutante2();
		assertEquals("MMMMMDCCC",mutano.coverterParaRomano(5800));
	}

	@Test
	void testM3() {
		var orig = new Original();
		assertEquals("DCCCXLVII",orig.coverterParaRomano(847));
		
		var vampira = new Mutante3();
		assertEquals("DCCCXLVII",vampira.coverterParaRomano(847));
		
		//São equivalentes pois no laço while trocando o operador > por !=
		//Não altera as letras produzidas e nem faz o mutante parar antes,
		//pois os valores vão sempre chegar a zero e sair do laço
	}
}
