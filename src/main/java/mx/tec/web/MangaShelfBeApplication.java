package mx.tec.web;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MangaShelfBeApplication {

	/**
	 * Main method
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MangaShelfBeApplication.class, args);
	}

	/**
	 * Create a Model Mapper for the application
	 * @return A model Mapper for conversion between objects.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
