package se.liu.ida.antbe028.tddd78.lab1;

public class Exercise6
{
    public static void main(String[] args) {
        // Prints every prime between 0 and 100.
        for (int i = 2; i < 101; i++) {
	    if (isPrime(i)) {
	        System.out.println(i);
	    }
	}
    }

    public static boolean isPrime(int number) {
        // Returns true if input is a prime.
	for (int i = 2; i < number; i++) {
	    int rest = number % i;
	    if (rest == 0) {
	        return false;
	    }
	}

	return true;
    }
}
