package br.laramara.ti.sislaracommons.dtos.inscricao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;

public class InscricaoDTO extends ModeloDTO {
	private static final long serialVersionUID = -2479060311240890334L;

	private Long id;
	private String nomeParaCracha;
	private String observacoes;
	private EnderecoDTO enderecoDto;
	private String areaFormacao;
	private String localTrabalho;
	private String cargoOuFuncao;
	private boolean usuarioExternoPossuiCadeiraDeRodas;
	private boolean usuarioExternoPossuiCaoGuia;
	private DetalheCursoDTO detalheCursoDto;
	private boolean valorTotalAlmocoIncluso;

	public InscricaoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnderecoDTO getEnderecoDto() {
		return enderecoDto;
	}

	public void setEnderecoDto(EnderecoDTO enderecoDto) {
		this.enderecoDto = enderecoDto;
	}

	public String getNomeParaCracha() {
		return nomeParaCracha;
	}
	
	public void setNomeParaCracha(String nomeParaCracha) {
		this.nomeParaCracha = nomeParaCracha;
	}
	
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getAreaFormacao() {
		return areaFormacao;
	}

	public void setAreaFormacao(String areaFormacao) {
		this.areaFormacao = areaFormacao;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getCargoOuFuncao() {
		return cargoOuFuncao;
	}
	
	public void setCargoOuFuncao(String cargoOuFuncao) {
		this.cargoOuFuncao = cargoOuFuncao;
	}

	public boolean isUsuarioExternoPossuiCadeiraDeRodas() {
		return usuarioExternoPossuiCadeiraDeRodas;
	}

	public void setUsuarioExternoPossuiCadeiraDeRodas(boolean usuarioExternoPossuiCadeiraDeRodas) {
		this.usuarioExternoPossuiCadeiraDeRodas = usuarioExternoPossuiCadeiraDeRodas;
	}

	public boolean isUsuarioExternoPossuiCaoGuia() {
		return usuarioExternoPossuiCaoGuia;
	}

	public void setUsuarioExternoPossuiCaoGuia(boolean usuarioExternoPossuiCaoGuia) {
		this.usuarioExternoPossuiCaoGuia = usuarioExternoPossuiCaoGuia;
	}

	public DetalheCursoDTO getDetalheCursoDto() {
		return detalheCursoDto;
	}

	public void setDetalheCursoDto(DetalheCursoDTO detalheCursoDto) {
		this.detalheCursoDto = detalheCursoDto;
	}

	public boolean isValorTotalAlmocoIncluso() {
		return valorTotalAlmocoIncluso;
	}

	public void setValorTotalAlmocoIncluso(boolean valorTotalAlmocoIncluso) {
		this.valorTotalAlmocoIncluso = valorTotalAlmocoIncluso;
	}

	@Override
	public String toString() {
		return "InscricaoDTO [id=" + id + ", nomeParaCracha=" + nomeParaCracha + ", observacoes=" + observacoes
				+ ", enderecoDto=" + enderecoDto + ", areaFormacao=" + areaFormacao + ", localTrabalho=" + localTrabalho
				+ ", cargoOuFuncao=" + cargoOuFuncao + ", usuarioExternoPossuiCadeiraDeRodas="
				+ usuarioExternoPossuiCadeiraDeRodas + ", usuarioExternoPossuiCaoGuia=" + usuarioExternoPossuiCaoGuia
				+ ", detalheCursoDto=" + detalheCursoDto + ", valorTotalAlmocoIncluso=" + valorTotalAlmocoIncluso + "]";
	}

}
