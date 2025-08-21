package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.repositorios.integracao.FabricaConexaoBDProfissionaisRH;
import br.laramara.ti.sislaraserver.repositorios.integracao.ProfissionalMapper;

@Repository
@QualificadorRepositorioProfissionalRH
public class RepositorioProfissionalBDdoRH extends RepositorioDB<Profissional> implements
		RepositorioProfissional {

	@Transactional
	public void salvar(Profissional profissionalASalvar) {
	}

	@Override
	public List<Profissional> obterTodos() {
		SqlSession session = FabricaConexaoBDProfissionaisRH
				.getObterSessao().openSession();
		try {
			ProfissionalMapper mapper = session
					.getMapper(ProfissionalMapper.class);
			List<Profissional> list = mapper.obterTodos();
			session.close();
			return list;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Profissional> obterProfissionaisAtivos() {
		return null;
	}

	@Override
	public List<Profissional> obterVoluntarioAtivo() {
		return null;
	}
}
