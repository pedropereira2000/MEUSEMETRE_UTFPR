package ex5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AnalisadorTest {

	@Test
	void testExcecao() {
		var a = new Analisador();
		//var ex = assertThrows(Exception.class, () -> a.analisar(null));
		//assertEquals("lista nula ou vazia",ex.getMessage());
		assertEquals("lista nula ou vazia",(assertThrows(Exception.class, () -> a.analisar(null))).getMessage());
	}
	
	//Normal
	@Test
	void testContextoSemConcorrencia() throws Exception{
		var a = new Analisador();
		var cands = new ArrayList<Candidato>();
		cands.add(new Candidato('F', 33));
		var e = a.analisar(cands);
		assertEquals("sem concorrencia",e.getContexto());
		assertEquals(1,e.getMulheres());
		assertEquals(0,e.getHomens());
		assertEquals(33.0,e.getIdadeMedia(),0.001);
	}
	
	//Polarizada
	@Test
	void testContextoPolarizada() throws Exception{
		var a = new Analisador();
		var cands = new ArrayList<Candidato>();
		cands.add(new Candidato('F', 30));
		cands.add(new Candidato('M', 40));
		var e = a.analisar(cands);
		assertEquals("polarizada",e.getContexto());
		assertEquals(1,e.getMulheres());
		assertEquals(1,e.getHomens());
		assertEquals(35.0,e.getIdadeMedia(),0.001);
	}

	@Test
	void testMaisCandidatas() throws Exception{
		var a = new Analisador();
		var cands = new ArrayList<Candidato>();
		cands.add(new Candidato('F', 30));
		cands.add(new Candidato('M', 40));
		cands.add(new Candidato('F', 20));
		var e = a.analisar(cands);
		assertEquals("normal",e.getContexto());
		assertEquals(2,e.getMulheres());
		assertEquals(1,e.getHomens());
		assertEquals(30.0,e.getIdadeMedia(),0.001);
	}
	
	@Test
	void testContextoNormals() throws Exception{
		var a = new Analisador();
		var cands = new ArrayList<Candidato>();
		cands.add(new Candidato('F', 30));
		cands.add(new Candidato('M', 40));
		cands.add(new Candidato('F', 20));
		cands.add(new Candidato('M', 30));
		var e = a.analisar(cands);
		assertEquals("normal",e.getContexto());
		assertEquals(2,e.getMulheres());
		assertEquals(2,e.getHomens());
		assertEquals(30.0,e.getIdadeMedia(),0.001);
	}
	
}
