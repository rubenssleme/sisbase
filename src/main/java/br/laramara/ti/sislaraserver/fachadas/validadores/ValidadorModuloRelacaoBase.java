package br.laramara.ti.sislaraserver.fachadas.validadores;

import java.util.List;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoModuloRelacaoBaseDTO;
import br.laramara.ti.sislaraserver.dominio.ModuloRelacaoBase;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursiva;

public abstract class ValidadorModuloRelacaoBase extends Validador{
	
	protected static Logger logger = Logger.getLogger(ValidadorModuloRelacaoBase.class);
	
	@SuppressWarnings("unchecked")
	public ResultadoValidacaoModuloRelacaoBaseDTO validarModuloRelacaoBase(
			List<? extends ModuloRelacaoBaseDTO> modulosRelacaoBaseDto,
			ModuloRelacaoBaseDTO moduloRelacaoBaseDto) {
		ResultadoValidacaoModuloRelacaoBaseDTO resultado = new ResultadoValidacaoModuloRelacaoBaseDTO();

		List<ModuloRelacaoBase> modulosRelacaoBase = (List<ModuloRelacaoBase>) obterFabrica()
				.converterParaDominio(modulosRelacaoBaseDto);

		ModuloRelacaoBase moduloRelacaoBaseAVerificar =  (ModuloRelacaoBase) obterFabrica().converterParaDominio(moduloRelacaoBaseDto);
		if (possuiDuplicacao(modulosRelacaoBase,
				moduloRelacaoBaseAVerificar)) {
			resultado = validarModulo(moduloRelacaoBaseDto);
		} else {
			resultado.adicionarErro(obterMenssagemErro());
		}
		return resultado;
	}
	
	protected abstract boolean possuiDuplicacao(List<? extends ModuloRelacaoBase> modulosRelacaoBase,
			ModuloRelacaoBase moduloRelacaoBaseAVerificar);

	public abstract ResultadoValidacaoModuloRelacaoBaseDTO validarModulo(ModuloRelacaoBaseDTO moduloBase);
	
	@SuppressWarnings("rawtypes")
	protected abstract FabricaRecursiva obterFabrica();
	
	protected abstract String obterMenssagemErro();
}
