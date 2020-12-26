package com.rantas.cursospring.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
			
	@Override
	public void sendEmail(SimpleMailMessage msg) {
	    LOG.info("Simulando envio de email");
		LOG.info(msg.toString());
	    LOG.info("Email enviado");

	}

	@Override
	public void sendEmailHTML(MimeMessage msg) {
		    LOG.info("Simulando envio de email");
			LOG.info(msg.toString());
		    LOG.info("Email enviado");
		
	}

}
