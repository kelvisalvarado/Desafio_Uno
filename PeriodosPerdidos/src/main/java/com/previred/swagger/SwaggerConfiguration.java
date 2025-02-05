package com.previred.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	
	ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API periodos perdidos")
            .description("Servicio Rest consulta periodos perdidos")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .contact(new Contact("Kelvis Alvarado","", "kelvisalvarado@gmail.com"))
            .build();
    }
  
   @Bean
      public Docket periodoApi() {
          return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.previred.rest"))
               .paths(regex("/periodos.*"))
               .build()
               .apiInfo(apiInfo());
      }



            
}