package br.laramara.ti.sislaracommons.utilitarios;

public class CpfCnpjUtils {

	public static boolean validarCpfCnpj(String cpfCnpj) {
		boolean validado = false;
		if (cpfCnpj == null) {
			validado = false;
		} else {
			String cpfCnpjTratado = cpfCnpj.trim();
			if (cpfCnpjTratado.length() == 11 || cpfCnpjTratado.length() == 14) {
				if (cpfCnpjTratado.length() == 14 && !validarCnpj(cpfCnpjTratado)) {
					validado = false;
				} else if (cpfCnpjTratado.length() == 11 && !CpfUtils.validarCPF(cpfCnpjTratado)) {
					validado = false;
				} else {
					validado = true;
				}
			} else {
				validado = false;
			}
		}
		return validado;
	}
	
	private static boolean validarCnpj(String textoCnpj) {

		int soma = 0, digito;
		String cnpjCalculado = textoCnpj.substring(0, 12);

		if (textoCnpj.length() != 14 || textoCnpj.equals("00000000000000"))
			return false;

		char[] cnpjArray = textoCnpj.toCharArray();

		/* Primeira parte */
		for (int i = 0; i < 4; i++)
			if (cnpjArray[i] - 48 >= 0 && cnpjArray[i] - 48 <= 9)
				soma += (cnpjArray[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (cnpjArray[i + 4] - 48 >= 0 && cnpjArray[i + 4] - 48 <= 9)
				soma += (cnpjArray[i + 4] - 48) * (10 - (i + 1));
		digito = 11 - (soma % 11);

		cnpjCalculado += (digito == 10 || digito == 11) ? "0" : Integer.toString(digito);

		/* Segunda parte */
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (cnpjArray[i] - 48 >= 0 && cnpjArray[i] - 48 <= 9)
				soma += (cnpjArray[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (cnpjArray[i + 5] - 48 >= 0 && cnpjArray[i + 5] - 48 <= 9)
				soma += (cnpjArray[i + 5] - 48) * (10 - (i + 1));
		digito = 11 - (soma % 11);
		cnpjCalculado += (digito == 10 || digito == 11) ? "0" : Integer.toString(digito);

		return textoCnpj.equals(cnpjCalculado);
	}
}
