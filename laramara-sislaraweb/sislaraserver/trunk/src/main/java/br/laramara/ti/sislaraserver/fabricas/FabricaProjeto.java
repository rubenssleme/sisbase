package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;

public class FabricaProjeto extends FabricaRecursiva<ProjetoDTO, Projeto> {

	@Override
	public Projeto converterParaDominio(ProjetoDTO projetoDto, Projeto projeto) {
		if (projetoDto != null) {
			if (projeto == null) {
				projeto = obterNovo();
			}
			projeto.setId(projetoDto.getId());
			projeto.setAtivo(projetoDto.isAtivo());
			projeto.setDataInicialVigencia(projetoDto.getDataInicialVigencia());
			projeto.setNome(projetoDto.getNome());
			projeto.setEditalInvestimento(projetoDto.getEditalInvestimento());
			projeto.setOrgaoParceiroFinanciador(projetoDto.getOrgaoParceiroFinanciador());
			projeto.setLei(projetoDto.getLei());
			projeto.setIncentivado(new FabricaSimNao().converterParaDominio(projetoDto.getIncentivadoDto()));
			projeto.setClassificacao(new FabricaSetor().converterParaDominio(projetoDto.getClassificacaoDto()));
			projeto.setResponsaveisTecnicos(new FabricaResponsavelTecnico().converterParaDominio(projetoDto.getResponsaveisTecnicosDto()));
			projeto.setObjetivoGeral(projetoDto.getObjetivoGeral());
			projeto.setPublicoAlvo(projetoDto.getPublicoAlvo());
			projeto.setProfissionalAdministrativoResponsavel(new FabricaProfissional()
					.converterParaDominio(projetoDto.getProfissionalAdministrativoResponsavelDto()));
			projeto.setPrestacaoConta(new FabricaPrestacaoConta().converterParaDominio(projetoDto.getPrestacaoContaDto()));
			projeto.setPatrocinios(new FabricaPatrocinio().converterParaDominio(projetoDto.getPatrociniosDto()));
			projeto.setNumeroTermoFomentoParceria(projetoDto.getNumeroTermoFomentoParceria());
			projeto.setArquivos(new FabricaArquivo().converterParaDominio(projetoDto.getArquivos()));
			projeto.setDuracao(projetoDto.getDuracao());
			projeto.setAditamento(projetoDto.getAditamento());
			projeto.setValorProdutos(projetoDto.getValorProdutos());
			projeto.setValorOutros(projetoDto.getValorOutros());
			projeto.setRecursosDisponiveis(
					new FabricaRecursoDisponivel().converterParaDominio(projetoDto.getRecursoDisponivelDto()));
			projeto.setIdadeMinima(projetoDto.getIdadeMinima());
			projeto.setIdadeMaxima(projetoDto.getIdadeMaxima());
		}
		return projeto;
	}

	@Override
	public ProjetoDTO converterParaDTO(Projeto projeto) {
		ProjetoDTO projetoDto = null;
		if (projeto != null) {
			projetoDto = new ProjetoDTO();
			projetoDto.setId(projeto.getId());
			projetoDto.setAtivo(projeto.isAtivo());
			projetoDto.setDataInicialVigencia(projeto.getDataInicialVigencia());
			projetoDto.setDataFinalVigencia(projeto.getDataFinalVigencia());
			projetoDto.setNome(projeto.getNome());
			projetoDto.setEditalInvestimento(projeto.getEditalInvestimento());
			projetoDto.setOrgaoParceiroFinanciador(projeto.getOrgaoParceiroFinanciador());
			projetoDto.setLei(projeto.getLei());
			projetoDto.setIncentivadoDto(new FabricaSimNao().converterParaDTO(projeto.getIncentivado()));
			projetoDto.setClassificacaoDto(new FabricaSetor().converterParaDTO(projeto.getClassificacao()));
			projetoDto.setResponsaveisTecnicosDto(
					new FabricaResponsavelTecnico().converterParaDTO(projeto.getResponsaveisTecnicos()));
			projetoDto.setObjetivoGeral(projeto.getObjetivoGeral());
			projetoDto.setPublicoAlvo(projeto.getPublicoAlvo());
			projetoDto.setProfissionalAdministrativoResponsavelDto(new FabricaProfissional()
					.converterParaDTO(projeto.getProfissionalAdministrativoResponsavel()));
			projetoDto.setPrestacaoContaDto(new FabricaPrestacaoConta().converterParaDTO(projeto.getPrestacaoConta()));
			projetoDto.setPatrociniosDto(new FabricaPatrocinio().converterParaDTO(projeto.getPatrocinios()));
			projetoDto.setNumeroTermoFomentoParceria(projeto.getNumeroTermoFomentoParceria());
			projetoDto.setArquivos(new FabricaArquivo().converterParaDTO(projeto.getArquivos()));
			projetoDto.setDuracao(projeto.getDuracao());
			projetoDto.setAditamento(projeto.getAditamento());
			projetoDto.setValorTotal(projeto.getValorTotal());
			projetoDto.setValorProdutos(projeto.getValorProdutos());
			projetoDto.setValorOutros(projeto.getValorOutros());
			projetoDto.setRecursoDisponivelDto(
					new FabricaRecursoDisponivel().converterParaDTO(projeto.getRecursosDisponiveis()));
			projetoDto.setIdadeMinima(projeto.getIdadeMinima());
			projetoDto.setIdadeMaxima(projeto.getIdadeMaxima());
		}
		return projetoDto;
	}

	@Override
	public Projeto obterNovo() {
		return new Projeto();
	}
}
