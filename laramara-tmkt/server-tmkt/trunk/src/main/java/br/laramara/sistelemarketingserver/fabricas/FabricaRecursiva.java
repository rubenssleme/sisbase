package br.laramara.sistelemarketingserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.sistelemarketingcommons.Identificavel;

public abstract class FabricaRecursiva<DTO, DOMINIO> extends FabricaBase<DTO, DOMINIO> {

	@SuppressWarnings("unchecked")
	protected DOMINIO obterPorId(DTO objetoParaLocalizar,
			List<DOMINIO> objetosDominio, DOMINIO novoObjeto) {
		DOMINIO retorno = novoObjeto;
		for (Object objetoDominio : objetosDominio) {
			if (((Identificavel) objetoDominio).getId().equals(
					((Identificavel) objetoParaLocalizar).getId())) {
				retorno = (DOMINIO) objetoDominio;
			}
		}
		return retorno;
	}

	public final List<DOMINIO> converterParaDominio(List<DTO> objetosDto,
			List<DOMINIO> objetosDominio) {
		List<DOMINIO> objetosDominioRetorno = new ArrayList<>();
		if (!objetosDto.isEmpty()) {
			for (DTO objetoDto : objetosDto) {
				DOMINIO objetoDominio = obterPorId(objetoDto, objetosDominio,
						obterNovo());
				objetosDominioRetorno.add(converterParaDominio(objetoDto,
						objetoDominio));
			}
		}else{
			objetosDominioRetorno = objetosDominio;
		}
		return objetosDominioRetorno;
	}

	public DOMINIO converterParaDominio(DTO objetoDto) {
		return objetoDto != null ? converterParaDominio(objetoDto, obterNovo())
				: null;
	}

	public abstract DOMINIO converterParaDominio(DTO objetoDto, DOMINIO dominio);

	public abstract DOMINIO obterNovo();
}