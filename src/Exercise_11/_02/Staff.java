package Exercise_11._02;

public class Staff extends Employee{
    protected String title;

    Staff(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Staff's name: " + getName();
    }
}
