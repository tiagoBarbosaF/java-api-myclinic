package med.myclinic.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("MyClinic-API")
                        .description("Rest API of MyClinic application, with CRUD functionalities for doctors " +
                                     "and patients, as well as scheduling and cancelling appointments")
                        .contact(new Contact()
                                .name("Tiago Barbosa")
                                .email("tiago@email.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://myclinic/api/license")));
    }


}
