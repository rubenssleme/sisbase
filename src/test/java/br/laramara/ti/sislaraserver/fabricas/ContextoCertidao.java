package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.TipoCertidaoDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.usuario.Certidao;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoCertidao;

public class ContextoCertidao {

	public static Certidao fabricarCertidaoComTodosOsDados() {
		Certidao certidao = new Certidao();
		certidao.setTipoCertidao(TipoCertidao.NASCIMENTO);
		certidao.setNumero("1234");
		certidao.setLivro("1234");
		certidao.setFolha("1234");
		certidao.setUf(UF.SP);
		certidao.setMunicipio(new Municipio(new Long(4850), "São Paulo", UF.SP));
		certidao.setDistrito("Distrito Industrial");
		return certidao;
	}

	public static CertidaoDTO fabricarCertidaoDTOComTodosOsDados() {
		CertidaoDTO certidaoDto = new CertidaoDTO();
		certidaoDto.setTipoCertidaoDto(new TipoCertidaoDTO(
				TipoCertidao.NASCIMENTO.toString()));
		certidaoDto.setNumero("1234");
		certidaoDto.setLivro("1234");
		certidaoDto.setFolha("1234");
		certidaoDto.setUfDto(new UfDTO(UF.SP.toString()));
		certidaoDto.setMunicipioDto(new MunicipioDTO(new Long(4850), "São Paulo",
				new UfDTO(UF.SP.toString())));
		certidaoDto.setDistrito("Distrito Industrial");
		return certidaoDto;
	}
}
