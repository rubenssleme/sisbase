package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;

public class FabricaInformacaoEssencial extends
		FabricaRecursiva<InformacaoEssencialDTO, InformacaoEssencial> {

	@Override
	public InformacaoEssencialDTO converterParaDTO(
			InformacaoEssencial objetoDominio) {
		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		if (objetoDominio.getId() != null) {
			informacaoEssencialDto.setId(objetoDominio.getId());
		}
		informacaoEssencialDto.setNome(objetoDominio.getNome());
		informacaoEssencialDto.setDataNascimento(objetoDominio.getDataNascimento());
		informacaoEssencialDto.setIdade(objetoDominio.getIdadeComoString());
		informacaoEssencialDto.setRg(objetoDominio.getRg());
		informacaoEssencialDto.setCpf(objetoDominio.getCpf());
		informacaoEssencialDto.setContatoDto(new FabricaContato()
				.converterParaDTO(objetoDominio.getContato()));
		informacaoEssencialDto.setUsuarioAssociado(objetoDominio
				.possuiUsuarioAssociado());
		EnderecoDTO enderecoDto = new FabricaEndereco()
				.converterParaDTO(objetoDominio.obterEnderecoAtual());
		informacaoEssencialDto.setEnderecoDto(enderecoDto);
		return informacaoEssencialDto;
	}

	@Override
	public InformacaoEssencial obterNovo() {
		return new InformacaoEssencial();
	}

	@Override
	public InformacaoEssencial converterParaDominio(
			InformacaoEssencialDTO informacaoEssencialDto,
			InformacaoEssencial informacaoEssencial) {
		if (informacaoEssencialDto != null) {
			if (informacaoEssencial == null) {
				informacaoEssencial = new InformacaoEssencial();
			}
			informacaoEssencial.setId(informacaoEssencialDto.getId());
			informacaoEssencial.setNome(informacaoEssencialDto.getNome());
			informacaoEssencial.setDataNascimento(informacaoEssencialDto.getDataNascimento());
			informacaoEssencial.adicionarRg(informacaoEssencialDto.getRg());
			informacaoEssencial.setCpf(informacaoEssencialDto.getCpf());
			informacaoEssencial.setContato(new FabricaContato()
					.converterParaDominio(informacaoEssencialDto
							.getContatoDto()));
			informacaoEssencial.adicionarEndereco(new FabricaEndereco()
					.converterParaDominio(informacaoEssencialDto
							.getEnderecoDto()));
		}
		return informacaoEssencial;
	}
}
