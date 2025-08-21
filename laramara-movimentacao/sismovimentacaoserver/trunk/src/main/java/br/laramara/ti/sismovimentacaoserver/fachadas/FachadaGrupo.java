package br.laramara.ti.sismovimentacaoserver.fachadas;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaProfissional;
import br.laramara.ti.sismovimentacaoserver.repositorios.QualificadorRepositorioProfissionalSistema;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioProfissional;

@Component
public class FachadaGrupo extends Fachada {

	@Inject
	@QualificadorRepositorioProfissionalSistema
	private RepositorioProfissional repositorioProfissional;

	public ResultadoListagemProfissionalDTO obterListagemProfissionaisAtivos() {
		return (ResultadoListagemProfissionalDTO) obterListagem(
				repositorioProfissional.obterProfissionaisAtivos(),
				new FabricaProfissional(), "Profissionais Ativos",
				new ResultadoListagemProfissionalDTO());
	}
	
	public ResultadoListagemProfissionalDTO obterListagemTodosProfissionais() {
		return (ResultadoListagemProfissionalDTO) obterListagem(
				repositorioProfissional.obterTodos(),
				new FabricaProfissional(), "Todos os Profissionais",
				new ResultadoListagemProfissionalDTO());
	}
}
