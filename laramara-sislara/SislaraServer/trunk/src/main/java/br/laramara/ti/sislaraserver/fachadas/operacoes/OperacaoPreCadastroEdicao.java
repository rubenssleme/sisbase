package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.repositorios.RgDuplicadoException;

public class OperacaoPreCadastroEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoPreCadastroEdicao.class);

	private FabricaPreCadastro fabricaPreCadastro;
	private RepositorioSislara repositorioSislara;
	private RepositorioPreCadastro repositorioPreCadastro;
	private PreCadastroDTO preCadastroDto;

	public OperacaoPreCadastroEdicao(FabricaPreCadastro fabricaPreCadastro,
			RepositorioPreCadastro repositorioPreCadastro, RepositorioSislara repositorioSislara,
			PreCadastroDTO preCadastroDto) {
		this.fabricaPreCadastro = fabricaPreCadastro;
		this.repositorioPreCadastro = repositorioPreCadastro;
		this.repositorioSislara = repositorioSislara;
		this.preCadastroDto = preCadastroDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		
		PreCadastro preCadastro = fabricaPreCadastro
				.converterParaDominio(preCadastroDto, repositorioPreCadastro
						.obterPorId(preCadastroDto.getId()));
		preCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (preCadastro.validado()) {
			if (!repositorioSislara.cpfJaExiste(preCadastro.getInformacaoEssencial())) {
				logger.info(contaAcesso + " efetuou Solicitação de Edição do " + preCadastro);
				try {
					repositorioPreCadastro.salvar(preCadastro);
					resultado.efetuadoComSucesso(fabricaPreCadastro.converterParaDTO(preCadastro));
				} catch (RgDuplicadoException e) {
					resultado.adicionarErro(e.getMessage());
				} catch (Exception e) {
					logger.error("Ocorreu algum erro durante o armazenamento do " + preCadastro + ". \nDetalhes: " + e);
				}
			} else {
				resultado.adicionarErro("Cpf já foi cadastrado.");
			}
		} else {
			resultado.adicionarErro(preCadastro.obterDescricaoErros());
		}

		return resultado;
	}
}
