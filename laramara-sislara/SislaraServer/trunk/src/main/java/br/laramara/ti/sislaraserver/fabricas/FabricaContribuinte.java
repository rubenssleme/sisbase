package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;

public class FabricaContribuinte extends
		FabricaRecursiva<ContribuinteDTO, Contribuinte> {

	public final synchronized Contribuinte converterParaDominio(
			ContribuinteDTO contribuinteDto, Contribuinte contribuinte) {
		if (contribuinteDto != null) {
			if (contribuinte == null) {
				contribuinte = new Contribuinte();
			}
			contribuinte.setId(contribuinteDto.getId());
			contribuinte.setNomeEmpresa(contribuinteDto.getNomeEmpresa());
			contribuinte.setEndereco(new FabricaEndereco()
					.converterParaDominio(contribuinteDto.getEnderecoDto()));
			contribuinte.setContato(new FabricaContato()
					.converterParaDominio(contribuinteDto.getContatoDto()));
			contribuinte.adicionarStatus(
					new FabricaStatusContribuinte().converterParaDominio(contribuinteDto.getStatusContribuinteDto()));
			contribuinte.setValorContribuicao(contribuinteDto.getContribuicao());
			contribuinte.setMotivoDesativacao(
					new FabricaMotivoDesativacao().converterParaDominio(contribuinteDto.getMotivoDesativacaoDTO()));
		}
		return contribuinte;
	}

	public final synchronized ContribuinteDTO converterParaDTO(
			Contribuinte contribuinte) {
		ContribuinteDTO contribuinteDto = null;
		if (contribuinte != null) {
			contribuinteDto = new ContribuinteDTO();
			contribuinteDto.setId(contribuinte.getId());
			contribuinteDto.setDataCadastro(contribuinte.getDataCadastro());
			contribuinteDto.setNomeEmpresa(contribuinte.getNomeEmpresa());
			contribuinteDto.setEnderecoDto(new FabricaEndereco()
					.converterParaDTO(contribuinte.getEndereco()));
			contribuinteDto.setContatoDto(new FabricaContato()
					.converterParaDTO(contribuinte.getContato()));
			contribuinteDto.setStatusContribuinteDto(
					new FabricaStatusContribuinte().converterParaDTO(contribuinte.obterStatusAtual()));
			contribuinteDto.setContribuicao(contribuinte.getValorContribuicao());
			contribuinteDto.setMotivoDesativacaoDTO(
					new FabricaMotivoDesativacao().converterParaDTO(contribuinte.getMotivoDesativacao()));
		}
		return contribuinteDto;
	}

	@Override
	public Contribuinte obterNovo() {
		return new Contribuinte();
	}
}
