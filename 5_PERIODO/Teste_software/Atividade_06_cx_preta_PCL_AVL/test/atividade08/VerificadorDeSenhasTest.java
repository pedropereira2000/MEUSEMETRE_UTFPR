package atividade08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

class VerificadorDeSenhasTest {

	//CT1 senha com caracteres entre 5 e 10
	//CT2 primeiro caractere alfabético
	//CT5 senha não existe
	
	@Test
	void testCT1eCT2eCT5() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertTrue(verfSenha.validarNovaSenha("Pedro123"));
		
	}
	
	//CT3 primeiro caractere numérico
	
	@Test
	void testCT3() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertTrue(verfSenha.validarNovaSenha("1Pedro123"));
		
	}
	
	//CT4 primeiro caractere underscore
	
	@Test
	void testCT4() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertTrue(verfSenha.validarNovaSenha("_Pedro123"));
	}
	
	// CT6 senha com menos que 5 caracteres
	
	@Test
	void testCT6() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertFalse(verfSenha.validarNovaSenha("P"));
	}
	
	//CT7 primeiro caractere inválido
	
	@Test
	void testCT7() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertFalse(verfSenha.validarNovaSenha("$$Pedro123"));
	}
	
	//CT8 senha já existe
	
	@Test
	void testCT8() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Pedro123");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertFalse(verfSenha.validarNovaSenha("Pedro123"));
	}
	
	//CT9 senha com mais de 10 caracteres
	
	@Test
	void testCT9() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertFalse(verfSenha.validarNovaSenha("Pedropereira2000"));
	}
	
	//CT10 senha com 5 caracteres
	@Test
	void testCT10() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertTrue(verfSenha.validarNovaSenha("Pedro"));
	}
	
	//CT11 senha com 10 caracteres
	
	@Test
	void testCT11() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertTrue(verfSenha.validarNovaSenha("Pedro12345"));
	}
	
	//CT12 senha com 4 caracteres
	
	@Test
	void testCT12() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertFalse(verfSenha.validarNovaSenha("Pedr"));
	}
	
	//CT13 senha com 11 caracteres
	
	@Test
	void testCT13() {
		var dicionarioMock = mock(Dicionario.class);
		var verfSenha = new VerificadorDeSenhas(dicionarioMock);
		var txt = new ArrayList<String>();
		txt.add("Jorge1231");
		
		when(dicionarioMock.getListaDeSenhasInvalidas()).thenReturn(txt);
				
		assertFalse(verfSenha.validarNovaSenha("Pedropereir"));
	}

}
