package se.liu.ida.antbe028.tddd78.lab1;

public class Queue extends ListManipulator
{

    public void enqueue(Person person) {
        elements.add(person);
    }

    public Person dequeue() {
        return elements.remove(0);
    }

}
