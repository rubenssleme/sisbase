package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;

public class ContextoEnderecoCEP {
	public static EnderecoCEP fabricarEnderecoComTodosOsDados() {
		EnderecoCEP endereco = new EnderecoCEP();
		endereco.setEndereco("Rua Brigadeiro Galvão");
		endereco.setBairro("Barra Funda");
		endereco.setMunicipio(new Municipio(new Long(4850), "São Paulo", UF.SP));
		endereco.setUf(UF.SP.toString());
		endereco.setPais(new Pais(new Long(1), "Brasil"));
		return endereco;
	}
}
