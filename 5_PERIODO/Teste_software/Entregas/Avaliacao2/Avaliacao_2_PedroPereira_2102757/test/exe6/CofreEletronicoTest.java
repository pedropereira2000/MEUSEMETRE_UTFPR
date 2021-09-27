package exe6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CofreEletronicoTest {

	@Test
	void testTravadoDuasVezes() throws Exception {
		//new
		var cE = new CofreEletronico();
		//fechar porta
		cE.fecharPorta();
		//Inserir Senha
		cE.inserirSenha("123");
		//DigitarSenhaErrada
		var resp=assertThrows(Exception.class, () -> {cE.digitarSenha("543");}).getMessage();
		assertEquals("Senha errada. Tente novamente", resp);
		//Digitar Senha Errada pela segunda vez
		var res=assertThrows(Exception.class, () -> {cE.digitarSenha("342");}).getMessage();
		assertEquals("Senha errada. Tente novamente", res);
		//Verificar status porta
		assertEquals("fechada",cE.statusPorta());
		//Verificar se a porta está travada
		assertTrue(cE.travado());
		//Tentar digitar a senha certa
		resp = assertThrows(Exception.class, () -> {cE.digitarSenha("123");}).getMessage();
		assertEquals("Cofre bloqueado por tentativas; reiniciar",resp);
		//Tentar digitar uma senha errada
		resp = assertThrows(Exception.class, () -> {cE.digitarSenha("585");}).getMessage();
		assertEquals("Cofre bloqueado por tentativas; reiniciar",resp);
	}

	@Test
	void testTravaCofreDestravaEInsereSenha() throws Exception {
		//new
		var cE = new CofreEletronico();
		//fechar porta
		cE.fecharPorta();
		//Inserir Senha
		cE.inserirSenha("098");
		//DigitarSenhaErrada
		var resp=assertThrows(Exception.class, () -> {cE.digitarSenha("543");}).getMessage();
		assertEquals("Senha errada. Tente novamente", resp);
		//Digitar a senha certa
		cE.digitarSenha("098");
		//Abrir porta
		cE.abrirPorta();
		//Verificar se está destravado
		assertFalse(cE.travado());
		//Tentar inserir senha 
		resp = assertThrows(Exception.class, () -> {cE.inserirSenha("098");}).getMessage();
		assertEquals("Porta aberta",resp);
	}
	
	@Test
	void testTodosCasosRestantes() throws Exception {
		//new
		var cE = new CofreEletronico();
		//Status da porta
		assertEquals("aberta",cE.statusPorta());
		//fechar porta
		cE.fecharPorta();
		//status porta
		assertEquals("fechada",cE.statusPorta());
		//Verifica se esta travada
		assertFalse(cE.travado());
		//Inserir Senha
		cE.inserirSenha("098");
		//Status porta
		assertEquals("fechada",cE.statusPorta());
		//Verifica se esta travada
		assertTrue(cE.travado());
		//Abrir porta
		var msg = assertThrows(Exception.class, () -> {cE.abrirPorta();}).getMessage();
		assertEquals("Nao eh possivel abrir a porta cofre travado com senha", msg);
		//DigitarSenhaErrada
		var resp=assertThrows(Exception.class, () -> {cE.digitarSenha("543");}).getMessage();
		assertEquals("Senha errada. Tente novamente", resp);
		//Status porta
		assertEquals("fechada",cE.statusPorta());
		//Verifica se esta travada
		assertTrue(cE.travado());
		//Abrir porta
		msg = assertThrows(Exception.class, () -> {cE.abrirPorta();}).getMessage();
		assertEquals("Nao eh possivel abrir a porta cofre travado com senha", msg);
		//Digitar a senha certa
		cE.digitarSenha("098");
		//Inserir senha
		cE.inserirSenha("123");
		//Digitando senha gravada
		cE.digitarSenha("123");
	}

	
}
