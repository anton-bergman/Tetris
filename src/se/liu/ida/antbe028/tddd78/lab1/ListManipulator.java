package se.liu.ida.antbe028.tddd78.lab1;

import java.util.ArrayList;
import java.util.List;

public class ListManipulator
{
    protected List<Person> elements = new ArrayList<>();

    public int size() {
	return elements.size();
    }

    public void clear() {
	elements.clear();
    }

    public boolean isEmpty() {
	return elements.isEmpty();
    }

    public boolean contains(final Object o) {
	return elements.contains(o);
    }
}
