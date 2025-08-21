package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;

public class TestesFabricaFamiliar {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_familiar_converte_objeto_de_dto_novo_para_dominio() {
		FamiliarDTO familiarDto = ContextoFamiliar.construirFamiliarDTO();
		familiarDto.setId(null);

		Familiar familiarCovertido = new FabricaFamiliar()
				.converterParaDominio(familiarDto);

		Assert.assertEquals(familiarCovertido.getId(), familiarDto.getId());
		Assert.assertEquals(familiarCovertido.getParentesco().getDescricao(),
				familiarDto.getParentescoDto().toString());
		Assert.assertEquals(familiarCovertido.getInformacaoEssencial().getNome(), familiarDto.getInformacaoEssencialDto().getNome());
		Assert.assertEquals(familiarCovertido.getInformacaoEssencial().getRg(), familiarDto.getInformacaoEssencialDto().getRg());
		Assert.assertEquals(familiarCovertido.getCpf(), familiarDto.getCpf());
		Assert.assertEquals(familiarCovertido.getDataNascimento(),
				DataHoraUtils.formatarData(DataHoraUtils.criar(familiarDto
						.getDataNascimento())));
		Assert.assertEquals(familiarCovertido.getEstadoCivil().getDescricao(),
				familiarDto.getEstadoCivilDto().toString());
		Assert.assertEquals(familiarCovertido.getInformacaoTrabalho().getId(),
				familiarDto.getInformacaoTrabalhoDto().getId());
		Assert.assertEquals(familiarCovertido.getObs(),  familiarDto.getObs());
		Assert.assertEquals(familiarCovertido.getInformacaoEssencial().getContato().getId(),
				familiarDto.getInformacaoEssencialDto().getContatoDto().getId());
		Assert.assertEquals(familiarCovertido.isNaoAlfabetizado(),
				familiarDto.isNaoAlfabetizado());
		Assert.assertEquals(familiarCovertido.obterInformacoesEducacionais()
				.size(), familiarDto.getInformacoesEducacionaisDto().size());
		Assert.assertEquals(familiarCovertido.getRenda(),
				familiarDto.getRenda());
		Assert.assertEquals(familiarCovertido.isPrincipalResponsavel(),
				familiarDto.isPrincipalResponsavel());
		Assert.assertEquals(familiarCovertido.isMoraNaCasa(), familiarDto.isMoraNaCasa());
		Assert.assertEquals(familiarCovertido.getGenero().toString(), familiarDto.getGeneroDto().toString());
		Assert.assertEquals(familiarCovertido.isResponsavelPelaAvaliacaoSocial(), familiarDto.isResponsavelPelaAvaliacaoSocial());
		Assert.assertEquals(familiarCovertido.isAcompanhante(), familiarDto.isAcompanhante());
		Assert.assertEquals(familiarCovertido.isResponsavelPeloUsuario(), familiarDto.isResponsavelPeloUsuario());
		Assert.assertEquals(familiarCovertido.isParadeiroIgnorado(), familiarDto.isParadeiroIgnorado());
		Assert.assertEquals(familiarCovertido.isFalecido(), familiarDto.isFalecido());
		Assert.assertEquals(familiarCovertido.getVulnerabilidades().size(), familiarDto.getVulnerabilidadesDto().size());

	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_familiar_converte_objeto_de_dto_como_data_de_nascimento_e_rendimento_mensal_invalida() {
		FamiliarDTO familiarDto = ContextoFamiliar.construirFamiliarDTO();
		familiarDto.setId(null);
		familiarDto.setDataNascimento("94/34/1222");
		
		Familiar familiarCovertido = new FabricaFamiliar()
				.converterParaDominio(familiarDto);

		Assert.assertEquals(familiarCovertido.getDataNascimento(),
				DataHoraUtils.formatarData(DataHoraUtils.obterDataInvalida()));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_familiar_converte_objeto_de_dominio_para_dto() {
		Familiar familiarDominio = ContextoFamiliar
				.fabricarFamiliarComTodosOsDados();
		familiarDominio.setId(null);

		FamiliarDTO familiarDto = new FabricaFamiliar()
				.converterParaDTO(familiarDominio);

		Assert.assertEquals(familiarDto.getId(), familiarDominio.getId());
		Assert.assertEquals(familiarDto.getParentescoDto().toString(),
				familiarDominio.getParentesco().getDescricao());
		Assert.assertEquals(familiarDto.getInformacaoEssencialDto().getNome(),
				familiarDominio.getInformacaoEssencial().getNome());
		Assert.assertEquals(familiarDto.getInformacaoEssencialDto().getRg(), familiarDominio.getInformacaoEssencial().getRg());
		Assert.assertEquals(familiarDto.getCpf(), familiarDominio.getCpf());
		Assert.assertEquals(familiarDto.getDataNascimento(), familiarDominio
						.getDataNascimento());
		Assert.assertEquals(familiarDto.getEstadoCivilDto().toString(),
				familiarDominio.getEstadoCivil().getDescricao());
		Assert.assertEquals(familiarDto.getInformacaoTrabalhoDto().getId(),
				familiarDominio.getInformacaoTrabalho().getId());
		Assert.assertEquals(familiarDto.getObs(),  familiarDominio.getObs());
		Assert.assertEquals(familiarDto.getInformacaoEssencialDto().getContatoDto().getId(),
				familiarDominio.getInformacaoEssencial().getContato().getId());
		Assert.assertEquals(familiarDto.isNaoAlfabetizado(),
				familiarDominio.isNaoAlfabetizado());
		Assert.assertEquals(familiarDto.getInformacoesEducacionaisDto().size(),
				familiarDominio.obterInformacoesEducacionais().size());
		Assert.assertEquals(familiarDto.getRenda(), familiarDominio.getRenda());
		Assert.assertEquals(familiarDto.isPrincipalResponsavel(),
				familiarDominio.isPrincipalResponsavel());
		Assert.assertEquals(familiarDto.isMoraNaCasa(), familiarDominio.isMoraNaCasa());
		Assert.assertEquals(familiarDto.getGeneroDto().toString(), familiarDominio.getGenero().toString());
		Assert.assertEquals(familiarDto.isResponsavelPelaAvaliacaoSocial(), familiarDominio.isResponsavelPelaAvaliacaoSocial());
		Assert.assertEquals(familiarDto.isAcompanhante(), familiarDominio.isAcompanhante());
		Assert.assertEquals(familiarDto.isResponsavelPeloUsuario(), familiarDominio.isResponsavelPeloUsuario());
		Assert.assertEquals(familiarDto.isParadeiroIgnorado(), familiarDominio.isParadeiroIgnorado());
		Assert.assertEquals(familiarDto.isFalecido(), familiarDominio.isFalecido());
		Assert.assertEquals(familiarDto.getVulnerabilidadesDto().size(), familiarDominio.getVulnerabilidades().size());
	}
}
