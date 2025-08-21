package br.laramara.ti.sislaraserver.repositorios;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;
import br.laramara.ti.sislaraserver.repositorios.integracao.CEPMapper;
import br.laramara.ti.sislaraserver.repositorios.integracao.FabricaConexaoBDCEPExterno;

@Repository
public class RepositorioCEPExterno extends RepositorioDB<EnderecoCEP> implements
		RepositorioCEP {

	@Inject
	private RepositorioPais repositorioPais;
	@Inject 
	private RepositorioMunicipio repositorioMunicipio;
		
	@Override
	public EnderecoCEP obterPorCEP(String cep) {
		SqlSession session = FabricaConexaoBDCEPExterno.getObterSessao()
				.openSession();
		try {
			CEPMapper mapper = session.getMapper(CEPMapper.class);
			EnderecoCEP enderecoCEP = mapper.obterTodos(cep);
			
			enderecoCEP = repositorioPais.carregarPais(enderecoCEP);
			enderecoCEP = repositorioMunicipio.carregarMunicipio(enderecoCEP);

			session.close();
			return enderecoCEP;
		} finally {
			session.close();
		}
	}
}
