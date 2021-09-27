package exemplo4;

import java.util.Random;

public class UtilException {

	public int gerarNumeroAleatorio(int inicio, int fim) throws Exception{
		 if (inicio < 0 || fim < 0) {
			 throw new Exception("intervalo negativo");
	}

	if (inicio >= fim) {
		throw new Exception("intervalo invalido");
	}
	int diff = fim -  inicio + 1;
	Random random = new Random();
	int tInt = random.nextInt(diff); //esse método retorna um número aleatorio
									 //entre 0 e diff [ 0, diff [

	return inicio + tInt;
	}
}

