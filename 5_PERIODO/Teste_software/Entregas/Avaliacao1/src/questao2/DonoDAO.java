package questao2;

import java.util.List;

public interface DonoDAO {
	public List<Dono> findBySobrenome(String sobrenome);
}
