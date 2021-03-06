package nl.igf.employees;

import java.security.spec.NamedParameterSpec;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

public class Main {
    private static Set<IEmployee> employees;
    private static Map<String, Employee> employeeMap;
    private static Map<String, Integer> empSalaryMap;

    public static void main(String[] args) {
        String peopleText = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000, yoe=10, iq=140}
            Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300, yoe=14, iq=100}
            Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300, yoe=8, iq=105}
            Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630, yoe=3, iq=115}
            Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5, yoe=10, iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300, dr=10}
            Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100, dr=4}
            Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200, dr=2}
            Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500, dr=8}
            Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175, dr=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
            Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
            Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEOZ, {avgStockPrice=300}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
            """;

        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);

        int totalSalaries = 0;
        IEmployee employee = null;
        Set<IEmployee> employees = new TreeSet<>((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        employeeMap = new LinkedHashMap<>();
        empSalaryMap = new TreeMap<>();

        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            Employee emp = (Employee) employee;
            employees.add(employee);
            employeeMap.put(emp.firstName, emp);
            empSalaryMap.put(emp.firstName, emp.getSalary());
        }

        // ----- Miscellaneous practice for collections
//        employees.remove(0);
//        employees.add(0, Employee.createEmployee("Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}"));
//        employees.set(2, Employee.createEmployee("Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}"));
//
//        List<IEmployee> newSubList = employees.subList(5, 7);
//        System.out.println(newSubList);
//
//        List<String> undesirables  = List.of("Wilma5", "Barney4", "Fred2");
//
//        List<String> newStrings = new ArrayList<>();
//        newStrings.addAll(undesirables);
//        System.out.println("New list created by adding undesirables list: " + newStrings);
//
//        IEmployee newEmp = Employee.createEmployee("Doe, John, 1/1/1970, Analyst, {projectCount=9}");
//        IEmployee newEmpTwo = Employee.createEmployee("Doe, John, 1/1/1970, Analyst, {projectCount=9}");
//        System.out.println(newEmp.equals(newEmpTwo));

        // ---- to make changes like removing while iterating through the collection
//        removeUndesirables(employees, undesirables);

        for (IEmployee worker : employees) {
            System.out.println(worker.toString());
            totalSalaries += worker.getSalary();
        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

        System.out.printf("The total should be %s%n", currencyInstance.format(totalSalaries));
        System.out.println(employees.size());

        System.out.println(employeeMap);
        System.out.println(employeeMap.values());
        System.out.println(employeeMap.keySet());
        System.out.println(empSalaryMap.entrySet());

        for (Map.Entry<String, Integer> entry : empSalaryMap.entrySet()) {
            System.out.printf("Key = %s, Value = %s%n", entry.getKey(), entry.getValue());
        }
    }

    private static void removeUndesirables(List<IEmployee> employees, List<String> removalNames) {
        for (Iterator<IEmployee> it = employees.iterator(); it.hasNext();) {
            IEmployee worker = it.next();
            if (worker instanceof Employee) {
                Employee tmpWorker = (Employee) worker;
                if (removalNames.contains(tmpWorker.firstName)) {
                    it.remove();
                }
            }
        }
    }

    public int getSalary(String firstName) {
        return employeeMap.get(firstName).getSalary();
    }

    public String getLastName(String firstName) {
        return employeeMap.get(firstName).lastName;
    }
}
