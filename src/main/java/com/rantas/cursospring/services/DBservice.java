package com.rantas.cursospring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rantas.cursospring.domain.Categoria;
import com.rantas.cursospring.domain.Cidade;
import com.rantas.cursospring.domain.Cliente;
import com.rantas.cursospring.domain.Endereco;
import com.rantas.cursospring.domain.Estado;
import com.rantas.cursospring.domain.ItemPedido;
import com.rantas.cursospring.domain.Pagamento;
import com.rantas.cursospring.domain.PagamentoComBoleto;
import com.rantas.cursospring.domain.PagamentoComCartao;
import com.rantas.cursospring.domain.Pedido;
import com.rantas.cursospring.domain.Produto;
import com.rantas.cursospring.domain.enums.EstadoPagamento;
import com.rantas.cursospring.domain.enums.Perfil;
import com.rantas.cursospring.domain.enums.TipoCliente;
import com.rantas.cursospring.repositories.CategoriaRepository;
import com.rantas.cursospring.repositories.CidadeRepository;
import com.rantas.cursospring.repositories.ClienteRepository;
import com.rantas.cursospring.repositories.EnderecoRepository;
import com.rantas.cursospring.repositories.EstadoRepository;
import com.rantas.cursospring.repositories.ItemPedidoRepository;
import com.rantas.cursospring.repositories.PagamentoRepository;
import com.rantas.cursospring.repositories.PedidoRepository;
import com.rantas.cursospring.repositories.ProdutoRepository;

@Service
public class DBservice {
	
	@Autowired
	private BCryptPasswordEncoder bCPassEncod;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;	
	@Autowired
	private EstadoRepository estadoRepository;	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pegamentoRepository;	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public void instatiateTestDataBase() throws ParseException {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null,"Eletrônicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");
		
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		Produto p4 = new Produto(null,"Mesa e Escritório",300.00);
		Produto p5 = new Produto(null,"Toalha",50.00);
		Produto p6 = new Produto(null,"Colcha",200.00);
		Produto p7 = new Produto(null,"Tv true color",1200.00);
		Produto p8 = new Produto(null,"Roçadeira",800.00);
		Produto p9 = new Produto(null,"Abajour",100.00);
		Produto p10 = new Produto(null,"Pendente",180.00);
		Produto p11 = new Produto(null,"Shampoo",90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia",est1);
		Cidade cid2 = new Cidade(null, "São Paulo",est2);
		Cidade cid3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		
		Cliente cli1 = new Cliente(null, "Robson P Antas", "robsonantas@gmail.com","36378912377",TipoCliente.PESSOAFISICA, bCPassEncod.encode("123456"));
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Cliente cli2 = new Cliente(null, "Ana Maria", "maria@gmail.com","92110176024",TipoCliente.PESSOAFISICA, bCPassEncod.encode("123456"));
		cli2.getTelefones().addAll(Arrays.asList("34551856","979798510"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco e1 = new Endereco(null, "Rua Flores","300", "Apto 303", "Jardim", "38220834",cli1,cid1);
		Endereco e2 = new Endereco(null, "Av Matos","105", "Apto 800", "Centro", "38777012",cli1,cid2);
		Endereco e3 = new Endereco(null, "Rua da Alegria","51", "Casa 100", "Jardim das Gaças", "38777012",cli2,cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("15/10/2019 17:00"), cli1, e2);
		
		Pagamento pgmt1  = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1,6);
		ped1.setPagamento(pgmt1);
		
		Pagamento pgmt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2019 00:00"), null);
		ped2.setPagamento(pgmt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pegamentoRepository.saveAll(Arrays.asList(pgmt1, pgmt2));
	
		ItemPedido itmp1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido itmp2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido itmp3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(itmp1,itmp2));
		ped2.getItens().addAll(Arrays.asList(itmp3));
		
		p1.getItens().addAll(Arrays.asList(itmp1));
		p2.getItens().addAll(Arrays.asList(itmp3));
		p3.getItens().addAll(Arrays.asList(itmp2));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(itmp1,itmp2,itmp3));

		
	}

}
