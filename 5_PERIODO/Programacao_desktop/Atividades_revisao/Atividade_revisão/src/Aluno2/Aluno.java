
package Aluno2;

import java.util.Random;

class Aluno
{
	double n1 = 0.0;
	double n2 = 0.0;
	double n3 = 0.0;
	double n4 = 0.0;
	
	double recebeNota1(double x) {
		return n1 = x;
	}
	
	double recebeNota2(double x) {
		return n2 = x;
	}
	
	double recebeNota3(double x) {
		return n3 = x;
	}
	
	double recebeNota4(double x) {
		return n4 = x;
	}

	double imprimeMedia()
	{
		
		double media = (n1+n2+n3+n4)/4;

		if(media >= 6.0){
			System.out.println("Media: " + String.format("%.2f",media) + " -> aprovado.");
		}

		if(media < 6.0 && media >= 4.0){
			System.out.println("Media: " + String.format("%.2f",media) + "-> recuperacao.");
		}

		if(media < 4.0){
			System.out.println("Media: "  + String.format("%.2f",media) + "-> reprovado.");
		}
		
		return media;
	}

}