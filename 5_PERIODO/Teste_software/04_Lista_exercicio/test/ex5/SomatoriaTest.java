package ex5;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;


class SomatoriaTest {

	@Test
	void testCaso1() {
		var matOpMock = mock(MathOps.class);
		var primoMock = mock(Primo.class);
		
		when( primoMock.ehPrimo(5) )
			.thenReturn(true);
		
		when( primoMock.ehPrimo(10) )
			.thenReturn(false);
		
		when( matOpMock.fatorial(5) )
			.thenReturn(0);
		
		when( matOpMock.fatorial(10) )
			.thenReturn(3628800);
		
		Somatoria soma = new Somatoria(matOpMock);
		
		
		int vet[] = {5, 10};
		
		int result = soma.somaDeFatoriais(vet, primoMock);
		
		assertEquals(3628800, result);
	}
	
	@Test
	void testCaso2() {
		var matOpMock = mock(MathOps.class);
		var primoMock = mock(Primo.class);
		
		when( primoMock.ehPrimo(3) )
			.thenReturn(true);
		
		when( primoMock.ehPrimo(4) )
			.thenReturn(false);
		
		when( primoMock.ehPrimo(5) )
			.thenReturn(true);
		
		when( matOpMock.fatorial(3) )
			.thenReturn(0);
		
		when( matOpMock.fatorial(4) )
			.thenReturn(24);
		
		when( matOpMock.fatorial(5) )
		.thenReturn(0);
		
		Somatoria soma = new Somatoria(matOpMock);
		
		
		int vet[] = {3, 4, 4, 5};
		
		int result = soma.somaDeFatoriais(vet, primoMock);
		
		assertEquals(48, result);
	}

}
