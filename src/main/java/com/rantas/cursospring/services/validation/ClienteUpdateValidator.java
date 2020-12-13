package com.rantas.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.rantas.cursospring.domain.Cliente;
import com.rantas.cursospring.dto.ClienteDto;
import com.rantas.cursospring.repositories.ClienteRepository;
import com.rantas.cursospring.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto>{
	
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private HttpServletRequest  httpSR;
	
	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) httpSR.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId =Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente cliVerifica = clienteRepo.findByEmail(objDto.getEmail());
		if(cliVerifica!=null && !cliVerifica.getId().equals(uriId)) {
			list.add(new FieldMessage("Email", "Email já Existente"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
