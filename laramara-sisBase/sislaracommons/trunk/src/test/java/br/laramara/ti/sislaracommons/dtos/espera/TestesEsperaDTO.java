package br.laramara.ti.sislaracommons.dtos.espera;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEsperaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamentodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		PreCadastroDTO preCadastroDTO = new PreCadastroDTO();
		preCadastroDTO.setId(id);
		UsuarioDTO usuarioDTO = new UsuarioDTO(id);
		String data = "31/12/2012";
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(id);
		ModuloDTO moduloDto = new ModuloDTO(id, "");
		SetorDTO setorDto = new SetorDTO("PROCEJA");
		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(new Long(1), "G02");
		TipoEsperaDTO tipoEsperaDto = new TipoEsperaDTO("LU");
		String texto = "Grande texto de observação";
		StatusEsperaDTO statusEsperaDto = new StatusEsperaDTO("AGENDADO");
		boolean estaCancelado = true;
		boolean estaAguardando = true;
		boolean estaTriando = true;
		boolean ligou = true;
		boolean pendencias = true;
		ProfissionalDTO profissionalDto = new ProfissionalDTO(id, "JOSEP", "123");
		List<SetorDTO> setoresUsuarios = new ArrayList<>();
		setoresUsuarios.add(new SetorDTO("CTO"));

		EsperaDTO esperaDto = new EsperaDTO();
		esperaDto.setId(id);
		esperaDto.setUsuarioDto(usuarioDTO);
		esperaDto.setPreCadastroDto(preCadastroDTO);
		esperaDto.setDataExpectativa(data);
		esperaDto.setDataCadastro(data);
		esperaDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDto);
		esperaDto.setModuloDto(moduloDto);
		esperaDto.setSetorDto(setorDto);
		esperaDto.setNomeGrupoDto(nomeGrupoDto);
		esperaDto.setProfissionalSolicitouDto(profissionalDto);
		esperaDto.setTipoEsperaDto(tipoEsperaDto);
		esperaDto.setStatusDto(statusEsperaDto);
		esperaDto.setEstaCancelado(estaCancelado);
		esperaDto.setEstaAguardando(estaAguardando);
		esperaDto.setEstaTriando(estaTriando);
		esperaDto.setObs(texto);
		esperaDto.setJustificativaCancelamento(texto);
		esperaDto.setHistorioOperacoes(texto);
		esperaDto.setSetoresUsuario(setoresUsuarios);
		esperaDto.setLmLigou(ligou);
		esperaDto.setPendencias(pendencias);
		esperaDto.setDataUltimaLigacaoInteresse(data);

		Assert.assertEquals(esperaDto.getId(), id);
		Assert.assertEquals(esperaDto.getUsuarioDto().getId(), id);
		Assert.assertEquals(esperaDto.getPreCadastroDto().getId(), id);
		Assert.assertEquals(esperaDto.getDataCadastro(), data);
		Assert.assertEquals(esperaDto.getDataExpectativa(), data);
		Assert.assertEquals(esperaDto.getDescricaoTipoAtendimentoDto().getId(),
				id);
		Assert.assertEquals(esperaDto.getModuloDto().getId(), id);
		Assert.assertEquals(esperaDto.getSetorDto().toString(),
				setorDto.toString());
		Assert.assertEquals(esperaDto.getNomeGrupoDto().toString(), nomeGrupoDto.toString());
		Assert.assertEquals(esperaDto.getProfissionalSolicitioDto().toString(),
				profissionalDto.toString());
		Assert.assertEquals(esperaDto.getTipoEsperaDto().toString(),
				tipoEsperaDto.toString());
		Assert.assertEquals(esperaDto.getStatusDto().toString(), statusEsperaDto.toString());
		Assert.assertEquals(esperaDto.estaCancelado(), estaCancelado);
		Assert.assertEquals(esperaDto.estaAguardando(), estaAguardando);
		Assert.assertEquals(esperaDto.estaTriando(), estaTriando);
		Assert.assertEquals(esperaDto.getObs(), texto);
		Assert.assertEquals(esperaDto.getJustificativaCancelamento(), texto);
		Assert.assertEquals(esperaDto.getHistorioOperacoes(), texto);
		Assert.assertEquals(esperaDto.getSetoresUsuario().size(),
				setoresUsuarios.size());
		Assert.assertEquals(esperaDto.isLmLigou(), ligou);
		Assert.assertEquals(esperaDto.isPendencias(),  pendencias);
		Assert.assertEquals(esperaDto.getDataUltimaLigacaoInteresse(), data);
	}
}
