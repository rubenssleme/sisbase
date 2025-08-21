package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.OpcaoIntegracaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;
import br.laramara.ti.sislaraserver.dominio.atendimento.OpcaoIntegracao;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public class ContextoAtendimentoIndividual {

	public static AtendimentoIndividual fabricarComTodosOsDados() {
		Usuario usuario = ContextoUsuario.fabricaUsuarioComTodosOsDadosEProntuarioAlternativo();
		InformacaoAtendimento informacaoAtendimento = ContextoInformacaoAtendimento
				.fabricarInformacaoAtendimentoComTodosOsDados();
		DescricaoTipoAtendimento descricaoTipoAtendimento = ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados();
		Modulo modulo = ContextoModulo.fabricarComTodosOsDados();
		Setor setor = Setor.CTO;
		String texto = "Grande observação.";
		String data = "31/12/2012";
		Horario horario = new Horario("09:00", "10:00");
		LocalAtendimento localAtendimento = ContextoLocalAtendimento.fabricarComTodosOsDados();
		List<AtendimentoProfissional> atendimentosProfissionais = new ArrayList<>();
		atendimentosProfissionais.add(ContextoAtendimentoProfissional.fabricarAtendimentoProfissionalComTodosOsDados());
		Status primeiraVez = Status.RETORNO;
		
		AtendimentoIndividual atendimento = new AtendimentoIndividual();
		atendimento.setUsuario(usuario);
		atendimento.setInformacaoAtendimento(informacaoAtendimento);
		atendimento.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		atendimento.setModulo(modulo);
		atendimento.setSetor(setor);
		atendimento.setInterdisciplinar(texto);
		atendimento.setData(data);
		atendimento.setHorario(horario);
		atendimento.setLocalAtendimento(localAtendimento);
		atendimento.setAtendimentosProfissionais(atendimentosProfissionais);
		atendimento.setPrimeiraVezOuRetorno(primeiraVez);
		atendimento.setOpcaoIntegracao(OpcaoIntegracao.INTEGRACAO);
		atendimento.setMotivoNaoIntegracao("Texto de motivo.");
		atendimento.setDiscussaoCasoSimNao(SimNao.NAO);
		return atendimento;
	}

	public static AtendimentoIndividualDTO fabricarDtoComTodosOsDadosUsuario(){
		AtendimentoIndividualDTO atendimentoIndividualDTO = fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDTO.setPreCadastroDto(null);
		atendimentoIndividualDTO.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());
		return atendimentoIndividualDTO;
	}
	
	public static AtendimentoIndividualDTO fabricarDtoComTodosOsDadosUsuarioAlternativa(){
		AtendimentoIndividualDTO atendimentoIndividualDTO = fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDTO.setPreCadastroDto(null);
		atendimentoIndividualDTO.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoC());
		return atendimentoIndividualDTO;
	}
	
	public static AtendimentoIndividualDTO fabricarDtoComTodosOsDadosUsuarioAlternativo(){
		AtendimentoIndividualDTO atendimentoIndividualDTO = fabricarDtoComTodosOsDadosUsuario();
		return atendimentoIndividualDTO;
	}
	
	public static AtendimentoIndividualDTO fabricarDtoComTodosOsDadosPreCadastro() {
		Long id = new Long(12222);
		String data = "31/12/2012";
		String horaInicio = "09:00";
		String horaTermino = "10:00";
		String inter = "Texto da inter.";
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		HorarioDTO horarioDto = new HorarioDTO(horaInicio, horaTermino);
		InformacaoAtendimentoDTO informacaoAtendimentoDto = ContextoInformacaoAtendimento.construirInformacaoAtendimentoDTO();
		informacaoAtendimentoDto.setFrequenciaDto(new FrequenciaDTO("AT"));
		informacaoAtendimentoDto.setParticipadaoDetalhadaDto(Arrays.asList(ContextoParticipacaoDetalhada.contruirParticipacaoDetalhadaDto()));
		ModuloDTO moduloDto = new ModuloDTO(new Long(1), "TESTE");
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);
		SetorDTO setorDto = new SetorDTO("CTO");
		StatusAtendimentoDTO statusAtendimentoDto = new StatusAtendimentoDTO(
				"NORMAL");
		List<AtendimentoProfissionalDTO> atendimentosProfissionaisDto = new ArrayList<>();
		atendimentosProfissionaisDto.add(ContextoAtendimentoProfissional.construirAtendimentoProfissionalDTO());
		LocalAtendimentoDTO localAtendimentoDto = ContextoLocalAtendimento.construirLocalAtendimentoDTO(); 
		ParticipacaoDTO participacaoDto = new ParticipacaoDTO(Participacao.APENAS_FAMILIA.toString());
		StatusDTO primeiraVezDto = new StatusDTO("RETORNO");
		
		AtendimentoIndividualDTO atendimentoIndividualDto = new AtendimentoIndividualDTO();

		atendimentoIndividualDto.setData(data);
		atendimentoIndividualDto
				.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDto);
		atendimentoIndividualDto.setHorarioDto(horarioDto);
		atendimentoIndividualDto
				.setInformacaoAtendimentoDto(informacaoAtendimentoDto);
		atendimentoIndividualDto.setInterdisciplinar(inter);
		atendimentoIndividualDto.setModuloDto(moduloDto);
		atendimentoIndividualDto.setPreCadastroDto(preCadastroDto);
		atendimentoIndividualDto.setSetorDto(setorDto);
		atendimentoIndividualDto.setStatusAtendimentoDto(statusAtendimentoDto);
		atendimentoIndividualDto.setAtendimentosProfissionalDto(atendimentosProfissionaisDto);
		atendimentoIndividualDto.setLocalAtendimentoDto(localAtendimentoDto);
		atendimentoIndividualDto.setParticipacaoDto(participacaoDto);
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(primeiraVezDto);
		atendimentoIndividualDto.setOpcaoIntegracaoDto(new OpcaoIntegracaoDTO(OpcaoIntegracao.INTEGRACAO.toString()));
		atendimentoIndividualDto.setMotivoNaoIntegracao("Texto de motivo.");
		atendimentoIndividualDto.setDiscussaoCasoSimNaoDto(new SimNaoDTO(SimNao.NAO.toString()));
		return atendimentoIndividualDto;
	}
}
