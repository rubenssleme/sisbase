package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

public class ContextoDemanda {
	public static Demanda fabricarDemandaComTodosOsDados() {
		Demanda demanda = new Demanda();
		demanda.setUsuario(ContextoUsuario.fabricarUsuarioComTodosOsDados());
		demanda.setPreCadastro(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		demanda.setGrupo(ContextoGrupo.fabricarGrupoComTodosOsDados());
		demanda.setRecurso(ContextoRecurso.fabricarRecursoComTodosOsDados());
		demanda.setSequenciaCricao("AAAABBBB");
		demanda.setObs("Texto de obs.");
		demanda.adicionarCaptacaoEmDemandasComStatusAguardandoOuProrrogada(ContextoEspecificacaoCaptacaoDemanda.fabricarDemandaComTodosOsDados(),
				ContaAcesso.obterAcessoRoot());
		demanda.setDataExpectativa("01/01/2000");
		return demanda;
	}

	public static DemandaDTO construirDemandaDTO() {
		DemandaDTO demandaDto = new DemandaDTO();
		demandaDto.setDataPrazoDeCaptacao("31/12/2012");
		demandaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTO());
		demandaDto.setPreCadastroDto(ContextoPreCadastro
				.construirPreCadastroDTO());
		demandaDto.setGrupoDto(ContextoGrupo.construirGrupoDTO());
		demandaDto.setRecursoDto(ContextoRecurso.construirRecursoDTO());
		demandaDto.setObs("Texto de observação.");
		demandaDto.setDataExpectativa("01/01/2000");
		return demandaDto;
	}
}
