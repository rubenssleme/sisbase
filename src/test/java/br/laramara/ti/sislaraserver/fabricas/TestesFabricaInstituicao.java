package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;

public class TestesFabricaInstituicao {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_instituicao_converte_objeto_de_dto_novo_para_dominio() {
		InstituicaoDTO instituicaoDto = ContextoInstituicao
				.construirInstituicaoDTOparaTesteUnitario();

		Instituicao instituicaoCovertida = new FabricaInstituicao()
				.converterParaDominio(instituicaoDto);

		Assert.assertEquals(instituicaoCovertida.getId(),
				instituicaoDto.getId());
		Assert.assertEquals(instituicaoCovertida.getTipoInstituicao()
				.toString(), instituicaoDto.getTipoInstituicaoDto().toString());
		Assert.assertEquals(instituicaoCovertida.getNome().toString(),
				instituicaoDto.getNome());
		Assert.assertEquals(instituicaoCovertida.getEndereco(), new FabricaEndereco()
				.converterParaDominio(instituicaoDto.getEnderecoDto()));
		Assert.assertEquals(
				instituicaoCovertida.getNomeCoordenadorResponsavel(),
				instituicaoDto.getNomeCoordenadorResponsavel());
		Assert.assertEquals(instituicaoCovertida.getClassificacaoInstituicao()
				.toString(), instituicaoDto.getClassificacaoInstituicaoDto()
				.toString());
		Assert.assertEquals(instituicaoCovertida.getObs(),
				instituicaoDto.getObs());
		Assert.assertEquals(instituicaoCovertida.getTipoApoio().toString(),
				instituicaoDto.getTipoApoioDto().toString());
		Assert.assertEquals(instituicaoCovertida.getTiposEspecialidade().size(),
				instituicaoDto.getTiposEspecialidadeDTO().size());
		Assert.assertEquals(instituicaoCovertida.getDreCefai().getNome(),
				instituicaoDto.getDreCefaiDto().toString());
		Assert.assertEquals(instituicaoCovertida.getDiretoriaEnsino().getNome(),
				instituicaoDto.getDiretoriaEnsinoDto().toString());
		Assert.assertEquals(instituicaoCovertida.getEscolaridades().size(),
				instituicaoDto.getEscolaridadesDto().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_instituicao_converte_objeto_de_dominio_para_dto() {
		Long id = new Long(822837);
		Instituicao instituicao = ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados();
		instituicao.setId(id);

		InstituicaoDTO instituicaoCovertida = new FabricaInstituicao()
				.converterParaDTO(instituicao);

		Assert.assertEquals(instituicaoCovertida.getId(), id);
		Assert.assertEquals(instituicaoCovertida.getNome(),
				instituicao.getNome());
		Assert.assertEquals(
				instituicaoCovertida.getNomeCoordenadorResponsavel(),
				instituicao.getNomeCoordenadorResponsavel());
		Assert.assertEquals(instituicaoCovertida
				.getClassificacaoInstituicaoDto().toString(), instituicao
				.getClassificacaoInstituicao().toString());
		Assert.assertEquals(instituicaoCovertida.getTipoInstituicaoDto()
				.toString(), instituicao.getTipoInstituicao().toString());
		Assert.assertEquals(instituicaoCovertida.getObs(), instituicao.getObs());
		Assert.assertEquals(instituicaoCovertida.getTipoApoioDto().toString(),
				instituicao.getTipoApoio().toString());
		Assert.assertEquals(instituicaoCovertida.getTiposEspecialidadeDTO().size(),
				instituicao.getTiposEspecialidade().size());
		Assert.assertEquals(instituicaoCovertida.getDreCefaiDto().toString(),
				instituicao.getDreCefai().getNome());
		Assert.assertEquals(instituicaoCovertida.getDiretoriaEnsinoDto().toString(),
				instituicao.getDiretoriaEnsino().getNome());
		Assert.assertEquals(instituicaoCovertida.getEscolaridadesDto().size(),
				instituicao.getEscolaridades().size());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_instituicao_converte_lista_de_objetos_de_dominio_para_dto() {
		Instituicao instituicaoA = ContextoInstituicao.fabricarInstituicaoComTodosOsDados();
		instituicaoA.setId(new Long(12345678));
		Instituicao instituicaoB = ContextoInstituicao.fabricarInstituicaoComTodosOsDados();
		instituicaoB.setId(new Long(34243344));
		
		List<Instituicao> listaInstituicao = new ArrayList<>();
		listaInstituicao.add(instituicaoA);
		listaInstituicao.add(instituicaoB);
		

		List<InstituicaoDTO> instuicoesCovertidas = new FabricaInstituicao()
				.converterParaDTO(listaInstituicao);

		Assert.assertEquals(instuicoesCovertidas.size(), 2);
		Assert.assertEquals(instuicoesCovertidas.get(0).getId(), new Long(12345678));
	}

}
