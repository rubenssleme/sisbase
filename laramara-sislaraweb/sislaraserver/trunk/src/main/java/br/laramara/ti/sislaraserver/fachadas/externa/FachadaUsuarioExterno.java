package br.laramara.ti.sislaraserver.fachadas.externa;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaPerfilPreenchidoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;
import br.laramara.ti.sislaraserver.fabricas.externa.FabricaUsuarioExterno;
import br.laramara.ti.sislaraserver.fachadas.Fachada;
import br.laramara.ti.sislaraserver.utilitarios.RecuperadorSenha;

@Component
public class FachadaUsuarioExterno extends Fachada {
	
	@Inject
	private RecuperadorSenha recuperadorSenha;

	public ResultadoConsultaUsuarioExternoDTO obterUsuarioExternoPorToken(String tokenDto) {
		ResultadoConsultaUsuarioExternoDTO resultadoConsultaUsuarioExternoDTO = new ResultadoConsultaUsuarioExternoDTO();

		if (estaLogado(tokenDto)) {
			resultadoConsultaUsuarioExternoDTO.efetuadoComSucesso(
					new FabricaUsuarioExterno().converterParaDTO(repositorioUsuarioExterno.obterPorToken(tokenDto)));
		} else {
			resultadoConsultaUsuarioExternoDTO.adicionarErro(ERRO_SUA_SESSAO_EXPIROU_EFETUE_LOGIN_NOVAMENTE);
		}

		return resultadoConsultaUsuarioExternoDTO;
	}
	
	public ResultadoCadastroUsuarioExternoDTO cadastrarUsuarioExterno(
			SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDto) {
		ResultadoCadastroUsuarioExternoDTO resultadoCadastroUsuarioExternoDTO = new ResultadoCadastroUsuarioExternoDTO();

		boolean usuarioExternoJaExiste = repositorioUsuarioExterno
				.obterPorEmail(solicitacaoCadastroUsuarioExternoDto.getEmail()) != null;

		if (!usuarioExternoJaExiste) {
			UsuarioExternoDTO usuarioExternoDTO = new UsuarioExternoDTO(solicitacaoCadastroUsuarioExternoDto.getEmail(),
					solicitacaoCadastroUsuarioExternoDto.getNomeCompleto(),
					solicitacaoCadastroUsuarioExternoDto.getDddETelefone(),
					solicitacaoCadastroUsuarioExternoDto.isAutorizoRecebimentoInformativo());

			UsuarioExterno usuarioExterno = new FabricaUsuarioExterno().converterParaDominio(usuarioExternoDTO);

			usuarioExterno.validarObrigatoriedadeETamanhoMaximoDeDados();

			if (usuarioExterno.validado()) {
				usuarioExterno = repositorioUsuarioExterno.salvar(usuarioExterno);
				resultadoCadastroUsuarioExternoDTO.efetuadoComSucesso();
				recuperadorSenha.enviarEmailDeConfirmacao(usuarioExterno.getEmail());
			} else {
				resultadoCadastroUsuarioExternoDTO.adicionarErro(usuarioExterno.obterDescricaoErros());
			}
		} else {
			resultadoCadastroUsuarioExternoDTO.adicionarErro(
					"O email " + solicitacaoCadastroUsuarioExternoDto.getEmail() + " já está sendo utilizado.");
		}

		return resultadoCadastroUsuarioExternoDTO;
	}

	public ResultadoConsultaPerfilPreenchidoDTO consultarPerfilPreenchido(TokenDTO tokenDto) {
		ResultadoConsultaPerfilPreenchidoDTO resultadoConsultaPerfilPreenchidoDto = new ResultadoConsultaPerfilPreenchidoDTO();

		if (estaLogado(tokenDto.getToken())) {
			UsuarioExterno usuarioExterno = repositorioUsuarioExterno.obterPorToken(tokenDto.getToken());

			usuarioExterno.validarPerfilPreenchido();

			if (usuarioExterno.validado()) {
				resultadoConsultaPerfilPreenchidoDto.efetuadoComSucesso(usuarioExterno.validado());
			} else {
				resultadoConsultaPerfilPreenchidoDto.adicionarErro(usuarioExterno.obterDescricaoErros());
			}
		} else {
			resultadoConsultaPerfilPreenchidoDto.adicionarErro(ERRO_SUA_SESSAO_EXPIROU_EFETUE_LOGIN_NOVAMENTE);
		}

		return resultadoConsultaPerfilPreenchidoDto;
	}

	public ResultadoEdicaoUsuarioExternoDTO editarUsuarioExterno(
			SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto) {
		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = new ResultadoEdicaoUsuarioExternoDTO();

		String token = solicitacaoEdicaoUsuarioExternoDto.getToken();

		if (estaLogado(token)) {
			UsuarioExterno usuarioExterno = new FabricaUsuarioExterno().converterParaDominio(
					solicitacaoEdicaoUsuarioExternoDto.getUsuarioExternoDto(),
					repositorioUsuarioExterno.obterPorToken(token));

			usuarioExterno.validarPerfilPreenchido();

			if (usuarioExterno.validado()) {
				if (!repositorioUsuarioExterno.cpfJaExiste(usuarioExterno.getCpf())) {
					usuarioExterno.atribuirTipoEnderecoResidencial();
					repositorioUsuarioExterno.salvar(usuarioExterno);
					resultadoEdicaoUsuarioExternoDto.efetuadoComSucesso();
				} else {
					resultadoEdicaoUsuarioExternoDto
							.adicionarErro("O cpf " + usuarioExterno.getCpf() + " já está sendo utilizado.");
				}
			} else {
				resultadoEdicaoUsuarioExternoDto.adicionarErro(usuarioExterno.obterDescricaoErros());
			}
		} else {
			resultadoEdicaoUsuarioExternoDto.adicionarErro(ERRO_SUA_SESSAO_EXPIROU_EFETUE_LOGIN_NOVAMENTE);
		}

		return resultadoEdicaoUsuarioExternoDto;
	}

}
