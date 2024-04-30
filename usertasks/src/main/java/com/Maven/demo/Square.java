package com.Maven.demo;

import org.springframework.stereotype.Component;

@Component
public class Square implements Shape{

    @Override
    public void draw() {
        System.out.println("i called square before rectangle");

    }
}
