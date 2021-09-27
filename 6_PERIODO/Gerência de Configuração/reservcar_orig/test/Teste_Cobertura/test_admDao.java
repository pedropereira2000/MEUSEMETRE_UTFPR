package Teste_Cobertura;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import Bean.Administrador;
import Dao.AdministradorDAO;
import Dao.LoginDAO;

class test_admDao {

	@Test
	void test_001() {
		var adm = new Administrador("Pedro", -2, "pedro@hotmail.com", 055, "123", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.cadastrarServidor(adm);
		}).getMessage();
		assertEquals("ERRO! Siape Incorreto", res);
	}
	
	@Test
	void test_002() {
		var adm = new Administrador("Pedro", 2, " ", 055, "123", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.cadastrarServidor(adm);
		}).getMessage();
		assertEquals("ERRO! Campos não informados", res);
	}

	@Test
	void test_003() throws SQLException {
		var adm = new Administrador("Pedro", 003, "pedro@hotmail.com", 055, "123", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		admDao.cadastrarServidor(adm);
		admDao.excluirServidor(adm);
	}
	
	@Test
	void test_004() {
		var adm = new Administrador("pedro", -2, "pedro@hotmail.com", 055, "123", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.editarServidor(adm);
		}).getMessage();
		assertEquals("ERRO! Campos para edicao errados", res);
	}
	
	@Test
	void test_005() {
		var adm = new Administrador("pedro", 002, "pedro@hotmail.com", 055, " ", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.editarServidor(adm);
		}).getMessage();
		assertEquals("ERRO! Campos não informados", res);
	}
	
	@Test
	void test_006() throws SQLException {
		var adm = new Administrador("pedro", 002, "pedro@hotmail.com", 055, "123", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		admDao.editarServidor(adm);
	}
	
	@Test
	void test_007() throws SQLException {
		var admDao = new AdministradorDAO();
		admDao.buscarInfos("pedro@hotmail.com", "pedro", "pedro", "123");
	}
	
	@Test
	void test_008() {
		var adm = new Administrador("pedro", 002, "pedro@hotmail.com", 055, " ", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.excluirServidor(adm);
		}).getMessage();
		assertEquals("ERRO! Servidor nao encontrado", res);
	}
	
	@Test
	void test_009() {
		var adm = new Administrador("pedro", -2, "pedro@hotmail.com", 055, "123", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.excluirServidor(adm);
		}).getMessage();
		assertEquals("ERRO! Nao foi possivel excluir o servidor", res);
	}
	
	@Test
	void test_010() throws SQLException {
		var adm = new Administrador("pedro", 002, "pedro@hotmail.com", 055, "123", "Administrador", "Pedro Pereira", "pedro");
		var admDao = new AdministradorDAO();
		admDao.cadastrarServidor(adm);
		admDao.excluirServidor(adm);
	}
	
	@Test
	void test_011(){
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.buscarInfos(" ", " ", " ", " ");
		}).getMessage();
		assertEquals("ERRO! Campos nao preenchidos", res);
	}
	
	@Test
	void test_012(){
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.buscarSiape("0");
		}).getMessage();
		assertEquals("ERRO! Siape incorreto", res);
	}
	
	@Test
	void test_013() throws SQLException{
		var admDao = new AdministradorDAO();
		admDao.buscarSiape("1");
	}
	
	@Test
	void test_014(){
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.consultarServidor(0);
		}).getMessage();
		assertEquals("ERRO! Siape informado esta incorreto", res);
	}
	
	@Test
	void test_015() throws SQLException{
		var admDao = new AdministradorDAO();
		admDao.consultarServidor(1);
	}
	
	/*@Test
	void test_016(){
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.listarServidor();
		}).getMessage();
		assertEquals("ERRO! Siape informado esta incorreto", res);
	}*/
	
	@Test
	void test_017() throws SQLException{
		var admDao = new AdministradorDAO();
		admDao.listarServidor();
	}
	
	@Test
	void test_018(){
		var admDao = new AdministradorDAO();
		var res = assertThrows(Exception.class, () -> {
			admDao.listarServidores(0);
		}).getMessage();
		assertEquals("ERRO! Id inserido esta invalido", res);
	}
	
	@Test
	void test_019() throws SQLException{
		var admDao = new AdministradorDAO();
		admDao.listarServidores(1);
	}
	
	@Test
	void test_020() {
		var lgDao = new LoginDAO();
		var res = assertThrows(Exception.class, () -> {
			lgDao.checkLogin(" ", "123");
		}).getMessage();
		assertEquals("ERRO! Login e senha nao preenchidos", res);
	}
	
	@Test
	void test_021() throws SQLException {
		var lgDao = new LoginDAO();
		lgDao.checkLogin("adm", "123");
	}
	
	@Test
	void test_022() {
		var lgDao = new LoginDAO();
		var res = assertThrows(Exception.class, () -> {
			lgDao.consultarLogin(" ");
		}).getMessage();
		assertEquals("ERRO! Login inserido esta invalido", res);
	}
	
	@Test
	void test_023() throws SQLException {
		var lgDao = new LoginDAO();
		lgDao.consultarLogin("adm");
	}
}
