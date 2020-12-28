package com.rantas.cursospring.domain.enums;

public enum Perfil {
	
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	private Integer cod;
	private String descricao;
	
	
	private Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public Integer getCod() {
		return cod;
	}


	public String getDescricao() {
		return descricao;
	}
	
	
	public static Perfil toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for(Perfil y:Perfil.values()) {
			
			if(cod.equals(y.getCod())) {
				return y;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
		
	}
	

}