package com.rantas.cursospring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rantas.cursospring.domain.ItemPedido;
import com.rantas.cursospring.domain.PagamentoComBoleto;
import com.rantas.cursospring.domain.Pedido;
import com.rantas.cursospring.domain.enums.EstadoPagamento;
import com.rantas.cursospring.repositories.ItemPedidoRepository;
import com.rantas.cursospring.repositories.PagamentoRepository;
import com.rantas.cursospring.repositories.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido buscarPedido(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new com.rantas.cursospring.services.exceptions.ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "+Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgtcb = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentocomBoleto(pgtcb, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.buscarProduto(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
