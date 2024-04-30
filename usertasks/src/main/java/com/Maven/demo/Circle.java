package com.Maven.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("circle")
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("I am from circle");
    }
}
