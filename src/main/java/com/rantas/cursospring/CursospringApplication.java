package com.rantas.cursospring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia",est1);
		Cidade cid2 = new Cidade(null, "São Paulo",est2);
		Cidade cid3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Da Silva", "maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
	
		Endereco e1 = new Endereco(null, "Rua Flores","300", "Apto 303", "Jardim", "38220834",cli1,cid1);
		Endereco e2 = new Endereco(null, "Av Matos","105", "Apto 800", "Centro", "38777012",cli1,cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
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
