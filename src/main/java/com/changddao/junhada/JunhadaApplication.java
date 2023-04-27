package com.changddao.junhada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JunhadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunhadaApplication.class, args);
	}

}
