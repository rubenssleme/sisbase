package br.laramara.sistelemarketingcommons.dtos.telefonia;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesLigacaoResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void ligacao_resultado_dto_foi_construida_com_sucesso() {

		List<LigacaoDTO> ligacaoDTO = Arrays.asList(ContextoLigacaoDTO.construir());

		LigacaoResultadoDTO ligacaoResultadoDTO = new LigacaoResultadoDTO();
		ligacaoResultadoDTO.efetuadoComSucesso(ligacaoDTO);

		Assert.assertEquals(ligacaoResultadoDTO.getLigacoesDto().size(), ligacaoResultadoDTO.getLigacoesDto().size());
	}
}
