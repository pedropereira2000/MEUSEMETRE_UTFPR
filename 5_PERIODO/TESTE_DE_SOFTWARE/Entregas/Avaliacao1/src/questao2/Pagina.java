package questao2;

public class Pagina {
	private String endereco;
	private Object dados;
	
	public Pagina(String endereco, Object dados) {
		this.endereco = endereco;
		this.dados = dados;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public Object getDados() {
		return dados;
	}
}
