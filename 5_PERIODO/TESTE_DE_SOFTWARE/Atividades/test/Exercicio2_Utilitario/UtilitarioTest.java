package Exercicio2_Utilitario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exercicio2_Extremos.Extremo;

class UtilitarioTest {

	@Test
	void testAcharExtremosVetor1() throws Exception{
		Utilitario util = new Utilitario();
		
		int[] vet = {1,2,3,4,5,6};
		
		Extremo ext = util.acharExtremos(vet);
		
		assertEquals(ext.getMenor(),1);
		assertEquals(ext.getIndiceMenor(),0);
		assertEquals(ext.getMaior(),6);
		assertEquals(ext.getIndiceMaior(),5);
	}
	
	@Test
	void testAcharExtremosVetor2() throws Exception{
		Utilitario util = new Utilitario();
		
		int[] vet = {1,99,3,-5,8};
		
		Extremo ext = util.acharExtremos(vet);
		
		assertEquals(ext.getMenor(),-5);
		assertEquals(ext.getIndiceMenor(),3);
		assertEquals(ext.getMaior(),99);
		assertEquals(ext.getIndiceMaior(),1);
	}
	
	@Test
	void testAcharExtremosVetorVazio(){
		Utilitario util = new Utilitario();
		
		int[] vet = {};
		
		var exc = assertThrows(Exception.class, () -> {
			util.acharExtremos(vet);
		});
		
		assertEquals("vetor com zero elementos",exc.getMessage());
	}
	
	@Test
	void testAcharExtremosVetorNulo(){
		Utilitario util = new Utilitario();
		
		int[] vet = null;
		
		//Extremo ext = util.acharExtremos(vet);
		
		var exc = assertThrows(Exception.class, () -> {
			util.acharExtremos(vet);
		});
		
		assertEquals("vetor nao pode ser nulo",exc.getMessage());
	}
	
	

}
