package com.rantas.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rantas.cursospring.domain.Pedido;
import com.rantas.cursospring.repositories.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscarPedido(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new com.rantas.cursospring.services.exceptions.ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+Pedido.class.getName()));
	}
}
