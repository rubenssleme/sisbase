package br.laramara.ti.sislaraserver.fachadas.externa;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroInscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroInscricaoDTO;
import br.laramara.ti.sislaraserver.dominio.inscricao.Inscricao;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;
import br.laramara.ti.sislaraserver.fabricas.externa.FabricaInscricao;
import br.laramara.ti.sislaraserver.fachadas.Fachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInscricao;

@Component
public class FachadaInscricao extends Fachada {

	@Inject
	private RepositorioInscricao repositorioInscricao;

	@Inject
	private RepositorioGrupo repositorioGrupo;

	public ResultadoCadastroInscricaoDTO cadastrarInscricao(
			SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto) {
		ResultadoCadastroInscricaoDTO resultadoCadastroInscricaoDto = new ResultadoCadastroInscricaoDTO();

		if (estaLogado(solicitacaoCadastroInscricaoDto.getToken())) {
			UsuarioExterno usuarioExterno = repositorioUsuarioExterno
					.obterPorToken(solicitacaoCadastroInscricaoDto.getToken());

			Inscricao inscricao = new FabricaInscricao()
					.converterParaDominio(solicitacaoCadastroInscricaoDto.getInscricaoDto());

			inscricao.validarObrigatoriedadeETamanhoMaximoDeDados();
			
			if (inscricao.validado()) {
				inscricao.setUsuarioExterno(usuarioExterno);
				inscricao.setGrupo(repositorioGrupo.obterPorId(inscricao.getGrupo().getId()));
				repositorioInscricao.salvar(inscricao);
				resultadoCadastroInscricaoDto.efetuadoComSucesso();
			} else {
				resultadoCadastroInscricaoDto.adicionarErro(inscricao.obterDescricaoErros());
			}
		} else {
			resultadoCadastroInscricaoDto.adicionarErro(ERRO_SUA_SESSAO_EXPIROU_EFETUE_LOGIN_NOVAMENTE);
		}
		
		return resultadoCadastroInscricaoDto;
	}

}
