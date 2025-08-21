package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

public class TestesFabricaLoteRecurso {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_lote_recurso_converte_objeto_de_dominio_para_dto() {
		Long id = new Long(1);
		String textoRecurso = "Bengala";
		String quantidade = "5";
		String valor = "2500,00";
		boolean cartelaDeSelos = true;
		boolean disponivelParaDemanda = true;
		
		Recurso recurso = new Recurso(id, textoRecurso, cartelaDeSelos, disponivelParaDemanda, valor);
		LoteRecurso loteRecurso = new LoteRecurso();
		loteRecurso.setId(id);
		loteRecurso.setRecurso(recurso);
		loteRecurso.setQuantidade(quantidade);
		loteRecurso.setValor(valor);

		LoteRecursoDTO loteRecursoDTO = new FabricaLoteRecurso()
				.converterParaDTO(loteRecurso);
		
		Assert.assertEquals(loteRecursoDTO.getId(), id);
		Assert.assertEquals(loteRecursoDTO.getRecursoDto().getId(), id );
		Assert.assertEquals(loteRecursoDTO.getQuantidade(), quantidade);
		Assert.assertEquals(loteRecursoDTO.getValor(), valor);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_lote_recurso_converte_objeto_de_dto_para_dominio() {
		LoteRecursoDTO loteRecursoDTO = ContextoLoteRecurso.fabricarComTodosOsDadosDTO();

		LoteRecurso loteRecurso = new FabricaLoteRecurso().converterParaDominio(loteRecursoDTO);
				
		Assert.assertEquals(loteRecurso.getId(),
				loteRecursoDTO.getId());
		Assert.assertEquals(loteRecurso.getRecurso().getId(),
				loteRecursoDTO.getRecursoDto().getId());
		Assert.assertEquals(loteRecurso.getQuantidade(), loteRecursoDTO.getQuantidade());
		Assert.assertEquals(loteRecurso.getValor(), loteRecursoDTO.getValor());
	}
}