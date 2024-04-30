package com.Maven.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
		Shape circle =  ac.getBean("getCircle",Shape.class);
		circle.draw();
		Shape rectangle = ac.getBean("getRectangle",Shape.class);
		rectangle.draw();

	}

}
