package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.TipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class ContextoEspera {
	public static Espera fabricarEsperaComTodosOsDados() {
		Espera espera = new Espera();
		espera.setId(new Long(12222));
		espera.setPreCadastro(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		espera.setDataExpectativa("31/12/2012");
		espera.setDescricaoTipoAtendimento(ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados());
		espera.setModulo(ContextoModulo.fabricarComTodosOsDados());
		espera.setSetor(Setor.PROCEJA);
		espera.setNomeGrupo(new NomeGrupo(new Long(1), "G02"));
		espera.setProfissionalSolicitou(ContextoProfissional.fabricarComTodosOsDados());
		espera.setTipoEspera(TipoEspera.RET);
		espera.setObs("Grande texto de observação");
		espera.setJustificativaCancelamento("Grande texto");
		espera.setContaAcessoOperacao(ContextoContaAcesso.fabricarComTodosOsDados());
		espera.setInteresse(true);		
		espera.setLmLigou(true);
		return espera;
	}
	
	public static Espera fabricarEsperaComTodosOsDadosUsuario() {
		Espera espera = fabricarEsperaComTodosOsDados();
		espera.setPreCadastro(null);
		espera.setUsuario(ContextoUsuario.fabricarUsuarioComTodosOsDados());
		return espera;
	}


	public static EsperaDTO construirEsperaDTO() {
		EsperaDTO esperaDto = new EsperaDTO();
		esperaDto.setPreCadastroDto(ContextoPreCadastro
				.construirPreCadastroDTOsemIdentificacao());
		esperaDto.setDataExpectativa("31/12/2012");
		esperaDto.setDataCadastro("01/01/2012 09:00:00");
		esperaDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento
						.construirDescricaoTipoAtendimentoDTO());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTO());
		esperaDto.setSetorDto(new SetorDTO(Setor.PROCEJA.toString()));
		esperaDto.setNomeGrupoDto(new NomeGrupoDTO(new Long(1000), "G02"));
		esperaDto.setProfissionalSolicitouDto(ContextoProfissional.construirProfissionalDTO());
		esperaDto.setTipoEsperaDto(new TipoEsperaDTO(TipoEspera.RET.toString()));
		esperaDto.setObs("Grande texto de observação");
		esperaDto.setJustificativaCancelamento("Grande texto");
		esperaDto.setLmLigou(true);
		esperaDto.setDataUltimaLigacaoInteresse("31/12/2016");
		esperaDto.setInteresse(true);
		return esperaDto;
	}
}
