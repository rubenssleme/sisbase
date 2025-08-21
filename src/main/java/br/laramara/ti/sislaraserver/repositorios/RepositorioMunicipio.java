package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;

public interface RepositorioMunicipio {
	public List<Municipio> pesquisarPor(UF uf);
	
	public EnderecoCEP carregarMunicipio(EnderecoCEP enderecoCEP);
}
