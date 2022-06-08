package com.example.sesiones456;

import com.example.sesiones456.entities.Laptop;
import com.example.sesiones456.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//Si no se añade la anotación no funciona
@EnableWebMvc
@SpringBootApplication
public class Sesiones456Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Sesiones456Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop miLaptop1 = new Laptop(null, "Asus 001", 599.99);
		Laptop miLaptop2 = new Laptop(null, "Acer 001", 389.95);

		repository.save(miLaptop1);
		repository.save(miLaptop2);

	}

}
