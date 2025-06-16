package com.ProjetoExtensao.Projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");

		ApplicationContext context = SpringApplication.run(ProjetoApplication.class, args);

		NavigationService navigationService = context.getBean(NavigationService.class);
		navigationService.abrirTelaLogin();
	}
}
