package questao1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class UtilitarioTest {

	//Testando quando a lista de estudantes é null
	@Test
	void testEstudantesNull() {
		var util = new Utilitario();
		
		assertThrows(RuntimeException.class, () -> util.definirConceito(null));
	}
	
	//Testando quando a lista de estudantes está vazia
	@Test
	void testEstudantesListaVazia() {
		var util = new Utilitario();
		var est = new ArrayList<Estudante>();
		
		String msg = assertThrows(Exception.class, () -> util.definirConceito(est)).getMessage();
		assertEquals("lista vazia de estudantes", msg);
	}
	
	//Testando quando há um estudante na lista com o escore inválido
	@Test
	void testEstudantesEscoreInvalido() {
		var util = new Utilitario();
		var est = new Estudante("Jose",-1);
		var listEst = new ArrayList<Estudante>();
		listEst.add(est);
		
		String msg = assertThrows(Exception.class, () -> util.definirConceito(listEst)).getMessage();
		assertEquals("estudante com escore invalido", msg);
	}
	
	//Testando quando tem um estudante com nome inválido
	@Test
	void testEstudantesNomeInvalido() {
		var util = new Utilitario();
		var est = new Estudante("",50);
		var listEst = new ArrayList<Estudante>();
		listEst.add(est);
		
		String msg = assertThrows(Exception.class, () -> util.definirConceito(listEst)).getMessage();
		assertEquals("estudante com nome invalido", msg);
	}
	
	//Testando quando há um estudante válido com conceito A
	@Test
	void testEstudantesConceitoA() {
		var util = new Utilitario();
		var est = new Estudante("Pedro", 92);
		var listEst = new ArrayList<Estudante>();
		listEst.add(est);
		
		util.definirConceito(listEst);
		
		assertEquals("A", est.getConceito());
	}

	//Testando quando há um estudante válido com conceito B
	@Test
	void testEstudantesConceitoB() {
		var util = new Utilitario();
		var est = new Estudante("Pedro", 85);
		var listEst = new ArrayList<Estudante>();
		listEst.add(est);
		
		util.definirConceito(listEst);
		
		assertEquals("B", est.getConceito());
	}
	
	//Testando quando há um estudante válido com conceito C
	@Test
	void testEstudantesConceitoC() {
		var util = new Utilitario();
		var est = new Estudante("Pedro", 54);
		var listEst = new ArrayList<Estudante>();
		listEst.add(est);
		
		util.definirConceito(listEst);
		
		assertEquals("C", est.getConceito());
	}
	
	//Testando quando há um estudante válido com conceito F
	@Test
	void testEstudantesConceitoF() {
		var util = new Utilitario();
		var est = new Estudante("Pedro", 44);
		var listEst = new ArrayList<Estudante>();
		listEst.add(est);
		
		util.definirConceito(listEst);
		
		assertEquals("F", est.getConceito());
	}
}
