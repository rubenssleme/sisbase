package br.laramara.ti.sislaracommons.dtos.instituicao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

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
import br.laramara.ti.sislaracommons.dtos.escola.TipoEspecialidadeDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInstituicaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void instituicaodto_foi_construida_com_sucesso() {
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

		Long id = new Long(3434);
		TipoInstituicaoDTO tipoDto = new TipoInstituicaoDTO("FACULDADE");
		String nome = "NOME DA INSTITUICAO";
		TelefoneDTO telefoneDto = new TelefoneDTO(new TipoTelefoneDTO(
				"RESIDENCIAL"), "43343434");
		String ramal = "34344";
		String nomeContato = "Paulo Josep MEaza";
		String nomeCoordenadorResponsavel = "Nome do Coordenador Responsável";
		String email = "teste@yahoo.com.br";
		ClassificacaoInstituicaoDTO classificacaoInstituicaoDto = new ClassificacaoInstituicaoDTO("ESTADUAL");
		String obs = "Texto gigantesco de OBS.";
		List<TipoEspecialidadeDTO> tiposEspecialidadeDto = new ArrayList<>();
		tiposEspecialidadeDto.add(new TipoEspecialidadeDTO("T1"));
		DreCefaiDTO dreCefaiDto = new DreCefaiDTO(new Long(1), "TABUAO");
		DiretoriaEnsinoDTO diretoriaEnsinoDto = new DiretoriaEnsinoDTO(new Long(1), "Regiao Centro");
		List<EscolaridadeDTO> escolaridadesDto = new ArrayList<>();
		escolaridadesDto.add(new EscolaridadeDTO(new Long(2), "Superior", null));
		TipoApoioDTO tipoApoioDTO = new TipoApoioDTO("SRMs");
		
		List<TelefoneDTO> telefonesDto = new ArrayList<>();
		telefonesDto.add(telefoneDto);
		telefonesDto.add(telefoneDto);
		telefonesDto.add(telefoneDto);
				
		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setId(id);
		contatoDto.setTelefonesDto(telefonesDto);
		contatoDto.setRamal(ramal);
		contatoDto.setNomeContato(nomeContato);
		contatoDto.setEmail(email);
		
		InstituicaoDTO instituicaoDto = new InstituicaoDTO();
		instituicaoDto.setId(id);
		instituicaoDto.setTipoInstituicaoDto(tipoDto);
		instituicaoDto.setNome(nome);
		instituicaoDto.setEnderecoDto(enderecoDto);
		instituicaoDto.setContatoDto(contatoDto);
		
		instituicaoDto
				.setNomeCoordenadorResponsavel(nomeCoordenadorResponsavel);
		instituicaoDto.setClassificacaoInstituicaoDto(classificacaoInstituicaoDto);
		instituicaoDto.setObs(obs);
		instituicaoDto.setTiposEspecialidadeDTO(tiposEspecialidadeDto);
		instituicaoDto.setDreCefaiDto(dreCefaiDto);
		instituicaoDto.setDiretoriaEnsinoDto(diretoriaEnsinoDto);
		instituicaoDto.setEscolaridadesDto(escolaridadesDto);
		instituicaoDto.setTipoApoioDto(tipoApoioDTO);
		
		Assert.assertEquals(instituicaoDto.getId(), id);
		Assert.assertEquals(instituicaoDto.getTipoInstituicaoDto().toString(),
				tipoDto.toString());
		Assert.assertEquals(instituicaoDto.getNome(), nome);
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getCep(), cep);
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getEndereco(),
				endereco);
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getNumero(), numero);
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getComplemento(),
				complemento);
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getZonaDto()
				.toString(), zona.toString());
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getBairro(), bairro);
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getMunicipioDto(),
				municipio);
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getUfDto()
				.toString(), uf.toString());
		Assert.assertEquals(instituicaoDto.getEnderecoDto().getPaisDto()
				.toString(), pais.toString());
		Assert.assertEquals(instituicaoDto.getContatoDto().getId(), id);
		Assert.assertEquals(instituicaoDto.getContatoDto().getTelefonesDto().size(), 3);
		Assert.assertEquals(instituicaoDto.getContatoDto().getRamal(), ramal);
		Assert.assertEquals(instituicaoDto.getContatoDto().getNomeContato(), nomeContato);
		Assert.assertEquals(instituicaoDto.getNomeCoordenadorResponsavel(),
				nomeCoordenadorResponsavel);
		Assert.assertEquals(instituicaoDto.getContatoDto().getEmail(), email);
		Assert.assertEquals(instituicaoDto.getClassificacaoInstituicaoDto().toString(),
				classificacaoInstituicaoDto.toString());
		Assert.assertEquals(instituicaoDto.getObs(), obs);
		Assert.assertEquals(instituicaoDto.getTiposEspecialidadeDTO().size(),
				tiposEspecialidadeDto.size());
		Assert.assertEquals(instituicaoDto.getTipoApoioDto().toString(),
				new TipoApoioDTO("SRMs").toString());
		Assert.assertEquals(instituicaoDto.getDreCefaiDto().getId(),
				dreCefaiDto.getId());
		Assert.assertEquals(instituicaoDto.getDiretoriaEnsinoDto().getId(),
				diretoriaEnsinoDto.getId());
		Assert.assertEquals(instituicaoDto.getEscolaridadesDto().size(),
				escolaridadesDto.size());
	}
}
