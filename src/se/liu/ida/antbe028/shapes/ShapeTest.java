package se.liu.ida.antbe028.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeTest
{
    public static void main(String[] args) {

	List<Shape> shapes = new ArrayList<>();

	Circle shapeCir1 = new Circle(10, 20, 50, Color.BLUE);
	Circle shapeCir2 = new Circle(50, 100, 500, Color.GREEN);
	Circle shapeCir3 = new Circle(500, 500, 100, Color.RED);

	shapes.add(shapeCir1);
	shapes.add(shapeCir2);
	shapes.add(shapeCir3);

	Rectangle shapeRect1 = new Rectangle(150, 200, 100, 50, Color.BLACK);
	Rectangle shapeRect2 = new Rectangle(600, 700, 30, 300, Color.YELLOW);

	shapes.add(shapeRect1);
	shapes.add(shapeRect2);

	Text txt1 =  new Text(1, 2, 15, Color.MAGENTA, "Hej på dig!");
	Text txt2 =  new Text(300, 310, 15, Color.CYAN, "Test 123 test 123");

	shapes.add(txt1);
	shapes.add(txt2);

	/*
	for (Shape element : shapes) {
	    System.out.println("X-coord: " + element.getX() + ", Y-coord: " + element.getY());
	    element.draw();
	}
	 */

    }
}
