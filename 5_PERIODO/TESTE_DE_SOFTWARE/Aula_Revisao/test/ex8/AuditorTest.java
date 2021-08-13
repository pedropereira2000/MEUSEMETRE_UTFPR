package ex8;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AuditorTest {

	@Test
	void testCategoriaInvalida() {
		var empDaoMock = mock(EmpregadoDAO.class);
		var valMock = mock(Validador.class);
		
		when( valMock.ehCategoriaValida("cat1") ).thenReturn(false);
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(valMock);
		var exc = assertThrows(IllegalArgumentException.class, () -> aud.getSuperSalarios("cat1", 0));
		assertEquals("categoria invalida", exc.getMessage());
	}
	
	@Test
	void testSalarioInvalido() {
		var empDaoMock = mock(EmpregadoDAO.class);
		var valMock = mock(Validador.class);
		
		when( valMock.ehCategoriaValida("motorista") ).thenReturn(true);
		when( valMock.ehSalarioValido(-1000) ).thenReturn(false);
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(valMock);
		var exc = assertThrows(IllegalArgumentException.class, () -> aud.getSuperSalarios("motorista", -1000));
		assertEquals("salario invalido", exc.getMessage());
	}
	
	@Test
	void testNinguemNaCategoria()  {
		var empDaoMock = mock(EmpregadoDAO.class);
		var valMock = mock(Validador.class);
		
		when( valMock.ehCategoriaValida("motorista") ).thenReturn(true);
		when( valMock.ehSalarioValido(anyLong()) ).thenReturn(true);
		
		var emps = new ArrayList<Empregado>();
		emps.add(new Empregado(5000, "assistente"));
		emps.add(new Empregado(2000, "estagiario"));
		
		when(empDaoMock.getAll()).thenReturn(emps);
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(valMock);
		var exc = aud.getSuperSalarios("motorista", 1000);
		assertEquals("ninguem na categoria", exc);
	}

	@Test
	void testAlguemComSalarioAlto()  {
		var empDaoMock = mock(EmpregadoDAO.class);
		var valMock = mock(Validador.class);
		
		when( valMock.ehCategoriaValida("motorista") ).thenReturn(true);
		when( valMock.ehSalarioValido(anyLong()) ).thenReturn(true);
		
		var emps = new ArrayList<Empregado>();
		emps.add(new Empregado(5000, "assistente"));
		emps.add(new Empregado(2000, "estagiario"));
		emps.add(new Empregado(4000, "motorista"));
		
		when(empDaoMock.getAll()).thenReturn(emps);
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(valMock);
		var exc = aud.getSuperSalarios("motorista", 4000);
		assertEquals("ninguem na categoria com salario alto", exc);
	}
	
	@Test
	void testPessoasComSalarioAlto()  {
		var empDaoMock = mock(EmpregadoDAO.class);
		var valMock = mock(Validador.class);
		
		when( valMock.ehCategoriaValida("motorista") ).thenReturn(true);
		when( valMock.ehSalarioValido(anyLong()) ).thenReturn(true);
		
		var emps = new ArrayList<Empregado>();
		emps.add(new Empregado(5000, "assistente"));
		emps.add(new Empregado(2000, "estagiario"));
		emps.add(new Empregado(40000, "motorista"));
		emps.add(new Empregado(10001, "motorista"));
		
		when(empDaoMock.getAll()).thenReturn(emps);
		
		var aud = new Auditor(empDaoMock);
		aud.setValidador(valMock);
		var exc = aud.getSuperSalarios("motorista", 4000);
		assertEquals("pessoas com salario alto: 2", exc);
	}
}
