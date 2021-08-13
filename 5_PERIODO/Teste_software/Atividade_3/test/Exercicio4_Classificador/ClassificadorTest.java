package Exercicio4_Classificador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exercicio4_Pessoa.Pessoa;

class ClassificadorTest {

	@Test
	void testIdadeInvalida() {
		Pessoa p = new Pessoa("Ismael",111);
		Classificador cla = new Classificador();
		
		Exception ex = assertThrows(Exception.class, () -> {
			cla.definirFaixaEtaria(p);
		});
		
		assertEquals("idade invalida", ex.getMessage());
		
	}
	
	@Test
	void testPessoaCrianca() {
		Pessoa p = new Pessoa("Gabriel",11);
		Classificador cla = new Classificador();
		assertEquals("Gabriel eh crianca",cla.definirFaixaEtaria(p));
	}
	
	@Test
	void testPessoaAdolecente() {
		Pessoa p = new Pessoa("Roberta",17);
		Classificador cla = new Classificador();
		assertEquals("Roberta eh adolescente",cla.definirFaixaEtaria(p));
	}
	
	@Test
	void testPessoaAdulta() {
		Pessoa p = new Pessoa("Critina",36);
		Classificador cla = new Classificador();
		assertEquals("Critina eh adulto",cla.definirFaixaEtaria(p));
	}
	
	@Test
	void testPessoaIdosa() {
		Pessoa p = new Pessoa("Jorge",72);
		Classificador cla = new Classificador();
		assertEquals("Jorge eh idoso",cla.definirFaixaEtaria(p));
	}

}
