package esm.dnd.DnDDAM2EnriqueSergioMangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = {""})
@SpringBootApplication
@ComponentScan({"esm.dnd.DnDDAM2EnriqueSergioMangel.modelo"})
@CrossOrigin(originPatterns = {"*"},methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.OPTIONS})    //Con esta anotacion se salta el protocolo para poder acceder a la API desde el fetch de javascript etcetc
public class DnDDam2EnriqueSergioMangelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnDDam2EnriqueSergioMangelApplication.class, args);
		
		
	}
}
