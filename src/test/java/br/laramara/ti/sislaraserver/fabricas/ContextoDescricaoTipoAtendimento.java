package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.TipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.TipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class ContextoDescricaoTipoAtendimento {

	public static DescricaoTipoAtendimentoDTO construirDescricaoTipoAtendimentoDTO() {

		Long id = new Long(122222);
		String nome = "Aula de informatica.";
		String descricao = "Descrição do tipo de atendimento.";
		String sigla = "OOAD";

		List<SetorDTO> setoresDto = new ArrayList<>();
		setoresDto.add(new SetorDTO("CTO"));
		setoresDto.add(new SetorDTO("PROCEJA"));
		
		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(new Long(11111));
		
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(1002), "G02"));
		
		List<ModuloDTO> modulosDto = new ArrayList<>();
		modulosDto.add(new ModuloDTO(new Long(1), "Informatica"));
		
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(id);
		descricaoTipoAtendimentoDto.setNome(nome);
		descricaoTipoAtendimentoDto.setDescricao(descricao);
		descricaoTipoAtendimentoDto.setSigla(sigla);
		descricaoTipoAtendimentoDto.setSetoresDto(setoresDto);
		descricaoTipoAtendimentoDto.setTipoAtendimentoDto(tipoAtendimentoDto);
		descricaoTipoAtendimentoDto.setNomesGruposDto(nomesGruposDto);
		descricaoTipoAtendimentoDto.setModulosDto(modulosDto);
		
		return descricaoTipoAtendimentoDto;
	}
	
	
	public static DescricaoTipoAtendimentoDTO construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOrtoptica() {
		TipoAtendimentoDTO tipoAtendimentoDTO = new TipoAtendimentoDTO();
		tipoAtendimentoDTO.setId(new Long(7));
		tipoAtendimentoDTO.setNome("Atenção Especializada em Oftalmologia e Ortóptica");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDTO);
		descricaoTipoAtendimentoDTO.setId(new Long(42));
		descricaoTipoAtendimentoDTO.setDescricao("Avaliação de Serviço de Atenção Especializada em Ortoptica");
		return descricaoTipoAtendimentoDTO;
	}
	
	public static DescricaoTipoAtendimentoDTO construirDescricaoTipoAtendimentoDTOAcompanhamentoServicoOrtoptica() {
		TipoAtendimentoDTO tipoAtendimentoDTO = new TipoAtendimentoDTO();
		tipoAtendimentoDTO.setId(new Long(7));
		tipoAtendimentoDTO.setNome("Atenção Especializada em Oftalmologia e Ortóptica");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDTO);
		descricaoTipoAtendimentoDTO.setId(new Long(43));
		descricaoTipoAtendimentoDTO.setDescricao("Acompanhamento de Serviço de Atenção Especializada em Ortoptica");
		return descricaoTipoAtendimentoDTO;
	}
	
	public static DescricaoTipoAtendimentoDTO construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia() {
		TipoAtendimentoDTO tipoAtendimentoDTO = new TipoAtendimentoDTO();
		tipoAtendimentoDTO.setId(new Long(7));
		tipoAtendimentoDTO.setNome("Atenção Especializada em Oftalmologia e Ortóptica");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDTO);
		descricaoTipoAtendimentoDTO.setId(new Long(40));
		descricaoTipoAtendimentoDTO.setDescricao("Avaliação de Serviço de Atenção Especializada em Oftalmologia");
		return descricaoTipoAtendimentoDTO;
	}
	
	
	public static DescricaoTipoAtendimentoDTO construirDescricaoTipoAtendimentoDTOAcompanhamentoServicoOftalmologia() {
		TipoAtendimentoDTO tipoAtendimentoDTO = new TipoAtendimentoDTO();
		tipoAtendimentoDTO.setId(new Long(7));
		tipoAtendimentoDTO.setNome("Atenção Especializada em Oftalmologia e Ortóptica");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDTO);
		descricaoTipoAtendimentoDTO.setId(new Long(41));
		descricaoTipoAtendimentoDTO.setDescricao("Acompanhamento de Serviço de Atenção Especializada em Oftalmologia");
		return descricaoTipoAtendimentoDTO;
	}
	
	public static DescricaoTipoAtendimentoDTO construirDescricaoAvaliacaoFuncionalDTO() {
		TipoAtendimentoDTO tipoAtendimentoDTO = new TipoAtendimentoDTO();
		tipoAtendimentoDTO.setId(new Long(2));
		tipoAtendimentoDTO.setNome("Avaliação e Triagem");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDTO);
		descricaoTipoAtendimentoDTO.setId(new Long(17));
		descricaoTipoAtendimentoDTO.setDescricao("Avaliação Funcional");
		return descricaoTipoAtendimentoDTO;
	}
	
	public static DescricaoTipoAtendimentoDTO construirDescricaoServicoSocialDTO() {
		TipoAtendimentoDTO tipoAtendimentoDTO = new TipoAtendimentoDTO();
		tipoAtendimentoDTO.setId(new Long(6));
		tipoAtendimentoDTO.setNome("Atenção Psicossocial");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDTO);
		descricaoTipoAtendimentoDTO.setId(new Long(37));
		descricaoTipoAtendimentoDTO.setDescricao("Serviço Social");
		return descricaoTipoAtendimentoDTO;
	}
	
	public static DescricaoTipoAtendimento fabricarComTodosOsDados() {

		List<Setor> setores = new ArrayList<>();
		setores.add(Setor.PROCEJA);
		setores.add(Setor.CTO);
		
		TipoAtendimento tipoAtendimento = new TipoAtendimento();
		tipoAtendimento.setId(new Long(22222));
		
		List<NomeGrupo> nomesGrupos = new ArrayList<>();
		nomesGrupos.add(new NomeGrupo(new Long(1002), "G02"));

		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));
		descricaoTipoAtendimento.setNome("ORTOPTICA");
		descricaoTipoAtendimento.setDescricao("Descrição do atendimento");
		descricaoTipoAtendimento.setSigla("ORT");
		descricaoTipoAtendimento.setSetor(setores);
		descricaoTipoAtendimento.setTipoAtendimento(tipoAtendimento);
		descricaoTipoAtendimento.setNomesGrupos(nomesGrupos);

		return descricaoTipoAtendimento;
	}

	public static DescricaoTipoAtendimento fabricarServicoSocial() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(DescricaoTipoAtendimento.ID_SERVICO_SOCIAL);
		return descricaoTipoAtendimento;
	}
}
