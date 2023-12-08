package poly.tt.thuexe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import poly.tt.thuexe.Config.StorageProperties;
import poly.tt.thuexe.Service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ThuexeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThuexeApplication.class, args);
	}
@Bean
  CommandLineRunner init(StorageService storageService) {
	  return (args->{
		  storageService.init();
	  });
  }
}
