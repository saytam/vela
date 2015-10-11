package vela.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UiConfig {

    @Bean
    public Screens screens(){
        return new Screens();
    }



}
