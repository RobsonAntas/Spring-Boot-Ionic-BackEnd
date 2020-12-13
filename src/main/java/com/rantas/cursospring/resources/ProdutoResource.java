package com.rantas.cursospring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rantas.cursospring.domain.Produto;
import com.rantas.cursospring.dto.ProdutoDto;
import com.rantas.cursospring.resources.utils.URL;
import com.rantas.cursospring.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
			
		 Produto obj = service.buscarProduto(id);
		
		  return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDto>> findPage(
			@RequestParam(value="nome", defaultValue = "") String nome,
			@RequestParam(value="categorias", defaultValue = "") String categorias,
			@RequestParam(value="page", defaultValue = "0") Integer pageNumber,
			@RequestParam(value="linesPerpage", defaultValue = "24") Integer linesPerpage,
			@RequestParam(value="direction", defaultValue = "ASC") String direction,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy){
		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecoded = URL.decodeParam(nome);
		Page<Produto> listObj =service.search(nomeDecoded, ids,pageNumber, linesPerpage, direction, orderBy);
		Page<ProdutoDto> listDto = listObj.map(obj -> new ProdutoDto(obj));
		return ResponseEntity.ok().body(listDto);		
	}


}
