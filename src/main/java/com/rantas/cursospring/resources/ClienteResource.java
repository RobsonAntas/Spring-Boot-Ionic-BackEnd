package com.rantas.cursospring.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rantas.cursospring.domain.Cliente;
import com.rantas.cursospring.dto.ClienteDto;
import com.rantas.cursospring.services.ClienteService;



@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
			
		 Cliente obj = service.find(id);
		
		  return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody ClienteDto objDto){
		Cliente obj = service.fromDto(objDto);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
			
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
	
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<ClienteDto>> findAll(){
		List<Cliente> listObj =service.findAll();
		List<ClienteDto> listDto = listObj.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);		
	}
	
	@RequestMapping(value="/page",method= RequestMethod.GET)
	public ResponseEntity<Page<ClienteDto>> findPage(
			@RequestParam(value="page", defaultValue = "0")
			Integer pageNumber,
			@RequestParam(value="linesPerpage", defaultValue = "24")
			Integer linesPerpage,
			@RequestParam(value="direction", defaultValue = "ASC")
			String direction,
			@RequestParam(value="orderBy", defaultValue = "nome")
			String orderBy){
		Page<Cliente> listObj =service.findPage(pageNumber, linesPerpage, direction, orderBy);
		Page<ClienteDto> listDto = listObj.map(obj -> new ClienteDto(obj));
		return ResponseEntity.ok().body(listDto);		
	}

}
