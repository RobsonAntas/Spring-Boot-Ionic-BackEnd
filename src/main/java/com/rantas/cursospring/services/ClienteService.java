package com.rantas.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rantas.cursospring.domain.Cliente;
import com.rantas.cursospring.dto.ClienteDto;
import com.rantas.cursospring.repositories.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new com.rantas.cursospring.services.exceptions.ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj =find(obj.getId());		
		return repo.save(inclui(newObj, obj));
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e ) {
			throw new DataIntegrityViolationException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();		
	}
	
	public Page<Cliente> findPage(Integer pageNumber, Integer linesPerpage, String direction, String orderBy){
		
		PageRequest pageRequest = PageRequest.of(pageNumber, linesPerpage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDto objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}
	
	public Cliente inclui(Cliente obj, Cliente newObj) {
		newObj.setCpfOuCnpj(obj.getCpfOuCnpj());
		newObj.setTipo(obj.getTipo());
		return newObj;
		
	}

}
