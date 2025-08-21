package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.TipoApoioDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.TipoInstituicaoDTO;
import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;
import br.laramara.ti.sislaraserver.dominio.escola.TipoApoio;
import br.laramara.ti.sislaraserver.dominio.escola.TipoEspecialidade;
import br.laramara.ti.sislaraserver.dominio.instituicao.ClassificacaoInstituicao;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.instituicao.TipoInstituicao;

public class ContextoInstituicao {

	public static InstituicaoDTO construirInstituicaoDTOparaTesteUnitario(){
		InstituicaoDTO instituicaoDto = construirInstitucaoDTO();
		instituicaoDto.setId(null);
		return instituicaoDto;
	}
	
	public static InstituicaoDTO construirInstitucaoDTO() {

		String cep = "01151000";
		String endereco = "Rua barra Funda";
		String numero = "4343";
		String complemento = "AP43";
		ZonaDTO zona = new ZonaDTO("NORTE");
		String bairro = "Barra Funda";
		MunicipioDTO municipio = new MunicipioDTO(new Long(4850), "São Paulo",
				new UfDTO("SP"));
		UfDTO uf = new UfDTO("SP");
		PaisDTO pais = new PaisDTO(new Long(1), "Brasil");
		
		EnderecoDTO enderecoDto = new EnderecoDTO();
		enderecoDto.setCep(cep);
		enderecoDto.setEndereco(endereco);
		enderecoDto.setNumero(numero);
		enderecoDto.setComplemento(complemento);
		enderecoDto.setZonaDto(zona);
		enderecoDto.setBairro(bairro);
		enderecoDto.setMunicipioDto(municipio);
		enderecoDto.setUfDto(uf);
		enderecoDto.setPaisDto(pais);

		TipoInstituicaoDTO tipoDto = new TipoInstituicaoDTO("FACULDADE");
		Long id = new Long(133333);
		String nome = "EE Ministro de Godoi";
		String telefone = "1143343434";
		String ramal = "34344";
		String nomeContato = "Paulo Josep MEaza";
		String nomeCoordenadorResponsavel = "Nome do Coordenador Responsável";
		String email = "teste@yahoo.com.br";
		ClassificacaoInstituicaoDTO classificacaoInstituicaoDto = new ClassificacaoInstituicaoDTO(
				"ESTADUAL");
		String obs = "Texto gigantesco de OBS.";
		
		List<TelefoneDTO> telefonesDto = new ArrayList<>();
		telefonesDto.add(new TelefoneDTO(new TipoTelefoneDTO("RESIDENCIAL"),
				telefone, ramal, nomeContato));
		telefonesDto.add(new TelefoneDTO(new TipoTelefoneDTO("CELULAR"),
				telefone, ramal, nomeContato));
		telefonesDto.add(new TelefoneDTO(new TipoTelefoneDTO("CONTATO"),
				telefone, ramal, nomeContato));
		
		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setTelefonesDto(telefonesDto);
		contatoDto.setRamal(ramal);
		contatoDto.setNomeContato(nomeContato);
		contatoDto.setEmail(email);

		List<EscolaridadeDTO> escolaridadeDtos = new ArrayList<>();
		escolaridadeDtos.add(ContextoEscolaridade.construirEscolaridadeDto());
		TipoApoioDTO tipoApoioDto = new TipoApoioDTO("SRMs");
		
		InstituicaoDTO instituicaoDto = new InstituicaoDTO();
		instituicaoDto.setId(id);
		instituicaoDto.setTipoInstituicaoDto(tipoDto);
		instituicaoDto.setNome(nome);
		instituicaoDto.setEnderecoDto(enderecoDto);
		instituicaoDto.setContatoDto(contatoDto);
		instituicaoDto.setDreCefaiDto(new DreCefaiDTO(new Long(11111),
				"CEFAI - Butantã"));
		instituicaoDto.setDiretoriaEnsinoDto(new DiretoriaEnsinoDTO(
				new Long(11111), "Diretoria Centro"));
		instituicaoDto.setEscolaridadesDto(escolaridadeDtos);
		instituicaoDto.setTipoApoioDto(tipoApoioDto);
		
		instituicaoDto
				.setNomeCoordenadorResponsavel(nomeCoordenadorResponsavel);
		instituicaoDto
				.setClassificacaoInstituicaoDto(classificacaoInstituicaoDto);
		instituicaoDto.setObs(obs);

		return instituicaoDto;
	}

	public static Instituicao fabricarInstituicaoComTodosOsDados() {
		List<TipoEspecialidade> tiposEspecialidade = new ArrayList<>();
		tiposEspecialidade.add(TipoEspecialidade.DA);
		List<Escolaridade> escolaridades = new ArrayList<>();
		escolaridades.add(ContextoEscolaridade.fabricarEscolaridadeComTodosOsDados());
		Instituicao instituicao = new Instituicao();
		instituicao.setId(new Long(133333));
		instituicao.setTipoInstituicao(TipoInstituicao.CEU);
		instituicao.setNome("DIRETORIA TESTE");
		instituicao.setEndereco(ContextoEndereco
				.fabricarEnderecoComTodosOsDados());
		instituicao.setContato(ContextoContato.fabricarContatoComTodosOsDados());
		instituicao
				.setNomeCoordenadorResponsavel("Nome do Coordenador ou Reponsável");
		instituicao.setClassificacao(ClassificacaoInstituicao.MUNICIPAL);
		instituicao.setObs("Observações sobre a instituição.");
		instituicao.setTiposEspecialidade(tiposEspecialidade);
		instituicao.setDreCefai(new DreCefai(new Long(11111), "SEFAI Butantã"));
		instituicao.setDiretoriaEnsino(new DiretoriaEnsino(new Long(11111),
				"Diretoria Centro"));
		instituicao.setEscolaridades(escolaridades);
		instituicao.setTipoApoio(TipoApoio.SRMs);
		return instituicao;
	}
}
