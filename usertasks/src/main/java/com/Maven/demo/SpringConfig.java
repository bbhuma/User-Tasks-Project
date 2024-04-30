package com.Maven.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    Circle getCircle(){
        return new Circle();
    }
    @Bean
    Square getSquare(){
        return new Square();
    }

    @Bean
    Rectangle getRectangle( ){
        return new Rectangle(new Square());  //Declare a bean that takes a dependency
    }
}
