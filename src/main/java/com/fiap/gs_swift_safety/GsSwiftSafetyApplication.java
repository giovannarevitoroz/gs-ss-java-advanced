package com.fiap.gs_swift_safety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GsSwiftSafetyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsSwiftSafetyApplication.class, args);
		System.out.println("Swift Safety API iniciada com sucesso. Aguardando requisições...");
	}

}
