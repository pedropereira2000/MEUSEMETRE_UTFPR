package ex2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class SomatoriaTest {

	@Test
	void testCaso1() {
		var matOpMock = mock(MathOps.class);

		Somatoria soma = new Somatoria(matOpMock);

		when(matOpMock.fatorial(3)).thenReturn(6);

		when(matOpMock.fatorial(4)).thenReturn(24);

		int vet[] = { 3, 4 };

		int result = soma.somaDeFatoriais(vet);

		assertEquals(30, result);

		verify(matOpMock).fatorial(3);

		verify(matOpMock).fatorial(4);
	}

	@Test
	void testCaso2() {
		var matOpMock = mock(MathOps.class);
		
		Somatoria soma = new Somatoria(matOpMock);
		
		when( matOpMock.fatorial(0) )
		.thenReturn(1);
	
		when( matOpMock.fatorial(1) )
			.thenReturn(1);	
	
		when( matOpMock.fatorial(2) )
			.thenReturn(2);
		
		when( matOpMock.fatorial(3) )
			.thenReturn(6);	
		
		when( matOpMock.fatorial(4) )
			.thenReturn(24);
		
		int vet[] = {0, 1, 2, 3, 4};
		
		int result = soma.somaDeFatoriais(vet);
		
		assertEquals(34, result);
		
		verify(matOpMock).fatorial(0);
		
		verify(matOpMock).fatorial(1);
		
		verify(matOpMock).fatorial(2);
		
		verify(matOpMock).fatorial(3);
		
		verify(matOpMock).fatorial(4);
	}

}
