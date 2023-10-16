// PredicateTester.java

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public int getAge() { return age; }
    
    public String toString() {
        return(name + ", " + age);
    }
}

public class PredicateTester {

    public static void printPersonsWithPredicate(List<Person> roster, 
                                                 Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        
        people.add(new Person("Sam", 40));
        people.add(new Person("Steve", 16));
        people.add(new Person("Michelle", 35));
        people.add(new Person("Sarah", 15));
        
        System.out.println("Following people are of legal age:");
        printPersonsWithPredicate(people, p -> p.getAge() >= 18);
    }
}