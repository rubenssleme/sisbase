package br.laramara.ti.sislaraweb.servicos.rest;

import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoConsultaDetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoListagemDetalhesCursoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.externa.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroInscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaPerfilPreenchidoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoSolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoSolicitacaoRecuperacaoSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroInscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;

public interface ServicoSisLaraClient {
	public ResultadoListagemDetalhesCursoDTO obterDetalhesCurso();

	public ResultadoConsultaDetalheCursoDTO obterDetalheCursoPorIdGrupo(Long idGrupo);

	public ResultadoCadastroUsuarioExternoDTO cadastrarUsuarioExterno(
			SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDTO);

	public ResultadoAutenticacaoDTO autenticarUsuarioExterno(CredencialExternaDTO credencialExternaDto);

	public ResultadoSolicitacaoRecuperacaoSenhaDTO efetuarSolicitacaoRecuperacaoSenha(String email);

	public ResultadoSolicitacaoCadastroNovaSenhaDTO cadastrarNovaSenha(
			SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDto);

	public ResultadoConsultaPerfilPreenchidoDTO consultarPerfilPreenchido(TokenDTO token);

	public ResultadoEdicaoUsuarioExternoDTO editarUsuarioExterno(SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDTO);
	
	public ResultadoConsultaUsuarioExternoDTO obterUsuarioExternoPorToken(String tokenDto);

	public ResultadoConsultaCEP consultarEnderecoPorCep(String cep);
	
	public ResultadoListagemUfDTO obterListagemUf();
	
	public ResultadoListagemMunicipioDTO obterListagemMunicipioPorUf(String uf);

	public ResultadoCadastroInscricaoDTO cadastrarInscricao(SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto);
}
