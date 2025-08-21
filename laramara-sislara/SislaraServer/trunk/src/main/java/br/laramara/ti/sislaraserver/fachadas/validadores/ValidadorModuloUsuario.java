package br.laramara.ti.sislaraserver.fachadas.validadores;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoModuloRelacaoBaseDTO;
import br.laramara.ti.sislaraserver.dominio.ModuloRelacaoBase;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaModuloUsuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursiva;

public class ValidadorModuloUsuario extends ValidadorModuloRelacaoBase{
	
	@Override
	@SuppressWarnings("unchecked")
	protected boolean possuiDuplicacao(List<? extends ModuloRelacaoBase> modulosRelacaoBase,
			ModuloRelacaoBase moduloRelacaoBaseAVerificar) {
		return !ModuloPeriodo.possuiUsuarioDuplicado((List<ModuloUsuario>)modulosRelacaoBase,
				(ModuloUsuario) moduloRelacaoBaseAVerificar);
	}

	@Override
	public ResultadoValidacaoModuloRelacaoBaseDTO validarModulo(ModuloRelacaoBaseDTO moduloRelacaoBaseDto) {
		return (ResultadoValidacaoModuloRelacaoBaseDTO) efetuarValidacao((ModuloUsuarioDTO) moduloRelacaoBaseDto,
				new FabricaModuloUsuario(), "Relação de Módulo e Usuário",
				new ResultadoValidacaoModuloRelacaoBaseDTO());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FabricaRecursiva obterFabrica() {
		return new FabricaModuloUsuario();
	}

	@Override
	protected String obterMenssagemErro() {
		return "O Usuário já está integrado.";
	}
}
