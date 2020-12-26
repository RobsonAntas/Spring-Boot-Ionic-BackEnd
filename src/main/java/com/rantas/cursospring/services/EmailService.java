package com.rantas.cursospring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.rantas.cursospring.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);
	
	
	//Versão Html
	void sendOrderConfirmationEmailHTML(Pedido pedido);

	void sendEmailHTML(MimeMessage msg);
}
