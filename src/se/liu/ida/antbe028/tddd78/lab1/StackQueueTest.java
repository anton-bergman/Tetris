package se.liu.ida.antbe028.tddd78.lab1;

import java.time.LocalDate;

public class StackQueueTest
{
    public static void main(String[] args) {
        Stack testStack = new Stack();

	Person anton = new Person("Anton", LocalDate.of(2000, 8, 18));
	Person johan = new Person("Johan", LocalDate.of(1971, 1, 1));
	Person mattias = new Person("Mattias", LocalDate.of(2004, 2, 29));
	Person anita = new Person("Anita", LocalDate.of(1900, 8, 18));
	Person alice = new Person("Alice", LocalDate.of(1999, 7, 30));

	testStack.push(anton);
	testStack.push(johan);
	testStack.push(mattias);
	testStack.push(anita);
	testStack.push(alice);

	while (!testStack.isEmpty()) {
	    System.out.println(testStack.pop());
	}

	Queue testQueue = new Queue();

	Person erik = new Person("Erik", LocalDate.of(2010, 3, 10));
	Person adam = new Person("Adam", LocalDate.of(2001, 7, 15));
	Person filippa = new Person("Filippa", LocalDate.of(2002, 7, 12));
	Person fredrik = new Person("Fredrik", LocalDate.of(1971, 5, 6));
	Person maria = new Person("Maria", LocalDate.of(1973, 8, 2));

	testQueue.enqueue(erik);
	testQueue.enqueue(adam);
	testQueue.enqueue(filippa);
	testQueue.enqueue(fredrik);
	testQueue.enqueue(maria);

	while (!testQueue.isEmpty()) {
	    System.out.println(testQueue.dequeue());
	}

    }
}
