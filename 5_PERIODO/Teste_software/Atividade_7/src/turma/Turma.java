package turma;

public class Turma {    
	String codDisciplina, codTurma;    
	int maximoAlunos;    
	//adicionar os getters e setters}
	
	public Turma(String codDisciplina, String codTurma, int maximoAlunos) {
		this.codDisciplina = codDisciplina;
		this.codTurma = codTurma;
		this.maximoAlunos = maximoAlunos;
	}
	
	//getters
	
	public String getCodDisciplina() {
		return codDisciplina;
	}
	
	public String getCodTurma() {
		return codTurma;
	}
	
	public Integer getMaximoAlunos() {
		return maximoAlunos;
	}
	
	//setters
	
	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
	
	public void setCodTurma(String codTurma) {
		this.codTurma = codTurma;
	}
	
	public void setMaximoAlunos(int maximoAlunos) {
		this.maximoAlunos = maximoAlunos;
	}
	
}
