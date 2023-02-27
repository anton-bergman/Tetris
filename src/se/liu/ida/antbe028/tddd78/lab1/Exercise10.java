package se.liu.ida.antbe028.tddd78.lab1;

public class Exercise10 {
    public static void main(String[] args) {
        int tal = 16777217;
        double decimaltal = tal;
        int tillbaka = (int)decimaltal;

        int big = 2147483647;
        long bigger = (long)big+1;


        System.out.println("tal: " + tal);
        System.out.println("decimaltal: " + decimaltal);
        System.out.println("tillbaka: " + tillbaka);

        System.out.println("big: " + big);
        System.out.println("bigger: " + bigger);
    }
}
