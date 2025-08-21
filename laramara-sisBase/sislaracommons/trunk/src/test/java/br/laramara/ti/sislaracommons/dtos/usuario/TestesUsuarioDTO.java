package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SerieDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SituacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ParentescoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesUsuarioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuariodto_foi_construido_com_sucesso() {
		Long id = new Long(123);
		String versao = "3333";
		ClassificacaoSocialDTO classificacaoSocial = new ClassificacaoSocialDTO(
				"I");
		StatusDTO status = new StatusDTO("CASO_NOVO");
		StatusRelacaoComModuloDTO statusRelacao = new StatusRelacaoComModuloDTO("INTEGRADO");
		String dataCadastro = "27/07/1982";
		String nome = "Paulo Augusto Bandeira dos Santos";
		GeneroDTO genero = new GeneroDTO("MASCULINO");
		String naturalidade = "São Paulo";
		String nacionalidade = "Intaliana";
		UfDTO ufRg = new UfDTO("PA");
		String rg = "348723478";
		String dataExpedicaoRg = "31/12/1982";
		String orgaoEmissorRg = "SSP-SP";
		String cpf = "23423423422";
		EstadoCivilDTO estadoCivil = new EstadoCivilDTO(new Long(1), "SOLTEIRO");
		String cep = "01151000";
		String endereco = "Rua barra Funda";
		String numero = "4343";
		String complemento = "AP43";
		ZonaDTO zona = new ZonaDTO("NORTE");
		String bairro = "Barra Funda";
		MunicipioDTO municipio = new MunicipioDTO(new Long(4850), "São Paulo", new UfDTO("SP"));
		UfDTO uf = new UfDTO("SP");
		PaisDTO pais = new PaisDTO(new Long(1), "Brasil");
		TelefoneDTO telResidencial = new TelefoneDTO(new TipoTelefoneDTO(
				"RESIDENCIAL"), "38743443");
		TelefoneDTO telCelular = new TelefoneDTO(
				new TipoTelefoneDTO("CELULAR"), "47834883");
		TelefoneDTO telRecado = new TelefoneDTO(new TipoTelefoneDTO("RECADO"),
				"34874833");
		String nomeRecado = "Josep Meaza";
		String email = "paulo_bandeira2007@yahoo.com.br";
		boolean associadoAoProceja = true;
		boolean associadoAoCTO = true;
		boolean responsavelPorSiMesmo = true;
		boolean multiplaDeficiencia = true;
		boolean cadeiraDeRodas = true;
		GrupoEtnicoDTO grupoEtnico = new GrupoEtnicoDTO("Preto");
		byte[] foto = new byte[12];
		String obs = "Observação";
		String outrosServicos = "TESTE";
		TipoLeituraDTO tipoLeituraDto = new TipoLeituraDTO("AMPLIADO");
		String tamanhoFonte = "23";
		List<RecursoDTO> recursosDto = new ArrayList<>();
		recursosDto.add(new RecursoDTO(id, "Bengala", false, "100,00"));
		SimNaoDTO simNaoDto = new SimNaoDTO("SIM");
		String renda = "20,00";
		String texto = "Texto de obs.";
				
		List<InstituicaoDTO> instituicoesDto = new ArrayList<>();
		instituicoesDto.add(new InstituicaoDTO());
		
		List<SituacaoGuardaDTO> situacoesGuardaDto = new ArrayList<>();
		situacoesGuardaDto.add(new SituacaoGuardaDTO(new Long(1222), "Adoção"));
		
		PeriodoBeneficioDTO periodoBeneficioDTO = new PeriodoBeneficioDTO();
		periodoBeneficioDTO.setBeneficioDto(new BeneficioDTO(new Long(1222), "Aposentado por Invalidez"));
		periodoBeneficioDTO.setDataInicial("01/01/2011");
		periodoBeneficioDTO.setDataFinal("02/01/2011");
		
		List<PeriodoBeneficioDTO> periodoBeneficiosDto = new ArrayList<>();
		periodoBeneficiosDto.add(periodoBeneficioDTO);
		
		PeriodoDeficienciaDTO periodoDeficienciaDto = new PeriodoDeficienciaDTO();
		periodoDeficienciaDto.setDataInicial("01/01/2012");
		periodoDeficienciaDto.setDeficienciaDto(new DeficienciaDTO(new Long(1), "BAIXA VISAO", true));
		
		List<PeriodoDeficienciaDTO> periodosDeficienciasDto = new ArrayList<>();
		periodosDeficienciasDto.add(periodoDeficienciaDto);
		
		CustoDTO custoDto = new CustoDTO();
		custoDto.setItemCustoDto(new ItemCustoDTO(new Long(12), "TERAPIAS"));
		custoDto.setValor("9999999,99");
		
		List<CustoDTO> custos = new ArrayList<>();
		custos.add(custoDto);
		
		List<ConvenioDTO> conveniosDto = new ArrayList<>();
		conveniosDto.add(new ConvenioDTO(new Long(1222), "UFRJ"));
		
		boolean alfabetizado = true;
		boolean naoEstaNaEscola = true;
		boolean falecido = true;
		
		EscolaridadeDTO escolaridadeDto = new EscolaridadeDTO(new Long(1),
				"SUPERIOR", new ArrayList<SerieDTO>());
		SerieDTO serieDto = new SerieDTO(new Long(1), "1º Série");
		String situacao = "CURSANDO";
		String periodo = "MANHA";
		String nomeProfessor = "Josep Meaza";
		
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
				
		List<InformacaoEducacionalDTO> informacoesEscolares = new ArrayList<>();
		InformacaoEducacionalDTO informacaoEscolarDto = new InformacaoEducacionalDTO();
		informacaoEscolarDto.setEscolaridadeDto(escolaridadeDto);
		informacaoEscolarDto.setSerieDto(serieDto);
		informacaoEscolarDto.setPeriodoDto(new PeriodoDTO(periodo));
		informacaoEscolarDto.setSituacaoEducacionalDto(new SituacaoEducacionalDTO(situacao));
		informacaoEscolarDto.setNomeProfessor(nomeProfessor);
		
		informacoesEscolares.add(informacaoEscolarDto);
		
		List<TelefoneDTO> telefonesDto = new ArrayList<>();
		telefonesDto.add(telResidencial);
		telefonesDto.add(telCelular);
		telefonesDto.add(telRecado);
		
		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setTelefonesDto(telefonesDto);
		contatoDto.setNomeContato(nomeRecado);
		contatoDto.setEmail(email);
		
		InformacaoTrabalhoDTO informacaoTrabalhoDto = new InformacaoTrabalhoDTO();
		informacaoTrabalhoDto.setEmpresa("CTIS");
		informacaoTrabalhoDto.setFuncao("Analista de Sistemas");
		
		InformacaoEssencialDTO informacaoEssencialDtoFamiliar = new InformacaoEssencialDTO();
		informacaoEssencialDtoFamiliar.setNome("José Paulo");
		informacaoEssencialDtoFamiliar.setRg("3442343");
		informacaoEssencialDtoFamiliar.setContatoDto(contatoDto);
		
		FamiliarDTO familiarDto = new FamiliarDTO();
		familiarDto.setInformacaoEssencialDto(informacaoEssencialDtoFamiliar);
		familiarDto.setParentescoDto(new ParentescoDTO(new Long(1), "Pai"));
		familiarDto.setDataNascimento("01/01/1982");
		familiarDto.setInformacaoTrabalhoDto(informacaoTrabalhoDto);
		familiarDto.setInformacoesEducacionaisDto(informacoesEscolares);
		familiarDto.setRenda("1234,44");
		familiarDto.setPrincipalResponsavel(true);
		
		List<FamiliarDTO> familiaresDto = new ArrayList<>();
		familiaresDto.add(familiarDto);

		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setNome(nome);
		informacaoEssencialDto.setRg(rg);
		informacaoEssencialDto.setCpf(cpf);
		informacaoEssencialDto.setContatoDto(contatoDto);
		informacaoEssencialDto.setEnderecoDto(enderecoDto);
		
		InstituicaoDTO instituicaoDto = new InstituicaoDTO();
		instituicaoDto.setId(new Long(1234));
		
		List<CertidaoDTO> certidoesDto = new ArrayList<>();
		certidoesDto.add(new CertidaoDTO());
		
		List<InformacaoTrabalhoCompletaDTO> informacoesTrabalhoCompleta = new ArrayList<>();
		informacoesTrabalhoCompleta.add(new InformacaoTrabalhoCompletaDTO());
		
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setVersao(versao);
		usuarioDto.setId(id);
		usuarioDto.setDataCadastro(dataCadastro);
		usuarioDto.setClassificacaoSocialDto(classificacaoSocial);
		usuarioDto.setStatusDto(status);
		usuarioDto.setInformacaoEssencialDto(informacaoEssencialDto);
		usuarioDto.setGeneroDto(genero);
		usuarioDto.setNaturalidade(naturalidade);
		usuarioDto.setNacionalidade(nacionalidade);
		usuarioDto.setUfRgDto(ufRg);
		usuarioDto.setDataExpedicaoRg(dataExpedicaoRg);
		usuarioDto.setOrgaoEmissorRg(orgaoEmissorRg);
		usuarioDto.setEstadoCivilDto(estadoCivil);
		usuarioDto.setNaoAlfabetizado(alfabetizado);
		usuarioDto.setNaoEstaNaEscola(naoEstaNaEscola);
		usuarioDto.setResponsavelPorSiMesmo(responsavelPorSiMesmo);
		usuarioDto.setInformacaoEducacionaisDto(informacoesEscolares);
		usuarioDto.setInstituicaoComSRMSDto(instituicaoDto);
		usuarioDto.setInstituicaoComSalaRecursoDto(instituicaoDto);
		usuarioDto.setInstituicaoComOutrosAEE(instituicaoDto);
		usuarioDto.setFamiliaresDto(familiaresDto);
		usuarioDto.setSituacoesGuardaDto(situacoesGuardaDto);
		usuarioDto.setPeriodoBeneficiosDto(periodoBeneficiosDto);
		usuarioDto.setPeriodoDeficienciaDto(periodosDeficienciasDto);
		usuarioDto.setMultiplaDeficiencia(multiplaDeficiencia);
		usuarioDto.setCadeiraDeRodas(cadeiraDeRodas);
		usuarioDto.setCustosDoencaDto(custos);
		usuarioDto.setCustosDeficienciaDto(custos);
		usuarioDto.setConveniosDto(conveniosDto);
		usuarioDto.setAssociadoAoSetorProceja(associadoAoProceja);
		usuarioDto.setAssociadoAoSetorCTO(associadoAoCTO);
		usuarioDto.setGrupoEtnicoDto(grupoEtnico);
		usuarioDto.setFoto(foto);
		usuarioDto.setObs(obs);
		usuarioDto.setOutrosApoiosServicos(outrosServicos);
		usuarioDto.setCertidoes(certidoesDto);
		usuarioDto.setTipoLeituraDto(tipoLeituraDto);
		usuarioDto.setTamanhoFonte(tamanhoFonte);
		usuarioDto.setRecursosDto(recursosDto);
		usuarioDto.setAtualmenteTrabalhandoDto(simNaoDto);
		usuarioDto.setInformacaoTrabalhoCompletaDto(informacoesTrabalhoCompleta);
		usuarioDto.setRenda(renda);
		usuarioDto.setRendaTotalFamiliar(renda);
		usuarioDto.setStatusUsuarioAtualDto(statusRelacao);
		usuarioDto.setFalecido(falecido);
		usuarioDto.setPossuiConsanguinidadeDto(simNaoDto);
		usuarioDto.setHistorico(texto);
		usuarioDto.setFuncionalidade(texto);
		usuarioDto.setReacaoFrenteADeficiencia(texto);
		usuarioDto.setReacaoFrenteADeficienciaFamiliar(texto);
		usuarioDto.setRedeDeApoio(texto);
		usuarioDto.setRedeDeAmigos(texto);
		usuarioDto.setNamoroCasamentoSexualidade(texto);	
		usuarioDto.setNecessidadesExpectativasQueixas(texto);
		usuarioDto.setEducacional(texto);
		usuarioDto.setComunicacao(texto);
		usuarioDto.setReligiaoLazerCulturaRotina(texto);
		usuarioDto.setParecer(texto);
		
		
		Assert.assertEquals(usuarioDto.getId(), id);
		Assert.assertEquals(usuarioDto.getVersao(), versao);
		Assert.assertEquals(usuarioDto.getDataCadastro(), dataCadastro);
		Assert.assertEquals(usuarioDto.getClassificacaoSocialDto().toString(),
				classificacaoSocial.toString());
		Assert.assertEquals(usuarioDto.getStatusDto().toString(),
				status.toString());
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getNome(), nome);
		Assert.assertEquals(usuarioDto.getGeneroDto().toString(), genero.toString());
		Assert.assertEquals(usuarioDto.getNaturalidade(), naturalidade);
		Assert.assertEquals(usuarioDto.getNacionalidade(), nacionalidade);
		Assert.assertEquals(usuarioDto.getUfRgDto().toString(), ufRg.toString());
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getRg(), rg);
		Assert.assertEquals(usuarioDto.getDataExpedicaoRg(), dataExpedicaoRg);
		Assert.assertEquals(usuarioDto.getOrgaoEmissorRg(), orgaoEmissorRg);
		Assert.assertEquals(usuarioDto.getEstadoCivilDto().toString(),
				estadoCivil.toString());
		Assert.assertEquals(usuarioDto.getGrupoEtnicoDto().toString(), grupoEtnico.toString());
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getCep(), cep);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getEndereco(), endereco);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getNumero(), numero);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getComplemento(), complemento);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getZonaDto().toString(), zona.toString());
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getBairro(), bairro);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getMunicipioDto(), municipio);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getUfDto().toString(), uf.toString());
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getEnderecoDto().getPaisDto().toString(), pais.toString());
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto()
				.getContatoDto().getTelefonesDto().size(), 3);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getContatoDto().getNomeContato(), nomeRecado);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getContatoDto().getEmail(), email);
		Assert.assertEquals(usuarioDto.getInformacaoEssencialDto().getCpf(), cpf);
		Assert.assertEquals(usuarioDto.isNaoAlfabetizado(), alfabetizado);
		Assert.assertEquals(usuarioDto.isNaoEstaNaEscola(), naoEstaNaEscola);
		Assert.assertEquals(usuarioDto.isResponsavelPorSiMesmo(), responsavelPorSiMesmo);
		Assert.assertEquals(usuarioDto.getInformacoesEducacionaisDto().get(0)
				.getEscolaridadeDto().toString(), escolaridadeDto.toString());
		Assert.assertEquals(usuarioDto.getInformacoesEducacionaisDto().get(0)
				.getSerieDto().toString(), serieDto.toString());
		Assert.assertEquals(usuarioDto.getInformacoesEducacionaisDto().get(0).getPeriodoDto()
				.toString(), periodo);
		Assert.assertEquals(usuarioDto.getInformacoesEducacionaisDto().get(0).getSituacaoEducacionalDto()
				.toString(), situacao);
		Assert.assertEquals(usuarioDto.getInformacoesEducacionaisDto().get(0).getNomeProfessor()
				.toString(), nomeProfessor);
		Assert.assertEquals(usuarioDto.getInstituicaoComSRMsDto().getId(), instituicaoDto.getId());
		Assert.assertEquals(usuarioDto.getInstituicaoComSalaRecursoDto().getId(), instituicaoDto.getId());
		Assert.assertEquals(usuarioDto.getInstituicaoComOutrosAEE().getId(), instituicaoDto.getId());
		Assert.assertEquals(usuarioDto.getFamiliaresDto().size(), 1);
		Assert.assertEquals(usuarioDto.getSituacoesGuardaDto().size(), 1);
		Assert.assertEquals(usuarioDto.getPeriodoBeneficiosDto().size(), 1);
		Assert.assertEquals(usuarioDto.getPeriodoDeficienciaDto().size(), 1);
		Assert.assertEquals(usuarioDto.isMultiplaDeficiencia(), multiplaDeficiencia);
		Assert.assertEquals(usuarioDto.isCadeiraDeRodas(), cadeiraDeRodas);
		Assert.assertEquals(usuarioDto.getCustosDoencaDto().size(), custos.size());
		Assert.assertEquals(usuarioDto.getConveniosDto().size(), 1);
		Assert.assertEquals(usuarioDto.getCustosDeficienciaDto().size(), custos.size());
		Assert.assertEquals(usuarioDto.isAssociadoAoSetorCTO(), associadoAoCTO);
		Assert.assertEquals(usuarioDto.isAssociadoAoSetorProceja(), associadoAoProceja);
		Assert.assertEquals(usuarioDto.getFoto().length, foto.length);
		Assert.assertEquals(usuarioDto.getObs(), obs);
		Assert.assertEquals(usuarioDto.getOutrosApoiosServicos(), outrosServicos);
		Assert.assertEquals(usuarioDto.getCertidoes().size(), 1);
		Assert.assertEquals(usuarioDto.getTipoLeituraDto().toString(), tipoLeituraDto.toString());
		Assert.assertEquals(usuarioDto.getTamanhoFonte(), tamanhoFonte);
		Assert.assertEquals(usuarioDto.getRecursosDto().size(), 1);
		Assert.assertEquals(usuarioDto.getAtualmenteTrabalhandoDto().toString(), simNaoDto.toString());
		Assert.assertEquals(usuarioDto.getInformacaoTrabalhoCompletaDto().size(), informacoesTrabalhoCompleta.size());
		Assert.assertEquals(usuarioDto.getRenda(), renda);
		Assert.assertEquals(usuarioDto.getRendaTotalFamiliar(),  renda);
		Assert.assertEquals(usuarioDto.getStatusUsuarioAtualDto().toString(), statusRelacao.toString());
		Assert.assertEquals(usuarioDto.isFalecido(), falecido);
		Assert.assertEquals(usuarioDto.getPossuiConsanguinidadeDto().toString(), simNaoDto.toString());
		Assert.assertEquals(usuarioDto.getHistorico(), texto);
		Assert.assertEquals(usuarioDto.getFuncionalidade(), texto);
		Assert.assertEquals(usuarioDto.getReacaoFrenteADeficiencia(), texto);
		Assert.assertEquals(usuarioDto.getReacaoFrenteADeficienciaFamiliar(), texto);
		Assert.assertEquals(usuarioDto.getRedeDeApoio(), texto);
		Assert.assertEquals(usuarioDto.getRedeDeAmigos(), texto);
		Assert.assertEquals(usuarioDto.getNamoroCasamentoSexualidade(), texto);
		Assert.assertEquals(usuarioDto.getNecessidadesExpectativasQueixas(), texto);
		Assert.assertEquals(usuarioDto.getEducacional(), texto);
		Assert.assertEquals(usuarioDto.getComunicacao(), texto);
		Assert.assertEquals(usuarioDto.getReligiaoLazerCulturaRotina(), texto);
		Assert.assertEquals(usuarioDto.getParecer(), texto);
	}
}
