package br.laramara.ti.sislaraserver.relatorios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;

@Component
public class GeradorRelatorio {
	
	protected static final String MENSAGEM_ERRO_DURANTE_OPERACAO = "Erro durante operação. Verifique a consistência dos dados.";

	private static final String COLUNA_NOME_USUARIO = "row_name";
	private static final String QUEBRA = "\n";
	protected static final String SEPARADOR = ";";
	
	@Inject
	private RepositorioSislara repositorioSislara;

	private static final Logger logger = Logger
			.getLogger(GeradorRelatorio.class);

	public Arquivo gerarRelatorio(ContaAcesso contaAcesso,
			Map<String, Object> parametros, ModeloRelatorio modeloRelatorio) {
		byte[] relatorio = null;
		try {
			Exportador exportador = contaAcesso
					.getExtensaoRelatorios().obterExportador();
			relatorio = exportador.exportar(modeloRelatorio,
					parametros);
		} catch (Exception e) {
			logger.fatal("Erro durante geração de relatório. "
					+ "OBS: A conexão do relatório não foi fechada. Ficar atendo ao limite de conexções abertas. \n Detalhes: "
					+ e);
		}
		return new Arquivo(relatorio, contaAcesso.getExtensaoRelatorios());
	}

	public ArquivoDTO gerarRelatorioFrequenciasDeAtendimentosGlobaisPorUsuarioNoPeriodo(String prontuarios,
			String dataInicio, String dataTermino, ContaAcesso contaAcesso) {
		ArquivoDTO arquivoCsvDto = new ArquivoDTO();
		try {
			String conteudoCsv = "Usuários no período de " + dataInicio + " até " + dataTermino + SEPARADOR
					+ removerMarcacoes(obterDiasDoMes().toString()) + QUEBRA;

			List<Map<String, Object>> frequencias = repositorioSislara
					.gerarRelatorioFrequenciasDeAtendimentosGlobaisPorUsuarioNoPeriodo(prontuarios, dataInicio,
							dataTermino);

			for (Map<String, Object> frequencia : frequencias) {
				String nome = (String) frequencia.get(COLUNA_NOME_USUARIO);
				conteudoCsv += nome + SEPARADOR + obterFrequenciasDosDias(frequencia) + QUEBRA;
			}
			arquivoCsvDto = new FabricaArquivo()
					.converterParaDTO(new Arquivo(conteudoCsv.getBytes(), ExtensaoArquivo.csv));
			logger.info("Relatório de frequência de atendimentos globais gerado com os prontuários " + prontuarios
					+ " do período " + dataInicio + " até " + dataTermino + ". Solicitado por: "+ contaAcesso);
		} catch (Exception e) {
			arquivoCsvDto.adicionarErro(MENSAGEM_ERRO_DURANTE_OPERACAO);
		}
		return arquivoCsvDto;
	}
	
	private String removerMarcacoes(String texto) {
		return texto.toString().replaceAll(", ", SEPARADOR).replaceAll("\\[", "").replaceAll("\\]", "")
				.replaceAll("null", "");
	}
	
	private String obterFrequenciasDosDias(Map<String, Object> resultado) {
		List<Integer> dias = obterDiasDoMes();
		String diasPreenchidos = removerMarcacoes(dias.stream().map(dia -> (String) resultado.get(dia.toString()))
				.collect(Collectors.toList()).toString());
		return diasPreenchidos;
	}

	private List<Integer> obterDiasDoMes() {
		return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
				22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
	}
}
