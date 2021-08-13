package ex1;

public class Pessoa {
	int codigo, idade;
	String nome;

	public Pessoa(int codigo, int idade, String nome) {
		this.codigo = codigo;
		this.idade = idade;
		this.nome = nome;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public int getIdate() {
		return idade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCodigo(int cod) {
		codigo = cod;
	}
	
	public void setIdade(int idi) {
		idade = idi;
	}
	
	public void setNome(String n) {
		nome = n;
	}
}
