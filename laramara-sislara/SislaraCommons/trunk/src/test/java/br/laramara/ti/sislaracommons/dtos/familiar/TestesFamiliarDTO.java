package br.laramara.ti.sislaracommons.dtos.familiar;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesFamiliarDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiardto_foi_construida_com_sucesso() {
		Long id = new Long(3344334);
		ParentescoDTO parentescoDto = new ParentescoDTO(new Long(2), "MÃE");
		String nome = "Adailza Conceição Barbosa";
		String rg = "8724898X";
		String cpf = "12312312399";
		String dataNascimento = "27/07/1982";
		EstadoCivilDTO estadoCivilDto = new EstadoCivilDTO(new Long(1), "SOLTEIRO");
		String renda = "1345,55";
		boolean principal = true;
		boolean naoAlfabetizado = true;
		StatusDTO statusDto = new StatusDTO("FALECIDO");
		InformacaoTrabalhoDTO informacaoTrabalhoDto = new InformacaoTrabalhoDTO();
		informacaoTrabalhoDto.setId(id);
		InformacaoEducacionalDTO informacaoEscolarDto = new InformacaoEducacionalDTO();
		informacaoEscolarDto.setId(id);
		
		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setId(id);

		InformacaoEssencialDTO informacaoEssencialDtoFamiliar = new InformacaoEssencialDTO();
		informacaoEssencialDtoFamiliar.setNome(nome);
		informacaoEssencialDtoFamiliar.setRg(rg);
		informacaoEssencialDtoFamiliar.setContatoDto(contatoDto);
		
		List<InformacaoEducacionalDTO> informacoesEscolaresDto = new ArrayList<>();
		informacoesEscolaresDto.add(informacaoEscolarDto);
		
		FamiliarDTO familiarDto = new FamiliarDTO();
		familiarDto.setId(id);
		familiarDto.setInformacaoEssencialDto(informacaoEssencialDtoFamiliar);
		familiarDto.setParentescoDto(parentescoDto);

		familiarDto.setCpf(cpf);
		familiarDto.setDataNascimento(dataNascimento);
		familiarDto.setEstadoCivilDto(estadoCivilDto);
		familiarDto.setInformacaoTrabalhoDto(informacaoTrabalhoDto);
		familiarDto.setNaoAlfabetizado(naoAlfabetizado);
		familiarDto.setInformacoesEducacionaisDto(informacoesEscolaresDto);
		familiarDto.setRenda(renda);
		familiarDto.setPrincipalResponsavel(principal);
		familiarDto.setStatusDto(statusDto);

		Assert.assertEquals(familiarDto.getId(), id);
		Assert.assertEquals(familiarDto.getParentescoDto().toString(),
				parentescoDto.toString());
		Assert.assertEquals(familiarDto.getInformacaoEssencialDto().getNome(), nome);
		Assert.assertEquals(familiarDto.getInformacaoEssencialDto().getRg(), rg);
		Assert.assertEquals(familiarDto.getCpf(), cpf);
		Assert.assertEquals(familiarDto.getDataNascimento(), dataNascimento);
		Assert.assertEquals(familiarDto.getEstadoCivilDto().toString(),
				estadoCivilDto.toString());
		Assert.assertEquals(familiarDto.getInformacaoTrabalhoDto().getId(),
				informacaoTrabalhoDto.getId());
		Assert.assertEquals(familiarDto.getInformacaoEssencialDto().getContatoDto().getId(),
				contatoDto.getId());
		Assert.assertEquals(familiarDto.isNaoAlfabetizado(), naoAlfabetizado);
		Assert.assertEquals(familiarDto.getInformacoesEducacionaisDto().size(), 1);
		Assert.assertEquals(familiarDto.getRenda(), renda);
		Assert.assertEquals(familiarDto.isPrincipalResponsavel(), principal);
		Assert.assertEquals(familiarDto.getStatusDto(), statusDto);
	}
}
