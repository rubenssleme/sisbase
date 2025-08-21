package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaUsuario;
import br.laramara.ti.sislaraserver.fachadas.Fachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;

public class OperacaoUsuarioEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoUsuarioEdicao.class);

	private FabricaUsuario fabricaUsuario;
	private RepositorioUsuario repositorioUsuario;
	private UsuarioDTO usuarioDto;

	public OperacaoUsuarioEdicao(FabricaUsuario fabricaUsuario,
			RepositorioUsuario repositorioUsuario, UsuarioDTO usuarioDto) {
		this.fabricaUsuario = fabricaUsuario;
		this.repositorioUsuario = repositorioUsuario;
		this.usuarioDto = usuarioDto;
	}

	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		Usuario usuario = fabricaUsuario.converterParaDominio(usuarioDto,
				 repositorioUsuario.obterPorId(usuarioDto
							.getId()));
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (usuarioDto.getVersao().equals(usuario.getVersao())) {
			if (usuario.validado()) {
				logger.info(contaAcesso + " efetuou Solicitação de Edição do "
						+ usuario);
				Usuario usuarioSalvo = repositorioUsuario.salvar(usuario);
				resultado.efetuadoComSucesso(fabricaUsuario
						.converterParaDTO(usuarioSalvo));
			} else {
				resultado.adicionarErro(usuario.obterDescricaoErros());
			}
		} else {
			logger.error(Fachada.MENSAGEM_DADOS_DESATUALIZADOS);
			resultado.adicionarErro(Fachada.MENSAGEM_DADOS_DESATUALIZADOS);
		}
		return resultado;
	}
}
