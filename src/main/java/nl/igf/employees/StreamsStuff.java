package nl.igf.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsStuff {
    public static void main(String[] args) {

        String peopleText = "src/main/java/nl/igf/employees/employees.txt";

        try {
            Files.lines(Path.of(peopleText))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        peopleText
                .lines()
                .map(Employee::createEmployee)
                .forEach(System.out::println);
//                .forEach(s -> System.out.println(s)); // same with Lambda

        Collection<String> numbs = Set.of("one", "two", "three", "four");
        numbs
                .stream()
                .map(String::hashCode)
                .map(Integer::toHexString)
                .forEach(System.out::println);

        Stream.of("duck", "dog", "cat", "pig")
                .map(String::hashCode)
                .forEach(h -> System.out.printf("hexad.: %h%n", h));

        record Animal(String which, String color) {}

        Stream.of(new Animal("Dog", "Brown"), new Animal("Cat", "Black"), new Animal("Horse", "White"))
                .filter(a -> "Cat".equals(a.which))
                .forEach(System.out::println);

        String myVar = null;
        Stream.ofNullable(myVar)
                .forEach(System.out::println);

        IntStream.rangeClosed(1, 50)
                .mapToObj(String::valueOf)
                .map(s -> s.concat("-"))
                .forEach(System.out::print);

        String[] names = { "Igor", "Terry", "Jake"};
        Arrays.stream(names)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
