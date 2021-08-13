package ex6;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ex6.Funcionario;
import ex6.FuncionarioDAO;
import ex6.ReceitaFederal;
import ex6.RelatorioDeFuncionarios;

class RelatorioDeFuncionariosTest {

	//Existem 2 funcion�rios na categoria �tecnico� que 
		//n�o est�o com o CPF bloqueado
		
		@Test
		void testTecnicoSemCPFBloqueado() {
			//mock
			var funcDaoMock = mock(FuncionarioDAO.class);
			var rfMock = mock(ReceitaFederal.class);
			var funcsRet = new ArrayList<Funcionario>();
			funcsRet.add(new Funcionario(1, "Romario","111.222.888-98"));
			funcsRet.add(new Funcionario(2, "Bebeto","222.222.555-98"));
			
			//configura
			when( funcDaoMock.getFuncionariosBy("tecnico") )
				.thenReturn(funcsRet);
			
			when( rfMock.isCPFBloqueado("111.222.888-98") )
				.thenReturn(false);
			
			when( rfMock.isCPFBloqueado("222.222.555-98") )
				.thenReturn(false);
			
			//testa o metodo
			var rel = new RelatorioDeFuncionarios(funcDaoMock);
			rel.setRf(rfMock);
			
			assertEquals(0, rel.getFuncComCPFBloqueado("tecnico"));
		}

		//Existem 4 funcion�rios na categoria �gerente� com os CPFs: (123456789-00,
		//111222333-44, 654321987-23, 098876654-99), sendo que os CPFs 111222333-
		//44 e 098876654-99 est�o bloqueados.
		
		@Test
		void testGerenteSendo2CPFBloqueado() {
			//mock
			var funcDaoMock = mock(FuncionarioDAO.class);
			var rfMock = mock(ReceitaFederal.class);
			var funcsRet = new ArrayList<Funcionario>();
			funcsRet.add(new Funcionario(1, "Romario","123456789-00"));
			funcsRet.add(new Funcionario(2, "Bebeto","111222333-44"));
			funcsRet.add(new Funcionario(3, "Imperador","654321987-23"));
			funcsRet.add(new Funcionario(4, "Ronaldo","098876654-99"));
			
			//configura
			when( funcDaoMock.getFuncionariosBy(anyString()) )
				.thenReturn(funcsRet);
			
			when( rfMock.isCPFBloqueado("111222333-44") )
				.thenReturn(true);
			
			when( rfMock.isCPFBloqueado("098876654-99") )
				.thenReturn(true);
			
			when( rfMock.isCPFBloqueado("123456789-00") )
				.thenReturn(false);
			
			when( rfMock.isCPFBloqueado("654321987-23") )
				.thenReturn(false);
			
			//testa o metodo
			var rel = new RelatorioDeFuncionarios(funcDaoMock);
			rel.setRf(rfMock);
			
			assertEquals(2, rel.getFuncComCPFBloqueado("gerente"));
		}
		
		//Existe 1 funcion�rio na categoria �analista� que est� com o CPF bloqueado.

		@Test
		void testAnalistaComCPFBloqueado() {
			//mock
			var funcDaoMock = mock(FuncionarioDAO.class);
			var rfMock = mock(ReceitaFederal.class);
			var funcsRet = new ArrayList<Funcionario>();
			funcsRet.add(new Funcionario(1, "Romario","111.222.888-98"));
			
			//configura
			when( funcDaoMock.getFuncionariosBy("analista") )
				.thenReturn(funcsRet);
			
			when( rfMock.isCPFBloqueado("111.222.888-98") )
				.thenReturn(true);
			
			//testa o metodo
			var rel = new RelatorioDeFuncionarios(funcDaoMock);
			rel.setRf(rfMock);
			
			assertEquals(1, rel.getFuncComCPFBloqueado("analista"));
		}
}
