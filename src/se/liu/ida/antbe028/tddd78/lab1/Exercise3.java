package se.liu.ida.antbe028.tddd78.lab1;

import javax.swing.JOptionPane;

public class Exercise3
{
    // Deklarerar en konstant i Java
    private final static int TABELL = 5;

    public static void multTable(int num, int tabell) {
        // Skriver ut en multiplikationstabell
        for (int i = 1; i < num+1; i++) {
            int product = tabell*i;
            System.out.println(i + " * " + tabell + " = " + product);
        }
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter a value: ");
        int tabell = Integer.parseInt(input);
        multTable(10, tabell);
    }
}
