package br.laramara.ti.sislaraserver.dominio.endereco;

public class EnderecoCEP {
	private Pais pais;
	private UF uf;
	private Municipio municipio;
	private String bairro;
	private String endereco;

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public void setNomePais(String pais) {
		this.pais = new Pais(null, pais);
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = UF.valueOf(uf);
	}

	public Municipio getMunicipio(){
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public void setNomeMunicipio(String municipio) {
		this.municipio = new Municipio(null, municipio, null);
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
