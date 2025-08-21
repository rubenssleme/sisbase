package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.inscricao.InscricaoDTO;
import br.laramara.ti.sislaraserver.dominio.inscricao.Inscricao;
import br.laramara.ti.sislaraserver.fabricas.ContextoEndereco;
import br.laramara.ti.sislaraserver.fabricas.ContextoGrupo;

public class ContextoInscricao {

	public static InscricaoDTO fabricarInscricaoDTOInvalida() {
		InscricaoDTO inscricaoDto = new InscricaoDTO();

		inscricaoDto.setVersao("");
		inscricaoDto.setId(null);
		inscricaoDto.setEnderecoDto(null);
		inscricaoDto.setNomeParaCracha("");
		inscricaoDto.setObservacoes("");
		inscricaoDto.setAreaFormacao("");
		inscricaoDto.setLocalTrabalho("");
		inscricaoDto.setCargoOuFuncao("");
		inscricaoDto.setDetalheCursoDto(null);
		
		return inscricaoDto;
	}
	
	public static InscricaoDTO fabricarInscricaoDTOComTodosOsDados() {
		InscricaoDTO inscricaoDto = new InscricaoDTO();

		inscricaoDto.setVersao("1");
		inscricaoDto.setEnderecoDto(ContextoEndereco.construirEnderecoDTOSemPaisEZona());
		inscricaoDto.setNomeParaCracha("Carlos Kafka");
		inscricaoDto.setObservacoes("obs");
		inscricaoDto.setAreaFormacao("Tecnologia da Informação");
		inscricaoDto.setLocalTrabalho("Laramara");
		inscricaoDto.setCargoOuFuncao("Analista de Sistemas");
		inscricaoDto.setUsuarioExternoPossuiCadeiraDeRodas(true);
		inscricaoDto.setUsuarioExternoPossuiCaoGuia(true);
		inscricaoDto.setDetalheCursoDto(ContextoDetalheCurso.fabricarDetalheCursoDTOComTodosOsDados());

		return inscricaoDto;
	}
	
	public static Inscricao construirInscricaoComTodosOsDados() {
		Inscricao inscricao = new Inscricao();

		inscricao.setUsuarioExterno(ContextoUsuarioExterno.fabricarUsuarioExternoComTodosOsDados());
		inscricao.setEndereco(ContextoEndereco.construirEnderecoSemPaisEZona());
		inscricao.setNomeParaCracha("Carlos Kafka");
		inscricao.setObservacoes("obs");
		inscricao.setAreaFormacao("Tecnologia da Informação");
		inscricao.setLocalTrabalho("Laramara");
		inscricao.setCargoOuFuncao("Analista de Sistemas");
		inscricao.setUsuarioExternoPossuiCadeiraDeRodas(true);
		inscricao.setUsuarioExternoPossuiCaoGuia(true);
		inscricao.setGrupo(ContextoGrupo.fabricarGrupoComDescricaoTipoAtendimentoCursosValido());
		
		return inscricao;
	}
	
	public static Inscricao construirInscricaoInvalida() {
		Inscricao inscricao = new Inscricao();

		inscricao.setId(null);
		inscricao.setDataHora(null);
		inscricao.setUsuarioExterno(null);
		inscricao.setEndereco(null);
		inscricao.setNomeParaCracha("");
		inscricao.setObservacoes("");
		inscricao.setAreaFormacao("");
		inscricao.setLocalTrabalho("");
		inscricao.setCargoOuFuncao("");
		inscricao.setGrupo(null);
		
		return inscricao;
	}
}
