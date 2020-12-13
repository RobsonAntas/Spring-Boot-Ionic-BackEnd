package com.rantas.cursospring.services.validation.utils;

public class BR {

	
	public static boolean validaCpf(String cpf) {
		boolean resultado =false;
		String cpfArranged = cpf.trim();		
		String[] cpf_array = cpfArranged.split("");
		
		//Validação do primeiro dígito		
		if(getDig(validaDigito(cpf,3,10))==Integer.parseInt(cpf_array[9])) {
			//Validação do segundo dígito
			if(getDig(validaDigito(cpf,2, 11))==Integer.parseInt(cpf_array[10])){
				resultado = true;
			}
		}
		
		return resultado;
	}
	
	public static boolean validaCnpj(String cnpj) {
		boolean resultado=false;
		String cnpjArranged = cnpj.trim();		
		String[] cnpj_array = cnpjArranged.split("");
		int[] weight = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] weight_1 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		//Primeira validação
		if(getDigCnpj(validaDigitoCnpj(weight,cnpj_array, 3))==Integer.parseInt(cnpj_array[12])) {
			//Validação do segundo dígito
			if(getDigCnpj(validaDigitoCnpj(weight_1,cnpj_array, 2))==Integer.parseInt(cnpj_array[13])){
				resultado = true;
			}
		}
		
		return resultado;
	}
	
	private static Integer validaDigito(String cpf, int min, int max) {
		
		
		String cpfArranged = cpf.trim();		
		String[] cpf_array = cpfArranged.split("");
		int aux=0;
		int cont =max;
		int soma =0;
		for(int i=0; i<=cpf_array.length-min;i++) {
			int	num = Integer.parseInt(cpf_array[i]);
				aux=num*cont;
				soma+=aux;
				cont-=1;
		}
		return soma;
		
	}
	private static Integer getDig(int num) {
		int dig = (num*10)%11;		
		dig= (dig==10)?dig=0:dig;
		
		System.out.println(dig);
		return dig;
	}
	
	private static Integer validaDigitoCnpj(int[] weight, String[] cnpj_array, int t) {
		int n,r;
		int soma=0;
		for(int i=0; i<=cnpj_array.length-t;i++) {
			n = Integer.parseInt(cnpj_array[i]);
			r=n*weight[i];
			soma+=r;
		}
		return soma;
	}
	
	private static Integer getDigCnpj(int num) {
		int dig = (num)%11;		
		dig= (dig<2)?dig=0:11-dig;
		System.out.println(dig);
		return dig;
	}
}
