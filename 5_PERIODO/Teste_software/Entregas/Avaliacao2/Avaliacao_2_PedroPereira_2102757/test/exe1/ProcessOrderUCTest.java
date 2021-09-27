
package exe1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ProcessOrderUCTest {

	//1, 2, 3 e 4
	@Test
	void test001() {
		var repoDaoMock = mock(Repository.class);
		var valDaoMock = mock(Validator.class);
		List<String> erros = new ArrayList();
		erros.add("email vazio");
		erros.add("desc vazio");
		erros.add("address vazio");
		when(valDaoMock.validateBasicData(any())).thenReturn(erros);;
		var ord = new Order(1,"","","");
		var process = new ProcessOrderUC(valDaoMock, repoDaoMock);
		
		var res = assertThrows(Exception.class, () -> {
			process.process(ord);
		}).getMessage();
		
		assertEquals("email vazio,desc vazio,address vazio", res);
		
	}
	
	//1, 2, 5 e 7
	@Test
	void test002() {
		List<String> erros = new ArrayList();
		var repoDaoMock = mock(Repository.class);
		var valDaoMock = mock(Validator.class);
		when(valDaoMock.validateBasicData(any())).thenReturn(erros);
		var servDaoMock = mock(TransportService.class);
		when(servDaoMock.isDown()).thenReturn(true);
		var ord = new Order(1,"pedro@hotmail.com","Brasil","Rua 2");
		var process = new ProcessOrderUC(valDaoMock, repoDaoMock);
		process.setService(servDaoMock);
		var res = assertThrows(Exception.class, () -> {
			process.process(ord);
		}).getMessage();
		
		assertEquals("Services offline. Try again later.", res);
		
	}
	
	//1, 2, 5, 6 e 7
	@Test
	void test003() {
		List<String> erros = new ArrayList();
		var repoDaoMock = mock(Repository.class);
		var valDaoMock = mock(Validator.class);
		when(valDaoMock.validateBasicData(any())).thenReturn(erros);
		var servDaoMock = mock(TransportService.class);
		when(servDaoMock.isDown()).thenReturn(false);
		var emailDaoMock = mock(EmailSender.class);
		when(emailDaoMock.isOffline()).thenReturn(true);
		var ord = new Order(1,"pedro@hotmail.com","Brasil","Rua 2");
		var process = new ProcessOrderUC(valDaoMock, repoDaoMock);
		process.setService(servDaoMock);
		process.setEmailSender(emailDaoMock);
		var res = assertThrows(Exception.class, () -> {
			process.process(ord);
		}).getMessage();
		
		assertEquals("Services offline. Try again later.", res);
		
	}
	
	//1, 2, 5, 6, 8, 9, 10, 11, 12, 9, 14, 15, 16 e 17
	@Test
	void test004() {
		List<String> erros = new ArrayList();
		var repoDaoMock = mock(Repository.class);
		when(repoDaoMock.orderProduct(1)).thenReturn(true);
		var valDaoMock = mock(Validator.class);
		when(valDaoMock.validateBasicData(any())).thenReturn(erros);
		var servDaoMock = mock(TransportService.class);
		when(servDaoMock.isDown()).thenReturn(false);
		when(servDaoMock.makeTag(anyInt(), anyString())).thenReturn(1);
		var emailDaoMock = mock(EmailSender.class);
		when(emailDaoMock.isOffline()).thenReturn(false);
		when(emailDaoMock.sendEmail(anyString(), anyString(), anyString())).thenReturn(1);
		var ord = new Order(1,"pedro@hotmail.com","Brasil","Rua 2");
		var process = new ProcessOrderUC(valDaoMock, repoDaoMock);
		process.setService(servDaoMock);
		process.setEmailSender(emailDaoMock);
		
		//int[] proId = {1};
		
		ord.getProdIds().add(1);
		
		process.process(ord);
		
		/*int[] result = {1,1,1,0};	
		
		
					
		assertEquals(result, resp);*/
		
	}


	//1, 2, 5, 6, 8, 9, 10, 11, 13, 9, 14, 15, 16 e 17
	@Test
	void test005() {
		List<String> erros = new ArrayList();
		var repoDaoMock = mock(Repository.class);
		when(repoDaoMock.orderProduct(1)).thenReturn(false);
		var valDaoMock = mock(Validator.class);
		when(valDaoMock.validateBasicData(any())).thenReturn(erros);
		var servDaoMock = mock(TransportService.class);
		when(servDaoMock.isDown()).thenReturn(false);
		when(servDaoMock.makeTag(anyInt(), anyString())).thenReturn(1);
		var emailDaoMock = mock(EmailSender.class);
		when(emailDaoMock.isOffline()).thenReturn(false);
		when(emailDaoMock.sendEmail(anyString(), anyString(), anyString())).thenReturn(1);
		var ord = new Order(1,"pedro@hotmail.com","Brasil","Rua 2");
		var process = new ProcessOrderUC(valDaoMock, repoDaoMock);
		process.setService(servDaoMock);
		process.setEmailSender(emailDaoMock);
		
		ord.getProdIds().add(1);
		
		process.process(ord);
		
		/*int[] result = new int[4];
		result[0]=1;
		result[1]=1;
		result[2]=0;
		result[3]=;
		
		var resp = 
		
		System.out.println(resp);
					
		assertEquals(result,resp);*/
		
	}

}
