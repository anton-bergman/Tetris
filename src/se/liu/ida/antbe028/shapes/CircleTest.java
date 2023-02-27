package se.liu.ida.antbe028.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleTest
{
    public static void main(String[] args) {

	final List<Circle> circles = new ArrayList<>();

	Circle circle1 = new Circle(10, 20, 50, Color.blue);
	Circle circle2 = new Circle(50, 100, 500, Color.green);
	Circle circle3 = new Circle(500, 500, 100, Color.red);

	circles.add(circle1);
	circles.add(circle2);
	circles.add(circle3);

	for (Circle element : circles) {
	    System.out.println("X-coord: " + element.getX() + ", Y-coord: " + element.getY());
	}

    }
}
