package com.rantas.cursospring.services;

import org.springframework.mail.SimpleMailMessage;

import com.rantas.cursospring.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);

}
