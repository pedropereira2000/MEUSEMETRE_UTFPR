package Exercicio3_ImcStatus;

public class IMCStatus {
	double imc;
	String classificacao;

	public IMCStatus(double imc, String classificacao) {
		this.imc = imc;
		this.classificacao = classificacao;
	} 
	
	public double getIMC() {
		return this.imc;
	}
	
	public String getClassificacao() {
		return this.classificacao;
	}
}
