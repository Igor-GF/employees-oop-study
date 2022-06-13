package nl.igf.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee {
    protected final DateTimeFormatter dtFormater = DateTimeFormatter.ofPattern("M/d/yyyy");
    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w*),\\s*\\{(?<details>.*)\\}\\n";
    protected final Pattern peoplePat = Pattern.compile(peopleRegex);
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    protected Matcher peopleMat;
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;

    public Employee(String personText) {
        peopleMat = peoplePat.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormater.parse(peopleMat.group("dob")));
        }
    }

    public abstract int getSalary();

    public double getBonus() {
        return getSalary() * 1.1;
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s Bonus: %s", lastName, firstName, moneyFormat.format(this.getSalary()), moneyFormat.format(this.getBonus()));
    }
}
