package com.Maven.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("rectangle")
public class Rectangle implements Shape {

    private  Square square;

    Rectangle(Square square){
        this.square = square;
    }
    @Override
    public void draw() {
        square.draw();
        System.out.println("I am from rectangle");
    }
}

