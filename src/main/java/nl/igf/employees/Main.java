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

        String performRegex = "\\w+\\=(?<locpd>\\w+), \\w+\\=(?<yoe>\\w+), \\w+\\=(?<iq>\\w+)";
        Pattern performPat = Pattern.compile(performRegex);

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
                    String details = peopleMat.group("details");
                    Matcher performMat = performPat.matcher(details);
                    int salary = 0;
                    if (performMat.find()) {
                        int locpd = Integer.parseInt(performMat.group("locpd"));
                        int yoe = Integer.parseInt(performMat.group("yoe"));
                        int iq = Integer.parseInt(performMat.group("iq"));
                        System.out.printf("Programmer loc: %s yoe: %s iq: %s%n", locpd, yoe, iq);
                        salary = 300 + locpd + yoe + iq;
                    } else {
                        salary = 3000;
                    }
                    yield salary;
                }
                case "Manager" -> {
                    String details = peopleMat.group("details");
                    Matcher mgrMat = mgrPat.matcher(details);
                    int salary = 0;
                    if (mgrMat.find()) {
                        int orgSize = Integer.parseInt(mgrMat.group("orgSize"));
                        int dr = Integer.parseInt(mgrMat.group("dr"));
                        System.out.printf("Manager orgSize: %s dr: %s%n", orgSize, dr);
                        salary = 3500 + orgSize + dr;
                    } else {
                        salary = 3500;
                    }
                    yield salary;
                }
                case "Analyst" -> {
                    String details = peopleMat.group("details");
                    Matcher analystMat = analystPat.matcher(details);
                    int salary = 0;
                    if (analystMat.find()) {
                        int projectCount = Integer.parseInt(analystMat.group("projectCount"));
                        System.out.printf("Analyst projectCount: %s%n", projectCount);
                        salary = 2500 + projectCount;
                    } else {
                        salary = 2500;
                    }
                    yield salary;
                }
                case "CEO" -> {
                    String details = peopleMat.group("details");
                    Matcher ceoMat = ceoPat.matcher(details);
                    int salary = 0;
                    if (ceoMat.find()) {
                        int avgStockPrice = Integer.parseInt(ceoMat.group("avgStockPrice"));
                        System.out.printf("CEO avgStockPrice: %s%n", avgStockPrice);
                        salary = 5000 + avgStockPrice;
                    } else {
                        salary = 5000;
                    }
                    yield salary;
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
