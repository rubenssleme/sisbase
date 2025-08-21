package br.laramara.ti.sislaraserver.fachadas;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.ResultadoEdicaoRetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaProfissional;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoRetiradaBaixar;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoRetiradaEfetuar;
import br.laramara.ti.sislaraserver.repositorios.QualificadorRepositorioProfissionalSistema;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProfissional;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRetirada;

@Component
public class FachadaRetirada extends Fachada {

	@Inject
	private RepositorioRetirada repositorioRetirada;
	@Inject
	@QualificadorRepositorioProfissionalSistema
	private RepositorioProfissional repositorioProfissional;
	

	public ResultadoEdicaoRetiradaDTO efetuarRetirada(RetiradaDTO retiradaDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoRetiradaEfetuar(
				repositorioRetirada, retiradaDto);
		return (ResultadoEdicaoRetiradaDTO) verificarPermissaoEProcessar(
				operacao, Permissao.RETIRADA_EDICAO,
				new ResultadoEdicaoRetiradaDTO(), tokenDto);
	}
	
	public ResultadoEdicaoRetiradaDTO baixarRetirada(RetiradaDTO retiradaDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoRetiradaBaixar(
				repositorioRetirada, retiradaDto);
		return (ResultadoEdicaoRetiradaDTO) verificarPermissaoEProcessar(
				operacao, Permissao.RETIRADA_EDICAO,
				new ResultadoEdicaoRetiradaDTO(), tokenDto);
	}

	public ResultadoListagemProfissionalDTO obterListagemVoluntarioAtivo() {
		return (ResultadoListagemProfissionalDTO) obterListagem(
				repositorioProfissional.obterVoluntarioAtivo(),
				new FabricaProfissional(), "Profissional Voluntário",
				new ResultadoListagemProfissionalDTO());
	}
}
