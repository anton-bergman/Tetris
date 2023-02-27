package se.liu.ida.antbe028.tddd78.lab1;

public class Stack extends ListManipulator
{
    public void push(Person person) {
        elements.add(0, person);
    }

    public Person pop() {
        return elements.remove(0);
    }
}
