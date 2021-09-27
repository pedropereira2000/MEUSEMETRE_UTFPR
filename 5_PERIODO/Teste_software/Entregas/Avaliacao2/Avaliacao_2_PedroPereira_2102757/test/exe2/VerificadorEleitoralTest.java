package exe2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class VerificadorEleitoralTest {

	@Test
	void testCT1() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(25, "14265478921");
		assertEquals("voto obrigatorio", res);
	}
	
	@Test
	void testCT2() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		assertThrows(Exception.class, () -> {verifEleit.consultarSituacao(-15, "14265478921");});	
	}
	
	@Test
	void testCT3() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(5, "14265478921");
		assertEquals("nao pode vota", res);
	}
	
	@Test
	void testCT4() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(16, "14265478921");
		assertEquals("voto facultativo", res);
	}
	
	@Test
	void testCT5() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(85, "14265478921");
		assertEquals("voto facultativo", res);
	}
	
	@Test
	void testCT6() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("142654")).thenReturn("nao existe");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = assertThrows(Exception.class, () -> {verifEleit.consultarSituacao(25, "142654");});
		assertEquals("faca um titulo", res);
	}
	
	@Test
	void testCT7() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("1426A4578A1")).thenReturn("nao existe");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = assertThrows(Exception.class, () -> {verifEleit.consultarSituacao(25, "1426A4578A1");});
		assertEquals("faca um titulo", res);
	}
	
	@Test
	void testCT8() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("24533122585")).thenReturn("pendencia");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(85, "24533122585");
		assertEquals("regularize seu titulo", res);
	}
	
	@Test
	void testCT9() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(1, "14265478921");
		assertEquals("nao pode vota", res);
	}

	@Test
	void testCT10() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(199, "14265478921");
		assertEquals("voto facultativo", res);
	}
	
	@Test
	void testCT11() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(15, "14265478921");
		assertEquals("nao pode votar", res);
	}
	
	@Test
	void testCT12() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(17, "14265478921");
		assertEquals("voto facultativo", res);
	}
	
	@Test
	void testCT13() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(71, "14265478921");
		assertEquals("voto facultativo", res);
	}
	
	@Test
	void testCT14() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("1426A4578A1")).thenReturn("nao existe");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		assertThrows(Exception.class, () -> {verifEleit.consultarSituacao(0, "14265478921");});
	}
	
	@Test
	void testCT15() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("1426A4578A1")).thenReturn("nao existe");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		assertThrows(Exception.class, () -> {verifEleit.consultarSituacao(200, "14265478921");});
	}
	
	@Test
	void testCT16() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("14265478921")).thenReturn("OK");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = verifEleit.consultarSituacao(70, "14265478921");
		assertEquals("voto obrigatorio", res);
	}

	@Test
	void testCT17() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("1426A4578A1")).thenReturn("nao existe");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = assertThrows(Exception.class, () -> {verifEleit.consultarSituacao(25, "1234567891");});
		assertEquals("faca um titulo", res);
	}
	
	@Test
	void testCT18() throws Exception {
		var cartEleitMock = mock(CartorioEleitoral.class);
		when(cartEleitMock.verificar("1426A4578A1")).thenReturn("nao existe");
		var verifEleit = new VerificadorEleitoral(cartEleitMock);
		
		var res = assertThrows(Exception.class, () -> {verifEleit.consultarSituacao(25, "123465789456");});
		assertEquals("faca um titulo", res);
	}
	
}
