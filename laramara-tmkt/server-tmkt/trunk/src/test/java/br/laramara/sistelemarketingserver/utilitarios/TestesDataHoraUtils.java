package br.laramara.sistelemarketingserver.utilitarios;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoGenerico;

public class TestesDataHoraUtils {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_converte_de_localdatetime_para_date_com_sucesso() {
		LocalDateTime dataHora = LocalDateTime.of(2018, 12, 31, 23, 59, 59, 999999999);

		Date dataHoraLegada = DataHoraUtils.converterParaDate(dataHora);

		Assert.assertEquals(DataHoraUtils.formatarDataHora(dataHoraLegada), DataHoraUtils.formatarDataHora(dataHora));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_converte_de_date_para_localdatetime_com_sucesso() throws ParseException {
		Date dataHoraLegada = ContextoGenerico.obterData("31/12/2018 23:59:59.999");

		LocalDateTime dataHora = DataHoraUtils.converterParaLocalDateTime(dataHoraLegada);

		Assert.assertEquals(DataHoraUtils.formatarDataHora(dataHora), DataHoraUtils.formatarDataHora(dataHoraLegada));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_valida_com_sucesso() {
		String textoData = "31/12/2018";

		LocalDate data = DataHoraUtils.obterDataValidaInvalidaOuNulo(textoData);

		Assert.assertEquals(data.getDayOfMonth(), 31);
		Assert.assertEquals(data.getMonthValue(), 12);
		Assert.assertEquals(data.getYear(), 2018);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_invalida_com_sucesso() {
		String textoData = "32/02/2018";

		LocalDate data = DataHoraUtils.obterDataValidaInvalidaOuNulo(textoData);

		Assert.assertTrue(DataHoraUtils.dataInvalida(data));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_nula_com_sucesso() {
		String textoData = "";

		LocalDate data = DataHoraUtils.obterDataValidaInvalidaOuNulo(textoData);

		Assert.assertNull(data);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_formatada_com_sucesso() {
		String textoData = "31/12/2018";

		LocalDate data = DataHoraUtils.obterDataValidaInvalidaOuNulo(textoData);

		String dataFormatada = DataHoraUtils.formatarData(data);

		Assert.assertEquals(dataFormatada, textoData);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_obtem_data_inicio_anterior_a_data_termino() {
		LocalDate dataAnterior = DataHoraUtils.obterDataValidaInvalidaOuNulo("01/12/2017");
		LocalDate dataPosterior = DataHoraUtils.obterDataValidaInvalidaOuNulo("31/12/2018");

		Assert.assertTrue(DataHoraUtils.datasForaDeOrdem(dataPosterior, dataAnterior));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_data_esta_dentro_do_periodo() {
		LocalDate dataInicio = DataHoraUtils.obterDataValidaInvalidaOuNulo("01/01/2017");
		MaquinaTempo.mudarDataAtual("01/01/2017");
		LocalDate dataTermino = DataHoraUtils.obterDataValidaInvalidaOuNulo("03/01/2017");

		Assert.assertTrue(DataHoraUtils.estaNoPeriodo(dataInicio, dataTermino));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_data_esta_dentro_do_periodo_alternativo() {
		LocalDate dataInicio = DataHoraUtils.obterDataValidaInvalidaOuNulo("01/01/2017");
		MaquinaTempo.mudarDataAtual("03/01/2017");
		LocalDate dataTermino = DataHoraUtils.obterDataValidaInvalidaOuNulo("03/01/2017");

		Assert.assertTrue(DataHoraUtils.estaNoPeriodo(dataInicio, dataTermino));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void datautils_data_nao_esta_dentro_do_periodo_alternativo() {
		LocalDate dataInicio = DataHoraUtils.obterDataValidaInvalidaOuNulo("01/01/2017");
		MaquinaTempo.mudarDataAtual("04/01/2017");
		LocalDate dataTermino = DataHoraUtils.obterDataValidaInvalidaOuNulo("03/01/2017");

		Assert.assertFalse(DataHoraUtils.estaNoPeriodo(dataInicio, dataTermino));
	}
}
