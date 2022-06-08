package nl.igf.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst  {

    private int projectCount;
    private String lastName;
    private String firstName;
    private LocalDate dob;

    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w*),\\s*\\{(?<details>.*)\\}\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);
    private final String analystRegex = "\\w+\\=(?<projectCount>\\w+)";
    private final Pattern analystPat = Pattern.compile(analystRegex);
    private final DateTimeFormatter dtFormater = DateTimeFormatter.ofPattern("M/d/yyyy");
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    public Analyst(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormater.parse(peopleMat.group("dob")));
            Matcher analystMat = analystPat.matcher(peopleMat.group("details"));
            if (analystMat.find()) {
                this.projectCount = Integer.parseInt(analystMat.group("projectCount"));
            }
        }
    }

    public int getSalary() {
        return 2500 + projectCount * 2;
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s", lastName, firstName, moneyFormat.format(this.getSalary()));
    }
}
