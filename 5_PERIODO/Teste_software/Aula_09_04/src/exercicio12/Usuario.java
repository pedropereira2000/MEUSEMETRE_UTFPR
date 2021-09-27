package exercicio12;

public class Usuario {
	private String nome, senha, senhaConfirmada;
	
	public Usuario(String nome, String senha, String senhaConfirmada) {
		this.nome = nome;
		this.senha = senha;
		this.senhaConfirmada = senhaConfirmada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaConfirmada() {
		return senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}

}
