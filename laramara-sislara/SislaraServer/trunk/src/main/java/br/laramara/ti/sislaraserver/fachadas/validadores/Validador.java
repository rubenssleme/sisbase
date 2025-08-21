package br.laramara.ti.sislaraserver.fachadas.validadores;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.fabricas.FabricaBase;

public abstract class Validador {
	
	protected static Logger logger = Logger.getLogger(Validador.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ResultadoDTO efetuarValidacao(ModeloDTO objetoDto,
			FabricaBase fabrica, String descricaoDaListagem,
			ResultadoDTO objetoResultado) {

		ResultadoDTO resultado = objetoResultado;
		try {
			ValidavelObrigatoriedadeETamanhoMaximo objetoDominioValidavel = (ValidavelObrigatoriedadeETamanhoMaximo) fabrica
					.converterParaDominio(objetoDto);
			objetoDominioValidavel
					.validarObrigatoriedadeETamanhoMaximoDeDados();

			if (objetoDominioValidavel.validado()) {
				resultado.efetuadoComSucesso((ModeloDTO) fabrica
						.converterParaDTO(objetoDominioValidavel));
			} else {
				resultado.adicionarErro(objetoDominioValidavel
						.obterDescricaoErros());
			}
		} catch (Exception e) {
			String erro = "Ocorreu um erro durante a validação do "
					+ descricaoDaListagem + ".";
			resultado.adicionarErro(erro);
			logger.error(erro + " \nDetalhes: " + e);
		}
		return resultado;
	}
}
