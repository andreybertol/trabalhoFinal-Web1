package br.edu.utfpr.pb.trabalhofinal;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrabalhoFinalApplication {

	public static void main(String[] args) {
	    SpringApplication.run(TrabalhoFinalApplication.class, args);
	}

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
