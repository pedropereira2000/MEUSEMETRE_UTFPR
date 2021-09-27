package ex5;

public class Somatoria {
	MathOps mathOps;

	public Somatoria(MathOps mathOps) {
		this.mathOps = mathOps;
	}

	/**
	 * @param numeros 
	 * @return a somatoria do fatorial de cada inteiro no array
	 * numeros que nao eh primo
	 */
	public int somaDeFatoriais(int numeros[], Primo p) {
		int soma = 0;
		for (int i = 0; i < numeros.length; i++) {
			int numero = numeros[i];
			if (!p.ehPrimo(numero)) {
				soma += mathOps.fatorial(numero);
			}
		}
		return soma;
	}

}
