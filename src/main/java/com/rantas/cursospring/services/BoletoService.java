package com.rantas.cursospring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.rantas.cursospring.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentocomBoleto(PagamentoComBoleto pgtcb, Date instanteDePedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDePedido);
		cal.set(cal.DAY_OF_MONTH, 7);
		
		pgtcb.setDataPagamento(cal.getTime());
	} 
}
