package com.rantas.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rantas.cursospring.domain.Categoria;
import com.rantas.cursospring.repositories.CategoriaRepository;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new com.rantas.cursospring.services.exceptions.ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+Categoria.class.getName()));
	}
}
