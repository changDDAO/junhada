package com.changddao.junjada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JunjadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunjadaApplication.class, args);
	}

}
