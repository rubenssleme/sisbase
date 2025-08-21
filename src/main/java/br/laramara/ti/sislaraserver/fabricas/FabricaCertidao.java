package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Certidao;

public class FabricaCertidao extends FabricaBase<CertidaoDTO, Certidao> {

	@Override
	public Certidao converterParaDominio(CertidaoDTO certidaoDto) {
		Certidao certidao = null;
		if (certidaoDto != null) {
			certidao = new Certidao();
			certidao.setId(certidaoDto.getId());
			certidao.setTipoCertidao(new FabricaTipoCertidao()
					.converterParaDominio(certidaoDto.getTipoCertidaoDto()));
			certidao.setNumero(certidaoDto.getNumero());
			certidao.setFolha(certidaoDto.getFolha());
			certidao.setLivro(certidaoDto.getLivro());
			certidao.setMunicipio(new FabricaMunicipio()
					.converterParaDominio(certidaoDto.getMunicipioDto()));
			certidao.setUf(new FabricaUf().converterParaDominio(certidaoDto
					.getUfDto()));
			certidao.setDistrito(certidaoDto.getDistrito());
		}
		return certidao;
	}

	@Override
	public CertidaoDTO converterParaDTO(Certidao certidao) {
		CertidaoDTO certidaoDto = new CertidaoDTO();
		if (certidao.getId() != null) {
			certidaoDto.setId(certidao.getId());
		}
		certidaoDto.setTipoCertidaoDto(new FabricaTipoCertidao()
				.converterParaDTO(certidao.getTipoCertidao()));
		certidaoDto.setNumero(certidao.getNumero());
		certidaoDto.setFolha(certidao.getFolha());
		certidaoDto.setLivro(certidao.getLivro());
		certidaoDto.setMunicipioDto(new FabricaMunicipio()
				.converterParaDTO(certidao.getMunicipio()));
		certidaoDto
				.setUfDto(new FabricaUf().converterParaDTO(certidao.getUf()));
		certidaoDto.setDistrito(certidao.getDistrito());
		return certidaoDto;
	}
}
