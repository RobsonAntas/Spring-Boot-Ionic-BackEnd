package com.rantas.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rantas.cursospring.domain.Cidade;
import com.rantas.cursospring.domain.Cliente;
import com.rantas.cursospring.domain.Endereco;
import com.rantas.cursospring.domain.enums.TipoCliente;
import com.rantas.cursospring.dto.ClienteDto;
import com.rantas.cursospring.dto.ClienteNewDto;
import com.rantas.cursospring.repositories.ClienteRepository;
import com.rantas.cursospring.repositories.EnderecoRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository repoEnd;
	
	public Cliente find(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new com.rantas.cursospring.services.exceptions.ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj =repo.save(obj);
		repoEnd.saveAll(obj.getEnderecos());
		return obj;	
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
	
	public Cliente fromDto(ClienteNewDto objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(),
				objDto.getCep(),cliente, cidade);
		cliente.getEnderecos().add(end);
		
		cliente.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cliente.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cliente.getTelefones().add(objDto.getTelefone3());
		}
		
		return cliente;
	}
	
	public Cliente inclui(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());;
		newObj.setEmail(obj.getEmail());
		return newObj;
		
	}

}
