package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDemandaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void demandadto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		String sequenciaCriacao = "1234AAAABBBBB";
		String obs = "Texto de observação";
		boolean novo = true;
		String data = "31/12/2012";
		String valor = "23,39";
		StatusDemandaDTO statusDemandaDto = new StatusDemandaDTO("NOVA");
		
		UsuarioDTO usuarioDto = new UsuarioDTO(id);
		usuarioDto.setId(id);
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(id);
		
		RecursoDTO recursoDto = new RecursoDTO(id, "ABC", false, "100,00");
		
		MotivoCancelamentoDTO motivoCancelamentoDTO = new MotivoCancelamentoDTO(id, "Motivo");

		DemandaDTO demandaDto = new DemandaDTO();
		demandaDto.setId(id);
		demandaDto.setUsuarioDto(usuarioDto);
		demandaDto.setPreCadastroDto(preCadastroDto);
		demandaDto.setGrupoDto(grupoDto);
		demandaDto.setRecursoDto(recursoDto);
		demandaDto.setSequenciaCriacao(sequenciaCriacao);
		demandaDto.setObs(obs);
		demandaDto.setResponsaveisOperacoes(obs);
		demandaDto.setEstaNovo(novo);
		demandaDto.setStatusDemandaDto(statusDemandaDto);
		demandaDto.setDataPrazoDeCaptacao(data);
		demandaDto.setCartelaDeSelos(novo);
		demandaDto.setValorCartela(valor);
		demandaDto.setValorSaldo(valor);
		demandaDto.setMotivoCancelamentoDTO(motivoCancelamentoDTO);
		
		Assert.assertEquals(demandaDto.getId(), id);
		Assert.assertEquals(demandaDto.getUsuarioDto().getId(),
				usuarioDto.getId());
		Assert.assertEquals(demandaDto.getPreCadastroDto().getId(),
				preCadastroDto.getId());
		Assert.assertEquals(demandaDto.getGrupoDto().getId(), grupoDto.getId());
		Assert.assertEquals(demandaDto.getRecursoDto().getId(),
				recursoDto.getId());
		Assert.assertEquals(demandaDto.getSequenciaCriacao(), sequenciaCriacao);
		Assert.assertEquals(demandaDto.getObs(), obs);
		Assert.assertEquals(demandaDto.getResponsaveisOperacoes(), obs);
		Assert.assertEquals(demandaDto.isEstaNovo(), novo);
		Assert.assertEquals(demandaDto.isCartelaDeSelos(), novo);
		Assert.assertEquals(demandaDto.getStatusDemandaDto().toString(), statusDemandaDto.toString());
		Assert.assertEquals(demandaDto.getDataPrazoCaptacao(), data);
		Assert.assertEquals(demandaDto.getValorCartela(), valor);
		Assert.assertEquals(demandaDto.getValorSaldo(), valor);
		Assert.assertEquals(demandaDto.getMotivoCancelamentoDTO().getId(), id);
	}
}
