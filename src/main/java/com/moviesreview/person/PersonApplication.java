package com.moviesreview.person;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
		info = @Info(
				title = "Persons microservice Rest API Documentation",
				description = "Persons (Actors, Directors,etc) microservice Rest API Documentation",
				version = "0.1",
				contact = @Contact(
						name = "Alberto de Teresa Chaves",
						email = "alberto.trabajo.es@gmail.com",
						url = "https://x.com/Beto_rvss"
				)
		)
)
@EnableConfigurationProperties
@SpringBootApplication
public class PersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}

}
