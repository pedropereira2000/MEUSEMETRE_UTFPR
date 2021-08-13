package ex8;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AuditorTest {

	@Test
	void testCategoriaInvalida() {
		var validMock = mock(Validador.class);
		var empDaoMock = mock(EmpregadoDAO.class);
		
		// when
		when( validMock.ehCategoriaValida("Jardineiro") )
			.thenReturn(false);
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(validMock);
		
		assertThrows(Exception.class, () -> {
			aud.getSuperSalarios("Encanador", 2000);
		});
	}
	
	@Test
	void testSalarioInvalido() {
		var validMock = mock(Validador.class);
		var empDaoMock = mock(EmpregadoDAO.class);
		
		// when
		when( validMock.ehCategoriaValida("Encanador") )
			.thenReturn(true);
		
		when( validMock.ehSalarioValido(1000) )
			.thenReturn(false);
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(validMock);
		
		assertThrows(Exception.class, () -> {
			aud.getSuperSalarios("Encanador", 2000);
		});
	}

	@Test
	void testNinguemNaCategoria() {
		var validMock = mock(Validador.class);
		var empDaoMock = mock(EmpregadoDAO.class);
		
		//empDaoMock.
		
		// when
		when( validMock.ehCategoriaValida("Encanador") )
			.thenReturn(true);
		
		when( validMock.ehSalarioValido(2000) )
			.thenReturn(true);
		
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(validMock);
		
		String res = aud.getSuperSalarios("Encanador", 2000);
		
		assertEquals("ninguem na categoria", res);
	}
	
	@Test
	void testNinguemNaCategoriaComSalarioAlto() {
		var validMock = mock(Validador.class);
		var empDaoMock = mock(EmpregadoDAO.class);
				
		// when
		when( validMock.ehCategoriaValida("Encanador") )
			.thenReturn(true);
		
		when( validMock.ehSalarioValido(2000) )
			.thenReturn(true);
		
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(validMock);
		
		String res = aud.getSuperSalarios("Encanador", 2000);
		
		assertEquals("ninguem na categoria", res);
	}
	
}
