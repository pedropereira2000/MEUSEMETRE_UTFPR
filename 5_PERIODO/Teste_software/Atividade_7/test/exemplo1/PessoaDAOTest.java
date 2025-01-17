package exemplo1;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PessoaDAOTest {

	@Test
	void testPessoaExiste() {
		// criar um objeto mockado
		var rhServiceMock = mock(RHService.class);
		
		// criar o objeto de retorno
		ArrayList<Pessoa> pessoasRet = new ArrayList<>(); 
		pessoasRet.add(new Pessoa(1, 32, "Joao da Silva"));
		pessoasRet.add(new Pessoa(2, 25, "Grace Hopper"));
		pessoasRet.add(new Pessoa(3, 19, "Monkey D. Luffy"));
		
		// configurar/simular o comportamento do mock
		when(rhServiceMock.getAllPessoas())
			.thenReturn(pessoasRet);
		
		var dao = new PessoaDAO(rhServiceMock);
		// metodo sob test
		assertTrue( dao.existePessoa("Monkey D. Luffy") );
		
		
	}
	
	@Test
	void testPessoNaoExiste() {
		// criar um objeto mockado
		var rhServiceMock = mock(RHService.class);
		
		// criar o objeto de retorno
		ArrayList<Pessoa> pessoasRet = new ArrayList<>(); 
		pessoasRet.add(new Pessoa(1, 32, "Joao da Silva"));
		pessoasRet.add(new Pessoa(2, 25, "Grace Hopper"));
		pessoasRet.add(new Pessoa(3, 19, "Monkey D. Luffy"));
		
		// configurar/simular o comportamento do mock
		when(rhServiceMock.getAllPessoas())
			.thenReturn(pessoasRet);
		
		//when(rhServiceMock.getAllPessoas())
		//	.thenThrow( new RuntimeException() );
	
		
		
		var dao = new PessoaDAO(rhServiceMock);
		// metodo sob test
		assertFalse(dao.existePessoa("Akagami Shanks"));
		
		verify(rhServiceMock).getAllPessoas();
	}

}
