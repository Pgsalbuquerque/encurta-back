package com.zg.encurtador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EncurtaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncurtaBackApplication.class, args);
	}

}
