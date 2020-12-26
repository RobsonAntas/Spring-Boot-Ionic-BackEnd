package com.rantas.cursospring.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rantas.cursospring.services.DBservice;
import com.rantas.cursospring.services.EmailService;
import com.rantas.cursospring.services.MockMailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBservice dbService;
	
	@Bean
	public boolean instatiateDB() throws ParseException {
		dbService.instatiateTestDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}
}
