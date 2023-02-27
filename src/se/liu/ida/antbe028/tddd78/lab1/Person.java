package se.liu.ida.antbe028.tddd78.lab1;

import java.time.LocalDate;
import java.time.Period;

public class Person {

    private String name;
    private LocalDate birthDay;

    public Person(String str, LocalDate date) {
        name = str;
        birthDay = date;

    }

    public String toString() {
        return "Person: " + "name: " + name + ", age: " + getAge();
    }

    public int getAge() {
        // Calculates a persons age
        LocalDate now = LocalDate.now();
        Period periodBetween = Period.between(birthDay, now);
        int age = periodBetween.getYears();

        return age;
    }

    public static void main(String[] args) {
        Person anton = new Person("Anton", LocalDate.of(2000, 8, 18));
        Person johan = new Person("Johan", LocalDate.of(1971, 1, 1));
        Person mattias = new Person("Mattias", LocalDate.of(2004, 2, 29));
        Person anita = new Person("Anita", LocalDate.of(1900, 8, 18));
        //System.out.println(anton.getAge());
        System.out.println(anton);
        System.out.println(johan);
        System.out.println(mattias);
        System.out.println(anita);
    }

}
