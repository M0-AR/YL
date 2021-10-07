package Exercise_11._02;

import Exercise_10._14.MyDate;

public class Faculty extends Employee{
    private double officeHours;
    private int rank;

    public Faculty(String name, String address, String phoneNumber, String email, String office, double salary, MyDate dateHired, double officeHours, int rank) {
        super(name, address, phoneNumber, email, office, salary, dateHired);
        this.officeHours = officeHours;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Faculty's name: " + name;
    }
}
