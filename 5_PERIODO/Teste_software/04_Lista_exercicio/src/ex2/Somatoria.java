package ex2;

public class Somatoria {
	MathOps mathOps;

	public Somatoria(MathOps mathOps) {
		this.mathOps = mathOps;
	}

	/**
	 * @param numeros
	 * @return a somatoria do fatorial de cada inteiro no array numeros
	 */
	public int somaDeFatoriais(int numeros[]) {
		int soma = 0;
		for (int i = 0; i < numeros.length; i++) {
			int numero = numeros[i];
				soma += mathOps.fatorial(numero);
		}
		return soma;
	}

}
