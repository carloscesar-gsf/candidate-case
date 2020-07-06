package br.com.carloscesargsf.candidatecase.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Candidate Case API")
                        .description("Candidate Case API endpoints.")
                        .version("1.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .termsOfService("http://example.com/terms/")
                        .contact(new Contact()
                                .name("Carlos César Gomes dos Santos Filho")
                                .email("carloscesar.gsf@gmail.com")
                                .url("https://www.linkedin.com/in/carloscesargsf/")));
    }

//    @Bean
//    public GroupedOpenApi CandidateCaseOpenAPI() {
//        return GroupedOpenApi.builder()
//                .group("Candidate Case API")
//                .addOpenApiCustomiser(openApi -> {
//                    openApi.getInfo()
//                            .title("Candidate Case API")
//                            .description("Candidate Case API endpoints.")
//                            .version("1.0")
//                            .license(new License()
//                                    .name("Apache 2.0")
//                                    .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
//                            .termsOfService("http://example.com/terms/")
//                            .contact(new Contact()
//                                    .name("Carlos César Gomes dos Santos Filho")
//                                    .email("carloscesar.gsf@gmail.com")
//                                    .url("https://www.linkedin.com/in/carloscesargsf/"));
//                })
//                .build();
//    }

}
