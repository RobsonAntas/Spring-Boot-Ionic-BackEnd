package com.rantas.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rantas.cursospring.domain.Categoria;
import com.rantas.cursospring.dto.CategoriaDto;
import com.rantas.cursospring.repositories.CategoriaRepository;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new com.rantas.cursospring.services.exceptions.ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+Categoria.class.getName()));
	}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e ) {
			throw new DataIntegrityViolationException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();		
	}
	
	public Page<Categoria> findPage(Integer pageNumber, Integer linesPerpage, String direction, String orderBy){
		
		PageRequest pageRequest = PageRequest.of(pageNumber, linesPerpage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDto objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
