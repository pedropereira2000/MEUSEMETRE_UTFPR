package turma;

public class TurmaController {
	TurmaDAO turmaDao;
	VerificadorDeCodigos verificador;

	public TurmaController(TurmaDAO pTurmaDao) {
		turmaDao = pTurmaDao;
	}

	public void setVerificador(VerificadorDeCodigos verificador) {
		this.verificador = verificador;
	}

	public String cadastrarTurma(Turma t) {
		if (!verificador.verificarCodigoDisciplina(t.getCodDisciplina()))
			return "codigo disciplina invalido";
		if (!verificador.verificarCodigoTurma(t.getCodTurma()))
			return "codigo turma invalido";
		if (turmaDao.existe(t))
			return "turma ja existe";
		if (turmaDao.salvar(t))
			return "turma salva com sucesso";
		else
			return "turma nao salva. Erro no BD";
	}
}
