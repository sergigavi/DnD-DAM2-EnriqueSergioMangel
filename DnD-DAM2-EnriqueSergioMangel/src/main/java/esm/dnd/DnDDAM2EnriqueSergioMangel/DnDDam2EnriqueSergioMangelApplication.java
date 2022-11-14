package esm.dnd.DnDDAM2EnriqueSergioMangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {""})
@SpringBootApplication
@ComponentScan({"esm.dnd.DnDDAM2EnriqueSergioMangel.modelo"})
public class DnDDam2EnriqueSergioMangelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnDDam2EnriqueSergioMangelApplication.class, args);
		
		
	}

}
