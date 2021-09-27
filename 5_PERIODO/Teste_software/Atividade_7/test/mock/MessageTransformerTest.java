package mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class MessageTransformerTest {

	@Test
	void testSuccess() {
		Helper helperMock = mock(Helper.class);
		String[] censoredWords = { "zeca", "vaso", "galinha"};
		when(helperMock.getCensoredWords())
			.thenReturn(censoredWords);
		
		Logger loggerMock = mock(Logger.class);
		when(loggerMock.logTransformation(anyString(), anyString()))
			.thenReturn(true);
		
		var msgTranf = new MessageTransformer(loggerMock, helperMock);
		var res = msgTranf.trasform("As palavras zeca e vaso nao aparecem");
		assertEquals("As palavras ??? e ??? nao aparecem",res);
		
	}

}
