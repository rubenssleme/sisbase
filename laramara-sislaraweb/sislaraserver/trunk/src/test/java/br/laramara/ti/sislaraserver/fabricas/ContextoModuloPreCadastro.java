package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;
import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPreCadastro;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;

public class ContextoModuloPreCadastro {

	public static ModuloPreCadastro fabricarComTodosOsDados() {
		ModuloPreCadastro moduloPreCadastro = new ModuloPreCadastro();
		moduloPreCadastro.setPreCadastro(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		moduloPreCadastro.setUsuarioVinculado(ContextoUsuario
				.fabricaUsuarioComTodosOsDadosEProntuario());
		moduloPreCadastro.setDataInicio("31/01/2011");
		moduloPreCadastro.setDataOcorrencia("31/12/2011");
		moduloPreCadastro.setStatus(StatusRelacaoComModulo.INTEGRADO);
		moduloPreCadastro.setAprovado(true);
		moduloPreCadastro.setObs("Grande texto de observação.");
		moduloPreCadastro
				.setTipoVinculo(new TipoVinculo(new Long(1), "Empresa"));
		moduloPreCadastro.setNomeOrigemComunidade("CTIS");
		moduloPreCadastro.setInstituicao(ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados());
		moduloPreCadastro.setInstituicaoComSrms(ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados());
		moduloPreCadastro.setInstituicaoComSalaRecurso(ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados());
		moduloPreCadastro.setInstituicaoComOutrosAEE(ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados());
		moduloPreCadastro.setDreCefai(new DreCefai(new Long(11111),
				"CEFAI - Butantã"));
		moduloPreCadastro.setDiretoriaEnsino(new DiretoriaEnsino(
				new Long(11111), "Diretoria Centro"));
		moduloPreCadastro.setQuantidadeCriancas("1234");
		moduloPreCadastro.setQuantidadeAdultos("1234");
		return moduloPreCadastro;
	}

	public static ModuloPreCadastroDTO fabricarModuloPreCadastroDTO() {
		ModuloPreCadastroDTO moduloPreCadastroDTO = new ModuloPreCadastroDTO();
		moduloPreCadastroDTO.setPreCadastroDto(ContextoPreCadastro
				.construirPreCadastroDTO());
		moduloPreCadastroDTO.setUsuarioVinculadoDto(ContextoUsuario
				.construirUsuarioDTOComIdentificacao());
		moduloPreCadastroDTO.setDataInicio("31/01/2011");
		moduloPreCadastroDTO.setDataOcorrencia("31/12/2011");
		moduloPreCadastroDTO.setStatusDto(new StatusRelacaoComModuloDTO(
				"DESLIGADO"));
		moduloPreCadastroDTO.setAprovado(true);
		moduloPreCadastroDTO.setObs("Grande texto de observação.");
		moduloPreCadastroDTO.setTipoVinculoDto(new TipoVinculoDTO(new Long(1),
				"Empresa"));
		moduloPreCadastroDTO.setNomeOrigemComunidade("CTIS");
		moduloPreCadastroDTO.setInstituicaoDto(ContextoInstituicao
				.construirInstitucaoDTO());
		moduloPreCadastroDTO.setInstituicaoComSrmsDto(ContextoInstituicao
				.construirInstitucaoDTO());
		moduloPreCadastroDTO
				.setInstituicaoComSalaDeRecursoDto(ContextoInstituicao
						.construirInstitucaoDTO());
		moduloPreCadastroDTO.setInstituicaoComOutrosAEEDto(ContextoInstituicao
				.construirInstitucaoDTO());
		moduloPreCadastroDTO.setDreCefaiDto(new DreCefaiDTO(new Long(11111),
				"CEFAI - Butantã"));
		moduloPreCadastroDTO.setDiretoriaEnsinoDto(new DiretoriaEnsinoDTO(
				new Long(11111), "Diretoria Centro"));
		moduloPreCadastroDTO.setQuantidadeCriancas("1234");
		moduloPreCadastroDTO.setQuantidadeAdultos("1234");
		return moduloPreCadastroDTO;
	}

	public static ModuloPreCadastroDTO fabricarModuloPreCadastroDTOSemErro() {
		ModuloPreCadastroDTO moduloPreCadastroDTO = fabricarModuloPreCadastroDTO();
		moduloPreCadastroDTO.setDiretoriaEnsinoDto(null);
		moduloPreCadastroDTO.setInstituicaoComSrmsDto(null);
		moduloPreCadastroDTO.setInstituicaoComOutrosAEEDto(null);
		return moduloPreCadastroDTO;
	}
}