package exe3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class DecisionMakerTest {

	@Test
	void testCT1() {
		//Usuario inativo por mais de 2 semanas
		var user = new Usuario(100,false,false);
		var decMak = new DecisionMaker();
		var res = decMak.mostrarAnuncio(user, false);
		
		assertFalse(res);
		
	}
	
	@Test
	void testCT2() {
		//Ativo nas duas ultimas semanas não viu anuncio e tem menos de 1000 seguidores
		var user = new Usuario(100,true,false);
		var decMak = new DecisionMaker();
		var res = decMak.mostrarAnuncio(user, false);
		
		assertTrue(res);
		
	}

	@Test
	void testCT3() {
		//Ativo nas duas ultimas semanas mas já viu anúncio e tem menos de 1000 seguidores
		var user = new Usuario(100,true,true);
		var decMak = new DecisionMaker();
		var res = decMak.mostrarAnuncio(user, false);
		
		assertFalse(res);
		
	}
	
	@Test
	void testCT4() {
		//tem mas de 1000 seguidores e o anúncio não é relevante 
		var user = new Usuario(100,true,true);
		var decMak = new DecisionMaker();
		var res = decMak.mostrarAnuncio(user, false);
		
		assertFalse(res);
		
	}
	
	@Test
	void testCT5() {
		//tem mas de 1000 seguidores e o anúncio é relevante
		var user = new Usuario(2000,true,false);
		var decMak = new DecisionMaker();
		var res = decMak.mostrarAnuncio(user, true);
		
		assertTrue(res);
		
	}
	
}
