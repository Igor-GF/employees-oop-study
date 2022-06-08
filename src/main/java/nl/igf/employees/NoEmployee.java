package nl.igf.employees;

public class NoEmployee implements IEmployee {

    public int getSalary() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("No employee: %s", this.getSalary());
    }
}
