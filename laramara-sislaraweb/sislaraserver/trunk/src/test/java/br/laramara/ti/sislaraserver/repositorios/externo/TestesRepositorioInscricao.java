package br.laramara.ti.sislaraserver.repositorios.externo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.inscricao.Inscricao;
import br.laramara.ti.sislaraserver.fabricas.externa.ContextoInscricao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInscricao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioInscricao extends TestesIntegracaoAbstrato {

	private RepositorioInscricao repositorioInscricao;

	public TestesRepositorioInscricao() {
		super("DadosTestesRepositorioInscricao.xml");
		repositorioInscricao = Registro.obterRepositorioInscricao();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_inscricao_salva_e_obtem_inscricao_cadastrada_com_sucesso() {
		Inscricao inscricao = repositorioInscricao.salvar(ContextoInscricao.construirInscricaoComTodosOsDados());

		Inscricao inscricaoObtida = repositorioInscricao.obterPorId(inscricao.getId());

		Assert.assertEquals(inscricaoObtida.getId(), inscricao.getId());
	}

}
