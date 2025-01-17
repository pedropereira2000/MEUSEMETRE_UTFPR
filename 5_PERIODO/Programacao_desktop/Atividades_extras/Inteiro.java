class Inteiro
{
	int valor;

	public static void main(String args[])
	{
		Inteiro i = new Inteiro();

		i.carregaValor(15);
		i.subtrai(20);
		i.devolveValor();
		i.devolveValorAbsoluto();
		i.soma(30);
		i.multiplicaPor(2);
		i.dividePor(0);
	}

	void carregaValor(int v)
	{
		valor = v;
	}

	int devolveValor()
	{
		imprime();
		return valor;
	}

	int devolveValorAbsoluto()
	{
		if(valor < 0)
		{
			valor = valor * -1;
			imprime();
			valor = valor * -1;
			return valor;
		}
		else
		{
			imprime();
			return valor;
		}
	}

	void imprime()
	{
		System.out.println(valor);
	}

	int soma(int v)
	{
		valor = valor + v;
		imprime();
		return valor;
	}

	int subtrai(int v)
	{
		valor = valor - v;
		imprime();
		return valor;
	}

	int multiplicaPor(int v)
	{
		valor = valor * v;
		imprime();
		return valor;
	}

	int dividePor(int v)
	{
		if(v == 0)
		{
			System.out.println("ERRO nao e possivel dividir por 0");
			return valor;
		}
		else
			valor = valor / v;
			imprime();
			return valor;
	}

	

}