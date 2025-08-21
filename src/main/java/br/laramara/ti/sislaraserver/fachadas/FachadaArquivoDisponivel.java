package br.laramara.ti.sislaraserver.fachadas;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemArquivoDisponivelDTO;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.ArquivoDisponivel;
import br.laramara.ti.sislaraserver.dominio.TipoArquivoDisponivel;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivo;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivoDisponivel;
import br.laramara.ti.sislaraserver.repositorios.RepositorioArquivo;

@Component
public class FachadaArquivoDisponivel {

	@Inject
	private RepositorioArquivo repositorioArquivo;

	public ResultadoListagemArquivoDisponivelDTO obterListagemArquivosDisponiveis(String dadosPesquisa, boolean somenteGrupos) {
		ResultadoListagemArquivoDisponivelDTO resultadoListagemArquivoDisponivelDTO = new ResultadoListagemArquivoDisponivelDTO();
		List<ArquivoDisponivel> arquivosDisponiveis = repositorioArquivo.obterArquivosDisponiveis(dadosPesquisa, somenteGrupos);
		resultadoListagemArquivoDisponivelDTO
				.efetuadoComSucesso(new FabricaArquivoDisponivel().converterParaDTO(arquivosDisponiveis));
		return resultadoListagemArquivoDisponivelDTO;
	}

	public ArquivoDTO obterArquivoDisponivel(String idAtendimento, String nome, String tipo) {
		if (tipo.equals(TipoArquivoDisponivel.INDIVIDUAL.toString())) {
			return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoAtendimentoIndividual(
					new AtendimentoIndividual(Long.valueOf(idAtendimento)), new Arquivo(nome)));
		} else if (tipo.equals(TipoArquivoDisponivel.USUARIO_NO_GRUPO.toString())) {
			return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoAtendimentoUsuario(
					new AtendimentoUsuario(Long.valueOf(idAtendimento)), new Arquivo(nome)));
		} else if (tipo.equals(TipoArquivoDisponivel.GRUPO.toString())) {
			return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoAtendimentoGrupo(
					new AtendimentoGrupo(Long.valueOf(idAtendimento)), new Arquivo(nome)));
		}
		return null;
	}
}
