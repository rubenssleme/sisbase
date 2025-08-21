package br.laramara.ti.sislaraserver.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.espera.StatusEspera;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

public class Automatizador {

	public static synchronized boolean possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(Long prontuario,
			RepositorioEspera repositorioEspera, RepositorioTipoAtendimento repositorioTipoAtendimento) {
		List<Espera> esperas = new ArrayList<>();
		if (prontuario != null) {
			EspecificacaoPesquisaEspera especificacaoPesquisaEspera = obterEspecificacaoPesquisaEsperaServicoSocialPorExcessoDeFaltas(
					repositorioTipoAtendimento, prontuario.toString());
			esperas = repositorioEspera.obterPor(especificacaoPesquisaEspera);
		}
		return !esperas.isEmpty();
	}

	public static synchronized List<Usuario> obterUsuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando(
			List<Usuario> usuarios, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento) {
		return usuarios.stream()
				.filter(usuario -> possuiEsperaNoServicoSocialPorExcessoDeFaltasAguardando(usuario.getId(),
						repositorioEspera, repositorioTipoAtendimento))
				.collect(Collectors.toList());
	}
	
	private static EspecificacaoPesquisaEspera obterEspecificacaoPesquisaEsperaServicoSocialPorExcessoDeFaltas(
			RepositorioTipoAtendimento repositorioTipoAtendimento, String prontuario) {
		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new EspecificacaoPesquisaEspera();
		especificacaoPesquisaEspera
				.setDescricaoTipoAtendimento(repositorioTipoAtendimento.obterDescricaoTipoAtendimentoServicoSocial());
		especificacaoPesquisaEspera.setModulo(repositorioTipoAtendimento.obterModuloExcessoDeFalta());
		especificacaoPesquisaEspera.setProntuario(prontuario);
		especificacaoPesquisaEspera.setStatusEspera(StatusEspera.AGUARDANDO);
		return especificacaoPesquisaEspera;
	}
}
