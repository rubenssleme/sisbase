package br.laramara.ti.sislaracommons.dtos.espera;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.OpcaoIntegracaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesAtendimentoIndividualDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentoindividualdto_foi_construida_com_sucesso() {
		Long id = new Long(1222);
		String data = "31/12/2012";
		String hora = "09:00";
		String texto = "Texto da inter.";
		String idade = "44";
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(id);
		HorarioDTO horarioDto = new HorarioDTO(hora, hora);
		InformacaoAtendimentoDTO informacaoAtendimentoDto = new InformacaoAtendimentoDTO();
		informacaoAtendimentoDto.setId(id);
		ModuloDTO moduloDto = new ModuloDTO(id, "TESTE");
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);
		SetorDTO setorDto = new SetorDTO("CTO");
		StatusAtendimentoDTO statusAtendimentoDto = new StatusAtendimentoDTO(
				"NORMAL");
		UsuarioDTO usuarioDto = new UsuarioDTO(id);
		LocalAtendimentoDTO localAtendimentoDto = new LocalAtendimentoDTO(id,
				"Sala");
		ParticipacaoDTO participacaoDto = new ParticipacaoDTO("COM_FAMILIA");
		StatusDTO statusDTO = new StatusDTO("RETORNO");
		
		SimNaoDTO simNaoDTO = new SimNaoDTO("SIM");
		
		OpcaoIntegracaoDTO opcaoIntegracaoDTO = new OpcaoIntegracaoDTO("INTEGRACAO");

		List<AtendimentoProfissionalDTO> atendimentosProfissionaisDto = new ArrayList<>();
		atendimentosProfissionaisDto.add(new AtendimentoProfissionalDTO());

		AtendimentoIndividualDTO atendimentoIndividualDto = new AtendimentoIndividualDTO();
		atendimentoIndividualDto.setId(id);
		atendimentoIndividualDto.setData(data);
		atendimentoIndividualDto
				.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDto);
		atendimentoIndividualDto.setHorarioDto(horarioDto);
		atendimentoIndividualDto
				.setInformacaoAtendimentoDto(informacaoAtendimentoDto);
		atendimentoIndividualDto.setInterdisciplinar(texto);
		atendimentoIndividualDto.setModuloDto(moduloDto);
		atendimentoIndividualDto.setPreCadastroDto(preCadastroDto);
		atendimentoIndividualDto.setSetorDto(setorDto);
		atendimentoIndividualDto.setStatusAtendimentoDto(statusAtendimentoDto);
		atendimentoIndividualDto.setUsuarioDto(usuarioDto);
		atendimentoIndividualDto
				.setAtendimentosProfissionalDto(atendimentosProfissionaisDto);
		atendimentoIndividualDto.setLocalAtendimentoDto(localAtendimentoDto);
		atendimentoIndividualDto.setParticipacaoDto(participacaoDto);
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(statusDTO);
		atendimentoIndividualDto.setIdadeDoUsuarioOuPreCadastro(idade);
		atendimentoIndividualDto.setOpcaoIntegracaoDto(opcaoIntegracaoDTO);
		atendimentoIndividualDto.setMotivoNaoIntegracao(texto);
		atendimentoIndividualDto.setDiscussaoCasoSimNaoDto(simNaoDTO);
		

		Assert.assertEquals(atendimentoIndividualDto.getId(), id);
		Assert.assertEquals(atendimentoIndividualDto.getData(), data);
		Assert.assertEquals(atendimentoIndividualDto
				.getDescricaoTipoAtendimentoDto().getId(), id);
		Assert.assertEquals(
				atendimentoIndividualDto.getHorarioDto().toString(),
				horarioDto.toString());
		Assert.assertEquals(atendimentoIndividualDto
				.getInformacaoAtendimentoDto().getId(), id);
		Assert.assertEquals(atendimentoIndividualDto.getInterdisciplinar(),
				texto);
		Assert.assertEquals(atendimentoIndividualDto.getModuloDto().getId(), id);
		Assert.assertEquals(atendimentoIndividualDto.getPreCadastroDto()
				.getId(), id);
		Assert.assertEquals(atendimentoIndividualDto.getSetorDto().toString(),
				setorDto.toString());
		Assert.assertEquals(atendimentoIndividualDto.getStatusAtendimentoDto()
				.toString(), statusAtendimentoDto.toString());
		Assert.assertEquals(atendimentoIndividualDto.getUsuarioDto().getId(),
				id);
		Assert.assertFalse(atendimentoIndividualDto
				.getAtendimentosProfissionalDto().isEmpty());
		Assert.assertEquals(atendimentoIndividualDto.getLocalAtendimentoDto()
				.getId(), localAtendimentoDto.getId());
		Assert.assertEquals(atendimentoIndividualDto.getParticipacaoDto()
				.toString(), participacaoDto.toString());
		Assert.assertEquals(atendimentoIndividualDto.getPrimeiraVezOuRetornoDto().toString(), statusDTO.toString());
		Assert.assertEquals(atendimentoIndividualDto.getIdadeDoUsuarioOuPreCadastro(), idade);
		Assert.assertEquals(atendimentoIndividualDto.getOpcaoIntegracaoDto().toString(), opcaoIntegracaoDTO.toString());
		Assert.assertEquals(atendimentoIndividualDto.getDiscussaoCasoSimNaoDto().toString(), simNaoDTO.toString());
		Assert.assertEquals(atendimentoIndividualDto.getMotivoNaoIntegracao(), texto);
	}
}
