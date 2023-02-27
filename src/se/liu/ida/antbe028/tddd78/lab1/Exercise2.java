package se.liu.ida.antbe028.tddd78.lab1;

import javax.swing.JOptionPane;

public class Exercise2
{
    public static void main(String[] args) {
        int min = 10;
        int max = 20;

        String input = JOptionPane.showInputDialog("Enter 'for' or 'while': ");

        switch (input) {
            case "for":
                System.out.println("Här är sumFor: " + sumFor(min, max));
                break;

            case "while":
                System.out.println("Här är sumWhile: " + sumWhile(min, max));
                break;

            default:
                System.out.println("Error: Expected input 'for' or 'while'.");
                break;
        }
    }

    public static int sumFor(int min, int max) {
        int result = 0;
        for (int i = min; i < max+1; i++) {
		    result += i;
	}
        return result;
    }

    public static int sumWhile(int min, int max) {
        int result = 0;
        int i = min;
        while (i < max+1) {
            result += i;
            i++;
        }

        return result;
    }
}
