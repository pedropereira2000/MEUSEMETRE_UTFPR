package ex8;

import java.util.List;

public class Auditor {
	Validador v;
	EmpregadoDAO dao;

	public Auditor(EmpregadoDAO dao) {
		this.dao = dao;
	}

	public void setValidador(Validador v) {
		this.v = v;
	}

	public String getSuperSalarios(String categoria, long salario) {
		if (!v.ehCategoriaValida(categoria))
			throw new IllegalArgumentException("categoriainvalida");
		if (!v.ehSalarioValido(salario))
			throw new IllegalArgumentException("salario invalido");
		List<Empregado> emps = dao.getAll();
		int naCategoria = 0, acima = 0;
		for (Empregado e : emps) {            
			if(categoria.equals( e.getCategoria())) {                
				naCategoria++;                
				if(e.getSalario() > salario)                    
					acima++;          
			}        
		}        
		
		if(naCategoria == 0)            
			return "ninguem na categoria";        
		if(acima == 0)            
			return "ninguem na categoria com salario alto";        
		
		return "pessoas com salario alto: " + acima;    
	}
}
