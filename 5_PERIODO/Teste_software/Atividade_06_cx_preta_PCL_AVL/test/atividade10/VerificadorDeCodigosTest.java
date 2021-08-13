package atividade10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class VerificadorDeCodigosTest {

	@Test
	void testCT1() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Sucesso", bd.cadastrarTurma("GH52J", "AJ51", 25));
		
	}
	
	@Test
	void testCT2() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52JJJ")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("GH52JJJ", "AJ51", 25));
		
	}
	
	@Test
	void testCT3() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("GH", "AJ51", 25));
		
	}
	
	@Test
	void testCT4() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("1H52J")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("1H52J", "AJ51", 25));
		
	}
	
	@Test
	void testCT5() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("G252J")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("G252J", "AJ51", 25));
		
	}
	
	@Test
	void testCT6() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("9152J")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("9152J", "AJ51", 25));
		
	}
	
	@Test
	void testCT7() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GHT2J")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("GHT2J", "AJ51", 25));
		
	}
	
	@Test
	void testCT8() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH5YJ")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("GH5YJ", "AJ51", 25));
		
	}
	
	@Test
	void testCT9() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GHTYJ")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("GHTYJ", "AJ51", 25));
		
	}
	
	@Test
	void testCT10() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH529")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de disciplina invalido", bd.cadastrarTurma("GH529", "AJ51", 25));
		
	}
	
	@Test
	void testCT11() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AAA111")).thenReturn(false);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de turma invalido", bd.cadastrarTurma("GH52J", "AAA111", 25));
		
	}
	
	@Test
	void testCT12() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("A1")).thenReturn(false);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de turma invalido", bd.cadastrarTurma("GH52J", "A1", 25));
		
	}
	
	@Test
	void testCT13() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("1D54")).thenReturn(false);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de turma invalido", bd.cadastrarTurma("GH52J", "1D54", 25));
		
	}
	
	@Test
	void testCT14() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("A254")).thenReturn(false);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de turma invalido", bd.cadastrarTurma("GH52J", "A254", 25));
		
	}
	
	@Test
	void testCT15() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("1364")).thenReturn(false);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de turma invalido", bd.cadastrarTurma("GH52J", "1364", 25));
		
	}
	
	@Test
	void testCT16() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AST4")).thenReturn(false);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de turma invalido", bd.cadastrarTurma("GH52J", "AST4", 25));
		
	}
	
	@Test
	void testCT17() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("ASTY")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Codigo de turma invalido", bd.cadastrarTurma("GH52J", "ASTY", 25));
		
	}
	
	@Test
	void testCT18() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Numero de alunos invalido", bd.cadastrarTurma("GH52J", "AJ51", 0));
		
	}
	
	@Test
	void testCT19() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Numero de alunos invalido", bd.cadastrarTurma("GH52J", "AJ51", 55));
		
	}
	
	@Test
	void testCT20() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Sucesso", bd.cadastrarTurma("GH52J", "AJ51", 3));
		
	}
	
	@Test
	void testCT21() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Sucesso", bd.cadastrarTurma("GH52J", "AJ51", 44));
		
	}
	
	@Test
	void testCT22() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Numero de alunos invalido", bd.cadastrarTurma("GH52J", "AJ51", 2));
		
	}

	@Test
	void testCT23() {
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("GH52J")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("AJ51")).thenReturn(true);
		var bd = new BancoDeDados(verfCodMock);
		
		assertEquals("Numero de alunos invalido", bd.cadastrarTurma("GH52J", "AJ51", 45));
		
	}

}
