package br.com.terceiroperiodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TerceiroPeriodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerceiroPeriodoApplication.class, args);
	}

}
