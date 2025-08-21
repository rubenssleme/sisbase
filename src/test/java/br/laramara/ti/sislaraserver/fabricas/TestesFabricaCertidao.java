package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Certidao;

public class TestesFabricaCertidao {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_certidao_converte_objeto_de_dto_para_dominio() {
		CertidaoDTO certidaoDto = ContextoCertidao
				.fabricarCertidaoDTOComTodosOsDados();
		Certidao certidao = new FabricaCertidao()
				.converterParaDominio(certidaoDto);

		Assert.assertEquals(certidao.getId(), certidaoDto.getId());
		Assert.assertEquals(certidao.getTipoCertidao().toString(), certidaoDto
				.getTipoCertidaoDto().toString());
		Assert.assertEquals(certidao.getNumero(), certidaoDto.getNumero());
		Assert.assertEquals(certidao.getLivro(), certidaoDto.getLivro());
		Assert.assertEquals(certidao.getFolha(), certidaoDto.getFolha());
		Assert.assertEquals(certidao.getDistrito(), certidaoDto.getDistrito());
		Assert.assertEquals(certidao.getMunicipio().getId(), certidaoDto
				.getMunicipioDto().getId());
		Assert.assertEquals(certidao.getUf().toString(), certidaoDto.getUfDto()
				.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_certidao_converte_objeto_de_dominio_para_dto() {
		Certidao certidao = ContextoCertidao.fabricarCertidaoComTodosOsDados();

		CertidaoDTO certidaoDto = new FabricaCertidao()
				.converterParaDTO(certidao);

		Assert.assertEquals(certidaoDto.getId(), certidao.getId());
		Assert.assertEquals(certidaoDto.getTipoCertidaoDto().toString(),
				certidao.getTipoCertidao().toString());
		Assert.assertEquals(certidaoDto.getNumero(), certidao.getNumero());
		Assert.assertEquals(certidaoDto.getLivro(), certidao.getLivro());
		Assert.assertEquals(certidaoDto.getFolha(), certidao.getFolha());
		Assert.assertEquals(certidaoDto.getDistrito(), certidao.getDistrito());
		Assert.assertEquals(certidaoDto.getMunicipioDto().getId(), certidao
				.getMunicipio().getId());
		Assert.assertEquals(certidaoDto.getUfDto().toString(), certidao.getUf()
				.toString());

	}
}
