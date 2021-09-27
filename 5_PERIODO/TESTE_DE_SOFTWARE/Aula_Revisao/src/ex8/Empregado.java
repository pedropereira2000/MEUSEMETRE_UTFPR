package ex8;

public class Empregado {
	long salario;
	String categoria;

	public Empregado(long salario, String categoria) {
		this.salario = salario;
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public long getSalario() {
		return salario;
	}
}
