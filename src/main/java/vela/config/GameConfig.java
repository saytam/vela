package vela.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vela.game.DistanceCalculator;

@Configuration
public class GameConfig {
    @Bean
    DistanceCalculator distanceCalculator(){
        return new DistanceCalculator();
    }
}
