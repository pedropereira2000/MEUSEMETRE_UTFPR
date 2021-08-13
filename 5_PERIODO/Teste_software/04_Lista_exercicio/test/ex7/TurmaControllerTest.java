package ex7;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import ex7.Turma;
import ex7.TurmaController;
import ex7.TurmaDAO;
import ex7.VerificadorDeCodigos;

class TurmaControllerTest {


	@Test
	void testCodigoDisciplinaInvalido() {
		// mock
		// mock da turmaDAO
		var turmaMock = mock(TurmaDAO.class);
		// mock do verificador
		var verfCodMock = mock(VerificadorDeCodigos.class);

		// configurar/simular o comportamento do mock
		when(verfCodMock.verificarCodigoDisciplina("2")).thenReturn(false);
		when(verfCodMock.verificarCodigoTurma("23")).thenReturn(true);
		when(turmaMock.existe(any())).thenReturn(false);
		when(turmaMock.salvar(any())).thenReturn(true);

		var turmaControl = new TurmaController(turmaMock);

		turmaControl.setVerificador(verfCodMock);
		
		// atribuindo uma nova turma
		var turmaNova = new Turma("2", "23", 20);

		assertEquals("codigo disciplina invalido", turmaControl.cadastrarTurma(turmaNova));

		verify(verfCodMock).verificarCodigoDisciplina(anyString());
	}

	@Test
	void testCodigoTurmaInvalido() {
		// mock
		// mock da turmaDAO
		var turmaMock = mock(TurmaDAO.class);
		// mock do verificador
		var verfCodMock = mock(VerificadorDeCodigos.class);

		// configurar/simular o comportamento do mock
		when(verfCodMock.verificarCodigoDisciplina("2")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("23")).thenReturn(false);
		when(turmaMock.existe(any())).thenReturn(false);
		when(turmaMock.salvar(any())).thenReturn(true);

		var turmaControl = new TurmaController(turmaMock);

		turmaControl.setVerificador(verfCodMock);
		
		// atribuindo uma nova turma
		var turmaNova = new Turma("2", "23", 20);

		assertEquals("codigo turma invalido", turmaControl.cadastrarTurma(turmaNova));

		verify(verfCodMock).verificarCodigoTurma(anyString());
	}

	@Test
	void testTurmaExiste() {
		// mock
		// mock da turmaDAO
		var turmaMock = mock(TurmaDAO.class);
		// mock do verificador
		var verfCodMock = mock(VerificadorDeCodigos.class);
		

		// configurar/simular o comportamento do mock
		when(verfCodMock.verificarCodigoDisciplina("2")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("23")).thenReturn(true);
		when(turmaMock.existe(any())).thenReturn(true);
		when(turmaMock.salvar(any())).thenReturn(true);

		var turmaControl = new TurmaController(turmaMock);

		turmaControl.setVerificador(verfCodMock);
		
		// atribuindo uma nova turma
		var turmaNova = new Turma("2", "23", 20);

		assertEquals("turma ja existe", turmaControl.cadastrarTurma(turmaNova));

		verify(verfCodMock).verificarCodigoTurma(anyString());
	}

	@Test
	void testTurmaSalva() {
		// mock
		// mock do verificador
		var verfCodMock = mock(VerificadorDeCodigos.class);
		when(verfCodMock.verificarCodigoDisciplina("2")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("23")).thenReturn(true);
		// mock da turmaDAO
		var turmaMock = mock(TurmaDAO.class);
		// configurar/simular o comportamento do mock
		when(turmaMock.existe(any())).thenReturn(false);
		when(turmaMock.salvar(any())).thenReturn(true);
		// atribuindo uma nova turma
				

		var turmaControl = new TurmaController(turmaMock);

		turmaControl.setVerificador(verfCodMock);
		
		var turmaNova = new Turma("2", "23", 20);
		
		String text = turmaControl.cadastrarTurma(turmaNova);
		assertEquals("turma salva com sucesso", text);
	}

	@Test
	void testErroBd() {
		// mock
		// mock da turmaDAO
		var turmaMock = mock(TurmaDAO.class);
		// mock do verificador
		var verfCodMock = mock(VerificadorDeCodigos.class);

		// configurar/simular o comportamento do mock
		when(verfCodMock.verificarCodigoDisciplina("2")).thenReturn(true);
		when(verfCodMock.verificarCodigoTurma("23")).thenReturn(true);
		when(turmaMock.existe(any())).thenReturn(false);
		when(turmaMock.salvar(any())).thenReturn(false);

		var turmaControl = new TurmaController(turmaMock);

		turmaControl.setVerificador(verfCodMock);
		
		// atribuindo uma nova turma
		var turmaNova = new Turma("2", "23", 20);

		assertEquals("turma nao salva. Erro no BD", turmaControl.cadastrarTurma(turmaNova));

		verify(verfCodMock).verificarCodigoDisciplina(anyString());
	}
}
