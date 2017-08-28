package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BlogConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(BlogConfiguration.class, args);
	}

}
