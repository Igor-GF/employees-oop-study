package nl.igf.employees;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class MainTest {

    @Test
    public void testNameToSalary() {
        Main main = new Main();
        main.main(new String[0]);
        int salary = main.getSalary("Wilma");
        assertEquals(2506, salary);
    }
}