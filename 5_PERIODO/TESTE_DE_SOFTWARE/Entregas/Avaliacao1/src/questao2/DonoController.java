package questao2;

public class DonoController {
	
	private StringValidador validador;
	private VisitasDAO visitas;
	private DonoDAO donos;
	
	public DonoController(StringValidador validador, VisitasDAO visitas, DonoDAO donos) {
		this.validador = validador;
		this.visitas = visitas;
		this.donos = donos;
	}
	
	public Pagina processarFormBusca(String sobrenome) throws Exception {
		var erro = validador.validarSobrenome(sobrenome);
		if (erro != null)
			throw new Exception(erro);
		
		var results = donos.findBySobrenome(sobrenome);
		if (results.isEmpty()) {
			return new Pagina("donos/encontrar", "ERRO: nao encontrado");
		}
		else if (results.size() == 1) {
			var endereco = "donos/" + results.get(0).getId();
			var views = visitas.incrementarVisitasPara(endereco);
			return new Pagina(endereco,views);
		}
		else {
			var views = visitas.incrementarVisitasPara("donos/listar");
			return new Pagina("donos/listar", views);
		}
	}
}
