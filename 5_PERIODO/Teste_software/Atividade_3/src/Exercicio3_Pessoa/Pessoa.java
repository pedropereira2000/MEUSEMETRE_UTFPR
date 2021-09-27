package Exercicio3_Pessoa;

public class Pessoa {
	String nome;
	double peso, altura;

	public Pessoa(String nome, double peso, double altura) {
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
	} 
	
	public double getPeso() {
		return this.peso;
	}
	
	public double getAltura() {
		return this.altura;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	
}
