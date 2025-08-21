package br.laramara.ti.sislaraserver.fachadas.validadores;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoModuloRelacaoBaseDTO;
import br.laramara.ti.sislaraserver.dominio.ModuloRelacaoBase;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.FabricaModuloPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursiva;

public class ValidadorModuloPreCadastro extends ValidadorModuloRelacaoBase{
	
	@Override
	@SuppressWarnings("unchecked")
	protected boolean possuiDuplicacao(List<? extends ModuloRelacaoBase> modulosRelacaoBase,
			ModuloRelacaoBase moduloRelacaoBaseAVerificar) {
		return !ModuloPeriodo.possuiPreCadastroDuplicado((List<ModuloPreCadastro>)modulosRelacaoBase,
				(ModuloPreCadastro) moduloRelacaoBaseAVerificar);
	}

	@Override
	public ResultadoValidacaoModuloRelacaoBaseDTO validarModulo(ModuloRelacaoBaseDTO moduloRelacaoBaseDto) {
		return (ResultadoValidacaoModuloRelacaoBaseDTO) efetuarValidacao((ModuloPreCadastroDTO) moduloRelacaoBaseDto,
				new FabricaModuloPreCadastro(), "Rela��o de M�dulo e Pr�-Cadastro",
				new ResultadoValidacaoModuloRelacaoBaseDTO());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FabricaRecursiva obterFabrica() {
		return new FabricaModuloPreCadastro();
	}

	@Override
	protected String obterMenssagemErro() {
		return "O Pr�-Cadastro j� est� integrado.";
	}
}
