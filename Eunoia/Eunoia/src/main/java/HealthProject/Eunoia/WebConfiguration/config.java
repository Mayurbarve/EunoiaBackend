package HealthProject.Eunoia.WebConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class config {

    @Bean
    public WebMvcConfigurer crossConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry register) {
                register.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
}
