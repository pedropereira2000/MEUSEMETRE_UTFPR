package Exercicio4_Classificador;

import Exercicio4_Pessoa.Pessoa;

public class Classificador {
	public String definirFaixaEtaria(Pessoa p) throws RuntimeException {
		if(p.getIdade()<0 || p.getIdade()>=110)
			throw new IllegalArgumentException("idade invalida");
		
		int idade = p.getIdade();
		String tipo = "";
		if(idade <= 11)
			tipo = "crianca";
		else if(idade <= 18)
			tipo = "adolescente";
		else if(idade <= 59)
			tipo = "adulto";
		else
			tipo = "idoso";
		
		return p.getNome()+" eh "+tipo;
	}
}
