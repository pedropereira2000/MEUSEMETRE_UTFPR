class Ola
{
	static int call = 0;

	public static void main(String args[])
	{
		Ola o = new Ola();
		call=o.cumprimenta();
		call=o.cumprimenta();
		call=o.cumprimenta();
	}

	int cumprimenta()
	{
		if(call == 0){
			System.out.println("Seja bem vindo Usuário!!!");
		}

		if(call == 1){
			System.out.println("Welcome User");
		}

		if(call == 2){
			System.out.println("Não se acanhe meu amigo pode se acomodar sinta-se em casa");
			call = -1;
		}

		return call+1;
	}

}