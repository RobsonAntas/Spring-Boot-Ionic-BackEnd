package com.rantas.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rantas.cursospring.domain.Cliente;
import com.rantas.cursospring.domain.enums.TipoCliente;
import com.rantas.cursospring.dto.ClienteNewDto;
import com.rantas.cursospring.repositories.ClienteRepository;
import com.rantas.cursospring.resources.exceptions.FieldMessage;
import com.rantas.cursospring.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDto>{
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Override
	public void initialize(ClientInsert constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.validaCpf(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.validaCnpj(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
		}
		
		Cliente cliVerifica = clienteRepo.findByEmail(objDto.getEmail());
		if(cliVerifica!=null) {
			list.add(new FieldMessage("Email", "Email já Existente"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
