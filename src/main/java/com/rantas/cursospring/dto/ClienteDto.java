package com.rantas.cursospring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rantas.cursospring.domain.Cliente;
import com.rantas.cursospring.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preencimento obrigatório")
	@Length(min=5, max= 120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	@NotEmpty(message="Preencimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	
	public ClienteDto() {	
	}
	
	public ClienteDto(Cliente obj) {	
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
	
	
	
}
