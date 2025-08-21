package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.TipoEnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.inscricao.InscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroInscricaoDTO;

@Named
@ViewScoped
public class ControladorPaginaInscricao extends Controlador implements Serializable {
	private static final long serialVersionUID = 8457055029688137674L;

	private InscricaoDTO inscricaoDto;

	private List<String> formatosAcessiveisMaterialSelecionados;
	private List<String> formatosAcessiveisMaterial;

	private boolean usarOutroEndereco;

	@PostConstruct
	public void inicializar() {
		carregarFormatosAcessiveisMaterial();
		carregarInscricao();
		autorizar();
		copiarEnderecoResidencialParaEnderecoInscricao();
	}

	private void carregarFormatosAcessiveisMaterial() {
		formatosAcessiveisMaterial = new ArrayList<String>();
		formatosAcessiveisMaterial.add("Impresso em Braille");
		formatosAcessiveisMaterial.add("Impresso em Tipos Ampliados");
		formatosAcessiveisMaterial.add("Arquivo Digital");
		formatosAcessiveisMaterial.add("Em aúdio");
	}

	private void carregarInscricao() {
		if (inscricaoDto == null) {
			inscricaoDto = new InscricaoDTO();
		}
	}

	public void autorizar() {
		redirecionarParaPaginaInicialSeNaoEstiverLogado();
	}

	public void sair(ActionEvent actionEvent) {
		sair();
	}

	public InscricaoDTO getInscricaoDto() {
		return inscricaoDto;
	}

	public void setInscricaoDto(InscricaoDTO inscricaoDto) {
		this.inscricaoDto = inscricaoDto;
	}

	public List<String> getFormatosAcessiveisMaterialSelecionados() {
		return formatosAcessiveisMaterialSelecionados;
	}

	public void setFormatosAcessiveisMaterialSelecionados(List<String> formatosAcessiveisMaterialSelecionados) {
		this.formatosAcessiveisMaterialSelecionados = formatosAcessiveisMaterialSelecionados;
	}

	public List<String> getFormatosAcessiveisMaterial() {
		return formatosAcessiveisMaterial;
	}

	public void setFormatosAcessiveisMaterial(List<String> formatosAcessiveisMaterial) {
		this.formatosAcessiveisMaterial = formatosAcessiveisMaterial;
	}

	public boolean iseEnderecoComercial() {
		return getEnderecoDto().getTipoEnderecoDto().equals(obterTipoEnderecoComercial());
	}

	public void seteEnderecoComercial(boolean eEnderecoComercial) {
		if (eEnderecoComercial) {
			getEnderecoDto().setTipoEnderecoDto(obterTipoEnderecoComercial());
		} else {
			getEnderecoDto().setTipoEnderecoDto(obterTipoEnderecoResidencial());
		}
	}

	private TipoEnderecoDTO obterTipoEnderecoComercial() {
		return new TipoEnderecoDTO(TIPO_ENDERECO_COMERCIAL);
	}

	private TipoEnderecoDTO obterTipoEnderecoResidencial() {
		return new TipoEnderecoDTO(TIPO_ENDERECO_RESIDENCIAL);
	}

	public boolean isUsarOutroEndereco() {
		return usarOutroEndereco;
	}

	public void setUsarOutroEndereco(boolean usarOutroEndereco) {
		this.usarOutroEndereco = usarOutroEndereco;
	}

	public boolean possuiValorTotalAlmoco() {
		String valorTotalAlmoco = getDetalheCursoDto().getValorTotalAlmoco();

		return valorTotalAlmoco != null && !valorTotalAlmoco.isEmpty();
	}

	private void copiarEnderecoResidencialParaEnderecoInscricao() {
		if (estaLogado() && !usarOutroEndereco) {
			ResultadoConsultaUsuarioExternoDTO resultadoConsultaUsuarioExternoDto = servicoSisLaraClient
					.obterUsuarioExternoPorToken(getToken());

			if (resultadoConsultaUsuarioExternoDto.sucesso()) {
				EnderecoDTO copiaEnderecoResidencial = resultadoConsultaUsuarioExternoDto.getUsuarioExternoDto()
						.getEnderecoResidencial();
				copiaEnderecoResidencial.setId(null);

				setEnderecoDto(copiaEnderecoResidencial);
			}
		}
	}

	public void cadastrarInscricao(ActionEvent actionEvent) {
		getInscricaoDto().setEnderecoDto(getEnderecoDto());
		getInscricaoDto().setDetalheCursoDto(getDetalheCursoDto());

		SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto = new SolicitacaoCadastroInscricaoDTO();

		solicitacaoCadastroInscricaoDto.setToken(getToken());
		solicitacaoCadastroInscricaoDto.setInscricaoDto(getInscricaoDto());

		exibirResultado(servicoSisLaraClient.cadastrarInscricao(solicitacaoCadastroInscricaoDto));
	}
}
