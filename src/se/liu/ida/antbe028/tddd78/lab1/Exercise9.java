package se.liu.ida.antbe028.tddd78.lab1;

import javax.swing.JOptionPane;

public class Exercise9 {
    public static void main(String[] args) {
        String num = JOptionPane.showInputDialog("Please input a value: ");
        double numDouble = Double.parseDouble(num);
        System.out.println(findRoot(numDouble));

    }

    public static double findRoot(double num) {
        double guess = num;

        for (int i = 0; i < 10; i++) {
	    guess -= (guess * guess - num) / (2 * guess);
        }

        return guess;
    }
}
