import java.util.Random;

class Aluno
{
	double p1 = 0.0;
	double p2 = 0.0;
	double p3 = 0.0;
	double p4 = 0.0;

	public static void main(String args[])
	{
		Aluno a = new Aluno();
		a.testCalcMedia(1000);
	}

	void calcMediaAri(double p1, double p2, double p3, double p4)
	{
		double media = (p1+p2+p3+p4)/4;

		if(media >= 6.0){
			System.out.println("Media: " + String.format("%.2f",media) + " -> aprovado.");
		}

		if(media < 6.0 && media >= 4.0){
			System.out.println("Media: " + String.format("%.2f",media) + "-> recuperacao.");
		}

		if(media < 4.0){
			System.out.println("Media: "  + String.format("%.2f",media) + "-> reprovado.");
		}
	}

	void testCalcMedia(int repet)
	{
		for (int i=0; i < repet;i++)
		{
			calcMediaAri(randomMedia(),randomMedia(),randomMedia(),randomMedia());
		}
	}

	double randomMedia()
	{
		Random gerador = new Random();

        return gerador.nextDouble()*10;
        
	}

}