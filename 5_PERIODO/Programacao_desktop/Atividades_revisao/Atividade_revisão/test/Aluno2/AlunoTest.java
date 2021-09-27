package Aluno2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlunoTest {

	@Test
	void testCalcMedias() {
		var alu = new Aluno();
		alu.recebeNota1(5.0);
		alu.recebeNota2(7.0);
		alu.recebeNota3(9.0);
		alu.recebeNota4(7.0);
		assertEquals(7.0, alu.imprimeMedia(), 0.01);
	}

}
