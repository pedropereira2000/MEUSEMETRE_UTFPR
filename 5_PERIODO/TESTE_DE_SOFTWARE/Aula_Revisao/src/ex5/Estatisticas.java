package ex5;

public class Estatisticas {
	int mulheres, homens;
	float idadeMedia;
	String contexto;

	public Estatisticas(int m, int h, float i, String c) {
		this.mulheres = m;
		this.homens = h;
		this.idadeMedia = i;
		this.contexto = c;
	}
	
	public String getContexto() {
		return contexto;
	}
	
	public int getHomens() {
		return homens;
	}
	
	public float getIdadeMedia() {
		return idadeMedia;
	}
	
	public int getMulheres() {
		return mulheres;
	}
}