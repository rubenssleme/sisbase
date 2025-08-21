package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Perfil;
import br.laramara.ti.sislaraserver.fabricas.FabricaPerfil;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPerfil;

public class OperacaoPerfilEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoPerfilEdicao.class);

	private RepositorioPerfil repositorioPerfil;
	private PerfilDTO perfilDto;

	public OperacaoPerfilEdicao(RepositorioPerfil repositorioPerfil,
			PerfilDTO perfilDto) {
		this.repositorioPerfil = repositorioPerfil;
		this.perfilDto = perfilDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		Perfil perfilASalvar = new FabricaPerfil()
				.converterParaDominio(perfilDto);

		logger.info(contaAcesso + " efetuou Solicitação de Edição do " + perfilASalvar);
		Perfil perfilSalvo = repositorioPerfil.salvar(perfilASalvar);
		resultado.efetuadoComSucesso(new FabricaPerfil()
				.converterParaDTO(perfilSalvo));
		return resultado;
	}
}
