package nl.igf.employees;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
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
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
            """;

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w*),\\s*\\{(?<details>.*)\\}\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        String progRegex = "\\w+\\=(?<locpd>\\w+), \\w+\\=(?<yoe>\\w+), \\w+\\=(?<iq>\\w+)";
        Pattern progPat = Pattern.compile(progRegex);

        String mgrRegex = "\\w+\\=(?<orgSize>\\w+), \\w+\\=(?<dr>\\w+)";
        Pattern mgrPat = Pattern.compile(mgrRegex);

        String analystRegex = "\\w+\\=(?<projectCount>\\w+)";
        Pattern analystPat = Pattern.compile(analystRegex);

        String ceoRegex = "\\w+\\=(?<avgStockPrice>\\w+)";
        Pattern ceoPat = Pattern.compile(ceoRegex);

        int totalSalaries = 0;

        while (peopleMat.find()) {

            totalSalaries += switch (peopleMat.group("role")) {
                case "Programmer" -> {
                    Programmer programmer = new Programmer(peopleMat.group());
                    System.out.println(programmer);
                    yield programmer.getSalary();
                }
                case "Manager" -> {
                    Manager manager = new Manager(peopleMat.group());
                    System.out.println(manager);
                    yield manager.getSalary();
                }
                case "Analyst" -> {
                    Analyst analyst = new Analyst(peopleMat.group());
                    System.out.println(analyst);
                    yield analyst.getSalary();
                }
                case "CEO" -> {
                    Ceo CEO = new Ceo(peopleMat.group());
                    System.out.println(CEO);
                    yield CEO.getSalary();
                }
                default -> {
                    yield 0;
                }
            };
        }
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

        System.out.printf("The total should be %s%n", currencyInstance.format(totalSalaries));
    }
}
