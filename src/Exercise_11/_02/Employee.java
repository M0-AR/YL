package Exercise_11._02;

public class Employee extends Person{
    protected String office;
    protected int salary;
    protected java.util.Calendar dateHired;

    Employee(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Employee's name: " + getName();
    }
}
