package soft.paradigm.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Demo API",
                version = "1.0.1",
                contact = @Contact(
                        name = "Hachem Alouane",
                        url = "https://www.linkedin.com/in/hachem-alouane-15a82a111/",
                        email = "hachem500@hotmail.com"))
)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}