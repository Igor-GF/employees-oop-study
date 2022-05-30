package nl.igf.employees;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String people = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000, yoe=10, iq=140}
            Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300, yoe=14, iq=100}
            Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=1630, yoe=3, iq=115}
            Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=5, yoe=10, iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300, dr=10}
            Rubble2, Barney2, 2/2/1905, Manager, {orgSize=200, dr=2}
            Rubble3, Barney3, 2/2/1905, Manager, {orgSize=500, dr=4}
            Rubble4, Barney4, 2/2/1905, Manager, {orgSize=175, dr=8}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=4}
            Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=5}
            Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=6}
            Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEO, {avgStickPrice=300}
            """;

        String regex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}), \\s*(?<role>\\w*)\\n";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(people);

        int totalSalaries = 0;

        while (mat.find()) {

            totalSalaries += switch (mat.group("role")) {
                case "Programmer" -> 3000;
                case "Manager" -> 3500;
                case "Analyst" -> 5000;
                default -> 0;
            };
        }
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

        System.out.printf("The total should be %s%n", currencyInstance.format(totalSalaries));
    }
}
